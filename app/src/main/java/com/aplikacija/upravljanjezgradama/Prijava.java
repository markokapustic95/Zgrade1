package com.aplikacija.upravljanjezgradama;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Prijava extends AppCompatActivity {

    EditText EMail,Zaporka;
    Button PrijaviSe;
    TextView RegistrirajSeLink;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prijava);


        EMail=findViewById(R.id.eMail);
        Zaporka=findViewById(R.id.zaporka);
        progressBar=findViewById(R.id.progressBar);
        fAuth=FirebaseAuth.getInstance();
        PrijaviSe=findViewById(R.id.prijaviSe);
        RegistrirajSeLink=findViewById(R.id.registrirajSeLink);

        PrijaviSe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emial=EMail.getText().toString().trim();
                String zaporka =Zaporka.getText().toString().trim();

                if (TextUtils.isEmpty(emial)){
                    EMail.setError("E-mail je potrebno upisati!");
                    return;
                }

                if (TextUtils.isEmpty(zaporka)){
                    Zaporka.setError("Potrebno je upisati zaporku!");
                    return;
                }

                if (zaporka.length()<6){
                    Zaporka.setError("Zaporka mora imati 6 ili više karaktera!");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //Autetificiranje korisnika

                fAuth.signInWithEmailAndPassword(emial,zaporka).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                                Toast.makeText(Prijava.this, "Uspiješna prijava", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),GlavniIzbornik.class));
                        }else{
                            Toast.makeText(Prijava.this, "Greška!"  +task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });

        RegistrirajSeLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Registracija.class));
            }
        });

    }

}

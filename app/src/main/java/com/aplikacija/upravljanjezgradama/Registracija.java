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

public class Registracija extends AppCompatActivity {

    EditText Ime,EMail,Zaporka,BrojTelefona;
    Button RegistrirajSe;
    TextView PrijaviSeLink;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registracija);


        Ime= findViewById(R.id.punoIme);
        EMail=findViewById(R.id.eMail);
        Zaporka=findViewById(R.id.zaporka);
        BrojTelefona=findViewById(R.id.brojTelefona);
        RegistrirajSe=findViewById(R.id.registrirajSe);
        PrijaviSeLink=findViewById(R.id.prijaviSeLink);

        fAuth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressBar);

        if (fAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),GlavniIzbornik.class));
            finish();
        }

        RegistrirajSe.setOnClickListener(new View.OnClickListener() {
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

                //registracija na Firebase

                fAuth.createUserWithEmailAndPassword(emial,zaporka).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Registracija.this, "Korisnik stvoren", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),GlavniIzbornik.class));
                        }else{
                            Toast.makeText(Registracija.this, "Greška!"  +task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                PrijaviSeLink.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(),Registracija.class));
                    }
                });

            }
        });

    }
}

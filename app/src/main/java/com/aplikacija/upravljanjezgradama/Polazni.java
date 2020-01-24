package com.aplikacija.upravljanjezgradama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Polazni extends AppCompatActivity {

    Button Nastavi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.polazni);

        Nastavi=findViewById(R.id.nastavi);



        Nastavi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Prijava.class));
            }
        });

    }


}

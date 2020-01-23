package com.aplikacija.zgrade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private Button Login;
    private TextView Info;
    private int counter=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name =(EditText)findViewById(R.id.etName);
        Password=(EditText)findViewById(R.id.etPassword);
        Info=(TextView)findViewById((R.id.tvInfo));
        Login=(Button)findViewById((R.id.btnLogin));

        Info.setText("No of attempts remaining: 5");

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Name.getText().toString(), Password.getText().toString());
            }
        });

        findViewById(R.id.etName).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(((EditText) findViewById(R.id.etName)).getText().toString().equals("") && !hasFocus)
                    Toast.makeText(v.getContext(), "Niste upisali ime", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showNameMsg() {}
    private void validate (String userName, String userPassword){
        if((userName=="Admin")&& (userPassword=="1234")){
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }else {
            counter--;

            Info.setText("No of attempts remaining: "+String.valueOf(counter));

            if (counter==0){
                Login.setEnabled(false);
            }
        }
    }
}

package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tictactoe.database.DbHandler;

public class MainActivity extends AppCompatActivity {

    EditText login_email, login_password;
    TextView button_login, button_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_email=findViewById(R.id.login_email);
        login_password=findViewById(R.id.login_password);
        button_login=findViewById(R.id.button_login);
        button_signup=findViewById(R.id.button_signup);

        DbHandler db=new DbHandler(this);

        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Registeration.class);
                startActivity(i);
            }
        });

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = login_email.getText().toString();
                String password = login_password.getText().toString();

                if(email.equals("") || password.equals("")){
                    Toast.makeText(getApplicationContext(),"Input should not be empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    boolean checkPassAndEmail=db.isPassAndEmailCorrect(email,password);
                    if(checkPassAndEmail==true){
                        Intent i = new Intent(getApplicationContext(),LoadingScreen.class);
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Email or Password may incorrect", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}
package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tictactoe.database.DbHandler;

public class Registeration extends AppCompatActivity {

    EditText reg_username, reg_email, reg_password, reg_confirmpassword;
    TextView button_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        reg_username=findViewById(R.id.reg_username);
        reg_email=findViewById(R.id.reg_email);
        reg_password=findViewById(R.id.reg_password);
        reg_confirmpassword=findViewById(R.id.reg_confirmpassword);
        button_register=findViewById(R.id.button_register);

        DbHandler db=new DbHandler(this);
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = reg_username.getText().toString();
                String email = reg_email.getText().toString();
                String password = reg_password.getText().toString();
                String confirmPassword = reg_confirmpassword.getText().toString();

                if(username.equals("") || email.equals("") || password.equals("") || confirmPassword.equals("")){
                    Toast.makeText(getApplicationContext(),"Input should not be empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(password.equals(confirmPassword)){
                        boolean isExist = db.isUserAlreadyExists(email);
                        if(isExist==false){
                            boolean insert = db.registerUser(username,email,password);
                            if(insert==true){
                                Toast.makeText(getApplicationContext(),"Successfully Registered", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(i);
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"User Already Exist", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Password and Confirm Password are not same", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
package com.example.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class SignUp extends AppCompatActivity {

    TextInputEditText textInputEditTextFullname, textInputEditTextUsername, textInputEditTextPassword , textInputEditTextMail, textInputEditTextAdress, textInputEditTextPostalCode, textInputEditTextPhone;
    Button buttonSignUp;
    TextView textViewLogin;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        textInputEditTextUsername = findViewById(R.id.username);
        textInputEditTextFullname = findViewById(R.id.fullname);
        textInputEditTextPassword = findViewById(R.id.password);
        textInputEditTextMail = findViewById(R.id.mail);
        textInputEditTextAdress = findViewById(R.id.adress);
        textInputEditTextPostalCode = findViewById(R.id.postalCode);
        textInputEditTextPhone = findViewById(R.id.phone);
        buttonSignUp = findViewById(R.id.buttonSignUp);
        textViewLogin = findViewById(R.id.loginText);
        progressBar = findViewById(R.id.progress);

        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fullname, username, password, mail, phone, adress, postalCode;
                fullname = String.valueOf(textInputEditTextFullname.getText());
                username = String.valueOf(textInputEditTextUsername.getText());
                password = String.valueOf(textInputEditTextPassword.getText());
                mail = String.valueOf(textInputEditTextMail.getText());
                phone = String.valueOf(textInputEditTextPhone.getText());
                adress = String.valueOf(textInputEditTextAdress.getText());
                postalCode = String.valueOf(textInputEditTextPostalCode.getText());

                Log.d("Mail from form",mail);

                if(!fullname.equals("") && !username.equals("") && !password.equals("") && !mail.equals("") && !adress.equals("") && !phone.equals("") && !postalCode.equals("")) {
                    progressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[7];
                            field[0] = "fullname";
                            field[1] = "phone";
                            field[2] = "mail";
                            field[3] = "adress";
                            field[4] = "postalCode";
                            field[5] = "password";
                            field[6] = "username";
                            //Creating array for data
                            String[] data = new String[7];
                            data[0] = fullname;
                            data[1] = phone;
                            data[2] = mail;
                            data[3] = adress;
                            data[4] = postalCode;
                            data[5] = password;
                            data[6] = username;
                            PutData putData = new PutData("http://192.168.0.59/LoginRegister/signup.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    if (result.equals("Sign up Success")) {
                                        Toast.makeText(getApplicationContext(),result, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), Login.class);
                                        startActivity(intent);
                                        finish();
                                    }

                                    else{
                                        Toast.makeText(getApplicationContext(),result, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    });
                }

                else
                {
                    Toast.makeText(getApplicationContext(), "All fields required", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
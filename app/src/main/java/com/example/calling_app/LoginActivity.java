package com.example.calling_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText emailBox,passwordBox;
    Button LoginBtn,SignUpbtn;
    FirebaseAuth auth;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait");

        auth = FirebaseAuth.getInstance();

        emailBox = findViewById(R.id.email);

        passwordBox =findViewById(R.id.password);

        LoginBtn = findViewById(R.id.loginbtn);

        SignUpbtn = findViewById(R.id.createbtn);

        LoginBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                dialog.show();
                String email,pass;
                email = emailBox.getText().toString();
                pass = passwordBox.getText().toString();

                auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        dialog.dismiss();
                        if(task.isSuccessful()){


                            Toast.makeText(LoginActivity.this,"Logged On",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(LoginActivity.this,DashBoardActivity.class));
                        }else {
                            Toast.makeText(LoginActivity.this,task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });

        SignUpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
            }
        });


    }
}

package com.example.calling_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUpActivity extends AppCompatActivity {

    EditText emailBox,passwordBox,textbox;
    Button LoginBtn,SignUpbtn;
    FirebaseAuth auth;
    FirebaseFirestore database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        database = FirebaseFirestore.getInstance();
          auth = FirebaseAuth.getInstance();

        emailBox = findViewById(R.id.email);

        passwordBox =findViewById(R.id.password);

        LoginBtn = findViewById(R.id.loginbtn);

        SignUpbtn = findViewById(R.id.createbtn);

        textbox = findViewById(R.id.textbox);

        SignUpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name ,email , pass;
                name = textbox.getText().toString();
                email = emailBox.getText().toString();
                pass = passwordBox.getText().toString();

                User user = new User();

                user.setEmail(email);
                user.setPass(pass);
                user.setName(name);

                auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                   if(task.isSuccessful()){
                       database.collection("Users")
                               .document().set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                           @Override
                           public void onSuccess(Void aVoid) {
                                 startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
                           }
                       });
   //                   Toast.makeText(SignUpActivity.this,"Account is created",Toast.LENGTH_SHORT).show();
                   }
                   else{
                       Toast.makeText(SignUpActivity.this,task.getException().getLocalizedMessage(),Toast.LENGTH_LONG).show();
                   }
                    }
                });
            }
        });


    }
}

package com.example.gathering;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.lang.UCharacterEnums;
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

public class SignIn extends AppCompatActivity {
    EditText nameBox,emailBox,passwordBox;
    Button create,account;

    FirebaseAuth auth;
    FirebaseFirestore database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        nameBox=findViewById(R.id.nameBox);
        emailBox=findViewById(R.id.edid_emailBox);
        passwordBox=findViewById(R.id.edid_passwordBox);

        create=findViewById(R.id.createBtn);
        account=findViewById(R.id.accountBtn);

        auth=FirebaseAuth.getInstance();
        database=FirebaseFirestore.getInstance();

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=nameBox.getText().toString();
                nameBox.setText("");
                String email=emailBox.getText().toString();
                emailBox.setText("");
                String password=passwordBox.getText().toString();
                passwordBox.setText("");

                User user=new User();
                user.setEmail(email);
                user.setPassword(password);
                user.setName(name);
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            database.collection("Users")
                                    .document().set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                   startActivity(new Intent(SignIn.this,Login.class));
                                }
                            });
                            //Toast.makeText(SignIn.this, "Account successfully created", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(SignIn.this, ""+task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }
}
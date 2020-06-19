package com.delick.firebaseauthent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private Button buttonRegister;
private EditText editTextEmail;
private EditText editTextPassword;
private TextView textViewSignUp;
private ProgressDialog progressDialog;
private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textViewSignUp = (TextView) findViewById(R.id.textVewSignUp);
        buttonRegister.setOnClickListener(this);
        textViewSignUp.setOnClickListener(this);
    }
private void registerUser(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(this, "Please Enter your Email", Toast.LENGTH_SHORT).show();
            //stopping the function from execution.
            return;
        }
    if(TextUtils.isEmpty(password)){
        //password is empty.
        Toast.makeText(this, "Please Enter your password", Toast.LENGTH_SHORT).show();
        //stopping the function from execution any further.
        return;
    }
    //if validations are ok we gonna first showing the progress bar.

progressDialog.setMessage("Registering the User...");
    progressDialog.show();
    firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if(task.isSuccessful()){
//user is successfully logdIn.
                //we will start the profile activity here
                //lets display a toast only this time round!
                Toast.makeText(MainActivity.this, "Cheers! Asiimwe Says You are registered!.", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(MainActivity.this, "Check your Network Connection & Try Again to access Asiimwe.",Toast.LENGTH_SHORT).show();
            }
        }
    });
    }
    @Override
    public void onClick(View view) {
        if(view == buttonRegister){
registerUser();
        }
        if(view == textViewSignUp){
            //will open login activity here.

        }

    }
}

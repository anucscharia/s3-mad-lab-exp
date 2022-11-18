package com.example.a12_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText email;
    private TextInputEditText password;
    private TextInputEditText phone;
    private TextInputLayout emailcontainer;
    private TextInputLayout passwordcontainer;
    private TextInputLayout phonecontainer;
    private MaterialButton login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=(TextInputEditText) findViewById(R.id.login_email);
        password=(TextInputEditText) findViewById(R.id.login_password);
        phone=(TextInputEditText) findViewById(R.id.login_phone);
        emailcontainer=(TextInputLayout) findViewById(R.id.emailcontainer);
        passwordcontainer=(TextInputLayout) findViewById(R.id.passwordcontainer);
        phonecontainer=(TextInputLayout) findViewById(R.id.phonecontainer);
        login=(MaterialButton) findViewById(R.id.login_button);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            password.setTooltipText("Minimum 8 charcters,at-least one Upper case,one lower case,one digit,one special character - @#$%^&+=");
        }
        login.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Login Button Clicked",Toast.LENGTH_SHORT);
                login();
            }
        });
        emailInputTextOnFocusListener();
        passwordInputTextOnFocusListener();
        phoneInputTextOnFocusListener();
    }
    private String validPassword(){
        String pass=password.getText().toString();
        if(pass.length()<8)
            return "Minimum 8 Character password";
        if (!pass.matches(".*[A-Z].*"))
            return "Must Contain 1 Upper-case Character";
        if (!pass.matches(".*[0-9].*"))
            return "Must Contain 1 digit";
        if (!pass.matches(".*[a-z].*"))
            return "Must Contain 1 Lower-case Character";
        if (!pass.matches(".*[@#$%^&+=].*"))
            return "Must Contain 1 Special Character (@#$%^&+=)";
        return null;
    }
    private String validEmail(){
        String emailinput=email.getText().toString();
        if(!Patterns.EMAIL_ADDRESS.matcher(emailinput).matches())
            return "Invalid Email Address";
        return null;
    }
    private String validPhone() {
        String phoneText = phone.getText().toString();
        if (!phoneText.matches(".*[0-9].*"))
            return "Must be all Digits";
        if (phoneText.length() != 10)
            return "Must be 10 Digits";
        return null;
    }
    private void login(){
        emailcontainer.setHelperText(validEmail());
        passwordcontainer.setHelperText(validPassword());
        phonecontainer.setHelperText(validPhone());
        boolean checkIfEmailIsValid=emailcontainer.getHelperText()==null;
        boolean validPassword=passwordcontainer.getHelperText()==null;
        boolean checkValidPhoneNumber=phonecontainer.getHelperText()==null;
        if (checkIfEmailIsValid && validPassword && checkValidPhoneNumber) {
            Intent intent = new Intent(MainActivity.this, Dashboard.class);
            startActivity(intent);
            finish();
        }else
            Toast.makeText(this, "Invalid Form", Toast.LENGTH_SHORT).show();
    }
    private void resetForm() {
        email.setText("");
        password.setText("");
        phone.setText("");
        passwordcontainer.setHelperText("Required");
        emailcontainer.setHelperText("Required");
        phonecontainer.setHelperText("Required");
    }
    private void emailInputTextOnFocusListener(){
        email.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v,boolean hasFocus){
                if(!hasFocus)
                    emailcontainer.setHelperText(validEmail());
            }
        });
    }
    private void passwordInputTextOnFocusListener(){
        password.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v,boolean hasFocus){
                if(!hasFocus)
                    passwordcontainer.setHelperText(validPassword());
            }
        });
    }
    private void phoneInputTextOnFocusListener(){
        phone.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v,boolean hasFocus){
                if(!hasFocus)
                    phonecontainer.setHelperText(validPhone());
            }
        });
    }
}
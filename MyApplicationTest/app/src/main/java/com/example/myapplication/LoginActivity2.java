package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.classes.Error;
import com.example.myapplication.classes.GlobalVariables;
import com.example.myapplication.classes.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Map;

public class LoginActivity2 extends AppCompatActivity {
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

         /*
            Button submit
         */
        //le boutton doit etre constant et non modifiable.
        Button btn_submit = (Button) findViewById(R.id.loginSubmit);
        TextInputLayout mailLayout = findViewById(R.id.loginMailLayout);
        TextInputLayout passwordLayout = findViewById(R.id.loginPasswordLayout);
        TextInputEditText mail = findViewById(R.id.loginMail);
        TextInputEditText password = findViewById(R.id.loginPassword);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = new User();
                user.clear();
                mailLayout.setError(null);
                password.setError(null);

                if (!user.inputControlLogin(mail.getText().toString(), password.getText().toString())){
                    //Display Errors
                    for(Map.Entry<Error, String> entry : user.getErrors().entrySet()){
                        if (entry.getKey() == Error.MAIL)
                            mailLayout.setError(entry.getValue());
                        else if(entry.getKey() == Error.PASSWORD)
                            passwordLayout.setError(entry.getValue());
                    }

                }else{//control db
                    mailLayout.setError(null);
                    passwordLayout.setError(null);
                    if (!user.dbControlLogin(LoginActivity2.this)){
                        for(Map.Entry<Error, String> entry : user.getDbErrors().entrySet()){
                            if (entry.getKey() == Error.MAIL)
                                mailLayout.setError(entry.getValue());
                            else if(entry.getKey() == Error.PASSWORD)
                                passwordLayout.setError(entry.getValue());
                        }

                    }else{
                        if (user.readByMailPwd(LoginActivity2.this)) {
                            Toast.makeText(LoginActivity2.this, "Enregistrement succes", Toast.LENGTH_LONG).show();
                            GlobalVariables.setUserIdCourant(user.getId());
                            Intent myIntent = new Intent(LoginActivity2.this, HomeActivity.class);
                            startActivity(myIntent);


                        }else {
                            Toast.makeText(LoginActivity2.this, "Enregistrement echec", Toast.LENGTH_LONG).show();
                        }

                    }
                }
            }
        });

    }

     protected void onSaveInstanceState(Bundle outState){
            super.onSaveInstanceState(outState);
     }
    protected void onRestoreInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
    }
}
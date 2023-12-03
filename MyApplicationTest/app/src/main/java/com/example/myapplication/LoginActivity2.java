package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.classes.Error;
import com.example.myapplication.classes.User;

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
        EditText mail = (EditText) findViewById(R.id.loginMail);
        EditText password = (EditText) findViewById(R.id.loginPassword);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = new User();
                mail.setError(null);
                password.setError(null);

                if (!user.inputControlLogin(mail.getText().toString(), password.getText().toString())){
                    //Display Errors
                    for(Map.Entry<Error, String> entry : user.getErrors().entrySet()){
                        if (entry.getKey() == Error.MAIL)
                            mail.setError(entry.getValue());
                        else if(entry.getKey() == Error.PASSWORD)
                            password.setError(entry.getValue());
                    }

                }else{
                    /* mettre les informations dans le buffer(comme une dict") */
                    Bundle params = new Bundle();
                    params.putString("mail", mail.getText().toString());
                    params.putString("password", password.getText().toString());

                    /* Mettre le buffer dans une variable global myIntent et le passer en parametre a une autre fenetre */
                    Intent myIntent = new Intent(LoginActivity2.this, HomeActivity.class);
                    myIntent.putExtras(params);
                    startActivity(myIntent);
                }
            }
        });

    }
}
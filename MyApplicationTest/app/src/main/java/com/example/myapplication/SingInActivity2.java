package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.classes.Error;
import com.example.myapplication.classes.GlobalVariables;
import com.example.myapplication.classes.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Map;

public class SingInActivity2 extends Activity {
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singin2);

         /*
            Button submit
         */
        //le boutton doit etre constant et non modifiable.
        Button btn_submit = (Button) findViewById(R.id.singInSubmit);
        TextInputLayout mailLayout = findViewById(R.id.singInMailLayout);
        TextInputLayout passwordLayout = findViewById(R.id.singInPasswordLayout);
        TextInputLayout passwordCLayout = findViewById(R.id.singInPasswordCLayout);
        TextInputEditText mail = findViewById(R.id.singInMail);
        TextInputEditText password = findViewById(R.id.singInPassword);
        TextInputEditText passwordC = findViewById(R.id.singInPasswordC);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = new User();
                user.clear();
                mailLayout.setError(null);
                password.setError(null);
                passwordC.setError(null);

                if (!user.inputControlSingInStep1(mail.getText().toString(), password.getText().toString(), passwordC.getText().toString())){
                    //Display Errors
                    for(Map.Entry<Error, String> entry : user.getErrors().entrySet()){
                        if (entry.getKey() == Error.MAIL)
                            mailLayout.setError(entry.getValue());
                        else if(entry.getKey() == Error.PASSWORD)
                            passwordLayout.setError(entry.getValue());
                        else if(entry.getKey() == Error.PASSWORD_COMFIRMATION)
                            passwordCLayout.setError(entry.getValue());
                    }
                }else{
                    mailLayout.setError(null);
                    if (!user.dbControlSingInStep1(SingInActivity2.this)){
                        for(Map.Entry<Error, String> entry : user.getDbErrors().entrySet()){
                            if (entry.getKey() == Error.MAIL)
                                mailLayout.setError(entry.getValue());
                        }
                    }else{
                        /* mettre les informations dans le buffer(comme une dict") */
                        Bundle params = new Bundle();
                        params.putString("mail", mail.getText().toString());
                        params.putString("password", password.getText().toString());

                        /* Mettre le buffer dans une variable global myIntent et le passer en parametre a une autre fenetre */

                        Intent myIntent = new Intent(SingInActivity2.this, SingInActivity3.class);

                        //myIntent.putExtras(params);
                        myIntent.putExtras(params);

                        startActivity(myIntent);
                    }
                }
            }
        });
    }
}

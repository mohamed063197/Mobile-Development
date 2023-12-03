package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.classes.Error;
import com.example.myapplication.classes.User;

import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login1);
        setContentView(R.layout.activity_login2);

        /* back button */
        Button btn_back = (Button) findViewById(R.id.back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity.this.finish();
            }
        });

        /*
            Button submit
         */
        //le boutton doit etre constant et non modifiable.
        Button btn_submit = (Button) findViewById(R.id.submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("je suis la1");
                EditText mail = (EditText) findViewById(R.id.mail);
                EditText password = (EditText) findViewById(R.id.password);
                System.out.println("je suis la2");
                User user = new User();

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
                    Intent myIntent = new Intent(LoginActivity.this, HomeActivity.class);
                    myIntent.putExtras(params);
                    startActivity(myIntent);
                }



            }
        });
    }


}

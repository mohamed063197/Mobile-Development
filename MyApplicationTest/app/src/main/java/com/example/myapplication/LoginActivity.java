package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        /*
            Button submit
         */
        //le boutton doit etre constant et non modifiable.
        final Button btn_submit = (Button) findViewById(R.id.submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText mail = (EditText) findViewById(R.id.mail);
                EditText password = (EditText) findViewById(R.id.password);


                User user = new User();
                if (!user.inputControlLogin(mail.getText().toString(), password.getText().toString())){

                    //Init champ error
                    TextView error_mail = (TextView) findViewById(R.id.error_mail);
                    TextView error_pwd = (TextView) findViewById(R.id.error_pwd);

                    error_mail = champErrorGone(error_mail);
                    error_pwd  = champErrorGone(error_pwd);

                    //Display Errors
                    for(Map.Entry<String, String> entry : user.getErrors().entrySet()){
                        switch (entry.getKey()){
                            case "mail":
                                error_mail = champErrorVisible(error_mail, entry.getValue());
                                break;
                            case "pwd":
                                error_pwd = champErrorVisible(error_pwd, entry.getValue());
                                break;
                        }
                    }
                    return;
                }

                /* mettre les informations dans le buffer(comme une dict") */
                Bundle params = new Bundle();
                params.putString("mail", mail.getText().toString());
                params.putString("password", password.getText().toString());

                /* Mettre le buffer dans une variable global myIntent et le passer en parametre a une autre fenetre */
                Intent myIntent = new Intent(LoginActivity.this, Home.class);
                myIntent.putExtras(params);
                startActivity(myIntent);

            }
        });
    }

    public TextView champErrorGone(TextView textViewError){
        textViewError.setVisibility(View.GONE);
        textViewError.setText("");
        return textViewError;
    }

    public TextView champErrorVisible(TextView textViewError, String value){
        textViewError.setVisibility(View.VISIBLE);
        textViewError.setText(value);
        return textViewError;
    }
}

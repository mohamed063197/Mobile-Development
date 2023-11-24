package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, "OnCreate - Main", Toast.LENGTH_LONG).show();
        setContentView(R.layout.activity_main);



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
                HashMap errors = connexionIsValid(mail.getText().toString(), password.getText().toString());
                if(!errors.isEmpty()){
                    //display errors.
                    return;
                }
                /* mettre les informations dans le buffer(comme une dict") */
                Bundle params = new Bundle();
                params.putString("mail", mail.getText().toString());
                params.putString("password", password.getText().toString());

                /* Mettre le buffer dans une variable global myIntent et le passer en parametre a une autre fenetre */
                Intent myIntent = new Intent(MainActivity.this, Home.class);
                myIntent.putExtras(params);
                startActivity(myIntent);

            }
        });



    }

    public HashMap connexionIsValid(String mail, String pwd){
        HashMap errors = new HashMap<String,String>();

        // mail
        if (mail.isEmpty()){
            errors.put("mail","Mail est vide");
        }else if (mail.length()<3){//regex
            errors.put("mail","Mail est incorrect");
        }

        // pwd
        if (pwd.isEmpty()){
            errors.put("pwd","Password est vide");
        }else if (pwd.length()<3){//regex
            errors.put("pwd","Password est incorrect");
        }
        return errors;
    }

    @Override
    public void onStart(){//call before pause
        super.onStart();
        Toast.makeText(this, "Start - Main", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResume(){//call before pause
        super.onResume();
        Toast.makeText(this, "Resume - Main", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onPause(){//call before pause
        super.onPause();
        Toast.makeText(this, "Pause - Main", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onStop(){//call before pause
        super.onStop();
        Toast.makeText(this, "Stop - Main", Toast.LENGTH_LONG).show();
    }

    public void onDestroy(){
        super.onDestroy();
        Toast.makeText(this, "Destroy - Main", Toast.LENGTH_LONG).show();
    }
}
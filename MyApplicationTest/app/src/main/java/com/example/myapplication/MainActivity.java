package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "Create", Toast.LENGTH_LONG).show();

        /*
            Button submit
         */

        final Button btn_submit = (Button) findViewById(R.id.submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText mail = (EditText) findViewById(R.id.mail);
                EditText password = (EditText) findViewById(R.id.password);

                /* mettre les informations dans le buffer */
                Bundle post = new Bundle();
                post.putString("mail", mail.getText().toString());
                post.putString("password", password.getText().toString());

                /* mettre le buffer dans une variable global myIntent */
                Intent myIntent = new Intent(MainActivity.this, Home.class);
                myIntent.putExtras(post);
                startActivity(myIntent);


            }
        });



    }

    @Override
    public void onStart(){//call before pause
        super.onStart();
        Toast.makeText(this, "Start", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onResume(){//call before pause
        super.onResume();
        Toast.makeText(this, "Resume", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onPause(){//call before pause
        super.onPause();
        Toast.makeText(this, "Pause", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onStop(){//call before pause
        super.onStop();
        Toast.makeText(this, "Stop", Toast.LENGTH_LONG).show();
    }

    public void onDestroy(){
        super.onDestroy();
        Toast.makeText(this, "Destroy", Toast.LENGTH_LONG).show();
    }
}
package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;
import java.util.Iterator;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, "OnCreate - Main", Toast.LENGTH_LONG).show();
        setContentView(R.layout.activity_main);

        /* Submit Login */
        Button login_submit = (Button) findViewById(R.id.login);
        login_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(myIntent);

            }
        });


        /* Submit SingIn */
        Button singin_submit = (Button) findViewById(R.id.singIn);
        singin_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, SinginActivity.class);
                startActivity(myIntent);
            }
        });

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
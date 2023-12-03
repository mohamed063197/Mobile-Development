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

        /* back button */
        Button btn_back = (Button) findViewById(R.id.back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity.this.finish();
            }
        });

        /* mail button */
        Button btn_mail = (Button) findViewById(R.id.btn_mail);
        btn_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /* Mettre le buffer dans une variable global myIntent et le passer en parametre a une autre fenetre */
                Intent myIntent = new Intent(LoginActivity.this, LoginActivity2.class);
                startActivity(myIntent);
            }
        });

    }
}

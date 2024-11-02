package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;

import com.example.myapplication.classes.Error;
import com.example.myapplication.classes.User;

import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login1);

        LinearLayout body=  findViewById(R.id.body);
        ProgressBar progressBar = findViewById(R.id.progressBar);

        /* Init */
        body.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        /* Finish */
        body.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);

        /* back button */
        Button btn_back = findViewById(R.id.back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity.this.finish();
            }
        });

        /* mail button */
        Button btn_mail = findViewById(R.id.btn_mail);
        btn_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /* Mettre le buffer dans une variable global myIntent et le passer en parametre a une autre fenetre */
                Intent myIntent = new Intent(getBaseContext(), LoginActivity2.class);
                startActivity(myIntent);
            }
        });

    }
}

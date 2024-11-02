package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.myapplication.classes.Error;
import com.example.myapplication.classes.User;
import com.example.myapplication.classes.Utils;
import com.google.android.material.slider.LabelFormatter;
import com.google.android.material.slider.Slider;

import java.util.Map;

public class SingInActivity extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_singin1);

        /* back button */
        Button btn_back = findViewById(R.id.back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SingInActivity.this.finish();
            }
        });

        /* mail button */
        Button btn_mail = findViewById(R.id.btn_mail);
        btn_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /* Mettre le buffer dans une variable global myIntent et le passer en parametre a une autre fenetre */
                Intent myIntent = new Intent(SingInActivity.this, SingInActivity2.class);
                startActivity(myIntent);
            }
        });

    }

 }

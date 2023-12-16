package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.myapplication.uiclasses.SliderAdapter;


public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    Button admin;
    SliderAdapter sliderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, "OnCreate - Main", Toast.LENGTH_LONG).show();
        setContentView(R.layout.activity_main);

        //Hooks
        viewPager = findViewById(R.id.slider);

        admin =  findViewById(R.id.admin);
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(myIntent);
            }
        });
        //Call Adapter
        sliderAdapter = new SliderAdapter(MainActivity.this);
        viewPager.setAdapter(sliderAdapter);


        // Le reste du code pour l'initialisation de l'activité principale
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
                Intent myIntent = new Intent(MainActivity.this, SingInActivity.class);
                startActivity(myIntent);
            }
        });
    }
}
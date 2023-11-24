package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class StandaloneToolbar extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Toast.makeText(this, "OnCreate - Standalone toolbar", Toast.LENGTH_LONG).show();
        setContentView(R.layout.activity_toolbar);
        Toolbar toolbar_standalone = findViewById(R.id.toolbar);
        toolbar_standalone.setTitle("Standalone Toolbar");
        toolbar_standalone.setSubtitle("subtitle");
        //toolbar_standalone.setNavigationIcon(R.drawable.settings);
        toolbar_standalone.setElevation(100f);
    }
}




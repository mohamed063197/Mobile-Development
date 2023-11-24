package com.example.myapplication;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CtxToolbar extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Toast.makeText(this, "OnCreate - contextuel menu toolbar", Toast.LENGTH_LONG).show();
        setContentView(R.layout.activity_toolbar);
    }
}
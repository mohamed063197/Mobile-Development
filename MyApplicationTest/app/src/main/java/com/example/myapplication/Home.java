package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView welcomeText = (TextView) findViewById(R.id.welcomeText);
        final Button btn_back = (Button) findViewById(R.id.back);

        Bundle b = getIntent().getExtras();
        welcomeText.setText(b.getString("mail") + " " + b.getString("password"));


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Home.this.finish();
            }
        });


        ImageView img_Viewer = (ImageView) findViewById(R.id.imgView);
        Button btn_previous = (Button) findViewById(R.id.previous);
        Button btn_next = (Button) findViewById(R.id.next);

        img_Viewer.setImageResource(R.drawable.gray);



    }
}
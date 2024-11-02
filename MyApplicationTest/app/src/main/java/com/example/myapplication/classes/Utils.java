package com.example.myapplication.classes;

import android.view.View;
import android.widget.TextView;

public class Utils {

    public static TextView champErrorGone(TextView textViewError){
        textViewError.setVisibility(View.GONE);
        textViewError.setText("");
        return textViewError;
    }

    public static TextView champErrorVisible(TextView textViewError, String value){
        textViewError.setVisibility(View.VISIBLE);
        textViewError.setText(value);
        return textViewError;
    }
}

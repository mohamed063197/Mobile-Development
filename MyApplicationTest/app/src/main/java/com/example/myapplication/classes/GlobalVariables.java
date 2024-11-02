package com.example.myapplication.classes;

import android.app.Application;

public class GlobalVariables extends Application {
    private static long userIdCourant;

    public static long getUserIdCourant(){
        return GlobalVariables.userIdCourant;
    }
    public static void setUserIdCourant(long idUser){
        GlobalVariables.userIdCourant = idUser;
    }
}

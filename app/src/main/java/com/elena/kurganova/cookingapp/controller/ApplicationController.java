package com.elena.kurganova.cookingapp.controller;

import android.app.Application;


import io.realm.Realm;


public class ApplicationController extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //Realm gets initialized
        Realm.init(this);

    }
}
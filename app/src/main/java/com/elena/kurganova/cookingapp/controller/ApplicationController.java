package com.elena.kurganova.cookingapp.controller;

import android.app.Application;

import com.elena.kurganova.cookingapp.R;


import io.realm.Realm;
import io.realm.RealmConfiguration;


public class ApplicationController extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

    }
}
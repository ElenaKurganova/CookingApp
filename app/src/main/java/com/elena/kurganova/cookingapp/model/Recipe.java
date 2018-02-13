package com.elena.kurganova.cookingapp.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Recipe extends RealmObject {

    public String title;

    public String url;
    public String description;
    public String servings;
    public String dietLabel;


    public Recipe() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getServings() {
        return servings;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }

    public String getDietLabel() {
        return dietLabel;
    }

    public void setDietLabel(String dietLabel) {
        this.dietLabel = dietLabel;
    }
}



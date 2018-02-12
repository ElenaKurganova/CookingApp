package com.elena.kurganova.cookingapp.model;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.elena.kurganova.cookingapp.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import io.realm.Realm;

public class JsonToRealmImporter {

    private static final String TAG = JsonToRealmImporter.class.getSimpleName();
    private Realm realm;

    public void importFromJson(final Resources resources) throws IOException {
        InputStream stream = resources.openRawResource(R.raw.dinner_recipes);
        InputStream streamTwo = resources.openRawResource(R.raw.recipes_one);
        InputStream streamThree = resources.openRawResource(R.raw.recipes_two);
        // Open a transaction to store items into the realm
        realm.beginTransaction();
        try {
            realm.createAllFromJson(Recipe.class, stream);
            realm.createAllFromJson(Recipe.class, streamTwo);
            realm.createAllFromJson(Recipe.class, streamThree);
            realm.commitTransaction();
        } catch (IOException e) {
            // Cancel the transaction
            realm.cancelTransaction();
        } finally {
            if (stream != null) {
                stream.close();
            }
            if (streamTwo != null) {
                stream.close();
            }
            if (streamThree != null) {
                stream.close();
            }
        }
        Log.d(TAG, "createAllFromJson task completed");
    }

    public JsonToRealmImporter(Realm realm) {
        this.realm = realm;
    }
}

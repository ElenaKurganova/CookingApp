package com.elena.kurganova.cookingapp.model;

import android.content.res.Resources;
import android.util.Log;

import com.elena.kurganova.cookingapp.R;

import java.io.IOException;
import java.io.InputStream;

import io.realm.Realm;

public class JsonToRealmImporter {

    private static final String TAG = JsonToRealmImporter.class.getSimpleName();
    private final Realm realm;

    public JsonToRealmImporter(Realm realm) {
        this.realm = realm;
    }

    /**
     * Load JSON to Realm from Stream
     *
     * @param resources
     * @throws IOException
     */
    public void importFromJson(final Resources resources) throws IOException {
        InputStream stream = resources.openRawResource(R.raw.recipes);
        InputStream streamTwo = resources.openRawResource(R.raw.desserts);
        InputStream streamThree = resources.openRawResource(R.raw.vegetarian);

        // Open the transaction to store recipe items into the realm
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

}

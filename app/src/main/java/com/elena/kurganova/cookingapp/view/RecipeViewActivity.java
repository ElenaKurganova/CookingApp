/**
 * Recipes Android app
 * @author Elena Kurganova
 * @version 1.0.0
 */

package com.elena.kurganova.cookingapp.view;

import android.app.Activity;
import android.os.Bundle;

import com.elena.kurganova.cookingapp.R;
import com.elena.kurganova.cookingapp.controller.SearchViewAdapter;
import com.elena.kurganova.cookingapp.model.JsonToRealmImporter;
import com.elena.kurganova.cookingapp.model.Recipe;

import java.io.IOException;
import java.util.List;

import co.moonmonkeylabs.realmsearchview.RealmSearchView;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RecipeViewActivity extends Activity {

    private RealmSearchView realmSearchView;
    private JsonToRealmImporter jsonToRealmImporter;
    private SearchViewAdapter searchViewAdapter;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_view);

        //Realm gets configured
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().name("recipes.realm").deleteRealmIfMigrationNeeded().build();
        Realm.deleteRealm(realmConfiguration);
        realm = Realm.getInstance(realmConfiguration);

        jsonToRealmImporter = new JsonToRealmImporter(realm);

        // SearchView associated with view
        realmSearchView = findViewById(R.id.search_view);

        //SearchView adapter that makes search and displays the list of recipes
        searchViewAdapter = new SearchViewAdapter(this, realm, "title");
        realmSearchView.setAdapter(searchViewAdapter);

        try {
            loadRecipes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    /**
     * Load recipes
     * @return
     * @throws IOException
     */
    private List<Recipe> loadRecipes() throws IOException {
        jsonToRealmImporter.importFromJson(getResources());
        return realm.where(Recipe.class).findAll();
    }
}



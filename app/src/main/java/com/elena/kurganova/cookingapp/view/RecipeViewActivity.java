package com.elena.kurganova.cookingapp.view;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.GridView;

import com.elena.kurganova.cookingapp.R;

import com.elena.kurganova.cookingapp.controller.RecipesAdapter;
import com.elena.kurganova.cookingapp.model.JsonToRealmImporter;
import com.elena.kurganova.cookingapp.model.Recipe;


import java.io.IOException;
import java.util.List;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RecipeViewActivity extends Activity {

    private static final String TAG = RecipeViewActivity.class.getSimpleName();
    private GridView mGridView;
    private JsonToRealmImporter jsonToRealmImporter;
    private RecipesAdapter recipesAdapter;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_recipes_view);

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().name("recipes.realm").build();
        Realm.deleteRealm(realmConfiguration);
        realm = Realm.getInstance(realmConfiguration);
        jsonToRealmImporter = new JsonToRealmImporter(realm);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Load from file "cities.json" first time
        if (recipesAdapter == null) {
            List<Recipe> recipes = null;
            try {
                recipes = loadRecipes();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //This is the GridView adapter
            recipesAdapter = new RecipesAdapter(this);
            recipesAdapter.setData(recipes);

            //This is the GridView which will display the list of recipes
            mGridView = (GridView) findViewById(R.id.recipes);
            mGridView.setAdapter(recipesAdapter);
            recipesAdapter.notifyDataSetChanged();
            mGridView.invalidate();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    public List<Recipe> loadRecipes() throws IOException {
        jsonToRealmImporter.importFromJson(getResources());
        return realm.where(Recipe.class).findAll();
    }
}

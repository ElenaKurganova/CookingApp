package com.elena.kurganova.cookingapp.controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.elena.kurganova.cookingapp.R;
import com.elena.kurganova.cookingapp.model.Recipe;
import com.elena.kurganova.cookingapp.view.RecipeViewActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class RecipesAdapter extends BaseAdapter {

    public static final String TAG = RecipesAdapter.class.getName();

    private LayoutInflater inflater;
    private List<Recipe> recipes = null;

    public RecipesAdapter(Context context) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<Recipe> details) {
        this.recipes = details;
    }

    @Override
    public int getCount() {
        if (recipes == null) {
            return 0;
        }
        return recipes.size();
    }

    @Override
    public Object getItem(int position) {
        if (recipes == null || recipes.get(position) == null) {
            return null;
        }
        return recipes.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View currentView, ViewGroup parent) {
        if (currentView == null) {
            currentView = inflater.inflate(R.layout.recipe_item, parent, false);
        }

        Recipe recipe = recipes.get(position);

        if (recipes != null) {
            ((TextView) currentView.findViewById(R.id.title)).setText(recipe.getTitle());
            ((TextView) currentView.findViewById(R.id.description)).setText(String.valueOf(recipe.getDescription()));
        }

        return currentView;
    }
}

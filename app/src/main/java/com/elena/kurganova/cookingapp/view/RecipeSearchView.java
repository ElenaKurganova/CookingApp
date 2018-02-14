package com.elena.kurganova.cookingapp.view;


import android.annotation.SuppressLint;
import android.widget.ImageView;

import com.elena.kurganova.cookingapp.R;
import com.elena.kurganova.cookingapp.model.Recipe;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeSearchView extends RelativeLayout {

    // Get relevant TextViews
    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.description)
    TextView description;

    @BindView(R.id.label)
    TextView label;

    @BindView(R.id.image)
    ImageView imageView;

    String url;

    public RecipeSearchView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.search_result, this);
        ButterKnife.bind(this);
    }

    @SuppressLint("ResourceType")
    public void bind(Recipe recipe) {

        // Update TextViews to show recipe's details
        title.setText(recipe.getTitle());
        description.setText(recipe.getDescription());
        label.setText(recipe.getDietLabel());

        // Picasso library to load the recipe's picture
        Picasso.with(getContext()).load(recipe.getImage()).into(imageView);
        url = recipe.getUrl();
    }

    public String getUrl() {
        return url;
    }
}



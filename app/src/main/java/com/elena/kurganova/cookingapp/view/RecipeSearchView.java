package com.elena.kurganova.cookingapp.view;


import android.content.Context;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.elena.kurganova.cookingapp.R;
import com.elena.kurganova.cookingapp.model.Recipe;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeSearchView extends RelativeLayout {

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.description)
    TextView description;

    @BindView(R.id.label)
    TextView label;


    public RecipeSearchView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.search_result, this);
        ButterKnife.bind(this);
    }

    public void bind(Recipe recipe) {
        title.setText(recipe.getTitle());
        description.setText(recipe.getDescription());
        label.setText(recipe.getDietLabel());
    }
}


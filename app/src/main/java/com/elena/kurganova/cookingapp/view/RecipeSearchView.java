package com.elena.kurganova.cookingapp.view;


import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.elena.kurganova.cookingapp.R;
import com.elena.kurganova.cookingapp.model.Recipe;
import com.squareup.picasso.Picasso;

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
        title.setText(recipe.getTitle());
        description.setText(recipe.getDescription());
        label.setText(recipe.getDietLabel());
        Picasso.with(getContext()).load(recipe.getImage()).into(imageView);
        url = recipe.getUrl();
    }

    public String getUrl() {
        return url;
    }
}



package com.elena.kurganova.cookingapp.controller;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.elena.kurganova.cookingapp.R;
import com.elena.kurganova.cookingapp.model.Recipe;
import com.elena.kurganova.cookingapp.view.RecipeSearchView;

import co.moonmonkeylabs.realmsearchview.RealmSearchAdapter;
import co.moonmonkeylabs.realmsearchview.RealmSearchViewHolder;
import io.realm.Realm;

public class SearchViewAdapter
        extends RealmSearchAdapter<Recipe, SearchViewAdapter.ViewHolder> {

    public SearchViewAdapter(
            Context context,
            Realm realm,
            String filterColumnName) {
        super(context, realm, filterColumnName);
    }

    public class ViewHolder extends RealmSearchViewHolder {

        private RecipeSearchView recipeView;

        public ViewHolder(FrameLayout container, TextView footerTextView) {
            super(container, footerTextView);
        }

        public ViewHolder(RecipeSearchView recipeView) {
            super(recipeView);
            this.recipeView = recipeView;
        }
    }

    @Override
    public ViewHolder onCreateRealmViewHolder(ViewGroup viewGroup, int viewType) {
        ViewHolder vh = new ViewHolder(new RecipeSearchView(viewGroup.getContext()));
        return vh;
    }

    @Override
    public void onBindRealmViewHolder(ViewHolder viewHolder, int position) {
        final Recipe recipe = realmResults.get(position);
        viewHolder.recipeView.bind(recipe);
    }

    @Override
    public ViewHolder onCreateFooterViewHolder(ViewGroup viewGroup) {
        View v = inflater.inflate(R.layout.footer_view, viewGroup, false);
        return new ViewHolder(
                (FrameLayout) v,
                (TextView) v.findViewById(R.id.footer_text_view));
    }

    @Override
    public void onBindFooterViewHolder(ViewHolder holder, int position) {
        super.onBindFooterViewHolder(holder, position);
        holder.itemView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                }
        );
    }
}



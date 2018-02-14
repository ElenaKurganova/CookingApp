package com.elena.kurganova.cookingapp.controller;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
    /**
     * RealmSearchAdapter is the Base adapter.
     * Performs searching and filtering on a Realm database to be used in a search view.
     * Realm is filtering upon the item's title.
     */
    private final Context mContext;

    public SearchViewAdapter(
            Context context,
            Realm realm,
            String filterColumnName) {
        super(context, realm, filterColumnName);
        this.mContext = context;
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

        public RecipeSearchView getRecipeView() {
            return recipeView;
        }
    }

    /**
     * Create ViewHolder with the view
     * @param viewGroup
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateRealmViewHolder(ViewGroup viewGroup, int viewType) {
        final ViewHolder vh = new ViewHolder(new RecipeSearchView(viewGroup.getContext()));
        vh.itemView.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               //Set opening recipe's page URL when a recycle view item is clicked
                                               openUrl(vh.getRecipeView().getUrl());
                                           }
                                       }
        );
        return vh;
    }

    /**
     * Bind the recipe to the view
     * @param viewHolder
     * @param position
     */
    @Override
    public void onBindRealmViewHolder(ViewHolder viewHolder, int position) {
        final Recipe recipe = realmResults.get(position);
        viewHolder.recipeView.bind(recipe);
    }

    /**
     * Inflate the custom row layout
     * @param viewGroup
     * @return
     */
    @Override
    public ViewHolder onCreateFooterViewHolder(ViewGroup viewGroup) {
        View v = inflater.inflate(R.layout.footer_view, viewGroup, false);
        return new ViewHolder(
                (FrameLayout) v,
                (TextView) v.findViewById(R.id.footer_text_view));
    }

    /**
     * Open recipe's source URL in a web browser
     * @param url
     */
    private void openUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        mContext.startActivity(intent);
    }
}





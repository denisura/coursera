package com.github.denisura.coursera.ui;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import com.github.denisura.coursera.R;
import com.github.denisura.coursera.ui.catalog.CatalogFragment;

import timber.log.Timber;

public class CatalogActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        String query = "";
        if (Intent.ACTION_SEARCH.equals(getIntent().getAction())) {
            query = getIntent().getStringExtra(SearchManager.QUERY);
            Timber.d("Query %s", query);
        }
        return CatalogFragment.newInstance(query);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Timber.d("onCreate");
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        // Inflate menu to add items to action bar if it is present.
        inflater.inflate(R.menu.catalog_menu, menu);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }
}

package com.duyrau.lolchampions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private GridView gridView;
    private SearchView searchView;

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.aatrox, R.drawable.ahri,
            R.drawable.blitzcrank, R.drawable.brand,
            R.drawable.cassiopeia, R.drawable.renekton,
            R.drawable.rengar, R.drawable.shyvana,
            R.drawable.aatrox, R.drawable.ahri,
            R.drawable.blitzcrank, R.drawable.brand,
            R.drawable.cassiopeia, R.drawable.renekton,
            R.drawable.rengar, R.drawable.shyvana
    };

    private String[] mChampions = {
            "Aatrox", "Ahri",
            "Akali", "Alistar",
            "Amumu", "Anivia",
            "Annie", "Ashe",
            "Azir", "Blitcrank", "Brand", "Braum", "Caitlyn",
            "Cassiopeia","Cho'gath","Corki",
            "Darius", "Diana", "Draven", "Dr. Mundo", "Elise", "Evelyn", "Ezreal",
            "Fiddlestick", "Fiora", "Fizz", "Galio", "Gangplank", "Garen", "Gnar"
    };

    private List<Champion> createData() {
        List<Champion> list = new ArrayList<>();
        for (int i = 0; i < mChampions.length; i++) {
            list.add(new Champion(mChampions[i], 0));
        }
        return list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        gridView = (GridView) findViewById(R.id.gridview_champions);
        gridView.setAdapter(new ImageAdapter(this, createData()));
        gridView.setTextFilterEnabled(true);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent championIntent = new Intent(getApplicationContext(), ChampionActivity.class);
                startActivity(championIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

//        MenuItem searchItem = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
//        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();

//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName() ));
//        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {
            gridView.clearTextFilter();
        } else {
            gridView.setFilterText(newText);
        }
        return true;
    }
}

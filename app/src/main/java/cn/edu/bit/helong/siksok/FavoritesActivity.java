package cn.edu.bit.helong.siksok;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import cn.edu.bit.helong.siksok.db.FavoritesDbHelper;

public class FavoritesActivity extends AppCompatActivity {

    private static final int FIRST_DB_VERSION = 1;

    public RecyclerView rv;
    SQLiteDatabase favoritesDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        FavoritesDbHelper favoritesDbHelper = new FavoritesDbHelper(this, FIRST_DB_VERSION);
        favoritesDatabase = favoritesDbHelper.getReadableDatabase();

        rv = findViewById(R.id.rv_favorites);
        GridLayoutManager layoutManager = new GridLayoutManager(this,3);
        rv.setLayoutManager(layoutManager);
        FavoritesAdapter favoritesAdapter = new FavoritesAdapter(favoritesDatabase);
        favoritesAdapter.setFavoriteClickListener(new FavoritesAdapter.FavoriteClickListener() {
            @Override
            public void onFavoriteClick(View view) {
                Intent intent = new Intent();
                intent.setClass(FavoritesActivity.this, DetailPlayerActivity.class);
                startActivity(intent);
            }
        });
        rv.setAdapter(favoritesAdapter);


    }
}
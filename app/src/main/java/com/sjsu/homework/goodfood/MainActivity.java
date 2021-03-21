package com.sjsu.homework.goodfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.sjsu.homework.goodfood.IdlingResource.BasicIdleResource;
import com.sjsu.homework.goodfood.model.Food;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ImageDownloader.DelayerCallback{

    Intent foodIntent;

    public final static String COMPLETE_FOOD_NAME = "com.sjsu.homework.goodfood.COMPLETE_FOOD_NAME";

    @Nullable
    private BasicIdleResource fIdlingResource;


    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (fIdlingResource == null) {
            fIdlingResource = new BasicIdleResource();
        }
        return fIdlingResource;
    }

    @Override
    protected void onStart() {
        super.onStart();
        ImageDownloader.downloadImage(this, this, fIdlingResource);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Toolbar menuToolbar = (Toolbar) findViewById(R.id.menu_toolbar);
        setSupportActionBar(menuToolbar);
        getSupportActionBar().setTitle(getString(R.string.menu_title));
        getIdlingResource();
    }

    @Override
    public void onDone(ArrayList<Food> foodItems) {

        GridView gridview = (GridView) findViewById(R.id.grid_view);
        FoodAdapter adapter = new FoodAdapter(this, R.layout.grid_item_layout, foodItems);
        gridview.setAdapter(adapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Food item = (Food) adapterView.getItemAtPosition(position);
                foodIntent = new Intent(MainActivity.this, SecondActivity.class);
                String foodName = item.getFoodName();
                foodIntent.putExtra(COMPLETE_FOOD_NAME, foodName);
                startActivity(foodIntent);
            }
        });

    }
}
package com.sjsu.homework.goodfood;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.sjsu.homework.goodfood.IdlingResource.BasicIdleResource;
import com.sjsu.homework.goodfood.model.Food;

import java.util.ArrayList;

class ImageDownloader {

    private static final int DELAY_MILLIS = 3;

    final static ArrayList<Food> foodList = new ArrayList<>();

    interface DelayerCallback{
        void onDone(ArrayList<Food> teas);
    }

    static void downloadImage(Context context, final DelayerCallback callback,
            @Nullable final BasicIdleResource idlingResource) {

        if (idlingResource != null) {
            idlingResource.setIdleState(false);
        }

        String text = context.getString(R.string.loading_img);
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        foodList.add(new Food(context.getString(R.string.garden_delight), R.drawable.pizza));
        foodList.add(new Food(context.getString(R.string.spicy_pepperoni), R.drawable.bacon));
        foodList.add(new Food(context.getString(R.string.bacon_tomato), R.drawable.margerita));
        foodList.add(new Food(context.getString(R.string.gourmet_veggie), R.drawable.p1));
        foodList.add(new Food(context.getString(R.string.grilled_chicken), R.drawable.p2));
        foodList.add(new Food(context.getString(R.string.pineapple_supreme), R.drawable.p3));

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onDone(foodList);
                    if (idlingResource != null) {
                        idlingResource.setIdleState(true);
                    }
                }
            }
        }, DELAY_MILLIS);
    }
}
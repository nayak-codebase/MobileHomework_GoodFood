package com.sjsu.homework.goodfood;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sjsu.homework.goodfood.model.Food;

import java.util.ArrayList;


public class FoodAdapter extends ArrayAdapter<Food> {

    private Context fContext;
    private int layoutResourceId;
    private ArrayList data = new ArrayList();

    public FoodAdapter(Context context, int layoutResourceId, ArrayList data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.fContext = context;
        this.data = data;
    }

    static class ViewHolder {
        TextView imageTitle;
        ImageView image;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder vholder = null;
        Food currentFoodItem = getItem(position);

        if (convertView == null) {
            LayoutInflater inflater = ((Activity) fContext).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
            vholder = new ViewHolder();
            vholder.imageTitle = (TextView) convertView.findViewById(R.id.food_grid_name);
            vholder.image = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(vholder);
        } else {
            vholder = (ViewHolder) convertView.getTag();;
        }
        vholder.imageTitle.setText(currentFoodItem.getFoodName());
        vholder.image.setImageResource(currentFoodItem.getImageResourceId());
        return convertView;
    }

}
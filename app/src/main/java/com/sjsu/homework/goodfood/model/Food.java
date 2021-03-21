package com.sjsu.homework.goodfood.model;

public class Food {

    private String foodName;
    private int foodImageResourceId;

    public Food(String fName, int imageResourceId) {
        foodName = fName;
        foodImageResourceId = imageResourceId;
    }
    public String getFoodName() {
        return foodName;
    }
    public int getImageResourceId() {
        return foodImageResourceId;
    }

}


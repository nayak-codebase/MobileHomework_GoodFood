package com.sjsu.homework.goodfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.NumberFormat;

public class SecondActivity extends AppCompatActivity {

    private int quantity = 0;
    private int totalPrice = 0;

    private static final String SIZE_PERSONAL = "Personal ($5)";
    private static final String SIZE_MEDIUM = "Medium ($6)";
    private static final String SIZE_LARGE = "Large ($7)";

    private String crustType;
    private String sauceType;
    private String foodName = "";

    private String fSize;

    private static final int PERSONAL_PRICE = 5;
    private static final int MEDIUM_PRICE = 6;
    private static final int LARGE_PRICE = 7;


    public final static String CRUST_TYPE = "com.sjsu.homework.goodfood.CRUST_TYPE";
    public final static String SAUCE_TYPE = "com.sjsu.homework.goodfood.SAUCE_TYPE";
    public final static String QUANTITY = "com.sjsu.homework.goodfood.QUANTITY";
    public final static String TOTAL_PRICE = "com.sjsu.homework.goodfood.TOTAL_PRICE";
    public final static String FOOD_NAME = "com.sjsu.homework.goodfood.FOOD_NAME";
    public final static String SIZE = "com.sjsu.homework.goodfood.SIZE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        Toolbar menuToolbar = (Toolbar) findViewById(R.id.order_toolbar);
        setSupportActionBar(menuToolbar);
        getSupportActionBar().setTitle(getString(R.string.order_title));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        foodName = intent.getStringExtra(MainActivity.COMPLETE_FOOD_NAME);

        TextView teaNameTextView = (TextView) findViewById(R.id.food_name_text_view);
        teaNameTextView.setText(foodName);

        TextView priceTextView = (TextView) findViewById(
                R.id.price_text_view);
        priceTextView.setText(getString(R.string.initial_cost));

        setupSizeSpinner();
        setupCrustTypeSpinner();
        setupSauceTypeSpinner();
    }

    private void setupSizeSpinner() {

        Spinner sizeSpinner = (Spinner) findViewById(R.id.size_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.food_size_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sizeSpinner.setAdapter(adapter);

        sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        fSize = getString(R.string.food_size_personal);
                        break;
                    case 1:
                        fSize = getString(R.string.food_size_medium);
                        break;
                    case 2:
                        fSize = getString(R.string.food_size_large);
                        break;

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                fSize = getString(R.string.food_size_personal);
            }
        });

    }

    private void setupCrustTypeSpinner() {

        Spinner crustTypeSpinner = (Spinner) findViewById(R.id.crustType_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.crustType_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        crustTypeSpinner.setAdapter(adapter);

        crustTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        crustType = getString(R.string.crust_type_none);
                        break;
                    case 1:
                        crustType = getString(R.string.crust_type_original);
                        break;
                    case 2:
                        crustType = getString(R.string.crust_type_pan);
                        break;
                    case 3:
                        crustType = getString(R.string.crust_type_stuffed);
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                crustType = getString(R.string.crust_type_none);
            }
        });

    }



    private void setupSauceTypeSpinner() {

        Spinner mSizeSpinner = (Spinner) findViewById(R.id.sauce_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sauce_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSizeSpinner.setAdapter(adapter);

        mSizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        sauceType = getString(R.string.sauce_type_zesty);
                        break;
                    case 1:
                        sauceType = getString(R.string.sauce_type_bbq);
                        break;
                    case 2:
                        sauceType = getString(R.string.sauce_type_pesto);
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                sauceType = getString(R.string.sauce_type_none);
            }
        });

    }

    public void increment(View view) {

        quantity = quantity + 1;
        displayQuantity(quantity);
        totalPrice = calculatePrice();
        displayCost(totalPrice);
    }


    public void decrement(View view) {
        if (quantity > 0) {

            quantity = quantity - 1;
            displayQuantity(quantity);
            totalPrice = calculatePrice();
            displayCost(totalPrice);
        }
    }


    private int calculatePrice() {

        switch (fSize) {
            case SIZE_PERSONAL:
                totalPrice = quantity * PERSONAL_PRICE;
                break;
            case SIZE_MEDIUM:
                totalPrice = quantity * MEDIUM_PRICE;
                break;
            case SIZE_LARGE:
                totalPrice = quantity * LARGE_PRICE;
                break;
        }
        return totalPrice;
    }

    private void displayQuantity(int numberOfTeas) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(String.valueOf(numberOfTeas));
    }

    private void displayCost(int totalPrice) {
        TextView costTextView = (TextView) findViewById(
                R.id.price_text_view);

        String convertPrice = NumberFormat.getCurrencyInstance().format(totalPrice);
        costTextView.setText(convertPrice);
    }


    public void placeOrder(View view) {
        Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
        intent.putExtra(TOTAL_PRICE, totalPrice);
        intent.putExtra(FOOD_NAME, foodName);
        intent.putExtra(SIZE, fSize);
        intent.putExtra(CRUST_TYPE, crustType);
        intent.putExtra(SAUCE_TYPE, sauceType);
        intent.putExtra(QUANTITY, quantity);

        startActivity(intent);
    }
}
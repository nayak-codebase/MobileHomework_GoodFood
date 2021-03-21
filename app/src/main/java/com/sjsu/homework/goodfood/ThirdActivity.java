package com.sjsu.homework.goodfood;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);
        Toolbar menuToolbar = (Toolbar) findViewById(R.id.order_summary_toolbar);
        setSupportActionBar(menuToolbar);
        getSupportActionBar().setTitle(getString(R.string.order_summary_title));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String pizzaName = intent.getStringExtra(SecondActivity.FOOD_NAME);
        int price = intent.getIntExtra(SecondActivity.TOTAL_PRICE, 0);
        String size = intent.getStringExtra(SecondActivity.SIZE);
        String crustType = intent.getStringExtra(SecondActivity.CRUST_TYPE);
        String sauceType = intent.getStringExtra(SecondActivity.SAUCE_TYPE);
        int quantity = intent.getIntExtra(SecondActivity.QUANTITY, 0);

        displayOrderSummary(pizzaName, price, size, crustType, sauceType, quantity);
    }


    private void displayOrderSummary(String pizzaName, int price, String size, String crustType,
            String sauceType, int quantity) {

        TextView teaNameTextView = (TextView) findViewById(
                R.id.summary_pizza_name);
        teaNameTextView.setText(pizzaName);

        TextView quantityTextView = (TextView) findViewById(
                R.id.summary_quantity);
        quantityTextView.setText(String.valueOf(quantity));

        TextView sizeTextView = (TextView) findViewById(
                R.id.summary_pizza_size);
        sizeTextView.setText(size);

        TextView milkTextView = (TextView) findViewById(
                R.id.summary_crust_type);
        milkTextView.setText(crustType);

        TextView sugarTextView = (TextView) findViewById(
                R.id.summary_sauce_type);
        sugarTextView.setText(sauceType);

        TextView priceTextView = (TextView) findViewById(
                R.id.summary_total_price);

        String convertPrice = NumberFormat.getCurrencyInstance().format(price);
        priceTextView.setText(convertPrice);

    }


    public void sendEmail(View view) {

        String emailMessage = getString(R.string.email_message);

        // Use an intent to launch an email app.
        // Send the order summary in the email body.
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT,
                getString(R.string.order_summary_email_subject));
        intent.putExtra(Intent.EXTRA_TEXT, emailMessage);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);

        }
    }

}
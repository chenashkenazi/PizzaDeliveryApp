package com.example.chen_ashkenazi;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FinishActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.finish_activity_name);
        setContentView(R.layout.finish_layout);

        final String fullName = getIntent().getStringExtra("fullname");
        final String phone = getIntent().getStringExtra("phone");
        final String street = getIntent().getStringExtra("street");
        final String city = getIntent().getStringExtra("city");
        final String house = getIntent().getStringExtra("house");
        final String apartment = getIntent().getStringExtra("apartment");

        final String pizzaSize = getIntent().getStringExtra("pizza_size");
        final String cheese = getIntent().getStringExtra("cheese");
        final int drinksSum = getIntent().getIntExtra("drinks_sum", 0);
        final int totalSum = getIntent().getIntExtra("total", 0);
        ArrayList<String> tops = getIntent().getStringArrayListExtra("topping");
        ArrayList<String> drinks = getIntent().getStringArrayListExtra("drinks");


        TextView nameTV = findViewById(R.id.name_tv);
        TextView phoneTV = findViewById(R.id.phone_tv);
        TextView streetTV = findViewById(R.id.street_tv);
        TextView houseTV = findViewById(R.id.house_tv);
        TextView cityTV = findViewById(R.id.city_tv);
        TextView apartmentTV = findViewById(R.id.apartment_tv);

        TextView pizzaSizeTV = findViewById(R.id.pizza_size_tv);
        TextView cheeseTV = findViewById(R.id.cheese_tv);
        TextView drinksNumTV = findViewById(R.id.drinks_num_tv);
        TextView totalSumTV = findViewById(R.id.total_tv);

        nameTV.setText(" " + fullName);
        phoneTV.setText(" " + phone);
        streetTV.setText(" " + street);
        houseTV.setText(" " + house);
        cityTV.setText(" " + city);
        apartmentTV.setText(" " + apartment);

        pizzaSizeTV.setText(" " + pizzaSize);
        cheeseTV.setText(" " + cheese);
        drinksNumTV.setText(" " + Integer.toString(drinksSum));
        totalSumTV.setText(" " + Integer.toString(totalSum));

        LinearLayout toppingsLayout = findViewById(R.id.toppings_layout);
//        LinearLayout drinksLayout = findViewById(R.id.drinks_layout);

        for (int i = 0; i < tops.size(); i++) {
            TextView top = new TextView(FinishActivity.this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            top.setLayoutParams(layoutParams);

            top.setText(tops.get(i));
            top.setTextSize(20);
            toppingsLayout.addView(top);
        }

//        for (int j = 0; j < drinks.size(); j++) {
//            TextView d = new TextView(FinishActivity.this);
//            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            d.setLayoutParams(layoutParams);
//
//            d.setText(drinks.get(j));
//            d.setTextSize(18);
//            drinksLayout.addView(d);
//        }

    }
}

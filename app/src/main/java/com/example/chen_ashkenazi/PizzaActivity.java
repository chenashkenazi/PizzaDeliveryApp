package com.example.chen_ashkenazi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PizzaActivity extends Activity {

    int sizeSum = 0;
    int extrasSum = 0;
    int drinksSum = 0;
    int totalSum = 0;

    TextView sumTextView;

    Spinner drinksSpinner;

    String pizzaSize;
    String cheese;
    ArrayList<String> toppings = new ArrayList<String>();
    ArrayList<String> drinks = new ArrayList<String>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.pizza_activity_name);
        setContentView(R.layout.pizza_layout);

        final String fullName = getIntent().getStringExtra("fullname");
        final String phone = getIntent().getStringExtra("phone");
        final String street = getIntent().getStringExtra("street");
        final String city = getIntent().getStringExtra("city");
        final String house = getIntent().getStringExtra("house");
        final String apartment = getIntent().getStringExtra("apartment");

        sumTextView = findViewById(R.id.sum_text_view);
        final EditText drinksNum = findViewById(R.id.drinks_num);
        Button drinksBtn = findViewById(R.id.drinks_btn);
        Button doneBtn = findViewById(R.id.done_btn);
        RadioGroup cheeseGroup = findViewById(R.id.cheese_rg);
        RadioButton checkedRadioButton = (RadioButton)cheeseGroup.findViewById(cheeseGroup.getCheckedRadioButtonId());

        CheckBox onionCheckbox = findViewById(R.id.onion_checkbox);
        CheckBox olivesCheckbox = findViewById(R.id.olives_checkbox);
        CheckBox mushroomsCheckbox = findViewById(R.id.mushrooms_checkbox);
        CheckBox tomatoCheckbox = findViewById(R.id.tomato_checkbox);
        CheckBox cornCheckbox = findViewById(R.id.corn_checkbox);
        CheckBox pepperoniCheckbox = findViewById(R.id.pepperoni_checkbox);
        CheckBox jalapenoCheckBox = findViewById(R.id.jalapeno_checkbox);

        onionCheckbox.setOnClickListener(new PizzaActivity.CheckboxClickListener());
        olivesCheckbox.setOnClickListener(new PizzaActivity.CheckboxClickListener());
        mushroomsCheckbox.setOnClickListener(new PizzaActivity.CheckboxClickListener());
        tomatoCheckbox.setOnClickListener(new PizzaActivity.CheckboxClickListener());
        cornCheckbox.setOnClickListener(new PizzaActivity.CheckboxClickListener());
        pepperoniCheckbox.setOnClickListener(new PizzaActivity.CheckboxClickListener());
        jalapenoCheckBox.setOnClickListener(new PizzaActivity.CheckboxClickListener());


        final Spinner sizesSpinner = findViewById(R.id.sizes_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sizes_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizesSpinner.setAdapter(adapter);

        sizesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                pizzaSize = sizesSpinner.getSelectedItem().toString();
                int i = sizesSpinner.getSelectedItemPosition();

                if (i == 1) {
                    sizeSum = 20;
                } else if (i == 2) {
                    sizeSum = 25;
                } else if (i == 3) {
                    sizeSum = 30;
                }

                totalSum = sizeSum + drinksSum + extrasSum;
                String ans = Integer.toString(totalSum);
                sumTextView.setText(ans);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // radio group
        cheeseGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                // This will get the radiobutton that has changed in its check state
                RadioButton checkedRadioButton = (RadioButton)group.findViewById(checkedId);
                // This puts the value (true/false) into the variable
                boolean isChecked = checkedRadioButton.isChecked();
                // If the radiobutton that has changed in check state is now checked...
                if (isChecked)
                {
                    // Changes the textview's text to "Checked: example radiobutton text"
                    //tv.setText("Checked:" + checkedRadioButton.getText());
                    cheese = checkedRadioButton.getText().toString();

                }
            }
        });

        drinksBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int amount=0;

                String numOfDrinks = drinksNum.getText().toString();

                if (numOfDrinks.equals("")) {
                    Toast.makeText(PizzaActivity.this, R.string.drinks_message, Toast.LENGTH_SHORT).show();
                } else {
                    amount = Integer.parseInt(numOfDrinks);
                    LinearLayout spinnersLayout = findViewById(R.id.spinners_layout);
                    spinnersLayout.removeAllViews();

                    for (int i = 0; i < amount ; i++) {
                        drinksSpinner = new Spinner(PizzaActivity.this);

                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                        drinksSpinner.setLayoutParams(layoutParams);

                        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(PizzaActivity.this, R.array.drinks_array, android.R.layout.simple_spinner_item);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        drinksSpinner.setAdapter(adapter);

                        drinksSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                String d = "";
                                d = drinksSpinner.getSelectedItem().toString();

                                if (!(d.equals(R.string.please_choose))) {
                                    System.out.println(d);
                                    drinks.add(d);
                                    for (int i=0; i < drinks.size(); i++) {
                                        System.out.println(drinks.get(i));
                                    }
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                        spinnersLayout.addView(drinksSpinner);
                    }
                }

                drinksSum = amount * 9;
                totalSum = sizeSum + drinksSum + extrasSum;
                String ans = Integer.toString(totalSum);
                sumTextView.setText(ans);

            }
        });

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PizzaActivity.this, FinishActivity.class);
                // all personal details
                intent.putExtra("fullname", fullName);
                intent.putExtra("phone", phone);
                intent.putExtra("street", street);
                intent.putExtra("city", city);
                intent.putExtra("house", house);
                intent.putExtra("apartment", apartment);

                // all order details
                // size, cheese, toppings, drinks, total
                int dNum = drinksSum / 9;
                System.out.println(drinksSum);
                System.out.println(dNum);
                intent.putExtra("pizza_size", pizzaSize);
                intent.putExtra("cheese", cheese);
                intent.putStringArrayListExtra("topping", toppings);
                intent.putStringArrayListExtra("drinks", drinks);
                intent.putExtra("drinks_sum", dNum);
                intent.putExtra("total", totalSum);

                startActivity(intent);

            }
        });

    }

    private class CheckboxClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            String topping = "";

            Boolean checked = ((CheckBox)view).isChecked();

            if (checked) {
                topping = ((CheckBox)view).getText().toString();
                extrasSum += 3;
                toppings.add(topping + " ");

            }
            else {
                topping = ((CheckBox)view).getText().toString();
                extrasSum -= 3;
                if (toppings.contains(topping + " ")) {
                    toppings.remove(topping + " ");
                }
            }

            totalSum = sizeSum + drinksSum + extrasSum;
            String ans = Integer.toString(totalSum);
            sumTextView.setText(ans);

        }
    }

//    private class myOnItemSelectedListener implements AdapterView.OnItemClickListener {
//
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//        }
//    }
}

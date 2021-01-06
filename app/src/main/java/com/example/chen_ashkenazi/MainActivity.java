package com.example.chen_ashkenazi;

import androidx.appcompat.app.AppCompatActivity;

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

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText fullNameET = findViewById(R.id.fullname_ed);
        final EditText phoneET = findViewById(R.id.phone_ed);
        final EditText streetET = findViewById(R.id.street_ed);
        final EditText cityET = findViewById(R.id.city_ed);
        final EditText houseET = findViewById(R.id.house_ed);
        final EditText apartmentET = findViewById(R.id.apartment_ed);

        Button nextBtn = findViewById(R.id.next_btn);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fullName = fullNameET.getText().toString();
                String phone = phoneET.getText().toString();
                String street = streetET.getText().toString();
                String city = cityET.getText().toString();
                String house = houseET.getText().toString();
                String apartment = apartmentET.getText().toString();

                Intent intent = new Intent(MainActivity.this, PizzaActivity.class);
                intent.putExtra("fullname", fullName);
                intent.putExtra("phone", phone);
                intent.putExtra("street", street);
                intent.putExtra("city", city);
                intent.putExtra("house", house);
                intent.putExtra("apartment", apartment);

                startActivity(intent);
            }
        });

    }

}

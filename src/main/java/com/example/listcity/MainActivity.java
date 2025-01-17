package com.example.listcity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
     ArrayList<String> cityList;
     ArrayAdapter<String> adapter;
     int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText cityNameEditText = findViewById(R.id.cityNameEditText);
        Button addCityButton = findViewById(R.id.addCityButton);
        Button deleteCityButton = findViewById(R.id.deleteCityButton);
        ListView cityListView = findViewById(R.id.cityListView);

        cityList = new ArrayList<>(Arrays.asList("Edmonton", "Beijing", "Shanghai", "Guangzhou", "Shenzhen"));


        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cityList);
        cityListView.setAdapter(adapter);


        addCityButton.setOnClickListener(v -> {
            String cityName = cityNameEditText.getText().toString().trim();
            if (!cityName.isEmpty()) {
                cityList.add(cityName);
                adapter.notifyDataSetChanged();
                cityNameEditText.setText(""); // Clear input
                Toast.makeText(MainActivity.this, "City added", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Please enter a city name", Toast.LENGTH_SHORT).show();
            }
        });

        /
        cityListView.setOnItemClickListener((parent, view, position, id) -> {
            selectedPosition = position;
            Toast.makeText(MainActivity.this, "Selected: " + cityList.get(position), Toast.LENGTH_SHORT).show();
        });

        deleteCityButton.setOnClickListener(v -> {
            if (selectedPosition >= 0 && selectedPosition < cityList.size()) {
                cityList.remove(selectedPosition);
                adapter.notifyDataSetChanged();
                selectedPosition = -1; // Reset selection
                Toast.makeText(MainActivity.this, "City deleted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Please select a city to delete", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

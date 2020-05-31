package com.example.prove06;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getWeather(View view) {
        //Creating a background thread with Runnable
        //get the city
        EditText txtCity = findViewById(R.id.get_city);
        String city = txtCity.getText().toString();

        //DeBug Log
        Log.d("MainActivity", "Retrieving weather for: " + city);

        //create the runnable object for the background thread
        GetWeatherAsync getWeatherAsync = new GetWeatherAsync(this, city);

        //create the thread
        Thread thread = new Thread(getWeatherAsync);
        thread.start();
    }

    void handleWeatherConditionsResult(WeatherConditions conditions) {
        Log.d("MainActivity", "Back from API on the UI thread with the weather results!");

        // Check for an error
        if (conditions == null) {
            Log.d("MainActivity", "API results were null");

            // Inform the user
            Toast.makeText(this, "An error occurred when retrieving the weather",
                    Toast.LENGTH_LONG).show();
        } else {
            Log.d("MainActivity", "Conditions: " + conditions.getMeasurements().toString());

            // Get the current temperature
            Float temp = conditions.getMeasurements().get("temp");

            // Show the temperature to the user
            Toast.makeText(this, "It is currently " + temp + " degrees.",
                    Toast.LENGTH_LONG).show();
        }
    }
}

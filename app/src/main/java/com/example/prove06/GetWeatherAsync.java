package com.example.prove06;

public class GetWeatherAsync implements Runnable {
    private MainActivity activity;
    private String city;

    public GetWeatherAsync(MainActivity activity, String city) {
        this.activity = activity;
        this.city = city;
    }

    @Override
    public void run() {
        // This is the function that will be run on the background thread.
        WeatherDataLoader loader = new WeatherDataLoader();

        // Now, call the function that will get the results from the API and then when it is done,
        // it will call the "handleResult" function on this new WeatherConditionsResultHandler
        // object that we are giving it.

        final WeatherConditions conditions = loader.getWeatherAndPostResults(city);
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // This is code that will now run on the UI thread. Call the function in
                // MainActivity that will update the UI correctly.
                activity.handleWeatherConditionsResult(conditions);
            }
        });
    }
}

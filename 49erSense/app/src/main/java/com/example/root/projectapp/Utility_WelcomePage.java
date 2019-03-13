package com.example.root.projectapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Utility_WelcomePage extends AppCompatActivity {

    private TextView textViewName;
    public static double mainThermostatpower = 500;
    public static double mainlightspower = 100;
    public static double upperThermostatpower = 400;
    public static double upperlightspower = 70;
    public static String mainThermostatPowerstring;
    public static String UpperThermostatPowerstriing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utility__welcome_page);
        textViewName = (TextView) findViewById(R.id.textView9);
        textViewName.setText(SharedPrefManager.getInstance(this).getUserName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //int id = item.getItemId();
        switch (item.getItemId()) {
            case R.id.menuLogout:
                SharedPrefManager.getInstance(this).logout();
                finish();
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
        return true;
    }
        public void RegisterButtonClick (View view) {

            Intent intent = new Intent(this, ViewGenerators.class);
            startActivity(intent);
        }

    public void PowerConsumptionButtonClick (View view) {
        Intent intent = new Intent(this, PowerConsumptionPage.class);
        startActivity(intent);
    }

    }


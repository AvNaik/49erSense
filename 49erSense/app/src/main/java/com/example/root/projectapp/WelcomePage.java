package com.example.root.projectapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class WelcomePage extends AppCompatActivity {

    private TextView textViewName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        textViewName = (TextView) findViewById(R.id.textView5);
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
       switch (item.getItemId())
       {
           case R.id.menuLogout:
               SharedPrefManager.getInstance(this).logout();
               finish();
               startActivity(new Intent(this, MainActivity.class));
           break;
       }
       return true;
    }

    public void onWeatherButtonClick (View view)
    {
        Intent intent = new Intent(this, WeatherPage.class);
        startActivity(intent);
    }

    public void onAppliancesButtonClick (View view)
    {
        Intent intent = new Intent(this,AppliancePage.class);
        startActivity(intent);
    }

    public void onSecuritySystemButtonClick (View view)
    {
        Intent intent = new Intent(this,SecuritySystemPage.class);
        startActivity(intent);
    }

    public void onoStatusButtonClick (View view)
    {
        Intent intent = new Intent(this,  StatusPage.class);
        startActivity(intent);
    }

    public void onVideoFeedButtonClick (View view)
    {
        Intent intent = new Intent(this,  VideoFeedPage.class);
        startActivity(intent);
    }


    public void onPowerConsumptionButtonClick (View view)
    {
        Intent intent = new Intent(this,PowerConsumptionPage.class);
        startActivity(intent);
    }
}

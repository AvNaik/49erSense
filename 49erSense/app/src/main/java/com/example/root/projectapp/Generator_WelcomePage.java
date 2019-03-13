package com.example.root.projectapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Generator_WelcomePage extends AppCompatActivity {

    private TextView textViewName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generator__welcome_page);
        textViewName = (TextView) findViewById(R.id.textView7);
        textViewName.setText(SharedPrefManager.getInstance(this).getUserName());
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

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

    public void ViewGeneratorButtonClick (View v) {

        Intent intent = new Intent(this, ViewGenerators.class);
        startActivity(intent);
    }

    public void ManageGeneratorButtonClick (View v) {

        Intent intent = new Intent(this, ManageGenerators.class);
        startActivity(intent);
    }
}

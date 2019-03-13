package com.example.root.projectapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AppliancePage extends AppCompatActivity  {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appliance_page);
    }

    public void SecuritySystemButtonClick (View view) {

        Intent intent = new Intent(this, SecuritySystemPage.class);
        startActivity(intent);
    }

    public void ThermostatButtonClick (View v) {

        Intent intent = new Intent(this, ThermostatPage.class);
        startActivity(intent);
    }

    public void LightingButtonClick (View view) {

        Intent intent = new Intent(this, LightingPage.class);
        startActivity(intent);
    }

    public void LocksButtonClick (View view) {

        Intent intent = new Intent(this, LocksPage.class);
        startActivity(intent);
    }

    public void MotionDetectorButtonClick (View v) {

        Intent intent = new Intent(this, MotionDetectorPage.class);
        startActivity(intent);
    }

}

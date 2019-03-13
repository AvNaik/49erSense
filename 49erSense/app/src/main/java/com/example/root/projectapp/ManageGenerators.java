package com.example.root.projectapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
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

public class ManageGenerators extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    public String Genset_Status;
    private static String Gen_Name;
    Double voltage, current;
    private static String Voltage = "405.20";
    private static String Current = "967.85";
    private TextView textViewName;
    int increment = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_generators);
        Spinner spinner = (Spinner) findViewById(R.id.spinner2);
        Spinner spinner1 = (Spinner)findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Genset_Array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.Genset_NameArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner1.setAdapter(adapter1);
        spinner.setOnItemSelectedListener(this);
        spinner1.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId())
        {
            case R.id.spinner2:
                Genset_Status = parent.getItemAtPosition(position).toString();
                break;
            case R.id.spinner3:
                Gen_Name = parent.getItemAtPosition(position).toString();
                break;
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void CalculateVandI ()
    {
        voltage = 400.00;
        current = 1126.00;
        for (increment= 100; increment > 0; increment--)
        {
            voltage = voltage + 0.5;
            if (voltage > 415.00)
            {
                voltage = 375.00;
            }
            current = current + 0.75;
            if (current > 2000.00)
            {
                current = 1054.00;
            }
        }
        if (Genset_Status.equals("OFF"))
        {
            voltage = 0.0;
            current = 0.0;
        }
        Voltage = voltage + " " + "Volts";
        Current = current + " " + "Amperes";
    }
        public void Toggle_Gens (View view) {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_Toggle_Generators,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                Toast.makeText(getApplicationContext(), jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                            } catch (JSONException e)
                            {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show ();
                        }
                    }
            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map <String, String> params = new HashMap<>();
                    params.put("Name", Gen_Name);
                    params.put ("Status", Genset_Status);
                        CalculateVandI ();

                    params.put("Voltage", Voltage);
                    params.put("Current", Current);
                    increment = 1000;
                    return params;
                }
            };

            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
            //RequestQueue requestQueue = Volley.newRequestQueue(this);
            //requestQueue.add (stringRequest);sa

        }


}

package com.example.root.projectapp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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

public class ThermostatPage extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    public static String Thermostat_Floor;
    public static String Temperature_Floor;
    public static String Thermostat_Mode;
    public static String Fan_Mode;
    private ProgressDialog progressDialog;
    public int Temperature = 68;
    public String temp1;
    public static String ControlTemp;
    private static final String settext = "Control Temperature";
    private static String setetxt1 = "Current Temperature";
    EditText e;
    TextView t1, t2, t3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thermostat_page);
        progressDialog = new ProgressDialog (this);
        e  = (EditText) findViewById(R.id.editText6);
        t1 = findViewById(R.id.textView29);
        t1.setText(setetxt1);
        t2 = findViewById(R.id.textView28);
        t2.setText(settext);

        for (int i = 10; i > 0; i++)
        {
            Temperature = Temperature + 1;
            if (Temperature == 75)
            {
                Temperature = 60;
            }
        }
        temp1 = Temperature + " Fahrenheits";
        t3 = findViewById(R.id.textView30);
        t3.setText("  " + temp1);

        Spinner spinner4 = (Spinner) findViewById(R.id.spinner19);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.Thermostat_Floor, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);
        spinner4.setOnItemSelectedListener(this);

        Spinner spinner1 = (Spinner) findViewById(R.id.spinner8);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.Thermostat_Floor, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(this);

        Spinner spinner2 = (Spinner) findViewById(R.id.spinner9);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.Thermostat_Mode, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);

        Spinner spinner3 = (Spinner) findViewById(R.id.spinner10);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.Fan_Status, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);
        spinner3.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId())
        {
            case R.id.spinner19:
                Temperature_Floor = parent.getItemAtPosition(position).toString();
                break;
            case R.id.spinner8:
                Thermostat_Floor = parent.getItemAtPosition(position).toString();
                break;
            case R.id.spinner9:
                Thermostat_Mode = parent.getItemAtPosition(position).toString();
                break;
            case R.id.spinner10:
                Fan_Mode = parent.getItemAtPosition(position).toString();
                break;

        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void ThermostatControl (View view)
    {
        ControlTemp = e.getText().toString();
        progressDialog.setMessage("Please Wait . . .");
        progressDialog.show ();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.THERMOSTAT_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss ();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(), jsonObject.getString("message") + " " + Thermostat_Floor + " " + "Thermostat set to " + Thermostat_Mode + " " + ". Fan set to " + Fan_Mode + " " + "Mode Successfully",Toast.LENGTH_LONG).show();
                        } catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide ();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show ();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map <String, String> params = new HashMap<>();
                params.put("Email", SharedPrefManager.user_email);
                params.put ("Floor", Thermostat_Floor);
                params.put ("Mode", Thermostat_Mode);
                params.put("Fan", Fan_Mode);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        //RequestQueue requestQueue = Volley.newRequestQueue(this);
        //requestQueue.add (stringRequest);sa
    }
/*
    public int Temperature ()
    {
        Temperature = 55;
        for (int i = 10; i > 0; i++)
        {
            Temperature = Temperature + 1;
            if (Temperature == 75)
            {
                Temperature = 60;
            }
        }
        return Temperature;
    }*/

        public void TemperatureControl (View view)
        {
            ControlTemp = e.getText().toString();
          //  Temperature();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.TEMPERATURE_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss ();
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
                            progressDialog.hide ();
                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show ();
                        }
                    }
            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map <String, String> params = new HashMap<>();
                    params.put("Email", SharedPrefManager.user_email);
                    params.put ("Floor", Temperature_Floor);
                    params.put ("Current", temp1);
                    params.put("Control", ControlTemp);
                    return params;
                }
            };
            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        }
    }


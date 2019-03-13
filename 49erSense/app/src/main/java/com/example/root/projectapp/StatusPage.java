package com.example.root.projectapp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
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

public class StatusPage extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    public static String Home_Status;
    public static String Floor;
    public static String Location;
    private ProgressDialog progressDialog;
    private TextView textViewName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_page);
        progressDialog = new ProgressDialog (this);
        progressDialog.setMessage("Please Wait ...");
        textViewName = (TextView) findViewById(R.id.textView23);
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner16);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.Appliance_array, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(this);
    }

    public void homeStatus (View view)
    {
        progressDialog.show();
         if (Home_Status.equals ("SECURITY SYSTEM"))
         {
             //spinner2.setEnabled(false);
             //spinner3.setEnabled(false);
             StringRequest stringRequest = new StringRequest(
                     Request.Method.POST,
                     Constants.getSecurity_Status_URL,
                     new Response.Listener<String>() {
                         @Override
                         public void onResponse(String response) {
                             progressDialog.dismiss();
                             try {
                                 JSONObject obj = new JSONObject(response);
                                 if (!obj.getBoolean("error"))
                                 {
                                     SharedPrefManager.getInstance(getApplicationContext()).SecuritySystemStatus(obj.getInt("Id"),  obj.getString("Email"), obj.getString("Status"));

                                     textViewName.setText("Security System Status: " + SharedPrefManager.Security_System_Status);
                                 } else {
                                     Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                                 }
                             } catch (JSONException e) {
                                 e.printStackTrace();
                             }
                         }
                     },
                     new Response.ErrorListener() {
                         @Override
                         public void onErrorResponse(VolleyError error) {
                             progressDialog.dismiss();
                             Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                         }
                     }
             ){
                 @Override
                 protected Map<String, String> getParams() throws AuthFailureError {
                     Map <String, String> params = new HashMap<>();
                     params.put("Email",  SharedPrefManager.user_email);
                     return params;
                 }
             };
             RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
         } else if (Home_Status.equals ("THERMOSTAT"))
         {
             Spinner spinner2 = (Spinner) findViewById(R.id.spinner17);
             ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.Floor_array, android.R.layout.simple_spinner_item);
             adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
             spinner2.setAdapter(adapter1);
             spinner2.setOnItemSelectedListener(this);

             StringRequest stringRequest = new StringRequest(
                     Request.Method.POST,
                     Constants.getThermostat_Status_URL,
                     new Response.Listener<String>() {
                         @Override
                         public void onResponse(String response) {
                             progressDialog.dismiss();
                             try {
                                 JSONObject obj = new JSONObject(response);
                                 if (!obj.getBoolean("error"))
                                 {
                                     SharedPrefManager.getInstance(getApplicationContext()).ThermostatStatus(obj.getInt("Id"),  obj.getString("Email"), obj.getString("Floor"), obj.getString("Mode"), obj.getString("Fan"));

                                     textViewName.setText("Thermostat Status:");
                                     textViewName.append(System.getProperty("line.separator"));
                                     textViewName.append("Floor: " + Floor);
                                     textViewName.append(System.getProperty("line.separator"));
                                     textViewName.append("Mode: " + SharedPrefManager.Thermostat_Mode);
                                     textViewName.append(System.getProperty("line.separator"));
                                     textViewName.append("Fan: " + SharedPrefManager.Fan_Mode);
                                 } else {
                                     Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                                 }
                             } catch (JSONException e) {
                                 e.printStackTrace();
                             }
                         }
                     },
                     new Response.ErrorListener() {
                         @Override
                         public void onErrorResponse(VolleyError error) {
                             progressDialog.dismiss();
                             Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                         }
                     }
             ){
                 @Override
                 protected Map<String, String> getParams() throws AuthFailureError {
                     Map <String, String> params = new HashMap<>();
                     params.put("Email",  SharedPrefManager.user_email);
                     params.put("Floor", Floor);
                     //     params.put("Voltage", SharedPrefManager.GEN_Voltage);
                     //     params.put("Current", SharedPrefManager.GEN_Current);
                     return params;
                 }
             };
             RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
           //  spinner2.setEnabled(false);
         } else if (Home_Status.equals ("LIGHTING"))
         {
             Spinner spinner2 = (Spinner) findViewById(R.id.spinner17);
             ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.Floor_array, android.R.layout.simple_spinner_item);
             adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
             spinner2.setAdapter(adapter1);
             spinner2.setOnItemSelectedListener(this);

             Spinner spinner3 = (Spinner) findViewById(R.id.spinner18);
             ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.Location_array, android.R.layout.simple_spinner_item);
             adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
             spinner3.setAdapter(adapter2);
             spinner3.setOnItemSelectedListener(this);

             StringRequest stringRequest = new StringRequest(
                     Request.Method.POST,
                     Constants.getLighting_Status_URL,
                     new Response.Listener<String>() {
                         @Override
                         public void onResponse(String response) {
                             progressDialog.dismiss();
                             try {
                                 JSONObject obj = new JSONObject(response);
                                 if (!obj.getBoolean("error"))
                                 {
                                     SharedPrefManager.getInstance(getApplicationContext()).LightingStatus(obj.getInt("Id"),  obj.getString("Email"), obj.getString("Floor"), obj.getString("Location"), obj.getString("Intensity"));

                                     textViewName.setText("Lighting Status:");
                                     textViewName.append(System.getProperty("line.separator"));
                                     textViewName.append("Floor: " + Floor);
                                     textViewName.append(System.getProperty("line.separator"));
                                     textViewName.append("Location: " + Location);
                                     textViewName.append(System.getProperty("line.separator"));
                                     textViewName.append("Intensity: " + SharedPrefManager.LightIntensity);
                                 } else {
                                     Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                                 }
                             } catch (JSONException e) {
                                 e.printStackTrace();
                             }
                         }
                     },
                     new Response.ErrorListener() {
                         @Override
                         public void onErrorResponse(VolleyError error) {
                             progressDialog.dismiss();
                             Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                         }
                     }
             ){
                 @Override
                 protected Map<String, String> getParams() throws AuthFailureError {
                     Map <String, String> params = new HashMap<>();
                     params.put("Email",  SharedPrefManager.user_email);
                     params.put("Floor", Floor);
                     params.put("Location", Location);
                     //     params.put("Current", SharedPrefManager.GEN_Current);
                     return params;
                 }
             };
             RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
         } else if (Home_Status.equals ("LOCKS"))
         {
             Spinner spinner2 = (Spinner) findViewById(R.id.spinner17);
             ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.Door_array, android.R.layout.simple_spinner_item);
             adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
             spinner2.setAdapter(adapter1);
             spinner2.setOnItemSelectedListener(this);

             StringRequest stringRequest = new StringRequest(
                     Request.Method.POST,
                     Constants.getLockStatus_URL,
                     new Response.Listener<String>() {
                         @Override
                         public void onResponse(String response) {
                             progressDialog.dismiss();
                             try {
                                 JSONObject obj = new JSONObject(response);
                                 if (!obj.getBoolean("error"))
                                 {
                                     SharedPrefManager.getInstance(getApplicationContext()).LockStatus(obj.getInt("Id"),  obj.getString("Email"), obj.getString("Location"), obj.getString("Status"));

                                     textViewName.setText("Lock Status:");
                                     textViewName.append(System.getProperty("line.separator"));
                                     textViewName.append("Location:" + Floor);
                                     textViewName.append(System.getProperty("line.separator"));
                                     textViewName.append("Status: " + SharedPrefManager.LockStatus);
                                 } else {
                                     Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                                 }
                             } catch (JSONException e) {
                                 e.printStackTrace();
                             }
                         }
                     },
                     new Response.ErrorListener() {
                         @Override
                         public void onErrorResponse(VolleyError error) {
                             progressDialog.dismiss();
                             Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                         }
                     }
             ){
                 @Override
                 protected Map<String, String> getParams() throws AuthFailureError {
                     Map <String, String> params = new HashMap<>();
                     params.put("Email",  SharedPrefManager.user_email);
                     params.put("Location", Floor);
                     //params.put("", Location);
                     //     params.put("Current", SharedPrefManager.GEN_Current);
                     return params;
                 }
             };
             RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
         } else if (Home_Status.equals ("MOTION DETECTOR"))
         {
             Spinner spinner2 = (Spinner) findViewById(R.id.spinner17);
             ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.Floor_array, android.R.layout.simple_spinner_item);
             adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
             spinner2.setAdapter(adapter1);
             spinner2.setOnItemSelectedListener(this);

             StringRequest stringRequest = new StringRequest(
                     Request.Method.POST,
                     Constants.getMotionDetectorStatus_URL,
                     new Response.Listener<String>() {
                         @Override
                         public void onResponse(String response) {
                             progressDialog.dismiss();
                             try {
                                 JSONObject obj = new JSONObject(response);
                                 if (!obj.getBoolean("error"))
                                 {
                                     SharedPrefManager.getInstance(getApplicationContext()).MotionDetectorStatus(obj.getInt("Id"),  obj.getString("Email"), obj.getString("Floor"), obj.getString("Status"));

                                     textViewName.setText("Motion Detector Status:");
                                     textViewName.append(System.getProperty("line.separator"));
                                     textViewName.append("Location:" + Floor);
                                     textViewName.append(System.getProperty("line.separator"));
                                     textViewName.append("Status: " + SharedPrefManager.MotionDetectorStatus);
                                 } else {
                                     Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                                 }
                             } catch (JSONException e) {
                                 e.printStackTrace();
                             }
                         }
                     },
                     new Response.ErrorListener() {
                         @Override
                         public void onErrorResponse(VolleyError error) {
                             progressDialog.dismiss();
                             Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                         }
                     }
             ){
                 @Override
                 protected Map<String, String> getParams() throws AuthFailureError {
                     Map <String, String> params = new HashMap<>();
                     params.put("Email",  SharedPrefManager.user_email);
                     params.put("Floor", Floor);
                     //params.put("", Location);
                     //     params.put("Current", SharedPrefManager.GEN_Current);
                     return params;
                 }
             };
             RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
         }
         }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId())
        {
            case R.id.spinner16:
                Home_Status = parent.getItemAtPosition(position).toString();
                break;
            case R.id.spinner17:
                Floor = parent.getItemAtPosition(position).toString();
                break;
            case R.id.spinner18:
                Location = parent.getItemAtPosition(position).toString();
                break;
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}

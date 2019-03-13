package com.example.root.projectapp;

import android.app.ProgressDialog;
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

public class LightingPage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static String Lighting_Floor;
    public static String Light_Intensity;
    public static String Location;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lighting_page);
        progressDialog = new ProgressDialog (this);
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner6);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.Lighting_Floor, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(this);

        Spinner spinner2 = (Spinner) findViewById(R.id.spinner7);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.Location, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);

        Spinner spinner3 = (Spinner) findViewById(R.id.spinner11);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.Intensity, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);
        spinner3.setOnItemSelectedListener(this);
    }
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId())
        {
            case R.id.spinner6:
                Lighting_Floor = parent.getItemAtPosition(position).toString();
                break;
            case R.id.spinner7:
                Location = parent.getItemAtPosition(position).toString();
                break;
            case R.id.spinner11:
                Light_Intensity = parent.getItemAtPosition(position).toString();
                break;
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void LightingControl (View view)
    {
        progressDialog.setMessage("Please Wait . . .");
        progressDialog.show ();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.Lighting_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss ();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(), jsonObject.getString("message") + " "+Lighting_Floor+ " " + Location+ " Light set to " + Light_Intensity,Toast.LENGTH_LONG).show();
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
                params.put ("Floor", Lighting_Floor);
                params.put ("Location", Location);
                params.put("Intensity", Light_Intensity);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
}

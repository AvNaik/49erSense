package com.example.root.projectapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;

public class ViewGenerators extends AppCompatActivity implements AdapterView.OnItemSelectedListener,  View.OnClickListener {

    public String Gen_NAME;
    private Button StatusButton;
    private ProgressDialog progressDialog;
    TextView textView;
    public static ArrayList <String> StatusList = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_generators);
        textView = findViewById(R.id.textView15);
        StatusButton = (Button) findViewById(R.id.button8);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait ...");
        StatusButton.setOnClickListener(this);
        Spinner spinner = (Spinner) findViewById(R.id.spinner4);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Genset_StatusArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Gen_NAME = parent.getItemAtPosition(position).toString();
        Toast.makeText(getApplicationContext(),Gen_NAME, Toast.LENGTH_LONG).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
    private void viewGensets()
    {
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_VIEW,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error"))
                            {
                                SharedPrefManager.getInstance(getApplicationContext()).viewGenerators(obj.getInt("Id"),  obj.getString("Name"), obj.getString("Status"), obj.getString("Voltage"), obj.getString("Current"));

                                textView.setText("Name: " + SharedPrefManager.GEN_Name);
                                textView.append(System.getProperty("line.separator"));
                                textView.append("Status: " + SharedPrefManager.GEN_Status);
                                textView.append(System.getProperty("line.separator"));
                                textView.append("Voltage: " + SharedPrefManager.GEN_Voltage);
                                textView.append(System.getProperty("line.separator"));
                                textView.append("Current: " + SharedPrefManager.GEN_Current);
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
                params.put("Name",  Gen_NAME);
          //      params.put("Status", SharedPrefManager.GEN_Status);
           //     params.put("Voltage", SharedPrefManager.GEN_Voltage);
           //     params.put("Current", SharedPrefManager.GEN_Current);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View v) {

        if (v == StatusButton)
        {
                viewGensets();
        }

    }
}

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

public class MotionDetectorPage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static String MotionDetector_Floor;
    public static String MotionDetector_Status;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion_detector_page);
        progressDialog = new ProgressDialog (this);
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner14);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.MotionDetector_Floor, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(this);

        Spinner spinner2 = (Spinner) findViewById(R.id.spinner15);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.MotionDetector_Status, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);

    }
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId())
        {
            case R.id.spinner14:
                MotionDetector_Floor = parent.getItemAtPosition(position).toString();
                break;
            case R.id.spinner15:
                MotionDetector_Status = parent.getItemAtPosition(position).toString();
                break;
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void MotionDetectorControl (View view)
    {
        progressDialog.setMessage("Please Wait . . .");
        progressDialog.show ();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.MotionDetector_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss ();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(), jsonObject.getString("message") + " " + MotionDetector_Floor + " " + MotionDetector_Status + " " + "Successfully",Toast.LENGTH_LONG).show();
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
                params.put ("Floor", MotionDetector_Floor);
                params.put ("Status", MotionDetector_Status);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
    }


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

public class SecuritySystemPage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static String SecuritySystem_Status = "DISARMED";
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_system_page);
        progressDialog = new ProgressDialog (this);
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner5);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.Security_System, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(this);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        SecuritySystem_Status = parent.getItemAtPosition(position).toString();
    }
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void SecuritySystemControl (View view)
    {
        progressDialog.setMessage("Please Wait . . .");
        progressDialog.show ();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.SECURITYSYSTEM_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss ();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(), jsonObject.getString("message") + " "+"Security System set to "+ SecuritySystem_Status + " " + "Mode Successfully",Toast.LENGTH_LONG).show();
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
                params.put ("Status", SecuritySystem_Status);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
}

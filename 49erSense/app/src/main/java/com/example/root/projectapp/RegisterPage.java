package com.example.root.projectapp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterPage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String Role;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        progressDialog = new ProgressDialog (this);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Users_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Role = parent.getItemAtPosition(position).toString();
        Toast.makeText(getApplicationContext(), Role, Toast.LENGTH_LONG).show();
    }
    public void RegisterButtonClick (View view) {
        EditText e = (EditText) findViewById(R.id.editText3);
        EditText e1 = (EditText) findViewById(R.id.editText4);
        EditText e2 = (EditText) findViewById(R.id.editText5);
        final String Name = e.getText().toString();
        final String Email =  e1.getText().toString();
        final String Password = e2.getText().toString();

        progressDialog.setMessage("Registering User . . .");
        progressDialog.show ();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_REGISTER,
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
                params.put("Name", Name);
                params.put ("Email", Email);
                params.put ("Password", Password);
                params.put("Role", Role);
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        //RequestQueue requestQueue = Volley.newRequestQueue(this);
        //requestQueue.add (stringRequest);sa
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

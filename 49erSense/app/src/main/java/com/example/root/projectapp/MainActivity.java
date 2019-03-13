package com.example.root.projectapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText e1, e2;
    private Button lbu;
    private ProgressDialog progressDialog;
    static int i = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        if (SharedPrefManager.getInstance(this).isLoggedIn())
        {
            finish();
            startActivity(new Intent(this, WelcomePage.class));
            return;
        }*/
         e1 = (EditText)findViewById(R.id.editText);
         e2 = (EditText)findViewById(R.id.editText2);
        lbu = (Button) findViewById(R.id.button2 );
         progressDialog = new ProgressDialog(this);
         progressDialog.setMessage("Please Wait ...");
         lbu.setOnClickListener(this);
    }
    public void RegisterButtonClick (View v) {

        Intent intent = new Intent(this, RegisterPage.class);
        startActivity(intent);
        }

    private void userLogin()
    {
        final String Email = e1.getText().toString();
        final String Password = e2.getText().toString();
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error"))
                            {
                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(obj.getInt("Id"),  obj.getString("Name"), obj.getString("Email"), obj.getString("Role"));
                                if (SharedPrefManager.user_role.equals("Consumer")) {
                                    startActivity(new Intent(getApplicationContext(), WelcomePage.class));
                                } else if (SharedPrefManager.user_role.equals("Utility Provider"))
                                {
                                    startActivity(new Intent(getApplicationContext(), Utility_WelcomePage.class));
                                } else if (SharedPrefManager.user_role.equals("Power Generator"))
                                {
                                    startActivity(new Intent(getApplicationContext(), Generator_WelcomePage.class));
                                }
                                Toast.makeText(getApplicationContext(), "User Login Successful", Toast.LENGTH_LONG).show();
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
                params.put("Email", Email);
                params.put("Password", Password);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
  }
    @Override
    public void onClick(View view) {
        if (view == lbu)
        {
            userLogin();
        }
    }
}

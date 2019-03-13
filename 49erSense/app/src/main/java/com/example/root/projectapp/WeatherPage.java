package com.example.root.projectapp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class WeatherPage extends AppCompatActivity {

    public ListView weatherListView ;
    public ArrayAdapter<String> listAdapter ;
    private ProgressDialog progressDialog;

    ArrayList<String> planetList = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_page);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait ...");
        weatherListView = (ListView) findViewById( R.id.weatherListView);
        listAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,planetList);
        weatherListView.setAdapter(listAdapter);
        weatherUpdate();
    }

    public void weatherUpdate() {
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_WEATHERFORECAST,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Toast.makeText(getApplicationContext(),
                                    new JSONObject(jsonObject.getString("Day1")).getString("temp"),
                                    Toast.LENGTH_SHORT).show();

                            planetList.add( "Day 1: \n" + "Temperature: "+new JSONObject(jsonObject.getString("Day1"))
                                    .getString("temp")+
                                    "\n"+"Time: "+new JSONObject(jsonObject.getString("Day1"))
                                    .getString("timestamp_local")+
                                    "\n"+"Description: "+new JSONObject(new JSONObject(jsonObject.getString("Day1"))
                                    .getString("weather")).getString("description"));


                            planetList.add( "Day 2: \n" +"Temperature: "+new JSONObject(jsonObject.getString("Day2"))
                                    .getString("temp")+
                                    "\n"+"Time: "+new JSONObject(jsonObject.getString("Day2"))
                                    .getString("timestamp_local")+
                                    "\n"+"Description: "+new JSONObject(new JSONObject(jsonObject.getString("Day2"))
                                    .getString("weather")).getString("description"));

                            planetList.add( "Day 3: \n" +"Temperature: "+new JSONObject(jsonObject.getString("Day3"))
                                    .getString("temp")+
                                    "\n"+"Time: "+new JSONObject(jsonObject.getString("Day3"))
                                    .getString("timestamp_local")+
                                    "\n"+"Description: "+new JSONObject(new JSONObject(jsonObject.getString("Day3"))
                                    .getString("weather")).getString("description"));

                            planetList.add( "Day 4: \n" +"Temperature: "+new JSONObject(jsonObject.getString("Day4"))
                                    .getString("temp")+
                                    "\n"+"Time: "+new JSONObject(jsonObject.getString("Day4"))
                                    .getString("timestamp_local")+
                                    "\n"+"Description: "+new JSONObject(new JSONObject(jsonObject.getString("Day4"))
                                    .getString("weather")).getString("description"));

                            planetList.add( "Day 5: \n" +"Temperature: "+new JSONObject(jsonObject.getString("Day5"))
                                    .getString("temp")+
                                    "\n"+"Time: "+new JSONObject(jsonObject.getString("Day5"))
                                    .getString("timestamp_local")+
                                    "\n"+"Description: "+new JSONObject(new JSONObject(jsonObject.getString("Day5"))
                                    .getString("weather")).getString("description"));

                            listAdapter.notifyDataSetChanged();
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"Unsuccessful",Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params =new HashMap<>();
                params.put("username",Constants.username);
                params.put("zipcode","28262");
                return  params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
}

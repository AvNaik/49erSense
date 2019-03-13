package com.example.root.projectapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class PowerConsumptionPage extends AppCompatActivity {

    TextView textView;
    public static int mainThermostatpower = 600;
    public static String mainThermostatPowerstring;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_consumption_page);
        GraphView graph = (GraphView) findViewById(R.id.graph);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(24);
        graph.getViewport().setXAxisBoundsManual(true);
        textView = findViewById(R.id.textView24);

        for (int x = 100;  x > 0;  x -- )
        {
            mainThermostatpower = mainThermostatpower + 15;
            if (mainThermostatpower > 850)
            {
                mainThermostatpower = 500;
            }
        }
        mainThermostatPowerstring = "              Average Power Consumption: " + mainThermostatpower + " Watts";

        textView.setText(mainThermostatPowerstring);

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 670),
                new DataPoint(2, 550),
                new DataPoint(4, mainThermostatpower),
                new DataPoint(6, 615),
                new DataPoint(8, 620),
                new DataPoint(10, 525),
                new DataPoint(12, 502),
                new DataPoint(14, 495),
                new DataPoint(16, 420),
                new DataPoint(18, 462),
                new DataPoint(20, mainThermostatpower),
                new DataPoint(22,620),
                new DataPoint(24, 725)
        });
        graph.addSeries(series);
        series.setColor(Color.RED);
        series.setThickness(12);
    }
}

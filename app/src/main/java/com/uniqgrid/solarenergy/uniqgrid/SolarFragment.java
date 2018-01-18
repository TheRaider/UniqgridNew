package com.uniqgrid.solarenergy.uniqgrid;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

import models.SolarGI;
import models.SolarGI;


public class SolarFragment extends Fragment {


    LineChart lineChart;
    ArrayList<String> labels = new ArrayList<>();
    ArrayList<SolarGI> solarGIArrayList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View customView = inflater.inflate(R.layout.fragment_solar, container, false);

        lineChart = (LineChart)customView.findViewById(R.id.lineChart);
        lineChart.getDescription().setEnabled(false);

        populateConsumptionBarGraphData();
        populateSolarLineGraph(solarGIArrayList,"Solar");

        lineChart.setVisibleXRangeMaximum(7);
        lineChart.setPinchZoom(false); // disabling zoom
        lineChart.setScaleEnabled(false); // disabling zoom



        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));


        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        leftAxis.setEnabled(false);  // disable axes

        YAxis rightAxis = lineChart.getAxisRight();
        rightAxis.setDrawGridLines(false); // disable gridlines
        rightAxis.setEnabled(false);  // disable axes


        Legend l = lineChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);



  //      lineChart.animateX(2500, Easing.EasingOption.EaseInOutQuart);


        return customView;
    }

    public void populateSolarLineGraph(ArrayList<SolarGI> SolarGIS , String Label){
        ArrayList<Entry> linegroup1 = new ArrayList<>();
        ArrayList<Entry> linegroup2 = new ArrayList<>();
        int i=0;
        for(SolarGI SolarGI : SolarGIS) {
            
            linegroup1.add(new Entry(i, SolarGI.getPastEnergy())); // xvals,yvals
            linegroup2.add(new Entry(i, SolarGI.getTargetEnergy())); // xvals,yvals
            
            i++;
            String label = SolarGI.getMonth();
            if(label.length()>3)
                labels.add(label.substring(0,3));
            else
                labels.add(label);
        }
   

        // creating dataset for Line Group1
        LineDataSet lineDataSet1 = new LineDataSet(linegroup1, "Total Charges");
        int color1 = Color.rgb(3,169,244);
        lineDataSet1.setColor(color1);
    //    lineDataSet1.setDrawCircles(false);
        lineDataSet1.setDrawFilled(true);
        lineDataSet1.setCircleColor(color1);
        lineDataSet1.setLineWidth(1f);
        lineDataSet1.setCircleRadius(3f);
        lineDataSet1.setDrawCircleHole(false);
        lineDataSet1.setValueTextSize(9f);
        lineDataSet1.setFillColor(color1);
    //    lineDataSet1.setMode(LineDataSet.Mode.CUBIC_BEZIER);


        // creating dataset for Line Group2
        LineDataSet lineDataSet2 = new LineDataSet(linegroup2, "New Total Charges");
        int color2 = Color.rgb(220,100,100);
        lineDataSet2.setColor(color2);
        lineDataSet2.setDrawFilled(true);
    //    lineDataSet2.setDrawCircles(false);
        lineDataSet2.setCircleColor(color2);
        lineDataSet2.setLineWidth(1f);
        lineDataSet2.setCircleRadius(3f);
        lineDataSet2.setDrawCircleHole(false);
        lineDataSet2.setValueTextSize(9f);
        lineDataSet2.setFillColor(color2);
       // lineDataSet2.setMode(LineDataSet.Mode.CUBIC_BEZIER);


        ArrayList<ILineDataSet> iDataSets = new ArrayList<>();
        iDataSets.add(lineDataSet1);
        iDataSets.add(lineDataSet2);

        // initialize the Bardata with argument labels and dataSet
        LineData data = new LineData(iDataSets);
        data.setDrawValues(false); // disable labels on top
        

        lineChart.setData(data);


    }

    public  void populateConsumptionBarGraphData(){
        solarGIArrayList.add(new SolarGI("January",100f , 25f));
        solarGIArrayList.add(new SolarGI("January",90f , 10f));
        solarGIArrayList.add(new SolarGI("January",80f , 15f));
        solarGIArrayList.add(new SolarGI("January",50f , 35f));
        solarGIArrayList.add(new SolarGI("January",70f , 18f));
        solarGIArrayList.add(new SolarGI("January",80f , 26f));
        solarGIArrayList.add(new SolarGI("January",90f , 8f));
        solarGIArrayList.add(new SolarGI("January",60f , 19f));
        solarGIArrayList.add(new SolarGI("January",90f , 21f));
        solarGIArrayList.add(new SolarGI("January",100f , 23f));
        solarGIArrayList.add(new SolarGI("January",50f , 26f));
        solarGIArrayList.add(new SolarGI("January",70f , 22f));






    }



}

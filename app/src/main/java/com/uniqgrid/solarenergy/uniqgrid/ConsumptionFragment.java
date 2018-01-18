package com.uniqgrid.solarenergy.uniqgrid;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.IMarker;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import models.ConsumptionGI;
import models.LoadDistGI;


public class ConsumptionFragment extends Fragment {

    PieChart pieChart;
    BarChart barChart;
    ArrayList<Integer> colors;
    ArrayList<LoadDistGI> loadDistGIArrayList = new ArrayList<>();
    ArrayList<ConsumptionGI> consumptionGIArrayList = new ArrayList<>();
    ArrayList<String> labels = new ArrayList<>();
    NestedScrollView nsAddEst;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View customView =  inflater.inflate(R.layout.fragment_consumption, container, false);


        // Adding colors
        colors = new ArrayList<>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);



        // Charts

        // Load Distribution PieChart
        pieChart = (PieChart) customView.findViewById(R.id.pieChart);

        // Setting Legend(actually removing the legend)
        //pieChart.getLegend().setEnabled(false);
        pieChart.getLegend().setWordWrapEnabled(true);
      //  pieChart.getLegend().setPosition(Legend.LegendPosition.RIGHT_OF_CHART_CENTER);
      //  pieChart.getLegend().setOrientation(Legend.LegendOrientation.VERTICAL);
        pieChart.getDescription().setEnabled(false);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setRotationEnabled(false);
        pieChart.setDrawSliceText(false);
        pieChart.setHoleRadius(20f);
        pieChart.setTransparentCircleRadius(20f);

      //  pieChart.setCenterText("Total  \n " + RoundOff(100));

        populatePieGraphData();
        populatePieGraph(loadDistGIArrayList,"Load Distribution");

        // Consumption Pattern Bar Chart

        barChart = (BarChart) customView.findViewById(R.id.barchart);
        barChart.getDescription().setEnabled(false);

        populateConsumptionBarGraphData();
        populateConsumptionBarGraph(consumptionGIArrayList,"Consumption");

        barChart.setVisibleXRangeMaximum(7); // set maximum bars visible
        barChart.setPinchZoom(false); // disabling zoom
        barChart.setScaleEnabled(false); // disabling zoom



        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new MyIndexAxisValueFormatter(labels));
        xAxis.setCenterAxisLabels(false);


        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        leftAxis.setEnabled(false);  // disable axes


        YAxis rightAxis = barChart.getAxisRight();
        rightAxis.setDrawGridLines(false); // disable gridlines
        rightAxis.setEnabled(false);  // disable axes


        Legend l = barChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);


        IMarker marker = new YourMarkerView(getContext(),R.layout.custom_marker_view_layout);
        barChart.setMarker(marker);

        barChart.animateY(1000);






        return  customView;
    }

    public  void populateConsumptionBarGraphData(){
        consumptionGIArrayList.add(new ConsumptionGI("January",10f));
        consumptionGIArrayList.add(new ConsumptionGI("January",100f));
        consumptionGIArrayList.add(new ConsumptionGI("January",90f));
        consumptionGIArrayList.add(new ConsumptionGI("January",10f));
        consumptionGIArrayList.add(new ConsumptionGI("January",10f));
        consumptionGIArrayList.add(new ConsumptionGI("January",10f));
        consumptionGIArrayList.add(new ConsumptionGI("January",10f));
        consumptionGIArrayList.add(new ConsumptionGI("January",10f));
        consumptionGIArrayList.add(new ConsumptionGI("January",10f));
        consumptionGIArrayList.add(new ConsumptionGI("January",10f));


    }

    public void populatePieGraphData(){
        loadDistGIArrayList.add(new LoadDistGI("Lighting akjsajdjjaj",4392 ,11.2));
        loadDistGIArrayList.add(new LoadDistGI("Lighting akjsajdjjaj",4392 ,11.2));
        loadDistGIArrayList.add(new LoadDistGI("Lighting akjsajdjjaj",4392 ,11.2));
        loadDistGIArrayList.add(new LoadDistGI("Lighting akjsajdjjaj",4392 ,11.2));
        loadDistGIArrayList.add(new LoadDistGI("Lighting akjsajdjjaj",4392 ,11.2));
        loadDistGIArrayList.add(new LoadDistGI("Lighting akjsajdjjaj",4392 ,11.2));
        loadDistGIArrayList.add(new LoadDistGI("Lighting akjsajdjjaj",4392 ,11.2));
        loadDistGIArrayList.add(new LoadDistGI("Lighting akjsajdjjaj",4392 ,11.2));
        loadDistGIArrayList.add(new LoadDistGI("Lighting akjsajdjjaj",4392 ,11.2));
        loadDistGIArrayList.add(new LoadDistGI("Lighting akjsajdjjaj",4392 ,11.2));
        loadDistGIArrayList.add(new LoadDistGI("Lighting akjsajdjjaj",4392 ,11.2));
        loadDistGIArrayList.add(new LoadDistGI("Lighting akjsajdjjaj",4392 ,11.2));
        loadDistGIArrayList.add(new LoadDistGI("Lighting akjsajdjjaj",4392 ,11.2));
        loadDistGIArrayList.add(new LoadDistGI("Lighting akjsajdjjaj",4392 ,11.2));


    }


    public void populateConsumptionBarGraph(ArrayList<ConsumptionGI> consumptionGIS , String Label){
        ArrayList<BarEntry> bargroup1 = new ArrayList<>();
        int i=0;
        for(ConsumptionGI consumptionGI : consumptionGIS) {
              bargroup1.add(new BarEntry(i, consumptionGI.getConsumption())); // xvals,yvals
              i+=1;
            String label = consumptionGI.getMonth();
            if(label.length()>3)
                labels.add(label.substring(0,3));
            else
                labels.add(label);
        }


        // creating dataset for Bar Group1
        BarDataSet barDataSet1 = new BarDataSet(bargroup1, "Consumption Pattern");
        barDataSet1.setColors(Color.rgb(3,169,244));

        // initialize the Bardata with argument labels and dataSet
        BarData data = new BarData(barDataSet1);
        data.setDrawValues(false); // disable labels on top
        data.setBarWidth(0.5f);

        barChart.setData(data);


    }




    public void populatePieGraph(ArrayList<LoadDistGI> graphItems , String Label){
        ArrayList<PieEntry> yValues = new ArrayList<>();
        for(LoadDistGI graphItem : graphItems) {

            if(graphItem.getkW() > 0){
                yValues.add(new PieEntry((float) graphItem.getPercent(),graphItem.getCategory()));
            }
        }

        PieDataSet pieDataSet = new PieDataSet(yValues,"");
        pieDataSet.setColors(colors);
      //  pieDataSet.setLabel("Load Distribution");
        pieDataSet.setSliceSpace(2f);

        IValueFormatter formatter = new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return value+"";
            }
        };


        IValueFormatter MyValueFormatter = new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                if(value < 5) return "";
                else return value + " %";
            }
        };


        PieData pieData = new PieData(pieDataSet);
        pieData.setValueTextSize(11f);
      //  pieData.setValueFormatter(formatter);
        pieData.setValueFormatter(new PercentFormatter());

        pieChart.setData(pieData);
        pieChart.animateY(1400);


    }

    public Double RoundOff(Double d){
        return Math.round(d * 100.0) / 100.0;
    }

    class YourMarkerView extends MarkerView {

        private TextView tvContent;

        public YourMarkerView(Context context, int layoutResource) {
            super(context, layoutResource);

            // find your layout components
            tvContent = (TextView) findViewById(R.id.tvContent);
        }

        // callbacks everytime the MarkerView is redrawn, can be used to update the
        // content (user-interface)
        @Override
        public void refreshContent(Entry e, Highlight highlight) {

            tvContent.setText("" + e.getY());

            // this will perform necessary layouting
            super.refreshContent(e, highlight);
        }

        private MPPointF mOffset;

        @Override
        public MPPointF getOffset() {

            if(mOffset == null) {
                // center the marker horizontally and vertically
                mOffset = new MPPointF(-(getWidth() / 2), -getHeight());
            }

            return mOffset;
        }
    }

    public class MyIndexAxisValueFormatter implements IAxisValueFormatter
    {
        private String[] mValues = new String[] {};
        private int mValueCount = 0;

        /**
         * An empty constructor.
         * Use `setValues` to set the axis labels.
         */
        public MyIndexAxisValueFormatter() {
        }

        /**
         * Constructor that specifies axis labels.
         *
         * @param values The values string array
         */
        public MyIndexAxisValueFormatter(String[] values) {
            if (values != null)
                setValues(values);
        }

        /**
         * Constructor that specifies axis labels.
         *
         * @param values The values string array
         */
        public MyIndexAxisValueFormatter(Collection<String> values) {
            if (values != null)
                setValues(values.toArray(new String[values.size()]));
        }

        public String getFormattedValue(float value, AxisBase axis) {
            int index = Math.round(value);

            if (index < 0 || index >= mValueCount || index != (int)value)
                return "";

            return mValues[index];
        }

        public String[] getValues()
        {
            return mValues;
        }

        public void setValues(String[] values)
        {
            if (values == null)
                values = new String[] {};

            this.mValues = values;
            this.mValueCount = values.length;
        }
    }

}




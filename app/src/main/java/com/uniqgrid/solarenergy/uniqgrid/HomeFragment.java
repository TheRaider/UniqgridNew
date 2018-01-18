package com.uniqgrid.solarenergy.uniqgrid;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import Utils.CircleDisplay;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    CircleDisplay cdConnLoad,cdDg,cdSolarPv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View customView = inflater.inflate(R.layout.fragment_home, container, false);

        cdConnLoad = (CircleDisplay) customView.findViewById(R.id.cdConnLoad);
        cdDg = (CircleDisplay) customView.findViewById(R.id.cdDg);
        cdSolarPv = (CircleDisplay) customView.findViewById(R.id.cdSolarPv);


        cdConnLoad.setAnimDuration(2000);
        cdConnLoad.setValueWidthPercent(20f);
        cdConnLoad.setTextSize(15f);
        cdConnLoad.setTextColor(Color.parseColor("#ef6762"));
        cdConnLoad.setColor(Color.parseColor("#ef6762"));
        cdConnLoad.setDrawText(true);
        cdConnLoad.setFormatDigits(0);
        cdConnLoad.setUnit("");
        cdConnLoad.setStepSize(0.5f);
        cdConnLoad.showValue(110, 100f, true);
        cdConnLoad.setTouchEnabled(false);

        cdDg.setAnimDuration(2000);
        cdDg.setValueWidthPercent(20f);
        cdDg.setTextSize(15f);
        cdDg.setTextColor(Color.parseColor("#8cc2dc"));
        cdDg.setColor(Color.parseColor("#8cc2dc"));
        cdDg.setDrawText(true);
        cdDg.setFormatDigits(0);
        cdDg.setUnit("");
        cdDg.setStepSize(0.5f);
        cdDg.showValue(20, 100f, true);
        cdDg.setTouchEnabled(false);

        cdSolarPv.setAnimDuration(2000);
        cdSolarPv.setValueWidthPercent(20f);
        cdSolarPv.setTextSize(15f);
        cdSolarPv.setTextColor(Color.parseColor("#6dd260"));
        cdSolarPv.setColor(Color.parseColor("#6dd260"));
        cdSolarPv.setDrawText(true);
        cdSolarPv.setFormatDigits(0);
        cdSolarPv.setUnit("");
        cdSolarPv.setStepSize(0.5f);
        cdSolarPv.showValue(35, 100f, true);
        cdSolarPv.setTouchEnabled(false);

        // A8C9D9 -- gray type color
        // 24c2fd -- blue color



         return  customView;
    }

}

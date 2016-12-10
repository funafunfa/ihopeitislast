package com.example.dmitriy.schedulenew.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dmitriy.schedulenew.R;

import org.json.JSONArray;

import static com.example.dmitriy.schedulenew.settings.APP_PREFERENCES_1401A;
import static com.example.dmitriy.schedulenew.settings.mSettings;


public class mon extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.mon, container,false);

        if (mSettings.contains(APP_PREFERENCES_1401A)) {
            JSONArray array = new JSONArray();
            String arrays = mSettings.getString(APP_PREFERENCES_1401A,"");
            array = ar

        }
        return rootView;

    }

}
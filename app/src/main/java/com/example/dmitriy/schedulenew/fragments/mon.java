package com.example.dmitriy.schedulenew.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dmitriy.schedulenew.R;

import org.json.JSONArray;
import org.w3c.dom.Text;

import static com.example.dmitriy.schedulenew.MainActivity.A;
import static com.example.dmitriy.schedulenew.settings.APP_PREFERENCES_1401A;
import static com.example.dmitriy.schedulenew.settings.mSettings;


public class mon extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.mon, container,false);
        TextView first = (TextView)rootView.findViewById(R.id.first);




        return rootView;

    }

}
package com.example.dmitriy.schedulenew.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dmitriy.schedulenew.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.dmitriy.schedulenew.MainActivity.backdata;
import static com.example.dmitriy.schedulenew.settings.APP_PREFERENCES_1301A;
import static com.example.dmitriy.schedulenew.settings.APP_PREFERENCES_1301B;
import static com.example.dmitriy.schedulenew.settings.APP_PREFERENCES_1401A;
import static com.example.dmitriy.schedulenew.settings.APP_PREFERENCES_1401B;
import static com.example.dmitriy.schedulenew.settings.APP_PREFERENCES_1501A;
import static com.example.dmitriy.schedulenew.settings.APP_PREFERENCES_1501B;
import static com.example.dmitriy.schedulenew.settings.APP_PREFERENCES_1601A;
import static com.example.dmitriy.schedulenew.settings.APP_PREFERENCES_1601B;
import static com.example.dmitriy.schedulenew.settings.mSettings;


public class thu extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.thu, container,false);
        TextView first = (TextView)rootView.findViewById(R.id.first);
        TextView second = (TextView)rootView.findViewById(R.id.second);
        TextView third = (TextView)rootView.findViewById(R.id.third);
        TextView fourth = (TextView)rootView.findViewById(R.id.fourth);
        TextView fifth = (TextView)rootView.findViewById(R.id.fifth);
        TextView sixth = (TextView)rootView.findViewById(R.id.sixth);
        TextView seventh = (TextView)rootView.findViewById(R.id.seventh);
        TextView one = (TextView)rootView.findViewById(R.id.one);
        TextView two = (TextView)rootView.findViewById(R.id.two);
        TextView three = (TextView)rootView.findViewById(R.id.three);
        TextView four = (TextView)rootView.findViewById(R.id.four);
        TextView five = (TextView)rootView.findViewById(R.id.five);
        TextView six = (TextView)rootView.findViewById(R.id.six);
        TextView seven = (TextView)rootView.findViewById(R.id.seven);
        Date current = new Date();
        SimpleDateFormat calendar = new SimpleDateFormat("w");

        GsonBuilder builder = new GsonBuilder();
        Gson gson = new Gson();
        Gson json = builder.create();



        int date = Integer.parseInt(calendar.format(current));
        switch (backdata){
            case "1401":
                if (date% 2 != 0) {
                    String file = mSettings.getString(APP_PREFERENCES_1401A, "");
                    try {
                        JSONArray jsonArray = new JSONArray(file);
                        JSONObject jsonObject = jsonArray.getJSONObject(3);
                        first.setText(jsonObject.getString("first"));
                        second.setText(jsonObject.getString("second"));
                        third.setText(jsonObject.getString("third"));
                        fourth.setText(jsonObject.getString("fourth"));
                        fifth.setText(jsonObject.getString("fifth"));
                        sixth.setText(jsonObject.getString("sixth"));
                        seventh.setText(jsonObject.getString("seventh"));
                        //Log.e("FAGGOT", jsonObject.getString("third"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    String file = mSettings.getString(APP_PREFERENCES_1401B, "");
                    try {
                        JSONArray jsonArray = new JSONArray(file);
                        JSONObject jsonObject = jsonArray.getJSONObject(3);
                        first.setText(jsonObject.getString("first"));
                        second.setText(jsonObject.getString("second"));
                        third.setText(jsonObject.getString("third"));
                        fourth.setText(jsonObject.getString("fourth"));
                        fifth.setText(jsonObject.getString("fifth"));
                        sixth.setText(jsonObject.getString("sixth"));
                        seventh.setText(jsonObject.getString("seventh"));
                        //Log.e("FAGGOT", jsonObject.getString("third"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case "1501":
                if (date% 2 != 0) {
                    String file = mSettings.getString(APP_PREFERENCES_1501A, "");
                    try {
                        JSONArray jsonArray = new JSONArray(file);
                        JSONObject jsonObject = jsonArray.getJSONObject(3);
                        first.setText(jsonObject.getString("first"));
                        second.setText(jsonObject.getString("second"));
                        third.setText(jsonObject.getString("third"));
                        fourth.setText(jsonObject.getString("fourth"));
                        fifth.setText(jsonObject.getString("fifth"));
                        sixth.setText(jsonObject.getString("sixth"));
                        seventh.setText(jsonObject.getString("seventh"));
                        //Log.e("FAGGOT", jsonObject.getString("third"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    String file = mSettings.getString(APP_PREFERENCES_1501B, "");
                    try {
                        JSONArray jsonArray = new JSONArray(file);
                        JSONObject jsonObject = jsonArray.getJSONObject(3);
                        first.setText(jsonObject.getString("first"));
                        second.setText(jsonObject.getString("second"));
                        third.setText(jsonObject.getString("third"));
                        fourth.setText(jsonObject.getString("fourth"));
                        fifth.setText(jsonObject.getString("fifth"));
                        sixth.setText(jsonObject.getString("sixth"));
                        seventh.setText(jsonObject.getString("seventh"));
                        //Log.e("FAGGOT", jsonObject.getString("third"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } break;
            case "1301":
                if (date% 2 != 0) {
                    String file = mSettings.getString(APP_PREFERENCES_1301A, "");
                    try {
                        JSONArray jsonArray = new JSONArray(file);
                        JSONObject jsonObject = jsonArray.getJSONObject(3);
                        first.setText(jsonObject.getString("first"));
                        second.setText(jsonObject.getString("second"));
                        third.setText(jsonObject.getString("third"));
                        fourth.setText(jsonObject.getString("fourth"));
                        fifth.setText(jsonObject.getString("fifth"));
                        sixth.setText(jsonObject.getString("sixth"));
                        seventh.setText(jsonObject.getString("seventh"));
                        //Log.e("FAGGOT", jsonObject.getString("third"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    String file = mSettings.getString(APP_PREFERENCES_1301B, "");
                    try {
                        JSONArray jsonArray = new JSONArray(file);
                        JSONObject jsonObject = jsonArray.getJSONObject(3);
                        first.setText(jsonObject.getString("first"));
                        second.setText(jsonObject.getString("second"));
                        third.setText(jsonObject.getString("third"));
                        fourth.setText(jsonObject.getString("fourth"));
                        fifth.setText(jsonObject.getString("fifth"));
                        sixth.setText(jsonObject.getString("sixth"));
                        seventh.setText(jsonObject.getString("seventh"));
                        //Log.e("FAGGOT", jsonObject.getString("third"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case "1601":
                if (date% 2 != 0) {
                    String file = mSettings.getString(APP_PREFERENCES_1601A, "");
                    try {
                        JSONArray jsonArray = new JSONArray(file);
                        JSONObject jsonObject = jsonArray.getJSONObject(3);
                        first.setText(jsonObject.getString("first"));
                        second.setText(jsonObject.getString("second"));
                        third.setText(jsonObject.getString("third"));
                        fourth.setText(jsonObject.getString("fourth"));
                        fifth.setText(jsonObject.getString("fifth"));
                        sixth.setText(jsonObject.getString("sixth"));
                        seventh.setText(jsonObject.getString("seventh"));
                        //Log.e("FAGGOT", jsonObject.getString("third"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    String file = mSettings.getString(APP_PREFERENCES_1601B, "");
                    try {
                        JSONArray jsonArray = new JSONArray(file);
                        JSONObject jsonObject = jsonArray.getJSONObject(3);
                        first.setText(jsonObject.getString("first"));
                        second.setText(jsonObject.getString("second"));
                        third.setText(jsonObject.getString("third"));
                        fourth.setText(jsonObject.getString("fourth"));
                        fifth.setText(jsonObject.getString("fifth"));
                        sixth.setText(jsonObject.getString("sixth"));
                        seventh.setText(jsonObject.getString("seventh"));
                        //Log.e("FAGGOT", jsonObject.getString("third"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
        if(first.getText().equals(""))one.setText("");
        if(second.getText().equals(""))two.setText("");
        if(third.getText().equals(""))three.setText("");
        if(fourth.getText().equals(""))four.setText("");
        if(fifth.getText().equals(""))five.setText("");
        if(sixth.getText().equals(""))six.setText("");
        if(seventh.getText().equals(""))seven.setText("");
        rootView.invalidate();
        return rootView;
    }

}
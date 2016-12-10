package com.example.dmitriy.schedulenew;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.dmitriy.schedulenew.sub.JSONParser;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class settings extends AppCompatActivity {
    public static final String APP_PREFERENCES = "groups";
    public static final String APP_PREFERENCES_1401A = "1401A";
    public static final String APP_PREFERENCES_1401B = "1401B";
    public static final String APP_PREFERENCES_1501A = "1501A";
    public static final String APP_PREFERENCES_1501B = "1501B";
    public static String group;
    public static String loader;
    public static SharedPreferences mSettings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Log.d("me","here");
        mSettings = getSharedPreferences(APP_PREFERENCES_1401A, Context.MODE_PRIVATE);
        mSettings = getSharedPreferences(APP_PREFERENCES_1501B, Context.MODE_PRIVATE);

        final Spinner spinner = (Spinner)findViewById(R.id.spinner);

        ArrayAdapter<?> adapter =
                ArrayAdapter.createFromResource(this, R.array.groups, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    @Override
    protected void onStop() {
        super.onStop();


    }

    private static final String TAG_SUCCESS = "success";
    private ProgressDialog pDialog;
    static JSONParser jParser = new JSONParser();
    static ArrayList<HashMap<String, String>> productsList;


    private static String url_all_products = "http://193.151.106.187/android/schedule/get_product.php";

    JSONArray products = null;
    List<NameValuePair> params = new ArrayList<>();



    static SharedPreferences preference;
    static JSONObject json;

    public void onClick(View view) {

        final Spinner spinner = (Spinner)findViewById(R.id.spinner);
        try {

            Log.e("ROCK", spinner.getSelectedItem().toString());
            LoadAllProducts load = new LoadAllProducts();
            loader = spinner.getSelectedItem().toString();
            load.execute(loader);
            Log.e("NIKITA","APP_PREFERENCES_" + loader);
        } catch (EnumConstantNotPresentException e){
            e.printStackTrace();
        }

    }


    class LoadAllProducts extends AsyncTask<String, String, String> {
        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(settings.this);
            pDialog.setMessage("Loading products. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * getting All products from url
         */
        protected String doInBackground(String... args) {
            // Building Parameters
            for(String arg :args) {
                Log.e("dsad",arg);
                params.add(new BasicNameValuePair("group", arg));
                group = arg;
            }
            JSONObject json = jParser.makeHttpRequest(url_all_products, "GET", params);

            try {
                int success = json.getInt(TAG_SUCCESS);
                     if (success == 1) {
                         products = json.getJSONArray("lessons");
                         Log.e("a","b");
                         Log.e("a",products.toString());

                         for (int i = 0; i < products.length(); i++) {
                             JSONObject c = products.getJSONObject(i);
                                //Log.e("ad",c.getString("day"));


                         }

                        }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
         }


        /**
         * After completing background task Dismiss the progress dialog
         **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            SharedPreferences.Editor editor = mSettings.edit();

            switch (loader){
                case "1401A":
                    editor.putString(APP_PREFERENCES_1401A, products.toString());
                    editor.apply();
                    break;
                case "1501B":
                    editor.putString(APP_PREFERENCES_1501B, products.toString());
                    editor.apply();
                    break;
            }
            pDialog.dismiss();





            // updating UI from Background Thread


            runOnUiThread(new Runnable() {
                public void run() {
                }
            });

        }

    }
}

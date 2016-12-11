package com.example.dmitriy.schedulenew;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
        Log.d("me", "here");
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        //mSettings = getSharedPreferences(APP_PREFERENCES_1501B, Context.MODE_PRIVATE);

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<?> adapter =
                ArrayAdapter.createFromResource(this, R.array.groups, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        try{
            Log.e("AGSKDAHSJ", mSettings.getString(APP_PREFERENCES_1401A, ""));
        }catch (NullPointerException e){
            e.printStackTrace();
        }
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

    JSONArray productsB = null;
    JSONArray productsA = null;
    JSONArray products = null;
    List<NameValuePair> params = new ArrayList<>();
    ArrayList arrayList = new ArrayList();
    Intent intent;

    static SharedPreferences preference;
    static JSONObject json;

    public void onClick(View view) {

        final Spinner spinner = (Spinner)findViewById(R.id.spinner);
        try {
            Log.e("ROCK", spinner.getSelectedItem().toString());
            LoadAllProducts load = new LoadAllProducts();
            loader = spinner.getSelectedItem().toString();
            load.execute(loader);

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
                         productsA = json.getJSONArray("lessonsA");
                         productsB = json.getJSONArray("lessonsB");

                         /*for (int i = 0; i < products.length(); i++) {
                             //JSONObject c = productsA.getJSONObject(i);
                                //Log.e("ad",c.getString("day"));



                         }*/

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
            intent = new Intent(settings.this, MainActivity.class);
            switch (loader){
                case "1401":
                    editor.putString(APP_PREFERENCES_1401A, productsA.toString());
                    editor.putString(APP_PREFERENCES_1401B, productsB.toString());
                    editor.apply();

                    break;
                case "1501":
                    editor.putString(APP_PREFERENCES_1501A, productsA.toString());
                    editor.putString(APP_PREFERENCES_1501B, productsB.toString());
                    editor.apply();
                    break;
            }
            intent.putExtra("group",loader);
            editor.putBoolean("FIRST",true);
            editor.apply();
            pDialog.dismiss();
            startActivity(intent);




            // updating UI from Background Thread


            runOnUiThread(new Runnable() {
                public void run() {
                }
            });

        }

    }
}

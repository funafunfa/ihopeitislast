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
import android.widget.CheckBox;
import android.widget.Spinner;

import com.example.dmitriy.schedulenew.sub.JSONParser;
import com.example.dmitriy.schedulenew.sub.day;
import com.google.gson.Gson;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

//import static com.example.dmitriy.schedulenew.sub.day.days;
import static com.example.dmitriy.schedulenew.sub.day.daysA;
import static com.example.dmitriy.schedulenew.sub.day.daysB;

public class settings extends AppCompatActivity {

    public static Spinner spinner = null;

    public static final String APP_PREFERENCES = "groups";
    public static final String APP_PREFERENCES_fisrt = "first";
    public static final String APP_PREFERENCES_1401A = "1401A";
    public static final String APP_PREFERENCES_1401B = "1401B";

    public static final String APP_PREFERENCES_1501A = "1501A";
    public static final String APP_PREFERENCES_1501B = "1501B";

    public static final String APP_PREFERENCES_1601A = "1601A";
    public static final String APP_PREFERENCES_1601B = "1601B";

    public static final String APP_PREFERENCES_1301A = "1301A";
    public static final String APP_PREFERENCES_1301B = "1301B";

    public static final String APP_PREFERENCES_1402A = "1402A";
    public static final String APP_PREFERENCES_1402B = "1402B";

    public static final String APP_PREFERENCES_1403A = "1403A";
    public static final String APP_PREFERENCES_1403B = "1403B";

    public static final String APP_PREFERENCES_1503A = "1503A";
    public static final String APP_PREFERENCES_1503B = "1503B";

    public static final String APP_PREFERENCES_1502A = "1502A";
    public static final String APP_PREFERENCES_1502B = "1502B";
    public static String group;
    public static String loader;
    public static SharedPreferences mSettings;
    public static String dataA, dataB;


    static ArrayList<String> arrayListx;
    static CheckBox c1501, c1301, c1401, c1601;
   // public static groupss g, ga;
    int selector = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        c1501 = (CheckBox)findViewById(R.id.checkBox);
        c1301 = (CheckBox)findViewById(R.id.checkBox2);
        c1401 = (CheckBox)findViewById(R.id.checkBox3);
        c1601 = (CheckBox)findViewById(R.id.checkBox4);
        Log.d("me", "here");
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        //mSettings = getSharedPreferences(APP_PREFERENCES_1501B, Context.MODE_PRIVATE);

         spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<?> adapter =
                ArrayAdapter.createFromResource(this, R.array.groups, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        if (mSettings.contains("position")) spinner.setSelection(mSettings.getInt("position", 0));
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
    public static String first, second, third,fourth,fifth,sixth,seventh,day,date;
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

        SharedPreferences.Editor editor = mSettings.edit();
        intent = new Intent(settings.this, MainActivity.class);
        final Spinner spinner = (Spinner)findViewById(R.id.spinner);
        try {
            Log.e("ROCK", spinner.getSelectedItem().toString());
            LoadAllProducts load = new LoadAllProducts();
            loader = spinner.getSelectedItem().toString();
            load.execute(loader);


            selector = spinner.getSelectedItemPosition();
            editor.putInt("position",selector);



            intent.putExtra("group",loader);
            editor.putString("FIRST",loader);
            editor.apply();


            daysA.clear();
            daysB.clear();

            //pDialog.dismiss();
            this.finish();
            startActivity(intent);

        } catch (EnumConstantNotPresentException e){
            e.printStackTrace();
        }

    }


    static ArrayList<String> stringArrayList = new ArrayList<>();

    public void onDownload(View view) {
        try {
            if (c1501.isChecked()) stringArrayList.add("1501");
            if (c1401.isChecked()) stringArrayList.add("1401");
            if (c1301.isChecked()) stringArrayList.add("1301");
            if (c1601.isChecked()) stringArrayList.add("1601");
            LoadAllProducts loadAllProducts = new LoadAllProducts();
            for (int i = 0; i <= stringArrayList.size()-1; i++) {
                Log.e("ALL SHIT", stringArrayList.get(i));
                loadAllProducts.execute(stringArrayList.get(i)).get();
            }
        }catch (NullPointerException | ExecutionException | InterruptedException e){
            e.printStackTrace();
        }

        stringArrayList.clear();
    }


    class LoadAllProducts extends AsyncTask<String, String, String> {
        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(settings.this);
            pDialog.setMessage("Loading. Please wait...");
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


                         for (int i = 0; i <= productsA.length()-1; i++) {
                             JSONObject array = productsA.getJSONObject(i);
                             //Log.e("sda", params.get(0).toString());
                             first = array.getString("first");
                             second = array.getString("second");
                             third = array.getString("third");
                             fourth = array.getString("fourth");
                             fifth = array.getString("fifth");
                             sixth = array.getString("sixth");
                             seventh = array.getString("seventh");
                             date = array.getString("time");
                             day = array.getString("day");
                             daysA.add(new day(day, first, second, third, fourth, fifth,sixth,seventh,date));
                         }


                         //g = new groupss(daysA.get(0),daysA.get(1),daysA.get(2),daysA.get(3),daysA.get(4));

                         for (int i = 0; i <= productsB.length()-1; i++) {
                             JSONObject array = productsB.getJSONObject(i);
                             first = array.getString("first");
                             second = array.getString("second");
                             third = array.getString("third");
                             fourth = array.getString("fourth");
                             fifth = array.getString("fifth");
                             sixth = array.getString("sixth");
                             seventh = array.getString("seventh");
                             date = array.getString("time");
                             day = array.getString("day");
                             daysB.add(new day(day, first, second, third, fourth, fifth,sixth,seventh,date));

                         }

                         //ga = new groupss(daysB.get(0),daysB.get(1),daysB.get(2),daysB.get(3),daysB.get(4));
                             /*for (int x = 0; x <=array.length(); x++){

                                 //Log.e("shdaskj", xxx.toString());
                             }
                         }
                             /*for (int x = 0; x <=array.length(); i++){
                                 JSONObject xxx = array.getJSONObject(i);
                                 Log.e("shdaskj", xxx.toString());
                             }
                         }
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
            Gson gson = new Gson();
            loader = group;
            switch (loader){
                case "1401":
                    editor.putString(APP_PREFERENCES_1401A, gson.toJson(daysA));
                    editor.putString(APP_PREFERENCES_1401B, gson.toJson(daysB));
                    editor.putString(APP_PREFERENCES_fisrt, "yes");
                    editor.apply();
                    break;
                case "1501":
                    editor.putString(APP_PREFERENCES_1501A, gson.toJson(daysA));
                    editor.putString(APP_PREFERENCES_1501B, gson.toJson(daysB));
                    editor.putString(APP_PREFERENCES_fisrt, "yes");
                    editor.apply();
                    break;
                case "1301":
                    editor.putString(APP_PREFERENCES_1301A, gson.toJson(daysA));
                    editor.putString(APP_PREFERENCES_1301B, gson.toJson(daysB));
                    editor.putString(APP_PREFERENCES_fisrt, "yes");
                    editor.apply();
                    break;
                case "1601":
                    editor.putString(APP_PREFERENCES_1601A, gson.toJson(daysA));
                    editor.putString(APP_PREFERENCES_1601B, gson.toJson(daysB));
                    editor.putString(APP_PREFERENCES_fisrt, "yes");
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

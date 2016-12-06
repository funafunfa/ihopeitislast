package com.example.dmitriy.schedulenew;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Log.d("me","here");
        //new LoadAllProducts("3","PS_1401").execute();
        LoadAllProducts first = new LoadAllProducts();
        first.execute("1401A");
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
            pDialog.dismiss();

            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                }
            });

        }

    }
}

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
        new LoadAllProducts().execute();

    }

    private ProgressDialog pDialog;
    static JSONParser jParser = new JSONParser();
    static ArrayList<HashMap<String, String>> productsList;

    private static String url_all_products = "http://193.151.106.187/schedule/get_product.php";

    JSONArray products = null;
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PRODUCTS = "product";
    private static final String TAG_DAY = "day";
    private static final String TAG_FIRST = "first";
    private static final String TAG_SECOND = "second";
    private static final String TAG_THIRD = "third";
    private static final String TAG_FOURTH = "fourth";
    private static final String TAG_FIFTH = "fifth";
    private static final String TAG_SIXTH = "sixth";
    private static final String TAG_SEVENTH = "seventh";
    private static final String TAG_EDIT = "edit_time";
    private static final String TAG_GROUP = "group";

    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_STRING = "string";
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
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("day_num","3"));
            params.add(new BasicNameValuePair("group","PS_1401"));
            // getting JSON string from URL
            Log.e("damn","good");
            JSONObject json = jParser.makeHttpRequest(url_all_products, "GET", params);
            Log.e("damn","ok");
            // Check your log cat for JSON reponse
            Log.d("All Products: ", json.toString());

            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    Log.e("damn","puk");
                    // products found
                    // Getting Array of Products
                    products = json.getJSONArray("product");
                    Log.e("tag",products.toString());
                    // looping through All Products
                    //for (int i = 0; i < products.length(); i++) {
                        //JSONObject c = products.getJSONObject(i);
                         //String each json item in variable



                    //}
                }
                Log.e("damn","bAAFASFASF");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.e("damn","gzzz");
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

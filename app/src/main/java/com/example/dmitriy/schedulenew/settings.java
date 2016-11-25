package com.example.dmitriy.schedulenew;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.dmitriy.schedulenew.sub.JSONParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.dmitriy.bdconnectqrex.ProductCreater.productCreaters;

//import android.content.SharedPreferences;
//import android.support.v7.app.AppCompatActivity;
//import android.widget.ArrayAdapter;
//import android.widget.TextView;
//import com.google.gson.Gson;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonParser;
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.FileNotFoundException;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import static android.provider.Telephony.Mms.Part.FILENAME;

public class settings extends ListActivity {
    private ProgressDialog pDialog;
    static JSONParser jParser = new JSONParser();
    static ArrayList<HashMap<String, String>> productsList;

    private static String url_all_products = "http://193.151.106.187/schedule/get_product.php?day_num=3&group=PS_1401";
    JSONArray products = null;
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PRODUCTS = "products";
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


    //final String FILENAME = "array";
    //final String FILENAME_SECOND = "hashmap";
    //String filePath =  "/data/data/fileName.txt";
    //String filePath2 = "/data/data/fileName2.txt";
    //boolean deleted = false;
    //boolean created = false;


    JSONArray sweets = null;
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_STRING = "string";
    static SharedPreferences preference;
    static JSONObject json;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_products);
        productsList = new ArrayList<>();

        preference = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);


        new LoadAllProducts().execute();

    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }
    class LoadAllProducts extends AsyncTask<String, String, String> {
        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(AllProductsActivity.this);
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
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting JSON string from URL
            JSONObject json = jParser.makeHttpRequest(url_all_products, "GET", params);

            // Check your log cat for JSON reponse
            //Log.d("All Products: ", json.toString());

            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // products found
                    // Getting Array of Products
                    products = json.getJSONArray(TAG_PRODUCTS);

                    // looping through All Products
                    for (int i = 0; i < products.length(); i++) {
                        JSONObject c = products.getJSONObject(i);

                        // Storing each json item in variable
                        String day = c.getString(TAG_DAY);
                        String first = c.getString(TAG_FIRST);
                        String second = c.getString(TAG_SECOND);
                        String third = c.getString(TAG_THIRD);
                        String fourth = c.getString(TAG_FOURTH);
                        String fifth = c.getString(TAG_FIFTH);
                        String sixth = c.getString(TAG_SIXTH);
                        String seventh = c.getString(TAG_SEVENTH);
                        String edit_time = c.getString(TAG_EDIT);
                        String group_name = c.getString(TAG_GROUP);
                        Log.e("tag",products.toString());
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
                    /**
                     * Updating parsed JSON data into ListView
                     * */

                    //ArrayAdapter adapter = new ArrayAdapter(AllProductsActivity.this,R.layout.list_item, products_name);

                    // updating listview
                    //ListAdapter adapter = new ArrayAdapter<String>(AllProductsActivity.this, R);
                }
            });

        }

    }
}

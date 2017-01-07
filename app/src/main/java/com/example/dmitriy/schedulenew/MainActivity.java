package com.example.dmitriy.schedulenew;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.example.dmitriy.schedulenew.fragments.fri;
import com.example.dmitriy.schedulenew.fragments.mon;
import com.example.dmitriy.schedulenew.fragments.thu;
import com.example.dmitriy.schedulenew.fragments.tue;
import com.example.dmitriy.schedulenew.fragments.wed;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.dmitriy.schedulenew.settings.APP_PREFERENCES;
import static com.example.dmitriy.schedulenew.settings.APP_PREFERENCES_1401A;
import static com.example.dmitriy.schedulenew.settings.APP_PREFERENCES_1401B;
import static com.example.dmitriy.schedulenew.settings.APP_PREFERENCES_1501A;
import static com.example.dmitriy.schedulenew.settings.APP_PREFERENCES_1501B;
import static com.example.dmitriy.schedulenew.settings.APP_PREFERENCES_fisrt;
import static com.example.dmitriy.schedulenew.settings.mSettings;

public class MainActivity extends AppCompatActivity {

    //public static final String APP_PREFERENCES = "mysettings";
    private SectionsPagerAdapter mSectionsPagerAdapter;
    public static final String APP_PREFERENCES_PS_1401_1 = "PS_1401_1";
    private ViewPager mViewPager;
    public static String A, B;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        try{



        }catch (NullPointerException e){
            e.printStackTrace();
        }

        if(mSettings.contains("FIRST")) {
            backdata = mSettings.getString("FIRST","");
            Log.e("YABADADU", backdata);
        }else{
            backdata = "1401";
            Log.e("NOT YABADADU", backdata);
        }
        setContentView(R.layout.activity_main);
        this.setTitle(backdata);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);


        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();

        String dayOfTheWeek = sdf.format(d);
        if (savedInstanceState == null) {
            switch (dayOfTheWeek){
                case ("Monday"):case ("понедельник"):
                    mViewPager.setCurrentItem(0);
                    break;
                case ("Tuesday"):case ("вторник"):
                    mViewPager.setCurrentItem(1);
                    break;
                case ("Wednesday"):case ("среда"):
                    mViewPager.setCurrentItem(2);
                    break;
                case ("Thursday"):case ("четверг"):
                    mViewPager.setCurrentItem(3);
                    break;
                case ("Friday"):case ("пятница"):
                    mViewPager.setCurrentItem(4);
                    break;
                default:
                    mViewPager.setCurrentItem(0);
                    break;
            }
        }

        /*if(mSettings.getString("first", "").equals("yes")){
            Log.e("NO", "NOOOOOOOOOOOOOOOO");
        }else{
            Intent intent = new Intent(MainActivity.this, settings.class);
            startActivity(intent);

        }*/
    }
    public static String backdata = null;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(MenuItem item) {
        Intent intent = new Intent(MainActivity.this, settings.class);
        this.finish();
        startActivity(intent);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            switch (position){
                case 0:
                    return new mon();
                case 1:
                    return new tue();
                case 2:
                    return new wed();
                case 3:
                    return new thu();
                case 4:
                    return new fri();
            }
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 5 total pages.
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Monday";
                case 1:
                    return "Tuesday";
                case 2:
                    return "Wednesday";
                case 3:
                    return "Thursday";
                case 4:
                    return "Friday";
            }
            return null;
        }
    }
}

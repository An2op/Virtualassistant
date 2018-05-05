package com.example.pentagon.virtualassistant;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.pentagon.virtualassistant.fragment.TodayEventTab;
import com.example.pentagon.virtualassistant.fragment.RequestFragment;
import com.example.pentagon.virtualassistant.fragment.TicketInfo;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //  mTextMessage.setText(R.string.title_home);
                    toolbar.setTitle("Home");
                    fragment = new TodayEventTab();
                    loadFragment(fragment);

                    return true;
                case R.id.navigation_request:

                    toolbar.setTitle("TicketInfo");
                    fragment = new TicketInfo();

                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_container, fragment);
                    getSupportFragmentManager().popBackStackImmediate();
                    // transaction.addToBackStack(null);
                    transaction.commit();


                    return true;
                case R.id.navigation_plans:
                    toolbar.setTitle("Requests");
                    fragment = new RequestFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };
    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = getSupportActionBar();
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        loadFragment(new TodayEventTab());
        Utility.fab = findViewById(R.id.fab);
Utility.fab.setVisibility(View.VISIBLE);
        Utility.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
Intent i=new Intent(MainActivity.this,AddEventActivity.class);
startActivity(i);
finish();

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            // action with ID action_refresh was selected
//            case R.id.action_settings:
//
//            Intent dbmanager = new Intent(MainActivity.this,AndroidDatabaseManager.class);
//            startActivity(dbmanager);
//
//                break;
            case R.id.logout:

new Cabd(MainActivity.this).deleteAll();
                new SharedPreferenceClass(getSharedPreferences(SharedPreferenceClass.PROPERTY_USERNAME, Context.MODE_PRIVATE)).setPropertyUsername("");
                new SharedPreferenceClass(getSharedPreferences(SharedPreferenceClass.PROPERTY_PASSWORD, Context.MODE_PRIVATE)).setPropertyPassword("");
                new SharedPreferenceClass(getSharedPreferences(SharedPreferenceClass.PROPERTY_USERID, Context.MODE_PRIVATE)).setPropertyUserid("");
                Intent ii = new Intent(MainActivity.this, LoginActivity.class);
                ii.putExtra("type", "edit");

                startActivity(ii);


                finish();
                break;
       }

        return true;
    }
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        getSupportFragmentManager().popBackStack();
        // transaction.addToBackStack(null);
        transaction.commit();

    }

    public void getProfile() {



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();


    }
}

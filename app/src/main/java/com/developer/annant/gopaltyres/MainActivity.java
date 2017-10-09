package com.developer.annant.gopaltyres;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.developer.annant.gopaltyres.Drawer_Activities.AboutDeveloperDrawerActivity;
import com.developer.annant.gopaltyres.Drawer_Activities.ContactUsDrawerActivity;
import com.developer.annant.gopaltyres.Drawer_Activities.FeedbackDrawerActivity;
import com.developer.annant.gopaltyres.Drawer_Activities.ShopImageDrawerActivity;
import com.developer.annant.gopaltyres.Drawer_Activities.ShopInfoDrawerActivity;
import com.developer.annant.gopaltyres.Fragments.BikeFragment;
import com.developer.annant.gopaltyres.Fragments.CarFragment;
import com.developer.annant.gopaltyres.Fragments.MiniTruckFragment;
import com.developer.annant.gopaltyres.Fragments.TractorFragment;
import com.developer.annant.gopaltyres.Fragments.TruckFragment;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {//Need TO write a method to solve error

String TAG = "MAIN_ACTIVITY";
    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;
    AdView mAdView;

    //Above are Variable and Object Declaration
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // modified Drawer Code
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_main);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        // drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);  //Need TO write a method to solve error


        //Modified Code Ends
        // startActivity(new Intent(MainActivity.this,BikeFragment.class));


        //Start Tab Layout And AdMob Code
        viewPager = (ViewPager) findViewById(R.id.view_pagerid);
        viewPager.setAdapter(new CustomAdapter(getSupportFragmentManager(), getApplicationContext()));

        tabLayout = (TabLayout) findViewById(R.id.tabs_viewid);
        tabLayout.setupWithViewPager(viewPager);


        //Start AdView Code
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest request = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)        // All emulators
                .addTestDevice("8C8485E8FF86CDBA3F9E16C49523FAAA")  // An example device ID
                .build();

        mAdView.loadAd(request);


        //End TabLayout and AdMob code


      //Firebase Test Code
/*
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

*/










    }  // End Of onCreate Method


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_main);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }// You Do understand the Use of above method


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }


    //Below is the method we were going to use for correcting the errors//  Important

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        // Enter All your drawer calling code here
        // For example Fragments and Activity


        switch (item.getItemId()) {

           /* case R.id.nav_home:
                startActivity(new Intent(MainActivity.this, HomeDrawerActivity.class));
                break;

                */
            case R.id.nav_feedback:
                startActivity(new Intent(MainActivity.this, FeedbackDrawerActivity.class));
                break;
            case R.id.nav_contactUs:
                startActivity(new Intent(MainActivity.this, ContactUsDrawerActivity.class));
                break;
            case R.id.nav_shop_info:
                startActivity(new Intent(MainActivity.this, ShopInfoDrawerActivity.class));
                break;
            case R.id.nav_shop_image:
                startActivity(new Intent(MainActivity.this, ShopImageDrawerActivity.class));
                break;

            case R.id.about_dev:
                startActivity(new Intent(MainActivity.this, AboutDeveloperDrawerActivity.class));
                break;

            default:
                //  startActivity(new Intent(MainActivity.this, HomeDrawerActivity.class));
                break;
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_main);
        drawer.closeDrawer(GravityCompat.START);
        return true;


    }


    //Drawer Code Ends Here
////////////////////////////////////////////////////////////////////////////////////////////////////


    /// Start AdmoB functionss
    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }


    // Start TabLayout Code

    private class CustomAdapter extends FragmentPagerAdapter {

        private String fragments[] = {"Bike", "Car", "Truck", "Tractor", "MiniTruck"};


        public CustomAdapter(FragmentManager supportFragmentManager, Context applicationContext) {

            super(supportFragmentManager);
        }


        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new BikeFragment();

                case 1:
                    return new CarFragment();
                case 2:
                    return new TruckFragment();
                case 3:
                    return new TractorFragment();
                case 4:
                    return new MiniTruckFragment();
                default:
                    return null;//new FragmentFirst();

            }

        }

        @Override
        public int getCount() {

            return fragments.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragments[position];
        }

    }

    ////// End AdMob And TabLayout code Function in main


}






package com.uniqgrid.solarenergy.uniqgrid;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import Utils.BottomNavigationViewBehaviour;
import Utils.BottomNavigationViewHelper;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String mTextMessage;
    ImageView ivDrawerIcon;

    // Managing Clicks in Bottom Navigation Drawer
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            final android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            switch (item.getItemId()) {

                case  R.id.navigation_home :

                    transaction.replace(R.id.fragment_container, new HomeFragment());
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    transaction.commit();

                    return true;


                case  R.id.navigation_assets :

                    transaction.replace(R.id.fragment_container, new AssetsFragment());
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    transaction.commit();

                    return true;


                case  R.id.navigation_consumption :

                    transaction.replace(R.id.fragment_container, new ConsumptionFragment());
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    transaction.commit();

                    return true;


                case  R.id.navigation_solar :

                    transaction.replace(R.id.fragment_container, new SolarFragment());
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    transaction.commit();

                    return true;


             /*   case R.id.navigation_home:

                    if(fragmentManager.findFragmentByTag("3") != null) {
                        //if the fragment exists, show it.
                        fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_up, R.anim.fade_out).show(fragmentManager.findFragmentByTag("0")).commit();
                    } else {
                        //if the fragment does not exist, add it to fragment manager.
                        fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_up, R.anim.fade_out).add(R.id.fragment_container, new HomeFragment(), "0").commit();
                    }
                    return true;


                case R.id.navigation_consumption:

                    if(fragmentManager.findFragmentByTag("2") != null) {
                        //if the fragment exists, show it.
                        fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_up, R.anim.fade_out).show(fragmentManager.findFragmentByTag("2")).commit();
                    } else {
                        //if the fragment does not exist, add it to fragment manager.
                        fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_up, R.anim.fade_out).add(R.id.fragment_container, new ConsumptionFragment(), "2").commit();
                    }
                    return true;

                case R.id.navigation_solar:

                    if(fragmentManager.findFragmentByTag("3") != null) {
                        //if the fragment exists, show it.
                        fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_up, R.anim.fade_out).show(fragmentManager.findFragmentByTag("3")).commit();
                    } else {
                        //if the fragment does not exist, add it to fragment manager.
                        fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_up, R.anim.fade_out).add(R.id.fragment_container, new SolarFragment(), "3").commit();
                    }
                    return true;
                    */





            }
            // TODO: 14-01-2018  Change it to return false 
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    /*    // Setting toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle("Uniqgrid");
        }
*/

        // Side Navigation Bar
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ivDrawerIcon = (ImageView) findViewById(R.id.ivDrawerIcon);
        ivDrawerIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(Gravity.START);
            }
        });


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        // Bottom Navigation Bar
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Helper Class to disable shift mode in bottom navigation drawer when there are more than 3 items
        // Disabling shift mode using helper class
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        // Hiding Bottom navigation bar on scroll
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomNavigationView.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationViewBehaviour());

        // Select Home
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if(id == R.id.nav_profile){
            Intent intent = new Intent(MainActivity.this,ProfileActivity.class);
            startActivity(intent);
        }else if(id == R.id.nav_tickets){
            Intent intent = new Intent(MainActivity.this,TicketsActivity.class);
            startActivity(intent);
        }
/*
        if (id == R.id.nav_dashboard) {
            DashBoard dashBoardFragment = new DashBoard();
            transaction.replace(R.id.fragment_container, dashBoardFragment);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.commit();
        } else if (id == R.id.nav_trends) {
            Trends trendsFragment = new Trends();
            transaction.replace(R.id.fragment_container, trendsFragment);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.commit();

        } else if (id == R.id.nav_reports) {
            Reports reportsFragment = new Reports();
            transaction.replace(R.id.fragment_container, reportsFragment);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.commit();

        }else if (id == R.id.nav_editProfile) {
            Intent intent = new Intent(MainActivity.this,EditProfileActivity.class);
            startActivity(intent);
        }else if (id == R.id.nav_manage_est) {
            Intent intent = new Intent(MainActivity.this,ManageEstActivity.class);
            startActivity(intent);
        }
        */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}

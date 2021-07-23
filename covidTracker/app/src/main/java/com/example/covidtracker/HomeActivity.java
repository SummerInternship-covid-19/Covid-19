package com.example.covidtracker;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.example.covidtracker.CovidIndia.fragIndia.CovidAware;
import com.example.covidtracker.CovidIndia.fragIndia.CovidDetailIndia;
import com.example.covidtracker.CovidIndia.helplineindiaState.HelplineFrag;

import com.example.covidtracker.fragments.CountryFrag;
import com.example.covidtracker.fragments.CovidLive;
import com.example.covidtracker.fragments.Symptoms;
import com.example.covidtracker.fragments.ZoneFrag;

//import com.example.covidtracker.R;

public class HomeActivity extends AppCompatActivity  {


    BottomNavigationView bottomNavigationView;

    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;

    TextView textView, msg, world;
    FrameLayout containerFrag;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    SearchView searchView;

    //    AdView adView, adviewHome;
    Dialog myDialog, dialogConnection;

    //    TextView emailEvent;
    NetworkInfo activeNetworkInfo;
    ConnectivityManager connMgr;
    @Override
    public void onBackPressed() {


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set title

        alertDialogBuilder.setTitle("Exit");

        // set dialog message
        alertDialogBuilder
                .setMessage("Do you really want to exit?")

                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, close
                        // current activity
                        finish();
                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();


        // show it

        alertDialog.show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        viewInitilize();

        btmBarSetup();

//        adMobAdSetup();

        internetConnectivity();


        draweSetup();


        try {

            Bundle bundle = new Bundle();
            Fragment controlerFrag = new CovidLive();
            FragmentManager fragmentManager = getSupportFragmentManager();
            final FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.container, controlerFrag);

            controlerFrag.setArguments(bundle);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    transaction.commit();
                }
            }, 100);


        } catch (NullPointerException e) {
            e.printStackTrace();
        }


        searchView.setVisibility(View.GONE);


    }

    private  boolean internetConnectivity() {

        dialogConnection = new Dialog(HomeActivity.this);
        dialogConnection.setContentView(R.layout.network_info);


        connMgr = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connMgr != null) {
            activeNetworkInfo = connMgr.getActiveNetworkInfo();

            if (activeNetworkInfo != null) { // connected to the internet


                if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {

                    Toast.makeText(getApplicationContext(),"Connected Mobile Wi-Fi",Toast.LENGTH_LONG).show();

                } else {
                }
                if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {

                    Toast.makeText(getApplicationContext(),"Connected Mobile Data",Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getApplicationContext(),"No Internet ",Toast.LENGTH_LONG).show();
                }
            }else {
                dialogConnection.show();
                dialogConnection.setCanceledOnTouchOutside(false);

            }
        }
        return false;
    }



//    private void adMobAdSetup() {
//
//          myDialog=new Dialog(HomeActivity.this);
//
//
//        myDialog.setContentView(R.layout.about_app);
//
////        emailEvent=myDialog.findViewById(R.id.email);
////        adView=myDialog.findViewById(R.id.adView);
//
//        /*emailEvent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                //Toast.makeText(getApplicationContext(),"Email is : sonu.kalinjer@gmail.com",Toast.LENGTH_LONG).show();
//
//
//
//            }
//        });
//
//*/
//
//        adviewHome=findViewById(R.id.adViewHome);
//
//        MobileAds.initialize(getApplicationContext(), "ca-app-pub-9635440789104240~2584349168");
//        AdRequest adRequestOne = new AdRequest.Builder().build();
//        adviewHome.loadAd(adRequestOne);
//
//
//
//        Objects.requireNonNull(myDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//
//        MobileAds.initialize(getApplicationContext(), "ca-app-pub-9635440789104240~2584349168");
//        AdRequest adRequest = new AdRequest.Builder().build();
//        adView.loadAd(adRequest);
//
//
//    }

    private void draweSetup() {



        toolbar.setNavigationIcon(R.drawable.ic_menu_black_24dp);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @SuppressLint("RtlHardcoded")
            @Override
            public void onClick(View v) {


                drawerLayout.openDrawer(Gravity.LEFT);


            }
        });

        navigationView.setItemIconTintList(null);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {




                switch (item.getItemId()){


                    case R.id.exitFra:
                        onBackPressed();
                        break;
                    case R.id.home_btn:

                        try {
                            searchView.setVisibility(View.GONE);
                            Bundle bundle = new Bundle();
                            Fragment controlerFrag = new CovidLive();
                            FragmentManager fragmentManager =getSupportFragmentManager();
                            final FragmentTransaction transaction = fragmentManager.beginTransaction();
                            transaction.replace(R.id.container, controlerFrag);

                            controlerFrag.setArguments(bundle);
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @SuppressLint("SetTextI18n")
                                @Override
                                public void run() {
                                    transaction.commit();
                                    collapsingToolbarLayout.setTitle("COVID-19 Dashboard");
                                    textView.setText("Dashboard");
                                    world.setVisibility(View.VISIBLE);
                                    msg.setText("COVID-19 LIVE UPDATE");

                                    drawerLayout.closeDrawers();
                                }
                            }, 100);


                            collapsingToolbarLayout.setVisibility(View.VISIBLE);
                            textView.setVisibility(View.VISIBLE);
                            world.setVisibility(View.VISIBLE);
                            msg.setVisibility(View.VISIBLE);

                        }catch (NullPointerException e){
                            e.printStackTrace();
                        }

                        break;



                    case R.id.helpline:



                        try {
                            searchView.setVisibility(View.GONE);

                            textView.setText("Helpline India");

                            Fragment controlerFrag = new HelplineFrag();
                            FragmentManager fragmentManager =getSupportFragmentManager();
                            final FragmentTransaction transaction = fragmentManager.beginTransaction();
                            transaction.replace(R.id.container, controlerFrag);
                            world.setVisibility(View.GONE);
                            drawerLayout.closeDrawers();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {


                                    transaction.commit();
                                    textView.setVisibility(View.VISIBLE);

                                    collapsingToolbarLayout.setVisibility(View.VISIBLE);
                                    msg.setVisibility(View.VISIBLE);
                                    msg.setText("ALL   STATES   HELPLINE   NO.");
                                    collapsingToolbarLayout.setTitle("COVID-19 Helpline No.");

                                }
                            }, 150);



                        }catch (NullPointerException e){
                            e.printStackTrace();
                        }
                        break;



                    case R.id.zoneFra:
                        drawerLayout.closeDrawers();

                        try {
                            textView.setText("Zone");

                            searchView.setVisibility(View.VISIBLE);
                            searchView.setQueryHint("Search your District");
                            Fragment controlerFrag = new ZoneFrag();
                            FragmentManager fragmentManager =getSupportFragmentManager();
                            final FragmentTransaction transaction = fragmentManager.beginTransaction();
                            transaction.replace(R.id.container, controlerFrag);
                            world.setVisibility(View.GONE);
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    transaction.commit();
                                    textView.setVisibility(View.VISIBLE);

                                    collapsingToolbarLayout.setVisibility(View.VISIBLE);
                                    msg.setVisibility(View.VISIBLE);
                                    msg.setText("ALL   STATES   ZONE.");
                                    collapsingToolbarLayout.setTitle("Zone");


                                }
                            }, 150);



                        }catch (NullPointerException e){
                            e.printStackTrace();
                        }
                        break;




//                    case R.id.about:
//
//                        myDialog.show();
//
//                        break;

//                    case R.id.share:
//
//
//                        Intent intentShare=new Intent(Intent.ACTION_SEND);
//
//                        intentShare.setType("text/plain");
//
//                        String body="Download CovidInfo app to get all /n" +
//                                " worldwide live updates information/status of COVID-19 disease."+
//                                "And share this app to other, Who also get the live update.";
//
//                        String title="CovidInfo \n Share with";
//
//
//                        intentShare.putExtra(Intent.EXTRA_TEXT,body);
//                        startActivity(Intent.createChooser(intentShare,title));
//
//                        break;
                    case R.id.covid_avair:


                        try {
                            searchView.setVisibility(View.GONE);
                            Bundle bundle = new Bundle();
                            textView.setText("STAY HOME STAY SAFE");
                            textView.setTextSize(24);

                            Fragment controlerFrag = new CovidAware();
                            FragmentManager fragmentManager =getSupportFragmentManager();
                            final FragmentTransaction transaction = fragmentManager.beginTransaction();
                            transaction.replace(R.id.container, controlerFrag);
                            world.setVisibility(View.GONE);
                            controlerFrag.setArguments(bundle);
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {


                                    transaction.commit();
                                    textView.setVisibility(View.VISIBLE);

                                    collapsingToolbarLayout.setVisibility(View.VISIBLE);
                                    msg.setVisibility(View.VISIBLE);
                                    msg.setText("");
                                    collapsingToolbarLayout.setTitle("#IndiaFightsCorona");


                                    drawerLayout.closeDrawers();
                                }
                            }, 50);



                        }catch (NullPointerException e){
                            e.printStackTrace();
                        }

                        break;

                }



                return false;
            }
        });


    }

    private void btmBarSetup() {



        bottomNavigationView.setItemIconTintList(null);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.home:

                        //Toast.makeText(getApplicationContext(),"Home Clicked",Toast.LENGTH_LONG).show();



                        try {
                            searchView.setVisibility(View.GONE);
                            Bundle bundle = new Bundle();
                            Fragment controlerFrag = new CovidLive();
                            FragmentManager fragmentManager =getSupportFragmentManager();
                            final FragmentTransaction transaction = fragmentManager.beginTransaction();
                            transaction.replace(R.id.container, controlerFrag);

                            controlerFrag.setArguments(bundle);
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @SuppressLint("SetTextI18n")
                                @Override
                                public void run() {
                                    transaction.commit();
                                    collapsingToolbarLayout.setTitle("COVID-19 Dashboard");
                                    textView.setText("Dashboard");
                                    world.setVisibility(View.VISIBLE);
                                    msg.setText("COVID-19 LIVE UPDATE");
                                }
                            }, 100);

                            collapsingToolbarLayout.setVisibility(View.VISIBLE);
                            textView.setVisibility(View.VISIBLE);
                            world.setVisibility(View.VISIBLE);
                            msg.setVisibility(View.VISIBLE);
                        }catch (NullPointerException e){
                            e.printStackTrace();
                        }


                        break;
                    case R.id.country:

                        //Toast.makeText(getApplicationContext(),"Country Clicked",Toast.LENGTH_LONG).show();
                        searchView.setVisibility(View.VISIBLE);

                        try {

                            Bundle bundle = new Bundle();
                            Fragment controlerFrag = new CountryFrag();
                            FragmentManager fragmentManager =getSupportFragmentManager();
                            final FragmentTransaction transaction = fragmentManager.beginTransaction();
                            transaction.replace(R.id.container, controlerFrag);

                            controlerFrag.setArguments(bundle);
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @SuppressLint("SetTextI18n")
                                @Override
                                public void run() {
                                    transaction.commit();
                                    collapsingToolbarLayout.setTitle("COVID-19  Countries List");
                                    textView.setText("All Countries");
                                    msg.setText("COVID-19 LIVE UPDATE");
                                    world.setVisibility(View.GONE);
                                    searchView.setQueryHint("Search country");
                                    searchView.isDrawingCacheEnabled();
                                    searchView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);

                                }
                            }, 50);
                            collapsingToolbarLayout.setVisibility(View.VISIBLE);
                            textView.setVisibility(View.VISIBLE);
                            msg.setVisibility(View.VISIBLE);

                        }catch (NullPointerException e){
                            e.printStackTrace();
                        }
                        break;

                    case R.id.symptoms:


                        //Toast.makeText(getApplicationContext(),"Symptoms Clicked",Toast.LENGTH_LONG).show();

                        searchView.setVisibility(View.GONE);

                        try {

                            Bundle bundle = new Bundle();
                            Fragment controlerFrag = new Symptoms();
                            FragmentManager fragmentManager =getSupportFragmentManager();
                            final FragmentTransaction transaction = fragmentManager.beginTransaction();
                            transaction.replace(R.id.container, controlerFrag);

                            controlerFrag.setArguments(bundle);
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @SuppressLint("SetTextI18n")
                                @Override
                                public void run() {
                                    transaction.commit();
                                    collapsingToolbarLayout.setTitle("COVID-19 Symptoms");
                                    world.setVisibility(View.GONE);

                                    textView.setText("Symptoms");
                                    msg.setText("CORONA VIRUS SYMPTOMS");
                                }
                            }, 50);
                            collapsingToolbarLayout.setVisibility(View.VISIBLE);
                            textView.setVisibility(View.VISIBLE);

                            msg.setVisibility(View.VISIBLE);

                        }catch (NullPointerException e){
                            e.printStackTrace();
                        }
                        break;




                    case R.id.india:

                        try {

                            Bundle bundle = new Bundle();
                            Fragment covidFragIndia = new CovidDetailIndia();
                            FragmentManager fragmentManager =getSupportFragmentManager();
                            final FragmentTransaction transaction = fragmentManager.beginTransaction();
                            transaction.replace(R.id.container, covidFragIndia);
                            searchView.setVisibility(View.VISIBLE);
                            searchView.setQueryHint("Search state");

                            covidFragIndia.setArguments(bundle);
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @SuppressLint("SetTextI18n")
                                @Override
                                public void run() {
                                    transaction.commit();
                                    collapsingToolbarLayout.setTitle("COVID-19  India");
                                    textView.setText("India");
                                    world.setVisibility(View.GONE);


                                    msg.setText("COVID-19 INDIA LIVE UPDATE");


                                }
                            }, 50);

                            collapsingToolbarLayout.setVisibility(View.VISIBLE);
                            textView.setVisibility(View.VISIBLE);
                            world.setVisibility(View.VISIBLE);
                            msg.setVisibility(View.VISIBLE);

                        }catch (NullPointerException e){
                            e.printStackTrace();
                        }
                        break;


                }


                return false;
            }
        });
    }

    private void viewInitilize() {

        toolbar=findViewById(R.id.toolBar);
        bottomNavigationView=findViewById(R.id.bottom_nav);
        containerFrag=findViewById(R.id.container);

        collapsingToolbarLayout=findViewById(R.id.colapsLayout);
        world=findViewById(R.id.world);
        textView=findViewById(R.id.textName);
        msg=findViewById(R.id.text_two);
        searchView= findViewById(R.id.searchView);
//        setSupportActionBar(toolbar);

        navigationView=findViewById(R.id.navigationView);
        drawerLayout=findViewById(R.id.drawerLayout);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.top_itmebar,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}

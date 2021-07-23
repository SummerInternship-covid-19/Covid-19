package com.example.covidtracker.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.covidtracker.R;
import com.example.covidtracker.adapters.ZoneAdapter;
import com.example.covidtracker.adapters.ZoneModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZoneFrag extends Fragment {


    private ArrayList<ZoneModel> arrayList;

    private LottieAnimationView lottieAnimationView;

    private RecyclerView recyclerView;

    private SearchView searchView;

    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {


        view= inflater.inflate(R.layout.fragment_zone, container, false);
        recyclerView=view.findViewById(R.id.recyclerViewZone);
        searchView=getActivity().findViewById(R.id.searchView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setItemViewCacheSize(500);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        lottieAnimationView=view.findViewById(R.id.loti_anim_content);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                new loader().execute();

            }
        },100);




        return  view;


    }

    private void searchSetupState() {




        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                ArrayList<ZoneModel> result=new ArrayList<>();

                for (ZoneModel state:arrayList){

                    if (state.getDisName().toLowerCase().contains(newText) || state.getDisName().contains(newText) || state.getZone().contains(newText) ){

                        result.add(state);
                    }

                    ((ZoneAdapter) Objects.requireNonNull(recyclerView.getAdapter())).updates(result);


                }


                return false;
            }
        });

    }


    private void viewInit() {

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ZoneAdapter zoneAdapter=new ZoneAdapter(arrayList);
        recyclerView.setAdapter(zoneAdapter);


    }


    private void getZoneDataAPI() {

        String url="https://api.covid19india.org/zones.json";
                            arrayList=new ArrayList<>();

                            StringRequest stringRequest=new StringRequest(Request.Method.GET,
                                    url, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    Log.i("TAG", "onResponse : " + arrayList);
                                    if (response != null) {

                                        lottieAnimationView.setVisibility(View.GONE);
                                        Log.i("TAG", "onResponse : " + response);

                                        try {
                                            JSONObject o = new JSONObject(response);
                                            JSONArray arr = o.getJSONArray("zones");

                                            for (int i = 0; i <arr.length(); i++) {
                                                JSONObject data = arr.getJSONObject(i);
                                                arrayList.add(new ZoneModel(data.getString("zone"),
                                    data.getString("state"),
                                    data.getString("district")));

                        }

                    } catch (JSONException r) {
                        r.printStackTrace();

                        Toast.makeText(getContext(),""+r,Toast.LENGTH_LONG).show();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                lottieAnimationView.setVisibility(View.GONE);

                Log.i("TAG", "onErrorResponse : " + error);
                Toast.makeText(getActivity(),""+error,Toast.LENGTH_LONG).show();

            }
        });
        Volley.newRequestQueue(Objects.requireNonNull(getActivity())).add(stringRequest);

        Log.i("tag", "onErrorResponse : " + stringRequest);

    }



    public class loader extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {

            getZoneDataAPI();

            searchSetupState();
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            viewInit();

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

}

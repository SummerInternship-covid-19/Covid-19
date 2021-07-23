package com.example.covidtracker.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.covidtracker.R;
import com.example.covidtracker.adapters.CountryListAdapter;
import com.example.covidtracker.models.CovidCountryDetail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class CountryFrag extends Fragment {



    private RecyclerView recyclerView;

    private ProgressBar progressBar;

    private ArrayList<CovidCountryDetail> arrayList;
    private SearchView searchView;
    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_country, container, false);





        getDataApi();
        recyclerView=view.findViewById(R.id.recyclerViewId);
        progressBar=view.findViewById(R.id.progressBar);

        searchView=((AppCompatActivity) Objects.requireNonNull(getActivity())).findViewById(R.id.searchView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



        searchViewSetup();





        return view;
    }

    private void searchViewSetup() {


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                ArrayList<CovidCountryDetail> result=new ArrayList<>();

                for (CovidCountryDetail countryDetail:arrayList){

                    if (countryDetail.getmCovidCountry().toLowerCase().contains(newText) || countryDetail.getmCovidCountry().contains(newText)  ){

                        result.add(countryDetail);
                    }

                    ((CountryListAdapter) Objects.requireNonNull(recyclerView.getAdapter())).update(result);



                }




                return false;
            }
        });

    }

    private void viewInit() {




        CountryListAdapter countryListAdapter=new CountryListAdapter(arrayList);
        recyclerView.setAdapter(countryListAdapter);

    }


    private void getDataApi() {

        String url="https://akashraj.tech/corona/api";
        arrayList=new ArrayList<>();



        StringRequest stringRequest=new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("TAG", "onResponse : " + arrayList);
                if (response != null) {

                    progressBar.setVisibility(View.GONE);

                    Log.i("TAG", "onResponse : " + response);


                    try {
                        JSONObject o = new JSONObject(response);
                        JSONArray arr = o.getJSONArray("countries_stat");


                        for (int i = 0; i <arr.length(); i++) {
                            JSONObject data = arr.getJSONObject(i);
                            arrayList.add(new CovidCountryDetail(data.getString("country_name"), data.getString("cases")));


                        }viewInit();


                    } catch (JSONException r) {
                        r.printStackTrace();

                        Toast.makeText(getContext(),""+r,Toast.LENGTH_LONG).show();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressBar.setVisibility(View.GONE);

                Log.i("TAG", "onErrorResponse : " + error);
                Toast.makeText(getActivity(),""+error,Toast.LENGTH_LONG).show();

            }
        });
        Volley.newRequestQueue(Objects.requireNonNull(getActivity())).add(stringRequest);

        Log.i("tag", "onErrorResponse : " + stringRequest);

    }


}

package com.example.covidtracker.CovidIndia.fragIndia;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.covidtracker.CovidIndia.DistrictCases.DistrictDetailAdapter;
import com.example.covidtracker.CovidIndia.DistrictCases.DistrictDetails;
import com.example.covidtracker.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class CityCases extends Fragment {




    View view;
    private RecyclerView recyclerView;

    private ArrayList<DistrictDetails> arrayList;
    private DistrictDetails details;

    private String stateName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_city_cases, container, false);




        Bundle bundle=this.getArguments();

        if (bundle != null) {
            stateName = bundle.getString("title");


        }

        viewInit();
        getApiDistrictData();

        adapterSetup();


        return view;
    }

    private void adapterSetup() {

        DistrictDetailAdapter districtDetails=new DistrictDetailAdapter(getActivity(),arrayList);
        recyclerView.setAdapter(districtDetails);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    private void getApiDistrictData() {


        String url="https://api.covid19india.org/v2/state_district_wise.json";
        arrayList=new ArrayList<>();





       /* ttcProgress.setVisibility(View.VISIBLE);
        stcProgress.setVisibility(View.VISIBLE);
        dsProgress.setVisibility(View.VISIBLE);*/

        StringRequest stringRequest=new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("TAG", "onResponse : " + arrayList);
                if (response != null) {

                    /*stcProgress.setVisibility(View.GONE);
                    ttcProgress.setVisibility(View.GONE);
                    dsProgress.setVisibility(View.GONE);*/



                    Log.i("TAG", "onResponse : " + response);


                    try {


                        JSONObject o = new JSONObject(response);



                        JSONArray jsonArray=o.getJSONArray("districtData");


                            for (int i = 0; i <jsonArray.length(); i++) {
                                JSONObject data = jsonArray.getJSONObject(i).getJSONObject("districtData");
                                arrayList.add(new DistrictDetails(data.getString("district"), data.getString("confirmed")));


                        }adapterSetup();

                    } catch (JSONException r) {
                        r.printStackTrace();
                        Toast.makeText(getActivity(),""+r,Toast.LENGTH_LONG).show();

                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("TAG", "onErrorResponse : " + error);
                Toast.makeText(getActivity(),""+error,Toast.LENGTH_LONG).show();

            }
        });
        Volley.newRequestQueue(Objects.requireNonNull(getActivity())).add(stringRequest);

        Log.i("tag", "onErrorResponse : " + stringRequest);

    }

    private void viewInit() {

        recyclerView=view.findViewById(R.id.recyclerCity);


    }
}

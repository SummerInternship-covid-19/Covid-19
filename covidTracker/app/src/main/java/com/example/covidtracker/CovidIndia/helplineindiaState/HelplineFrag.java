package com.example.covidtracker.CovidIndia.helplineindiaState;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.covidtracker.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class HelplineFrag extends Fragment {



    private RecyclerView recyclerView;

    private View view;
    private ArrayList<HelplineDetails> arrayList;
    private ProgressBar progressBar;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_helpline, container, false);




        viewInit();
        adapterSetup();
        getApiContact();
        return  view;

    }

    private void adapterSetup() {

        HelplineAdapterInd helplineAdapter=new HelplineAdapterInd(arrayList);
        recyclerView.setAdapter(helplineAdapter);

    }

    private void getApiContact() {



        String url="https://api.rootnet.in/covid19-in/contacts";
        arrayList=new ArrayList<>();


        progressBar.setVisibility(View.VISIBLE);

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
                        JSONArray arr = o.getJSONObject("data").getJSONObject("contacts").getJSONArray("regional");


                        for (int i = 0; i <arr.length(); i++) {
                            JSONObject data = arr.getJSONObject(i);
                            arrayList.add(new HelplineDetails(data.getString("number"), data.getString("loc")));


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


        recyclerView=view.findViewById(R.id.recyclerViewHelp);
        progressBar=view.findViewById(R.id.progressHelp);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }
}

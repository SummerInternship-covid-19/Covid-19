package com.example.covidtracker.CovidIndia.fragIndia;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.covidtracker.CovidIndia.stateListIndia.StateModel;
import com.example.covidtracker.CovidIndia.stateListIndia.StateListAdapter;
import com.example.covidtracker.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class CovidDetailIndia extends Fragment {




    private TextView totalCaseInd;
    private TextView totalDeathInd;
    private TextView totalRecoveredInd;
    private TextView foreignerCase;
    private TextView totalConfirmInd;
    private TextView lastUpdate;
    private TextView lastUpdateTest,totalIndiVidual,totalTested,totalPositive;
    private ProgressBar ttcProgress,stcProgress,dsProgress;
    private RecyclerView recyclerView;
    private ArrayList<StateModel> arrayList;
    private CardView dbCard,ttcCard;
    private SearchView searchView;
    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_covid_detail_india, container, false);

        viewInit();

        getDataApi();

        getCovidTestApi();
        getStateDataApi();

        searchSetupState();



        return view;


    }

    private void searchSetupState() {




        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                ttcCard.setVisibility(View.VISIBLE);
                dbCard.setVisibility(View.VISIBLE);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                ttcCard.setVisibility(View.GONE);
                dbCard.setVisibility(View.GONE);


                ArrayList<StateModel> result=new ArrayList<>();

                for (StateModel state:arrayList){

                    if (state.getStateName().toLowerCase().contains(newText) || state.getStateName().contains(newText)  ){

                        result.add(state);
                    }

                    ((StateListAdapter) Objects.requireNonNull(recyclerView.getAdapter())).updates(result);



                    if (newText.length()==0){
                        ttcCard.setVisibility(View.VISIBLE);
                        dbCard.setVisibility(View.VISIBLE);
                    }


                }






                return false;
            }
        });



    }

    private void recyclerViewAdapt() {

        StateListAdapter stateListAdapter=new StateListAdapter(getActivity(),arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(stateListAdapter);
    }

  private void getStateDataApi() {





        String url="https://api.rootnet.in/covid19-in/stats/latest";
        arrayList=new ArrayList<>();


        ttcProgress.setVisibility(View.VISIBLE);
      stcProgress.setVisibility(View.VISIBLE);
      dsProgress.setVisibility(View.VISIBLE);

        StringRequest stringRequest=new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("TAG", "onResponse : " + arrayList);
                if (response != null) {

                    stcProgress.setVisibility(View.GONE);
                    ttcProgress.setVisibility(View.GONE);
                    dsProgress.setVisibility(View.GONE);


                    Log.i("TAG", "onResponse : " + response);


                    try {
                        JSONObject o = new JSONObject(response);
                        JSONArray arr = o.getJSONObject("data").getJSONArray("regional");

                        for (int i = 0; i <arr.length(); i++) {
                            JSONObject data = arr.getJSONObject(i);
                          arrayList.add(new StateModel(data.getString("loc"), data.getString("totalConfirmed")));


                        }recyclerViewAdapt();


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

    private void getCovidTestApi() {

        RequestQueue request= Volley.newRequestQueue(Objects.requireNonNull(getActivity()));

        String url="https://api.rootnet.in/covid19-in/stats/testing/latest";
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {

                    JSONObject jsonObject = new JSONObject(response).getJSONObject("data");
                    JSONObject lastUptdJson = new JSONObject(response);

                    totalTested.setText(jsonObject.getString("totalSamplesTested"));
                    totalIndiVidual.setText(jsonObject.getString("totalIndividualsTested"));
                    totalPositive.setText(jsonObject.getString("totalPositiveCases"));
                    lastUpdateTest.setText(lastUptdJson.getString("lastOriginUpdate"));


                } catch (JSONException e) {

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity(),""+error,Toast.LENGTH_LONG).show();

                Log.i(TAG,error.toString());

            }
        });
        request.add(stringRequest);


    }

    private void getDataApi() {
        RequestQueue request= Volley.newRequestQueue(Objects.requireNonNull(getActivity()));

        String url="https://api.rootnet.in/covid19-in/stats/latest";
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {

                    JSONObject jsonObject = new JSONObject(response).getJSONObject("data").getJSONObject("summary");

                    JSONObject jsonLastUpdate = new JSONObject(response);

                    totalCaseInd.setText(jsonObject.getString("total"));
                    totalDeathInd.setText(jsonObject.getString("deaths"));
                    totalConfirmInd.setText(jsonObject.getString("confirmedCasesIndian"));

                    foreignerCase.setText(jsonObject.getString("confirmedCasesForeign"));
                    totalRecoveredInd.setText(jsonObject.getString("discharged"));
                    lastUpdate.setText(jsonLastUpdate.getString("lastOriginUpdate"));






                } catch (JSONException e) {

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity(),""+error,Toast.LENGTH_LONG).show();

                Log.i(TAG,error.toString());

            }
        });
        request.add(stringRequest);

    }

    private void viewInit() {

        totalCaseInd=view.findViewById(R.id.totalCasesInd);
        totalDeathInd=view.findViewById(R.id.totalDethInd);
        totalRecoveredInd=view.findViewById(R.id.totalRecoveredInd);
        totalConfirmInd=view.findViewById(R.id.totalCaseConfirmIndian);
        foreignerCase=view.findViewById(R.id.totalCaseConfirmForner);
        lastUpdate=view.findViewById(R.id.lastUpdate);

        lastUpdateTest=view.findViewById(R.id.lastUpdt);
        totalPositive=view.findViewById(R.id.totalPositive);
        totalTested=view.findViewById(R.id.totalSample);
        totalIndiVidual=view.findViewById(R.id.totalIndividuals);

        recyclerView=view.findViewById(R.id.recyclerViewInd);

        stcProgress=view.findViewById(R.id.stateProgressBar);
        ttcProgress=view.findViewById(R.id.ttcProgress);
        dsProgress=view.findViewById(R.id.dsProgress);

        searchView= Objects.requireNonNull(getActivity()).findViewById(R.id.searchView);
        dbCard=view.findViewById(R.id.dashboardViewCard);
        ttcCard=view.findViewById(R.id.ttcViewCard);



    }
}

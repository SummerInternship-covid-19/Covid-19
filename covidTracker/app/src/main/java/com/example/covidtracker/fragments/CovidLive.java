package com.example.covidtracker.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.covidtracker.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

import static android.content.ContentValues.TAG;

public class CovidLive extends Fragment {




    private View view;

    private TextView totalConfirmed,totalDeath,totalRecovered,queFirst,ansFirst,queTwo,ansTwo;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_covid_live, container, false);




        viewInit();

        transLator();

        getDataApi();

        return view;
    }

    private void transLator() {



//        ansFirst.setOnLongClickListener(new View.OnLongClickListener() {
//            @SuppressLint("SetTextI18n")
//            @Override
//            public boolean onLongClick(View v) {
//
//                queFirst.setText("COVID-19 क्या है?");
//                ansFirst.setText("COVID-19 सबसे हाल ही में खोजे गए कोरोना वायरस के कारण होने वाला संक्रामक रोग है। यह नया वायरस और बीमारी दिसंबर 2019 में चीन के वुहान में फैलने से पहले अज्ञात थी।");
//
//
//                return false;
//            }
//        });

        ansFirst.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                queFirst.setText("What is COVID-19?");


                ansFirst.setText("COVID-19 is the infectious disease caused by the most recently discovered corona virus. This new virus and disease were unknown before the outbreak began in Wuhan, China, in December 2019.");

            }
        });



//        ansTwo.setOnLongClickListener(new View.OnLongClickListener() {
//            @SuppressLint("SetTextI18n")
//            @Override
//            public boolean onLongClick(View v) {
//
//
//                ansTwo.setText("कोरोना वायरस वायरस का एक बड़ा परिवार है जो जानवरों या मनुष्यों में बीमारी का कारण हो सकता है। मनुष्यों में, कई कोरोनवीरस को सामान्य सर्दी से लेकर अधिक गंभीर बीमारियों जैसे मध्य पूर्व श्वसन श्वसन सिंड्रोम (MERS) और गंभीर तीव्र श्वसन सिंड्रोम (SARS) के कारण श्वसन संक्रमण का कारण माना जाता है। सबसे हाल ही में खोजे गए कोरोनावायरस का कारण कोरोनोवायरस रोग COVID-19 है");
//
//                queTwo.setText("कोरोना वायरस क्या है?");
//                return false;
//            }
//        });

        ansTwo.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                queTwo.setText("What is corona virus");


                ansTwo.setText("Corona viruses are a large family of viruses which may cause illness in animals or humans.  In humans, several coronaviruses are known to cause respiratory infections ranging from the common cold to more severe diseases such as Middle East Respiratory Syndrome (MERS) and Severe Acute Respiratory Syndrome (SARS). The most recently discovered coronavirus causes coronavirus disease COVID-19");

            }
        });

    }

    private void getDataApi() {

        RequestQueue request= Volley.newRequestQueue(Objects.requireNonNull(getActivity()));

        String url="https://akashraj.tech/corona/api";
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {

                    JSONObject jsonObject = new JSONObject(response).getJSONObject("world_total");


                    totalConfirmed.setText(jsonObject.getString("total_cases"));
                    totalDeath.setText(jsonObject.getString("total_deaths"));

                    totalRecovered.setText(jsonObject.getString("total_recovered"));


                } catch (JSONException e) {

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                Log.i(TAG,error.toString());

            }
    });
        request.add(stringRequest);


    }

    @SuppressLint("CutPasteId")
    private void viewInit() {


        totalConfirmed=view.findViewById(R.id.confirmCase);
        totalDeath=view.findViewById(R.id.totalDeath);
        queFirst=view.findViewById(R.id.queText);
        totalRecovered=view.findViewById(R.id.recovered);
        ansFirst=view.findViewById(R.id.ansFirst);
        ansTwo=view.findViewById(R.id.ansTextOneTwo);
        queTwo=view.findViewById(R.id.queTextTwo);


    }
}

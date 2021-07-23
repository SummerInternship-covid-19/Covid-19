package com.example.covidtracker.CovidIndia.fragIndia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.covidtracker.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CovidAware extends Fragment {

    public CovidAware() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_covid_aware, container, false);
    }
}

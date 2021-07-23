package com.example.covidtracker.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidtracker.R;
import com.example.covidtracker.models.CovidCountryDetail;

import java.util.ArrayList;


public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.ViewHolder>  {

    private ArrayList<CovidCountryDetail> detailArrayList;

    public CountryListAdapter( ArrayList<CovidCountryDetail> detailArrayList) {
        this.detailArrayList = detailArrayList;
    }
   public void update(ArrayList<CovidCountryDetail> countryDetails){

        detailArrayList=new ArrayList<>();
        detailArrayList.addAll(countryDetails);
        notifyDataSetChanged();

   }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.country_cases,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        CovidCountryDetail countryDetail=detailArrayList.get(position);

        holder.totalCases.setText(countryDetail.getmCase());
        holder.countryName.setText(countryDetail.getmCovidCountry());



    }

    @Override
    public int getItemCount() {
        return detailArrayList != null ? detailArrayList.size() : 0;
    }



    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView countryName,totalCases;
        ViewHolder(@NonNull View itemView) {
            super(itemView);



            countryName=itemView.findViewById(R.id.countryName);
            totalCases=itemView.findViewById(R.id.countryCases);


        }
    }
}
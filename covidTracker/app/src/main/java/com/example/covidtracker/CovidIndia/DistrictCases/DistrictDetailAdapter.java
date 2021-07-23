package com.example.covidtracker.CovidIndia.DistrictCases;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidtracker.R;

import java.util.ArrayList;


public class DistrictDetailAdapter extends RecyclerView.Adapter<DistrictDetailAdapter.ViewHolder> {

    private ArrayList<DistrictDetails> districtArrayList;
    private Context context;
    public DistrictDetailAdapter(FragmentActivity activity, ArrayList<DistrictDetails> detailArrayList) {
        this.districtArrayList = detailArrayList;
        this.context=activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.distic_cases,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {


        DistrictDetails state=districtArrayList.get(position);

        holder.districtName.setText((CharSequence) state.getDistrictName());
        holder.districtCases.setText(state.getDistrictCase());


    }

    @Override
    public int getItemCount() {
        return districtArrayList != null ? districtArrayList.size() : 0;
    }

    /*public void updates(ArrayList<StateModel> result) {

        detailArrayList=new ArrayList<>();
        detailArrayList.addAll(result);
        notifyDataSetChanged();

    }*/


    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView districtName;

        TextView districtCases;


        ViewHolder(@NonNull View itemView) {
            super(itemView);



            districtName=itemView.findViewById(R.id.districtName);
            districtCases=itemView.findViewById(R.id.districtCases);



        }
    }
}
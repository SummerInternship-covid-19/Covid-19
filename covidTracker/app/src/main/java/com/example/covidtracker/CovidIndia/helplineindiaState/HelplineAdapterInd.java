package com.example.covidtracker.CovidIndia.helplineindiaState;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidtracker.R;

import java.util.ArrayList;


public class HelplineAdapterInd extends RecyclerView.Adapter<HelplineAdapterInd.ViewHolder>  {

    private ArrayList<HelplineDetails> detailArrayList;

    HelplineAdapterInd( ArrayList<HelplineDetails> detailArrayList) {
        this.detailArrayList = detailArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.state_helpline,parent,false);


        return new ViewHolder(view);
    }
    public void update(ArrayList<HelplineDetails> results){

        detailArrayList=new ArrayList<>();
        detailArrayList.addAll(results);
        notifyDataSetChanged();

    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        HelplineDetails state=detailArrayList.get(position);

        holder.stateName.setText(state.getStateName());
       holder.contactNo.setText(state.getNo());




    }

    @Override
    public int getItemCount() {
        return detailArrayList != null ? detailArrayList.size() : 0;
    }

    public void updates(ArrayList<HelplineDetails> result) {

        detailArrayList=new ArrayList<>();
        detailArrayList.addAll(result);
        notifyDataSetChanged();

    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView stateName,contactNo;


        ViewHolder(@NonNull View itemView) {
            super(itemView);



            stateName=itemView.findViewById(R.id.stateNameTwo);
            contactNo=itemView.findViewById(R.id.helpNo);


        }
    }
}
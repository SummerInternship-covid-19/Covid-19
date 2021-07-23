package com.example.covidtracker.CovidIndia.stateListIndia;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.example.covidtracker.CovidIndia.fragIndia.CityCases;
import com.example.covidtracker.R;

import java.util.ArrayList;


public class StateListAdapter extends RecyclerView.Adapter<StateListAdapter.ViewHolder> {

    private ArrayList<StateModel> detailArrayList;
    private Context context;
    private TextView textView,world,msg;

    private SearchView searchView;

    private CollapsingToolbarLayout collapsingToolbarLayout;



    public StateListAdapter(FragmentActivity activity, ArrayList<StateModel> detailArrayList) {
        this.detailArrayList = detailArrayList;
        this.context=activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.state_cases,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {


        StateModel state=detailArrayList.get(position);

        holder.stateCases.setText(state.getStateData());
        holder.stateName.setText(state.getStateName());




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String stateName=detailArrayList.get(position).getStateName();
                Toast.makeText(context,stateName,Toast.LENGTH_LONG).show();







                try {
                    searchView.setVisibility(View.GONE);
                    Bundle bundle = new Bundle();
                    bundle.putString("title",stateName);
                    Fragment cityCasesFrag = new CityCases();
                    FragmentManager fragmentManager = (((AppCompatActivity) context).getSupportFragmentManager());
                    final FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.container, cityCasesFrag);

                    cityCasesFrag.setArguments(bundle);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void run() {
                            transaction.commit();
                            collapsingToolbarLayout.setCollapsedTitleGravity(-20);
                            collapsingToolbarLayout.setTitle("All District "+stateName);


                            textView.setTextSize(20);
                           textView.setText(stateName);

                            world.setVisibility(View.GONE);
                            msg.setText(stateName+" COVID-19 All DISTRICT LIVE UPDATE");


                        }
                    }, 50);


                    collapsingToolbarLayout.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.VISIBLE);
                    world.setVisibility(View.VISIBLE);
                    msg.setVisibility(View.VISIBLE);

                }catch (NullPointerException e){
                    e.printStackTrace();
                }



            }
        });




    }

    @Override
    public int getItemCount() {
        return detailArrayList != null ? detailArrayList.size() : 0;
    }

    public void updates(ArrayList<StateModel> result) {

        detailArrayList=new ArrayList<>();
        detailArrayList.addAll(result);
        notifyDataSetChanged();

    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView stateName,stateCases;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);



            stateName=itemView.findViewById(R.id.stateName);
            stateCases=itemView.findViewById(R.id.stateCases);

            searchView=((AppCompatActivity)context).findViewById(R.id.searchView);
            collapsingToolbarLayout=((AppCompatActivity)context).findViewById(R.id.colapsLayout);

            world=((AppCompatActivity)context).findViewById(R.id.world);
            textView=((AppCompatActivity)context).findViewById(R.id.textName);
            msg=((AppCompatActivity)context).findViewById(R.id.text_two);


        }
    }
}
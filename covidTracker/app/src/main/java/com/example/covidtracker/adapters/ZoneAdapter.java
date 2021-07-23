package com.example.covidtracker.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidtracker.R;

import java.util.ArrayList;


public class ZoneAdapter extends RecyclerView.Adapter<ZoneAdapter.ViewHolder>  {

    private ArrayList<ZoneModel> zoneArrayList;

    public ZoneAdapter(ArrayList<ZoneModel> detailArrayList) {
        this.zoneArrayList = detailArrayList;
    }
   public void updates(ArrayList<ZoneModel> countryDetails){

       zoneArrayList=new ArrayList<>();
       zoneArrayList.addAll(countryDetails);
        notifyDataSetChanged();

   }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.area_zone,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        ZoneModel zoneModel=zoneArrayList.get(position);

        holder.disName.setText(zoneModel.getDisName());
        holder.stName.setText(zoneModel.getStName());
        //holder.lastUpdate.setText(zoneModel.getLastUpdate());
        holder.zone.setText(zoneModel.getZone());


        String zons=holder.zone.getText().toString();

        if (zons.matches("Green")){

            holder.zoneImg.setImageResource(R.drawable.green_zone);
        }else if(zons.matches("Red")){
            holder.zoneImg.setImageResource(R.drawable.red_zone);

        }else if(zons.matches("Orange")){
            holder.zoneImg.setImageResource(R.drawable.orange_zone);

        }


    }

    @Override
    public int getItemCount() {
        return zoneArrayList != null ? zoneArrayList.size():0;
    }



   public static class ViewHolder extends RecyclerView.ViewHolder {

       public TextView disName,stName,zone,lastUpdate;
        ImageView zoneImg;
        ViewHolder(@NonNull View itemView) {
            super(itemView);



            disName=itemView.findViewById(R.id.disName);
            stName=itemView.findViewById(R.id.stName);

            zone=itemView.findViewById(R.id.zone);
            zoneImg=itemView.findViewById(R.id.zoneImg);


        }
    }
}
package com.example.weatheappv3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ClimateAdapter extends RecyclerView.Adapter<ClimateViewHolder> {

    ArrayList<ClimateDataList> list;
    Context context;

    public ClimateAdapter() {
    }

    public ClimateAdapter(ArrayList<ClimateDataList> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ClimateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ClimateViewHolder(LayoutInflater.from(context).inflate(R.layout.climatelayout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ClimateViewHolder holder, int position) {

       final ClimateDataList climateDataList = list.get(position);
       holder.tvmonth.setText(climateDataList.getMonth());
       holder.tvtemp.setText(climateDataList.getTemp());
       holder.tvrain.setText(climateDataList.getRain());
       holder.tvpress.setText(climateDataList.getPressure());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

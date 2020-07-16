package com.example.weatheappv3;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ClimateViewHolder extends RecyclerView.ViewHolder {
    TextView tvmonth, tvtemp, tvrain, tvpress;
    LinearLayout linearLayout;

    public ClimateViewHolder(@NonNull View itemView) {
        super(itemView);
        tvmonth = itemView.findViewById(R.id.tvm);
        tvtemp = itemView.findViewById(R.id.tvtemp);
        tvrain = itemView.findViewById(R.id.tvrain);
        tvpress = itemView.findViewById(R.id.tvpress);
        linearLayout = itemView.findViewById(R.id.cll1);
    }
}

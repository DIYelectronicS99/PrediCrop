package com.example.weatheappv3;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RowViewHolder extends RecyclerView.ViewHolder {

    TextView id, name;
    LinearLayout linearLayout;
    public RowViewHolder(@NonNull View itemView) {
        super(itemView);
        id = itemView.findViewById(R.id.tv1);
        name = itemView.findViewById(R.id.tv2);
        linearLayout = itemView.findViewById(R.id.ll1);

    }
}

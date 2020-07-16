package com.example.weatheappv3;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class RowAdapter extends RecyclerView.Adapter<RowViewHolder> {

    ArrayList<RowDatalist> list;
    Context context;
    String cname;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    //



    public RowAdapter() {
    }

    public RowAdapter(ArrayList<RowDatalist> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new RowViewHolder(LayoutInflater.from(context).inflate(R.layout.regionlayout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final RowViewHolder holder, final int position) {

        final RowDatalist newlist = list.get(position);
        holder.id.setText(newlist.getId());
        holder.name.setText(newlist.getName());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, position, Toast.LENGTH_SHORT);
                Log.i("clicked" , String.valueOf(position));
                Intent intent = new Intent(context.getApplicationContext(), ClimateDataActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                intent.putExtra("ID",list.get(position).getId());
                //intent.putExtra("Cropname", getCrop());
                context.getApplicationContext().startActivity(intent);

            }
        });




    }

    @Override
    public int getItemCount() {
        return list.size();
    }




}

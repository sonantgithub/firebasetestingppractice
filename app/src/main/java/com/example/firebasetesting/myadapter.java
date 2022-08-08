package com.example.firebasetesting;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class myadapter extends RecyclerView.Adapter<myadapter.ViewHolder> {
    ArrayList<model> mismatchedList;
    int resource;
    private LayoutInflater Inflater;
    Context cxnt;

    public myadapter(Context context, int resource, ArrayList<model> mismatchedList) {
        this.Inflater = LayoutInflater.from(context);
        this.mismatchedList = mismatchedList;
        this.resource = resource;
        this.cxnt = context;
    }

    @Override
    public myadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = (View) LayoutInflater.from(cxnt).inflate(R.layout.singlerow, parent, false);
        return new myadapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final myadapter.ViewHolder holder, final int position) {

        final model sm = mismatchedList.get(position);
        holder.aid.setText(sm.getAid());
        holder.bname.setText(sm.getBname());
        holder.cemail.setText(sm.getCemail());
        holder.dactive.setText(sm.getDactive());
        holder.aid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cxnt.startActivity(new Intent(cxnt,RealtimeFirebase.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mismatchedList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView aid, bname, cemail, dactive;
        Button btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            aid = (TextView) itemView.findViewById(R.id.txtidone);
            bname = (TextView) itemView.findViewById(R.id.txtnametwo);
            cemail = (TextView) itemView.findViewById(R.id.txtemailthree);
            dactive = (TextView) itemView.findViewById(R.id.txtactivefour);
            btn = itemView.findViewById(R.id.buttonidone);

        }
    }
}
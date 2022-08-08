package com.example.firebasetesting;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

public class personAdapter extends FirebaseRecyclerAdapter<person, personAdapter.personsViewholder> {


    TextView fid;


    private RealtimeFirebaseThree realtimeFirebaseThree;


    public personAdapter(RealtimeFirebaseThree realtimeFirebaseThree, @NonNull FirebaseRecyclerOptions<person> options) {
        super(options);
        this.realtimeFirebaseThree = realtimeFirebaseThree;
    }


    @Override
    protected void
    onBindViewHolder(@NonNull final personsViewholder holder, final int position, @NonNull final person model) {
        holder.aid.setText(model.getAid());
        holder.bname.setText(model.getBname());
        holder.cemail.setText(model.getCemail());
        holder.dactive.setText(model.getDactive());
        holder.btn.setOnClickListener(view -> {

           // Log.d("YUPS", "onClickheyyyyy" +getRef(position).getKey());
            Intent intent = new Intent(realtimeFirebaseThree, RealtimeFirebase.class);

            intent.putExtra("aid",model.getAid());
            intent.putExtra("bname",model.getBname());
            intent.putExtra("cemail",model.getCemail());
            intent.putExtra("dactive",model.getDactive());
            intent.putExtra("dkey",getRef(position).getKey());

            realtimeFirebaseThree.startActivity(intent);


            //   Log.d("YUPS", "onClickheyyyyy" + getRef(position).getKey());


        });
    }

    // Function to tell the class about the Card view (here
    // "person.xml")in
    // which the data will be shown
    @NonNull
    @Override
    public personsViewholder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.person, parent, false);
        return new personAdapter.personsViewholder(view);
    }
    // Sub Class to create references of the views in Crad
    // view (here "person.xml")
    class personsViewholder extends RecyclerView.ViewHolder {
        TextView aid, bname, cemail, dactive;
        Button btn;

        public personsViewholder(@NonNull View itemView) {
            super(itemView);

            aid = itemView.findViewById(R.id.idnotxtpersonone);
            bname = itemView.findViewById(R.id.nametxtpersontwo);
            cemail = itemView.findViewById(R.id.emailtxtpersonthree);
            dactive = itemView.findViewById(R.id.activetxtpersonfour);
            btn = itemView.findViewById(R.id.buttontxtpersonfour);



        }
    }
}
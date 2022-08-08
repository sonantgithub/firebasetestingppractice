package com.example.firebasetesting;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class RealtimeFirebasetwo extends AppCompatActivity {

    RecyclerView recview;
    myadapter adapter;
    ArrayList<model> model = new ArrayList<>();

    // Firebase Realtime Database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realtime_firebasetwo);

        recview = (RecyclerView) findViewById(R.id.recycler00);
        recview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new myadapter(this, model.size(), model);
        recview.setAdapter(adapter);

        FirebaseDatabase.getInstance().getReference().child("users")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.getValue() != null) {
                            //     Log.d("TAG", "onDataChange: "+snapshot.toString());

                            if (snapshot.getValue() != null) {
                                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                    model.add(new model(dataSnapshot.child("aid").getValue().toString(),
                                            dataSnapshot.child("bname").getValue().toString(),
                                            dataSnapshot.child("cemail").getValue().toString(),
                                            dataSnapshot.child("dactive").getValue().toString()));
                                }
                            }

                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }

}

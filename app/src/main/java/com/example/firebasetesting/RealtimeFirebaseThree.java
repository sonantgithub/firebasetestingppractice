package com.example.firebasetesting;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RealtimeFirebaseThree extends AppCompatActivity {

    private RecyclerView recyclerView;
    personAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realtime_firebase_three);


        DatabaseReference mbase;
        mbase = FirebaseDatabase.getInstance().getReference("users");

        recyclerView = findViewById(R.id.recyclrviewthree);

        // To display the Recycler view linearly
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data
        FirebaseRecyclerOptions<person> options
                = new FirebaseRecyclerOptions.Builder<person>()
                .setQuery(mbase, person.class)
                .build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = new personAdapter(this, options);

        // Connecting Adapter class with the Recycler view*/
        recyclerView.setAdapter(adapter);
    }

    // Function to tell the app to start getting
    // data from database on starting of the activity
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stoping of the activity
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.searchmenu, menu);
//
//        MenuItem item = menu.findItem(R.id.search_bar_one);
//
//        SearchView searchView = (SearchView) item.getActionView();
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                processsearch(s);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                processsearch(s);
//                return false;
//            }
//        });
//
//        return super.onCreateOptionsMenu(menu);
//    }
//    private void processsearch(String s) {
//        FirebaseRecyclerOptions<person> options =
//                new FirebaseRecyclerOptions.Builder<person>()
//                        .setQuery(FirebaseDatabase.getInstance().getReference("users").orderByChild("bname")
//                                .startAt(s).endAt(s + "\uf8ff"), person.class)
//                        .build();
//
//        adapter = new personAdapter(this, options);
//        adapter.startListening();
//        recyclerView.setAdapter(adapter);
//
//    }
}
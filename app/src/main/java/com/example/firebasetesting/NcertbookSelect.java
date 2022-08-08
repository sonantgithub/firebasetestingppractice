package com.example.firebasetesting;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NcertbookSelect extends AppCompatActivity {

    // String[] country = {"India", "USA", "China", "Japan", "Other"};
    ArrayList<String> al = new ArrayList<>();
    ArrayList<String> subjectal = new ArrayList<>();
    ArrayList<String> chapteral = new ArrayList<>();

    ArrayList<String> chapterlink = new ArrayList<>();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    Spinner spin, spinsubject, spinchapter;
    String selectclass, selectsubject, selectchapter;
    String value1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ncertbook_select);
        spin = findViewById(R.id.classspinner);
        spinsubject = findViewById(R.id.subjectspinner);
        spinchapter = findViewById(R.id.chapterspinner);

        getClasstToSpinner();

    }

    private void getClasstToSpinner() {

        al.clear();

        myRef.child("classes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("TAG", "onDataChange: " + dataSnapshot.getChildrenCount() + dataSnapshot);
                if (dataSnapshot.getValue() == null) {
                    return;
                }
                String[] tempArray = new String[((int) dataSnapshot.getChildrenCount()) + 1];
                tempArray[0] = "Select Class";
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
//                    al.add(Integer.parseInt(String.valueOf(snap.child("position").getValue())),String.valueOf(snap.child("name").getValue()));
                    tempArray[Integer.parseInt(String.valueOf(snap.child("position").getValue()))] = String.valueOf(snap.child("name").getValue());
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(NcertbookSelect.this, android.R.layout.simple_spinner_item, tempArray);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spin.setAdapter(arrayAdapter);
                spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        selectclass = parent.getItemAtPosition(position).toString();
                        // Toast.makeText(NcertbookSelect.this, "Selected: " + tutorialsName,Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });


    }

    public void subjectbtn(View view) {

        subjectal.clear();

        myRef.child("classes").child(selectclass).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() == null) {
                    return;
                }
                for (DataSnapshot snapp : dataSnapshot.getChildren()) {
                    subjectal.add(snapp.getKey());
                }

                ArrayAdapter<String> arrayAdapterb = new ArrayAdapter<String>(NcertbookSelect.this, android.R.layout.simple_spinner_item, subjectal);
                arrayAdapterb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinsubject.setAdapter(arrayAdapterb);
                spinsubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selectsubject = parent.getItemAtPosition(position).toString();

                        //  String selectclass = parent.getItemAtPosition(position).toString();
                        // Toast.makeText(NcertbookSelect.this, "Selected: " + tutorialsName,Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });


    }

    public void chapterbtn(View view) {

        chapteral.clear();

        myRef.child("classes").child(selectclass).child(selectsubject).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() == null) {
                    return;
                }
                for (DataSnapshot snappp : dataSnapshot.getChildren()) {
                    chapteral.add(snappp.getKey());
                    //  chapterlink.add(String.valueOf(snappp.getValue()));
                    selectsubject = null;
                }

                ArrayAdapter<String> arrayAdapterbb = new ArrayAdapter<String>(NcertbookSelect.this, android.R.layout.simple_spinner_item, chapteral);
                arrayAdapterbb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinchapter.setAdapter(arrayAdapterbb);
                spinchapter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selectchapter = parent.getItemAtPosition(position).toString();


                        value1 = dataSnapshot.child(selectchapter).getValue(String.class);

                        Log.d("TAG", "onItemSelectedgottheselectchapterkey " + value1);


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });


    }

    public void getpdffromfirebase(View view) {

        Intent intent = new Intent(this, NcertTopicsShowInApp.class);
        intent.putExtra("pdfurl", value1);
        startActivity(intent);

    }
}
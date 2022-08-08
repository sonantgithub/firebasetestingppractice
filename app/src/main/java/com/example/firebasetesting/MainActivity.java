package com.example.firebasetesting;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {


    String TAG = MainActivity.class.getSimpleName();

    EditText txt1, txt2, txt3;
    Button btn1, btn2, btn3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt3 = findViewById(R.id.editTextTextPersonName3);
        txt1 = findViewById(R.id.editTextTextPersonName);
        txt2 = findViewById(R.id.editTextTextPersonName2);

        btn3 = findViewById(R.id.button3);
        btn1 = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);

    }

    public void senddata(View view) {

        String v3 = txt3.getText().toString();
        String v2 = txt2.getText().toString();
        String v1 = txt1.getText().toString();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        myRef = myRef.child("users");
        myRef.child("id").setValue(v3);
        myRef.child("name").setValue(v2);
        myRef.child("password").setValue(v1);

        txt3.setText("");
        txt2.setText("");
        txt1.setText("");

    }

    public void getdata(View view) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        myRef.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value1 = dataSnapshot.child("id").getValue(String.class);
                String value2 = dataSnapshot.child("name").getValue(String.class);
                String value3 = dataSnapshot.child("password").getValue(String.class);

                txt3.setText(value1);
                txt2.setText(value2);
                txt1.setText(value3);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


    }

    public void updatedata(View view) {
        Intent intent = new Intent(this, Storage.class);
        startActivity(intent);
    }
}
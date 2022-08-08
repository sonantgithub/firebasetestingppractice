package com.example.firebasetesting;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SharedPrefere extends AppCompatActivity {

    EditText sftxt1, sftxt2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);

        sftxt1 = findViewById(R.id.editTextTextPersonName4);

        sftxt2 = findViewById(R.id.editTextTextPersonName5);

        // Storing data into SharedPreferences

    }

    @Override
    protected void onResume() {
        super.onResume();

        // Fetching the stored data
        // from the SharedPreference
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        String s1 = sh.getString("name", "");
        String a = sh.getString("age", "");

        int b = Integer.parseInt(a);

        // Setting the fetched data
        // in the EditTexts
        sftxt1.setText(s1);
        sftxt2.setText(b);
    }

    // Store the data in the SharedPreference
    // in the onPause() method
    // When the user closes the application
    // onPause() will be called
    // and data will be stored
    @Override
    protected void onPause() {
        super.onPause();

        // Creating a shared pref object
        // with a file name "MySharedPref"
        // in private mode
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        // write all the data entered by the user in SharedPreference and apply
        myEdit.putString("name", sftxt1.getText().toString());
        myEdit.putString("age", sftxt2.getText().toString());
        myEdit.apply();
    }
}



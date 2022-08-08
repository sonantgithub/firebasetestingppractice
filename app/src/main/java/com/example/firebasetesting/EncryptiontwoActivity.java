package com.example.firebasetesting;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;

public class EncryptiontwoActivity extends AppCompatActivity {
    String password = "password";
    EditText editTexte1, editTexte2, editTexte3;
    String message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encryptiontwo);

        editTexte1 = findViewById(R.id.encrit1);
        editTexte2 = findViewById(R.id.encrit2);
        editTexte3 = findViewById(R.id.encrit3);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        myRef.child("users").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    editTexte1.setText(String.valueOf(task.getResult().getValue()));

                    // Log.d("firebase", "got the data"+String.valueOf(task.getResult().getValue()));
                }
            }
        });
    }


    public void encript(View view) {
        try {


            if (editTexte1.getText().toString().isEmpty()) {

                editTexte1.setError(getResources().getString(R.string.edittext1));
            } else {

                message = editTexte1.getText().toString();
                String encryptedMsg = AESCrypt.encrypt(password, message);
                Log.d("TAG", "encryptedMsgv " + encryptedMsg);

                editTexte2.setText(encryptedMsg);
            }

        } catch (GeneralSecurityException e) {
            Toast.makeText(EncryptiontwoActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            //handle error
        }
    }

    @SuppressLint("ResourceAsColor")
    public void decript(View view) {
        try {
            String encrypted = editTexte2.getText().toString();
            String messageAfterDecrypt = AESCrypt.decrypt(password, encrypted);
            Log.d("TAG", "messageAfterDecrypt " + messageAfterDecrypt);
            editTexte3.setText(messageAfterDecrypt);

        } catch (GeneralSecurityException e) {
            String abc = ".";
            editTexte3.setText(abc);
            Toast.makeText(EncryptiontwoActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            //handle error - could be due to incorrect password or tampered encryptedMsg

        }
    }
}
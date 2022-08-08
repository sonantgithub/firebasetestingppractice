package com.example.firebasetesting;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;

public class EncryptionActivity extends AppCompatActivity {

    EditText editTextencrpt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encryption);
      //  editTextencrpt = findViewById(R.id.encryptedit);
    }

    public void btnencrypt1(View view) {

//        String v1 = editTextencrpt.getText().toString();
//
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference();
//
//        myRef.child("users")
//        .setValue(v1);
//        editTextencrpt.setText("");
//        Toast.makeText(EncryptionActivity.this, "Text upload to firebase", Toast.LENGTH_SHORT).show();
    }

    public void btnencrypt2(View view) {

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReference("dsli.txt");

        File file = new File(Environment.getExternalStorageDirectory(), "sonant dsli");
        if (!file.exists()) {
            file.mkdirs();
        }


        final File localFile = new File(file, "dsli.txt");

        storageReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                Log.e("firebase ", ";local tem file created  created " + localFile.toString());
                //  updateDb(timestamp,localFile.toString(),position);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.e("firebase ", ";local tem file not created  created " + exception.toString());
            }
        });

    }

    public void btnencrypt3(View view) {
        Intent intent = new Intent(this, DecryptionActivity.class);
        startActivity(intent);
    }
}
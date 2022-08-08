package com.example.firebasetesting;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.internal.api.FirebaseNoSignedInUserException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Storage extends AppCompatActivity {

    String TAG = Storage.class.getSimpleName();
    ImageView imageView;
    Button BSelectImage;
    ImageView IVPreviewImage;
    int SELECT_PICTURE = 200;
    StorageReference mountainsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        Button upoadimg = findViewById(R.id.button5);
        BSelectImage = findViewById(R.id.BSelectImage);
        IVPreviewImage = findViewById(R.id.IVPreviewImage);
        mountainsRef = FirebaseStorage.getInstance().getReference("mountains.jpg");

        BSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });

    }

    void imageChooser() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    IVPreviewImage.setImageURI(selectedImageUri);
                }
            }
        }
    }

    public void uploadbtn(View view) throws FileNotFoundException {
        //StorageReference mountainImagesRef = mountainsRef.child("mountains.jpg");
        // Get the data from an ImageView as bytes
        IVPreviewImage.setDrawingCacheEnabled(true);
        IVPreviewImage.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) IVPreviewImage.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = mountainsRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                Toast.makeText(Storage.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...
                Toast.makeText(Storage.this, "Success", Toast.LENGTH_SHORT).show();
                IVPreviewImage.setImageDrawable(null);
            }
        });

    }

    public void downloadbtn(View view) {
//        mountainsRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://fir-testing-5024d.appspot.com/mountains.jpg");

        StorageReference islandRef = mountainsRef;

        final long ONE_MEGABYTE = 1024 * 1024;
        islandRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                // Data for "images/island.jpg" is returns, use this as needed
                Bitmap bmp= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                IVPreviewImage.setImageBitmap(bmp);
                Toast.makeText(Storage.this, "Successful", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(Storage.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });




       // Glide.with(Storage.this /* context */)
        //        .load("https://firebasestorage.googleapis.com/v0/b/fir-testing-5024d.appspot.com/o/mountains.jpg?alt=media&token=afc2ad90-de02-4ed4-8766-8bf9653a76a4")
        //        .into(IVPreviewImage);
    }

    public void camera(View view) {
        Intent intent = new Intent (this, CameraImage.class);
        startActivity(intent);
    }
}
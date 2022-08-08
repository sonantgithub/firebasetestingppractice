package com.example.firebasetesting;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class CameraImage extends AppCompatActivity {

    private static final int pic_id = 123;
    ImageView click_image_id;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference mountainsRef = storage.getReference("images/mountains.jpg");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_image);
        click_image_id = findViewById(R.id.imageView);

    }

    public void takepicture(View view) {
        Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera_intent, pic_id);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // Match the request 'pic id with requestCode
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == pic_id) {

            // BitMap is data structure of image file
            // which stor the image in memory
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            // Set the image in imageview for display
            click_image_id.setImageBitmap(photo);
        }
    }

    public void upload(View view) {

        // mountainsRef = FirebaseStorage.getInstance().getReference("mountains.jpg");

        StorageReference mountainsRef = storage.getReference("FirebaseTesting");
        StorageReference mountainImagesRef = mountainsRef.child("mountains.jpg");


        click_image_id.setDrawingCacheEnabled(true);
        click_image_id.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) click_image_id.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = mountainImagesRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                Toast.makeText(CameraImage.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...
                Toast.makeText(CameraImage.this, "Success", Toast.LENGTH_SHORT).show();
                click_image_id.setImageDrawable(null);
            }
        });

    }

    public void download(View view) {

        StorageReference islandRef = mountainsRef;

        final long ONE_MEGABYTE = 1024 * 1024;
        islandRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                // Data for "images/island.jpg" is returns, use this as needed
                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                click_image_id.setImageBitmap(bmp);
                Toast.makeText(CameraImage.this, "Successful", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(CameraImage.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void dsil(View view) {
        Intent intent = new Intent(this,Dsil.class);
        startActivity(intent);
    }
}
package com.example.firebasetesting;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Dsil extends AppCompatActivity {

    StorageReference mountainsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsil);

        bookImageHistory();
        //    booksHess();
        //   booksVarun();
        //    abc();
        //    defaultUser();
        //    sonantLogo();


    }


    private void bookImageHistory() {
        mountainsRef = FirebaseStorage.getInstance().getReference("/BookImages/history1.png");
        StorageReference islandRef = mountainsRef;

        final long ONE_MEGABYTE = 1024 * 1024;
        islandRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {

                // Data for "images/island.jpg" is returns, use this as needed
                //   Bitmap bmp= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                //   IVPreviewImage.setImageBitmap(bmp);
                Toast.makeText(Dsil.this, "Successfully downloaded Bookimages/history1.png", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(Dsil.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });


    }


    private void booksHess() {

        mountainsRef = FirebaseStorage.getInstance().getReference("Books/hess401.pdf");
        StorageReference islandRef = mountainsRef;

        final long ONE_MEGABYTE = 1024 * 1024 * 5;
        islandRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                // Data for "images/island.jpg" is returns, use this as needed
                //   Bitmap bmp= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                //   IVPreviewImage.setImageBitmap(bmp);
                Toast.makeText(Dsil.this, "Successfully downloaded Books/hess401.pdf", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(Dsil.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void booksVarun() {

        mountainsRef = FirebaseStorage.getInstance().getReference("Books/varun.epub");
        StorageReference islandRef = mountainsRef;

        final long ONE_MEGABYTE = 1024 * 1024 * 5;
        islandRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                // Data for "images/island.jpg" is returns, use this as needed
                //   Bitmap bmp= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                //   IVPreviewImage.setImageBitmap(bmp);
                Toast.makeText(Dsil.this, "Successfully downloaded Books/varun.epub", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(Dsil.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void abc() {

        mountainsRef = FirebaseStorage.getInstance().getReference("abc.mov");
        StorageReference islandRef = mountainsRef;

        final long ONE_MEGABYTE = 1024 * 1024 * 5;
        islandRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {


                // Data for "images/island.jpg" is returns, use this as needed
                // Bitmap bmp= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                // IVPreviewImage.setImageBitmap(bmp);
                Toast.makeText(Dsil.this, "Successfully downloaded abc.mov", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(Dsil.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void defaultUser() {
        mountainsRef = FirebaseStorage.getInstance().getReference("defaultuser.png");
        StorageReference islandRef = mountainsRef;

        final long ONE_MEGABYTE = 1024 * 1024;
        islandRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                // Data for "images/island.jpg" is returns, use this as needed
                //   Bitmap bmp= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                //   IVPreviewImage.setImageBitmap(bmp);
                Toast.makeText(Dsil.this, "Successfully downloaded defaultuser.png", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(Dsil.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void sonantLogo() {

        mountainsRef = FirebaseStorage.getInstance().getReference("sonant logo.mov");
        StorageReference islandRef = mountainsRef;

        final long ONE_MEGABYTE = 1024 * 1024 * 5;
        islandRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                // Data for "images/island.jpg" is returns, use this as needed
                //   Bitmap bmp= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                //   IVPreviewImage.setImageBitmap(bmp);
                Toast.makeText(Dsil.this, "Successfully downloaded sonant logo.mov", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(Dsil.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
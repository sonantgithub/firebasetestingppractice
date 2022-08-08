package com.example.firebasetesting;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.folioreader.ui.activity.FolioActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;


public class Database extends AppCompatActivity {

    Button write_button;
    ImageView imageView;
    TextView txt;

    static final int READ_BLOCK_SIZE = 100;
    VideoView video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        imageView = findViewById(R.id.imageViews);
        write_button = findViewById(R.id.write_button);
        txt = findViewById(R.id.edittxt152);
        video = findViewById(R.id.viddeo);


        write_button.setOnClickListener(v -> {
            // getImageFromFirebaseAndSendInMemory();
            // readTxtFileFromInternalStorage();
            //  loadImageFileFromInternalStorgae();
            // loadVideoFileFromInternalStorgae();
        });
    }


    private void getImageFromFirebaseAndSendInMemory() {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReference("abc.mov");

        File file = new File(Environment.getExternalStorageDirectory(), "file_name");
        if (!file.exists()) {
            file.mkdirs();
        }

        final File localFile = new File(file, "abc.mp4");

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

    private void readTxtFileFromInternalStorage() {
//
//        try {
//            File root = new File(Environment.getExternalStorageDirectory(), "aye");
//            if (!root.exists()) {
//                root.mkdirs();
//            }
//            File wardFile = new File(root, "new.txt");
//
//            String files = wardFile.toString();
//            Log.d("TAG", "readTxtFileFromInternalStorage: "+files);
//
//
//            try {
//                Log.d("TAG", "readTxtFileFromInternalStorage: "+files);
//
//                int a;
//                StringBuilder temp = new StringBuilder();
//                while ((a = isr.read()) != -1) {
//                    temp.append((char) a);
//                }
//
//                // setting text from the file.
//                txt.setText(temp.toString());
//                isr.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        catch (Exception e){
//
//            Toast.makeText(this, " failed"+e.getMessage(), Toast.LENGTH_SHORT).show();
//
//        }


    }


    private void loadImageFileFromInternalStorgae() {


        try {
            File root = new File(Environment.getExternalStorageDirectory(), "aye");
            if (!root.exists()) {
                root.mkdirs();
            }
            File wardFile = new File(root, "ss_1.jpg");
            try {
//                FileOutputStream out = new FileOutputStream(wardFile);
//
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
//                out.flush();
//                out.close();
                Glide.with(Database.this).load(Uri.fromFile(wardFile)).fitCenter().into(imageView);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "failed" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "failed" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void loadVideoFileFromInternalStorgae() {

        try {
            File root = new File(Environment.getExternalStorageDirectory(), "aye");
            if (!root.exists()) {
                root.mkdirs();
            }
            File wardFile = new File(root, "video.mp4");
            try {
                Uri videoUri = Uri.parse(String.valueOf(wardFile));
                // Log.d("Video Player", filePath);
                video.setVideoURI(videoUri);
                video.start();


            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "failed" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "failed" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public void next(View view) {

        Intent intent = new Intent(Database.this, PdfActivity.class);
   //     intent.putExtra(FolioActivity.INTENT_EPUB_SOURCE_TYPE, FolioActivity.EpubSourceType.RAW);
     //   intent.putExtra(FolioActivity.INTENT_EPUB_SOURCE_PATH, R.raw.demo);
        startActivity(intent);
    }
}



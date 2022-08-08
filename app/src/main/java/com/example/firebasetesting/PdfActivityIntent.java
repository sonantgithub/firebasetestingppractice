package com.example.firebasetesting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.folioreader.ui.activity.FolioActivity;

public class PdfActivityIntent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_intent);
            Intent intent = new Intent(PdfActivityIntent.this, PdfActivity.class);
        //    intent.putExtra(FolioActivity.INTENT_EPUB_SOURCE_TYPE, FolioActivity.EpubSourceType.RAW);
        //   intent.putExtra(FolioActivity.INTENT_EPUB_SOURCE_PATH, R.raw.demo);
            startActivity(intent);

    }
}
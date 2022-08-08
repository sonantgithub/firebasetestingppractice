package com.example.firebasetesting;

import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.multidex.MultiDex;

import com.folioreader.FolioReader;
import com.folioreader.model.HighLight;
import com.folioreader.model.locators.ReadLocator;
import com.folioreader.util.OnHighlightListener;
import com.folioreader.util.ReadLocatorListener;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class PdfActivity extends AppCompatActivity implements OnHighlightListener, ReadLocatorListener, FolioReader.OnClosedListener {

    PDFView pdfView;
    // private FolioReader folioReader;
    FolioReader folioReader = FolioReader.get();

    String TAG = PdfActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        pdfView = findViewById(R.id.id_pdf_view);
        MultiDex.install(this);
        folioReader = FolioReader.get()
                .setOnHighlightListener(this)
                .setReadLocatorListener(this)
                .setOnClosedListener(this);
        loadPdfFromLocalStorage();

        //   loadEpubFromLocalStorage();

    }

    private void loadPdfFromLocalStorage() {
        {
            //PDFView pdfView = findViewById(R.id.pdfView);

            {

                try {

                    File root = new File(Environment.getExternalStorageDirectory(), "hey");
                    if (!root.exists()) {
                        root.mkdirs();
                    }
                    File wardFile = new File(root, "pdf.pdf");


                    try {
                        int pageRange[] = new int[]{1,2,3,4};
                        pdfView.fromFile(wardFile)
                                .pages(pageRange)
                                .defaultPage(2)
                                .enableSwipe(true)
                                .load();
                        Toast.makeText(this, "pass", Toast.LENGTH_SHORT).show();


                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(this, "failed" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(this, "failed" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void loadEpubFromLocalStorage() {

        //  Config config = AppUtil.getSavedConfig(getApplicationContext());
        // if (config == null)
        //    config = new Config();
        //  config.setAllowedDirection(Config.AllowedDirection.VERTICAL_AND_HORIZONTAL);
        {

            try {
                File root = new File(Environment.getExternalStorageDirectory(), "hey");
                if (!root.exists()) {
                    root.mkdirs();
                }
                File wardFile = new File(root, "okok.epub");

                try {

                    folioReader.openBook(String.valueOf(wardFile));

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this, "failed" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(this, "failed" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }


    @Override
    public void onHighlight(HighLight highlight, HighLight.HighLightAction type) {

    }

    @Override
    public void saveReadLocator(ReadLocator readLocator) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onFolioReaderClosed() {

    }
}
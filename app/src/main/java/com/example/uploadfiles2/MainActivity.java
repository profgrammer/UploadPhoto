package com.example.uploadfiles2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.kosalgeek.android.photoutil.CameraPhoto;
import com.kosalgeek.android.photoutil.GalleryPhoto;
import com.kosalgeek.android.photoutil.ImageLoader;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    CameraPhoto cameraPhoto;
    GalleryPhoto gp;
    Button cameraBtn, galleryBtn;
    ImageView iv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        cameraPhoto = new CameraPhoto(getApplicationContext());
        gp = new GalleryPhoto(getApplicationContext());
        cameraBtn = findViewById(R.id.cameraBtn);
        galleryBtn = findViewById(R.id.galleryBtn);
        iv2 = findViewById(R.id.iv2);
        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivityForResult(cameraPhoto.takePhotoIntent(), 15);
                    cameraPhoto.addToGallery();
                } catch (IOException e) {
                    Log.d("error", e.getMessage());
                    Toast.makeText(getApplicationContext(), "Error while opening camera", Toast.LENGTH_SHORT).show();
                }
            }
        });

        galleryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivityForResult(gp.openGalleryIntent(), 16);
                } catch (Exception e) {
                    Log.d("error", e.getMessage());
                    Toast.makeText(getApplicationContext(), "Error while opening gallery", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK) {
            if(requestCode == 15) {
                String photoPath = cameraPhoto.getPhotoPath();
                Intent i = new Intent(MainActivity.this, infoActivity.class);
                i.putExtra("photoPath", photoPath);
                startActivity(i);
            }
            else if(requestCode == 16) {
                Uri uri = data.getData();
                gp.setPhotoUri(uri);
                String photoPath = gp.getPath();
                Log.d("gallery photo path", photoPath);
                Intent i = new Intent(MainActivity.this, infoActivity.class);
                i.putExtra("photoPath", photoPath);
                startActivity(i);

            }
        }
    }
}

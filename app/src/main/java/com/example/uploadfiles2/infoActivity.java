package com.example.uploadfiles2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.kosalgeek.android.photoutil.ImageLoader;

import java.io.FileNotFoundException;

public class infoActivity extends AppCompatActivity {

    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Bundle extras = getIntent().getExtras();
        String photoPath = extras.getString("photoPath");
        Log.d("photo path", photoPath);
        iv = findViewById(R.id.ivImage);
        try {

            Bitmap bmp = ImageLoader.init().from(photoPath).requestSize(512,512).getBitmap();
            iv.setImageBitmap(bmp);
        } catch (FileNotFoundException e) {
            Toast.makeText(getApplicationContext(), "Error while loading photo", Toast.LENGTH_SHORT);
        }
    }
}

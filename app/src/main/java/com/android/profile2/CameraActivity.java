package com.android.profile2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import top.defaults.camera.CameraView;
import top.defaults.camera.Photographer;
import top.defaults.camera.PhotographerFactory;


public class CameraActivity extends AppCompatActivity {

    CameraView preview = findViewById(R.id.preview);
    Photographer photographer = PhotographerFactory.createPhotographerWithCamera2(this, preview);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        photographer.startPreview();

    }

    @Override
    protected void onResume() {
        super.onResume();
        photographer.startPreview();
    }

    @Override
    protected void onPause() {
        photographer.stopPreview();
        super.onPause();
    }


}

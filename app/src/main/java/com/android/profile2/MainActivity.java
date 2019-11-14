package com.android.profile2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.profile2.PrayerTimesData.PrayerTimes;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        Button btnProfile = findViewById(R.id.btnProfile);
        Button btnDial = findViewById(R.id.btnDial);
        Button btnCamera = findViewById(R.id.btnCamera);
        Button btnPrayerTimes = findViewById(R.id.btnPrayerTimes);
        Button btnMovieFinder = findViewById(R.id.btnMovieFinder);
        Button btnLoadMoviesFromDB = findViewById(R.id.btnLoadMoviesFromDB);
        final Button btnDrawer = findViewById(R.id.btnDrawer);


        btnDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerLayout navDrawer = findViewById(R.id.drawer_layout);
                if (!navDrawer.isDrawerOpen(GravityCompat.START))
                    navDrawer.openDrawer(GravityCompat.START);
                else navDrawer.closeDrawer(GravityCompat.END);

            }
        });
        ArrayList<String> list = new ArrayList<>();
        list.add("AAA");
        list.add("BBBBBBB");
        list.add("CCC");
        list.add("DDDDDDDDDD");
        list.add("EEEEEE");
        list.add("FFFF");
        list.add("GGGG");

        RecyclerView recycler = findViewById(R.id.recycler);
        RecyclerAdapter adapter = new RecyclerAdapter(list);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.HORIZONTAL, false));


        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(i);
            }
        });

        btnDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, DialActivity.class);
                startActivity(i);
            }
        });

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CameraActivity.class);
                startActivity(i);
            }
        });

        btnPrayerTimes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, PrayerTimesActivity.class);
                startActivity(i);
            }
        });
        btnMovieFinder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MovieFinderActivity.class);
                startActivity(i);
            }
        });
        btnLoadMoviesFromDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MovieLoaderActivity.class);
                startActivity(i);
            }
        });
    }
}

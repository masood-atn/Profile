package com.android.profile2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MovieLoaderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_loader);


        TextView txtMoviesFromDB = findViewById(R.id.txtMoviesFromDB);


        final SqliteHelper helper = new SqliteHelper(MovieLoaderActivity.this, "Main", null, 1);

        txtMoviesFromDB.setText(helper.getAllMoviesName());

    }
}

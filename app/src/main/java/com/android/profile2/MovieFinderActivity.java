package com.android.profile2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.profile2.MovieFinderData.MovieFinder;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MovieFinderActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_finder);

        EditText edtMovie = findViewById(R.id.edtMovie);
        Button btnSearch = findViewById(R.id.btnSearch);
        Button btnSaveToDB = findViewById(R.id.btnSaveToDB);
        TextView txtResult = findViewById(R.id.txtResult);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = edtMovie.getText().toString();


                String url = "http://www.omdbapi.com/?t=" + item + "&apikey=254f905a";

                AsyncHttpClient client = new AsyncHttpClient();
                client.get(url, new JsonHttpResponseHandler() {


                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);

                        Gson gson = new Gson();
                        MovieFinder movieFinder = gson.fromJson(response.toString(), MovieFinder.class);

                        String result = movieFinder.getTitle() + " " + movieFinder.getYear() + "\n" + "Directed By: " + movieFinder.getDirector();


                        txtResult.setText(result);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);

                        System.out.println(throwable.getMessage());
                    }

                });


            }
        });
        btnSaveToDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final SqliteHelper helper = new SqliteHelper(MovieFinderActivity.this, "Main", null, 1);
                helper.insertMovies(txtResult.getText().toString());
                Toast.makeText(MovieFinderActivity.this, "Saved!", Toast.LENGTH_SHORT).show();

            }
        });

    }
}

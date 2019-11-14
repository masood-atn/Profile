package com.android.profile2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.profile2.PrayerTimesData.PrayerTimes;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class PrayerTimesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayer_times);

        TextView txtPrayerTimes = findViewById(R.id.txtPrayerTimes);
        EditText edtCity = findViewById(R.id.edtCity);
        Button btnShowPrayerTimes = findViewById(R.id.btnShowPrayerTimes);


        btnShowPrayerTimes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String city = edtCity.getText().toString();
                String url = "http://api.aladhan.com/v1/timingsByCity?city=" + city + "&country=iran&method=8";

                AsyncHttpClient client = new AsyncHttpClient();
                client.get(url, new JsonHttpResponseHandler() {


                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);

                        Gson gson = new Gson();
                        PrayerTimes pray = gson.fromJson(response.toString(), PrayerTimes.class);


                        String Fajr = pray.getData().getTimings().getFajr();
                        String Dhuhr = pray.getData().getTimings().getDhuhr();
                        String Maghrib = pray.getData().getTimings().getMaghrib();


                        String prayerTimes = "صبح " + Fajr + "\n" +
                                "ظهر " + Dhuhr + "\n" +
                                "مغرب " + Maghrib + "\n";

                        txtPrayerTimes.setText(prayerTimes);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);

                        System.out.println(throwable.getMessage());
                    }

                });


            }
        });


    }
}

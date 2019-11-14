package com.android.profile2;

import com.android.profile2.PrayerTimesData.PrayerTimes;
import com.loopj.android.http.*;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


public class GetPrayerTimes {


    public String Fajr;
    public String Dhuhr;
    public String Maghrib;

    public GetPrayerTimes getTimesByCity(String city) {

        String url = "http://api.aladhan.com/v1/timingsByCity?city=" + city + "&country=iran&method=8";

        GetPrayerTimes getPrayerTimes = new GetPrayerTimes();
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler() {


            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                Gson gson = new Gson();
                PrayerTimes pray = gson.fromJson(response.toString(), PrayerTimes.class);


                getPrayerTimes.Fajr = pray.getData().getTimings().getFajr();
                getPrayerTimes.Dhuhr = pray.getData().getTimings().getDhuhr();
                getPrayerTimes.Maghrib = pray.getData().getTimings().getMaghrib();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);

                System.out.println(throwable.getMessage());
            }

        });
        return getPrayerTimes;
    }

}

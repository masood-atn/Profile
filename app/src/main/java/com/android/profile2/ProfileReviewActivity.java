package com.android.profile2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileReviewActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_review);


        Intent i = getIntent();

        final String firstName = i.getStringExtra("firstName");
        final String lastName = i.getStringExtra("lastName");
        final String email = i.getStringExtra("email");
        final String age = i.getStringExtra("age");

        TextView txtFirstName = findViewById(R.id.txtFirstName);
        TextView txtLastName = findViewById(R.id.txtLastName);
        TextView txtEmail = findViewById(R.id.txtEmail);
        TextView txtAge = findViewById(R.id.txtAge);

        txtFirstName.setText(String.format("First Name: %s", firstName));
        txtLastName.setText(String.format("Last Name: %s", lastName));
        txtEmail.setText(String.format("Email: %s", email));
        txtAge.setText(String.format("Age: %s", age));

        Button btnConfirm = findViewById(R.id.btnConfirm);
        Button btnEdit = findViewById(R.id.btnEdit);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent returnIntent = new Intent();
                returnIntent.putExtra("firstName", firstName);
                returnIntent.putExtra("lastName", lastName);
                returnIntent.putExtra("email", email);
                returnIntent.putExtra("age", age);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();

            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("firstName", firstName);
                returnIntent.putExtra("lastName", lastName);
                returnIntent.putExtra("email", email);
                returnIntent.putExtra("age", age);
                setResult(Activity.RESULT_CANCELED, returnIntent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(ProfileReviewActivity.this, ProfileActivity.class);
        startActivity(i);
    }
}
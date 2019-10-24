package com.android.profile2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        final EditText edtFirstName = findViewById(R.id.edtFirstName);
        final EditText edtLastName = findViewById(R.id.edtLastName);
        final EditText edtEmail = findViewById(R.id.edtEmail);
        final EditText edtAge = findViewById(R.id.edtAge);

        Button btnReview = findViewById(R.id.btnReview);


        edtFirstName.setText(PreferenceManager.getDefaultSharedPreferences(ProfileActivity.this).getString("firstName", "Empty"));
        edtLastName.setText(PreferenceManager.getDefaultSharedPreferences(ProfileActivity.this).getString("lastName", "Empty"));
        edtEmail.setText(PreferenceManager.getDefaultSharedPreferences(ProfileActivity.this).getString("email", "Empty"));
        edtAge.setText(PreferenceManager.getDefaultSharedPreferences(ProfileActivity.this).getString("age", "0"));


        btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String firstName = edtFirstName.getText().toString();
                String lastName = edtLastName.getText().toString();
                String email = edtEmail.getText().toString();
                String age = edtAge.getText().toString();

                // input validation
                if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || age.isEmpty()) {
                    Toast.makeText(ProfileActivity.this, "Please fill in all fields.", Toast.LENGTH_LONG).show();
                    return;
                }
                EmailValidator emailValidator = new EmailValidator();
                if (!emailValidator.isEmailValid(email)) {
                    Toast.makeText(ProfileActivity.this, "Email is not valid.", Toast.LENGTH_LONG).show();
                    return;
                }


                Intent i = new Intent(ProfileActivity.this, ProfileReviewActivity.class);
                i.putExtra("firstName", firstName);
                i.putExtra("lastName", lastName);
                i.putExtra("email", email);
                i.putExtra("age", age);
                startActivityForResult(i, 200);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        final EditText edtFirstName = findViewById(R.id.edtFirstName);
        final EditText edtLastName = findViewById(R.id.edtLastName);
        final EditText edtEmail = findViewById(R.id.edtEmail);
        final EditText edtAge = findViewById(R.id.edtAge);

        if (requestCode == 200) {

            if (resultCode == Activity.RESULT_OK) {

                PreferenceManager.getDefaultSharedPreferences(ProfileActivity.this).
                        edit().putString("firstName", data.getStringExtra("firstName")).apply();
                PreferenceManager.getDefaultSharedPreferences(ProfileActivity.this).
                        edit().putString("lastName", data.getStringExtra("lastName")).apply();
                PreferenceManager.getDefaultSharedPreferences(ProfileActivity.this).
                        edit().putString("email", data.getStringExtra("email")).apply();
                PreferenceManager.getDefaultSharedPreferences(ProfileActivity.this).
                        edit().putString("age", data.getStringExtra("age")).apply();

                Toast.makeText(ProfileActivity.this, "Profile saved successfully.", Toast.LENGTH_LONG).show();
                Intent i = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(i);
                finish();


            }

            if (resultCode == Activity.RESULT_CANCELED) {

                String userFirstName = data.getStringExtra("firstName");
                String userLastName = data.getStringExtra("lastName");
                String userEmail = data.getStringExtra("email");
                String userAge = data.getStringExtra("age");

                edtFirstName.setText(userFirstName);
                edtLastName.setText(userLastName);
                edtEmail.setText(userEmail);
                edtAge.setText(userAge);

            }

        }
    }
}

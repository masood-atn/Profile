package com.android.profile2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

        btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String firstName = edtFirstName.getText().toString();
                String lastName = edtLastName.getText().toString();
                String email = edtEmail.getText().toString();
                String age = edtAge.getText().toString();

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

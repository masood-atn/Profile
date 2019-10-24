package com.android.profile2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dial);


        Button btnDial = findViewById(R.id.btnDial);
        final EditText edtDial = findViewById(R.id.edtDial);


        btnDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri phoneNumber = Uri.parse("tel:" + edtDial.getText().toString());
                Intent i = new Intent(Intent.ACTION_DIAL, phoneNumber);
                startActivity(i);
            }
        });

    }
}

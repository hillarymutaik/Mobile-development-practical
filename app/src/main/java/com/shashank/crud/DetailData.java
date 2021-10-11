package com.shashank.crud;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

public class DetailData extends AppCompatActivity {
    TextView fname, lname, dob, gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data);

        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        dob = findViewById(R.id.dob);
        gender = findViewById(R.id.gender);

        fname.setText(getIntent().getStringExtra("fname"));
        lname.setText(getIntent().getStringExtra("lname"));
        dob.setText(getIntent().getStringExtra("dob"));
        gender.setText(getIntent().getStringExtra("gender"));
    }
}

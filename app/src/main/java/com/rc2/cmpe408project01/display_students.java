package com.rc2.cmpe408project01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class display_students extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_students);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Display Students");
    }
}
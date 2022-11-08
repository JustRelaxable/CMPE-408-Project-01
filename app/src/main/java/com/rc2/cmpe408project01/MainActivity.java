package com.rc2.cmpe408project01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    String[] facultyList = {"Faculty of Arts","Faculty of Law","Faculty of Engineering"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFacultySpinner();
    }

    private void loadFacultySpinner() {
        Spinner facultySpinner = findViewById(R.id.faculty_spinner);
        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item,facultyList);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        facultySpinner.setAdapter(ad);
    }
}
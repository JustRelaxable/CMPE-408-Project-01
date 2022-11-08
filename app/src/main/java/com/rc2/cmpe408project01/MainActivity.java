package com.rc2.cmpe408project01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String[] facultyList = {"Faculty of Arts","Faculty of Law","Faculty of Engineering"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFacultySpinner();
        handleStudentIDTextChangedListener();
    }

    private void handleStudentIDTextChangedListener() {
        EditText studentIDText = findViewById(R.id.student_id);
        studentIDText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.length() > 11){
                    editable = editable.delete(11,12);
                }
            }
        });
    }

    private void loadFacultySpinner() {
        Spinner facultySpinner = findViewById(R.id.faculty_spinner);
        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item,facultyList);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        facultySpinner.setAdapter(ad);
    }
}
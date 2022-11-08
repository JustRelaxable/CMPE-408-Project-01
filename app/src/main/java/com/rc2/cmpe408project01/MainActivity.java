package com.rc2.cmpe408project01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String[] facultyList = {"Faculty of Arts","Faculty of Law","Faculty of Engineering"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();
        loadFacultySpinner();
        handleStudentIDTextChangedListener();
    }

    private void setupViews() {
        Button submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.submit_button:
                //TODO: Implement control mechanism to all defined widgets to check if fields are complete.
                boolean allFieldsFilled = false;
                if(allFieldsFilled)
                    Toast.makeText(this, R.string.fields_submitted_successfully, Toast.LENGTH_SHORT).show();
                    //TODO: Implement and show TextView, fill it with submitted information and display to the user.
                else
                    Toast.makeText(this, R.string.fields_are_incomplete_warning, Toast.LENGTH_SHORT).show();
                break;
            case R.id.reset_button:
                break;
            case R.id.exit_button:
                break;
            default:
                break;
        }
    }
}
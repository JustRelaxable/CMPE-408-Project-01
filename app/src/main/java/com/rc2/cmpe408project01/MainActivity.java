package com.rc2.cmpe408project01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    HashMap<String,String[]> facultyDepartmentHashMap = new HashMap<>();
    JSONObject cities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFacultyDepartmentHashMap();
        setupViews();
        loadCities();
        loadBirthplaceSpinner();
        loadFacultySpinner();
        handleStudentIDTextChangedListener();
    }

    private void loadFacultyDepartmentHashMap() {
        facultyDepartmentHashMap.put("Faculty of Arts",new String[] {"Art 1","Art 2","Art 3"});
        facultyDepartmentHashMap.put("Faculty of Law",new String[]{"Law 1","Law 2","Law 3"});
        facultyDepartmentHashMap.put("Faculty of Engineering",new String[]{"Engineering 1","Engineering 2","Engineering 3"});
    }

    private void loadCities() {
        try {
            InputStream is = getAssets().open("cities.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String raw = new String(buffer,"UTF-8");
            cities = new JSONObject(raw);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void loadBirthplaceSpinner() {
        Spinner birthplaceSpinner = findViewById(R.id.birthplace_spinner);
        String[] cityCodes = new String[81];
        for (int i = 1;i<=81;i++){
            cityCodes[i-1] = String.valueOf(i);
        }
        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item,cityCodes);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        birthplaceSpinner.setAdapter(ad);
        TextView cityName = findViewById(R.id.city_name);
        birthplaceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    cityName.setText(cities.getString(String.valueOf(i+1)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setupViews() {
        Button submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(this);

        Button exitButton = findViewById(R.id.exit_button);
        exitButton.setOnClickListener(this);

        Button resetButton = findViewById(R.id.reset_button);
        resetButton.setOnClickListener(this);

        Button birtDateButton = findViewById(R.id.birth_date_button);
        birtDateButton.setOnClickListener(this);
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
        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item,facultyDepartmentHashMap.keySet().toArray());

        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        facultySpinner.setAdapter(ad);
        Spinner departmentSpinner = findViewById(R.id.department_spinner);
        facultySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedFaculty = facultySpinner.getSelectedItem().toString();
                String[] selectedFacultyDepartments = facultyDepartmentHashMap.get(selectedFaculty);
                ArrayAdapter<String> departmentAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_spinner_item,selectedFacultyDepartments);
                departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                departmentSpinner.setAdapter(departmentAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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
                System.exit(0);
                break;
            case R.id.birth_date_button:
                new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        TextView birthDateText = findViewById(R.id.birth_date_text);
                        birthDateText.setText(day+"/"+month+"/"+year);
                    }
                },2020,1,1).show();
                break;
            default:
                break;
        }
    }
}
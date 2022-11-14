package com.rc2.cmpe408project01;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText gpa;
    private EditText name ;
    private EditText last_name;
    private EditText id ;
    private EditText studentIDText;
    private RadioButton sex_male;
    private RadioButton sex_female;
    private RadioButton full_scholarship;
    private RadioButton half_scholarship;
    private RadioButton none_scholarship;
    private AlertDialog dialog;
    private AlertDialog.Builder dialogBuilder;
    private TextView pop_name;
    private TextView pop_Lname;
    private TextView pop_id;
    private TextView pop_gender;
    private TextView pop_scholar;
    private TextView pop_bp;
    private TextView pop_bd;
    private TextView pop_gpa;
    private TextView birthDateText;
    private String city_value;
    private String bd_values;
    private String faculty_value;





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
        handleGPATextChangedListener();
    }

    private void handleGPATextChangedListener() {
        EditText gpaText = findViewById(R.id.gpa_id);
        gpaText.addTextChangedListener(new TextWatcher() {
            boolean periodEnabled = false;
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.length() == 1 && !periodEnabled){
                    editable.append(".");
                    periodEnabled = true;
                }
                if(editable.length() == 1 && periodEnabled){
                    editable.clear();
                    periodEnabled = false;
                }
                if(editable.length() > 4){
                    editable = editable.delete(4,5);
                }
            }
        });
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
                    city_value = (cities.getString(String.valueOf(i+1)));
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
                if(allFieldsFilled || true) {   // geçici oalrak true
                    name = (EditText)findViewById(R.id.name_id);
                    String name_value = name.getText().toString();

                    last_name = (EditText)findViewById(R.id.last_name);
                    String Lname_value = last_name.getText().toString();

                    studentIDText = (EditText)findViewById(R.id.student_id);
                    String id_value = studentIDText.getText().toString();

                    gpa = (EditText)findViewById(R.id.gpa_id);
                    String gpa_value = gpa.getText().toString();




                    sex_male = (RadioButton)findViewById(R.id.male);
                    sex_female = (RadioButton)findViewById(R.id.female);
                    String sex_value = "none";

                    if(sex_male.isChecked()){
                        sex_value = "male";
                    }
                    else if(sex_female.isChecked()){
                        sex_value = "female";
                    }

                    full_scholarship = (RadioButton)findViewById(R.id.full);
                    half_scholarship = (RadioButton)findViewById(R.id.half);
                    none_scholarship = (RadioButton)findViewById(R.id.none);
                    String scholarship_value = "none";

                    if(full_scholarship.isChecked()){
                        scholarship_value = "full";
                    }
                    else if(half_scholarship.isChecked()){
                        scholarship_value = "half";
                    }
                    else if(none_scholarship.isChecked()){
                        scholarship_value = "none";
                    }



                    CreateNewDialog(name_value,Lname_value,id_value,sex_value,scholarship_value,city_value,gpa_value,bd_values);

                }else
                    Toast.makeText(this, R.string.fields_are_incomplete_warning, Toast.LENGTH_SHORT).show();
                break;
            case R.id.reset_button:

                id = (EditText)findViewById(R.id.student_id);
                id.setText("");

                name = (EditText)findViewById(R.id.name_id);
                name.setText("");

                last_name = (EditText)findViewById(R.id.last_name);
                last_name.setText("");

                gpa = (EditText)findViewById(R.id.gpa_id);
                gpa.setText("");

                sex_male = (RadioButton)findViewById(R.id.male);
                sex_male.setChecked(false);

                sex_female = (RadioButton)findViewById(R.id.female);
                sex_female.setChecked(false);

                full_scholarship = (RadioButton)findViewById(R.id.full);
                full_scholarship.setChecked(false);
                half_scholarship = (RadioButton)findViewById(R.id.half);
                half_scholarship.setChecked(false);
                none_scholarship = (RadioButton)findViewById(R.id.none);
                none_scholarship.setChecked(false);

                birthDateText.setText("Birth date is not selected!");






                break;
            case R.id.exit_button:
                System.exit(0);
                break;
            case R.id.birth_date_button:
                new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        birthDateText = findViewById(R.id.birth_date_text);
                        birthDateText.setText(day+"/"+month+"/"+year);
                        bd_values =(day+"/"+month+"/"+year);
                    }
                },2020,1,1).show();
                break;
            default:
                break;
        }
    }



    public void CreateNewDialog(String name,String last_name,String id,String sex,String scholarship,String bp,String gpa,String bd){
        dialogBuilder = new AlertDialog.Builder(this);
        final View contactPopupView = getLayoutInflater().inflate(R.layout.popup,null);

        dialogBuilder.setView(contactPopupView);
        dialog = dialogBuilder.create();
        dialog.show();

        pop_id = (TextView) contactPopupView.findViewById(R.id.pop_id);
        pop_id.setText(id);

        pop_name = (TextView) contactPopupView.findViewById(R.id.pop_name);
        pop_name.setText(name);

        pop_Lname = (TextView) contactPopupView.findViewById(R.id.pop_Lname);
        pop_Lname.setText(last_name);

        pop_gender = (TextView) contactPopupView.findViewById(R.id.pop_gender);
        pop_gender.setText(sex);

        pop_scholar = (TextView) contactPopupView.findViewById(R.id.pop_scholar);
        pop_scholar.setText(scholarship);

        pop_bp = (TextView) contactPopupView.findViewById(R.id.pop_bp);
        pop_bp.setText(bp);

        pop_gpa = (TextView) contactPopupView.findViewById(R.id.pop_gpa);
        pop_gpa.setText(gpa);

        pop_bd = (TextView) contactPopupView.findViewById(R.id.pop_bd);
        pop_bd.setText(bd);



    }


}
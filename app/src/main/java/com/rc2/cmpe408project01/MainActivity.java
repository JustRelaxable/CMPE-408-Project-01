package com.rc2.cmpe408project01;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    HashMap<String, String[]> facultyDepartmentHashMap = new HashMap<>();
    JSONObject cities;
    private EditText gpa;
    private EditText name;
    private EditText last_name;
    private EditText studentIDText;
    private RadioButton sex_male;
    private RadioButton sex_female;
    private RadioButton full_scholarship;
    private RadioButton half_scholarship;
    private RadioButton none_scholarship;
    private TextView birthDateText;
    private TextView pop_faculty;
    private TextView popDepartment;
    private TextView cityName;
    private EditText additionalText;
    private CheckBox additionalCheck;
    private Spinner facultySpinner,departmentSpinner,birthplaceSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StudentDatabaseHandler studentDatabaseHandler = new StudentDatabaseHandler(this);
        //StudentDatabaseHandler.instance.addStudentToDatabase(new StudentModel("1","Taha","Sokmen","Faculty of Engineering","Computer Engineering"));
        //StudentModel x = StudentDatabaseHandler.instance.getStudentByID("1");

        setContentView(R.layout.activity_main);

        loadFacultyDepartmentHashMap();
        setupViews();
        loadCities();
        loadBirthplaceSpinner();
        loadFacultySpinner();
        handleStudentIDTextChangedListener();
        handleGPATextChangedListener();
        handleAdditionalInfoCheckedListener();

    }

    private void handleAdditionalInfoCheckedListener() {
        additionalCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                additionalText.setVisibility(b?View.VISIBLE:View.INVISIBLE);
            }
        });
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
                if (editable.length() == 1 && !periodEnabled) {
                    editable.append(".");
                    periodEnabled = true;
                }
                if (editable.length() == 1 && periodEnabled) {
                    editable.clear();
                    periodEnabled = false;
                }
                if (editable.length() > 4) {
                    editable = editable.delete(4, 5);
                }
            }
        });
    }

    private void loadFacultyDepartmentHashMap() {
        facultyDepartmentHashMap.put("Faculty of Arts", new String[]{"Art 1", "Art 2", "Art 3"});
        facultyDepartmentHashMap.put("Faculty of Law", new String[]{"Law 1", "Law 2", "Law 3"});
        facultyDepartmentHashMap.put("Faculty of Engineering", new String[]{"Engineering 1", "Engineering 2", "Engineering 3"});
    }

    private void loadCities() {
        try {
            InputStream is = getAssets().open("cities.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            int result = is.read(buffer);
            is.close();
            String raw = new String(buffer, StandardCharsets.UTF_8);
            cities = new JSONObject(raw);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    private void loadBirthplaceSpinner() {
        String[] cityCodes = new String[81];
        for (int i = 1; i <= 81; i++) {
            cityCodes[i - 1] = String.valueOf(i);
        }
        ArrayAdapter<String> ad = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cityCodes);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        birthplaceSpinner.setAdapter(ad);
        TextView cityName = findViewById(R.id.city_name);
        birthplaceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    cityName.setText(cities.getString(String.valueOf(i + 1)));
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

        full_scholarship = findViewById(R.id.full);
        half_scholarship = findViewById(R.id.half);
        none_scholarship = findViewById(R.id.none);

        additionalText = findViewById(R.id.additional_text_id);
        name = findViewById(R.id.name_id);
        last_name = findViewById(R.id.last_name);
        studentIDText = findViewById(R.id.student_id);
        gpa = findViewById(R.id.gpa_id);
        sex_male = findViewById(R.id.male);
        sex_female = findViewById(R.id.female);

        cityName = findViewById(R.id.city_name);
        additionalCheck = findViewById(R.id.additional_check_id);
        additionalText = findViewById(R.id.additional_text_id);
        birthDateText = findViewById(R.id.birth_date_text);
        facultySpinner = findViewById(R.id.faculty_spinner);
        departmentSpinner = findViewById(R.id.department_spinner);
        birthplaceSpinner = findViewById(R.id.birthplace_spinner);
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
                if (editable.length() > 11) {
                    editable = editable.delete(11, 12);
                }
            }
        });
    }

    private void loadFacultySpinner() {
        Spinner facultySpinner = findViewById(R.id.faculty_spinner);
        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, facultyDepartmentHashMap.keySet().toArray());

        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        facultySpinner.setAdapter(ad);
        Spinner departmentSpinner = findViewById(R.id.department_spinner);
        facultySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedFaculty = facultySpinner.getSelectedItem().toString();
                String[] selectedFacultyDepartments = facultyDepartmentHashMap.get(selectedFaculty);
                ArrayAdapter<String> departmentAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_spinner_item, selectedFacultyDepartments);
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
        switch (view.getId()) {
            case R.id.submit_button:
                //TODO: Implement control mechanism to all defined widgets to check if fields are complete.
                boolean allFieldsFilled = true;
                if(studentIDText.getText().length() != 11 ||
                        name.getText().length() == 0 ||
                        last_name.getText().length() == 0 ||
                        gpa.getText().length() == 0 ||
                        !(sex_male.isChecked() || sex_female.isChecked()) ||
                        !(full_scholarship.isChecked() || half_scholarship.isChecked() || none_scholarship.isChecked()) ||
                        birthDateText.getText().toString() == getString(R.string.birth_date_not_selected)) allFieldsFilled = false;
                if(additionalCheck.isChecked() && additionalText.getText().length() == 0) allFieldsFilled = false;
                if(gpa.getText().length() < 4) allFieldsFilled = false;
                if(gpa.getText().length() == 4){
                    float gpaScore = Float.parseFloat(gpa.getText().toString());
                    if(gpaScore > 4.00 || gpaScore < 0) allFieldsFilled = false;
                }

                if (allFieldsFilled) {   // geçici oalrak true
                    StudentDatabaseHandler.instance.addStudentToDatabase(new StudentModel(studentIDText.getText().toString(),name.getText().toString(),last_name.getText().toString(),facultySpinner.getSelectedItem().toString(),departmentSpinner.getSelectedItem().toString()));
                    CreateStudentOutputDialog();

                } else
                    Toast.makeText(this, R.string.fields_are_incomplete_warning, Toast.LENGTH_SHORT).show();
                break;
            case R.id.reset_button:

                studentIDText.setText("");
                name.setText("");
                last_name.setText("");
                gpa.setText("");
                sex_male.setChecked(false);
                sex_female.setChecked(false);
                full_scholarship.setChecked(false);
                half_scholarship.setChecked(false);
                none_scholarship.setChecked(false);
                birthDateText.setText(R.string.birth_date_not_selected);
                additionalText.setText("");
                facultySpinner.setSelection(0);
                departmentSpinner.setSelection(0);
                birthplaceSpinner.setSelection(0);



                break;
            case R.id.exit_button:
                System.exit(0);
                break;
            case R.id.birth_date_button:
                new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        birthDateText.setText(day + "/" + month + "/" + year);

                    }
                }, 2020, 1, 1).show();
                break;
            default:
                break;
        }
    }



    public void CreateStudentOutputDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        final View contactPopupView = getLayoutInflater().inflate(R.layout.popup, null);

        dialogBuilder.setView(contactPopupView);
        Dialog dialog = dialogBuilder.create();
        dialog.show();


        String add_value = additionalText.getText().toString();
        String name_value = name.getText().toString();
        String Lname_value = last_name.getText().toString();
        String id_value = studentIDText.getText().toString();
        String gpa_value = gpa.getText().toString();
        sex_male = findViewById(R.id.male);
        sex_female = findViewById(R.id.female);

        String sex_value = "none";

        if (sex_male.isChecked()) {
            sex_value = "male";
        } else if (sex_female.isChecked()) {
            sex_value = "female";
        }


        String scholarship_value = "none";

        if (full_scholarship.isChecked()) {
            scholarship_value = "full";
        } else if (half_scholarship.isChecked()) {
            scholarship_value = "half";
        } else if (none_scholarship.isChecked()) {
            scholarship_value = "none";
        }

        TextView pop_add = contactPopupView.findViewById(R.id.pop_add);
        pop_add.setText(add_value);

        TextView pop_id = contactPopupView.findViewById(R.id.pop_id);
        pop_id.setText(id_value);

        TextView pop_name = contactPopupView.findViewById(R.id.pop_name);
        pop_name.setText(name_value);

        TextView pop_Lname = contactPopupView.findViewById(R.id.pop_Lname);
        pop_Lname.setText(Lname_value);

        TextView pop_gender = contactPopupView.findViewById(R.id.pop_gender);
        pop_gender.setText(sex_value);

        TextView pop_scholar = contactPopupView.findViewById(R.id.pop_scholar);
        pop_scholar.setText(scholarship_value);

        TextView pop_bp = contactPopupView.findViewById(R.id.pop_bp);
        pop_bp.setText(cityName.getText());

        TextView pop_gpa = contactPopupView.findViewById(R.id.pop_gpa);
        pop_gpa.setText(gpa_value);

        TextView pop_bd = contactPopupView.findViewById(R.id.pop_bd);
        pop_bd.setText(birthDateText.getText());

        pop_faculty = contactPopupView.findViewById(R.id.pop_faculty);

        pop_faculty.setText(facultySpinner.getSelectedItem().toString());

        popDepartment = contactPopupView.findViewById(R.id.pop_dep);

        popDepartment.setText(departmentSpinner.getSelectedItem().toString());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Submit");
        menu.add("Reset");
        menu.add("Display");
        menu.add("Search");
        menu.add("Update");
        menu.add("Help");
        menu.add("Delete");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String menuTitle = (String) item.getTitle();
        switch (menuTitle){
            case "Submit":
                Toast.makeText(this, "You clicked Submit", Toast.LENGTH_SHORT).show();
                break;
            case "Reset":
                break;
            case "Display":
                startActivity(new Intent(MainActivity.this,display_students.class));
                break;
            case "Search":
                startActivity(new Intent(this,search_student.class));
                break;
            case "Update":
                break;
            case "Help":
                break;
            case "Delete":
                startActivity(new Intent(this,delete_student.class));
                break;

        }
        return true;
    }
}
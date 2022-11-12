package com.rc2.cmpe408project01;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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
    private TextView pop_sex;
    private TextView pop_schl;
    private String city_value;
    private String faculty_value;

    String[] facultyList = {"Faculty of Arts","Faculty of Law","Faculty of Engineering"};
    JSONObject cities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();
        loadCities();
        loadBirthplaceSpinner();
        loadFacultySpinner();
        handleStudentIDTextChangedListener();
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
    }

    private void handleStudentIDTextChangedListener() {
        studentIDText = findViewById(R.id.student_id);
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
                if(true){  // geçici olarak true yoksa çalışmıyor hep else'e düşüyor
                    name = (EditText)findViewById(R.id.name_id);
                    String name_value = name.getText().toString();

                    last_name = (EditText)findViewById(R.id.last_name);
                    String Lname_value = last_name.getText().toString();

                    studentIDText = (EditText)findViewById(R.id.student_id);
                    String id_value = studentIDText.getText().toString();

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



                    CreateNewDialog(name_value,Lname_value,id_value,sex_value,scholarship_value,city_value);








                }
                else
                    Toast.makeText(this, R.string.fields_are_incomplete_warning, Toast.LENGTH_SHORT).show();
                break;
            case R.id.reset_button:

                id = (EditText)findViewById(R.id.student_id);
                id.setText("");

                name = (EditText)findViewById(R.id.name_id);
                name.setText("");

                last_name = (EditText)findViewById(R.id.last_name);
                last_name.setText("");

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












                break;
            case R.id.exit_button:
                System.exit(0);
                break;
            default:
                break;
        }
    }


// submitten sontraki ekran için

    public void CreateNewDialog(String name,String last_name,String id,String sex,String scholarship,String bp){
        dialogBuilder = new AlertDialog.Builder(this);
        final View contactPopupView = getLayoutInflater().inflate(R.layout.popup,null);

        dialogBuilder.setView(contactPopupView);
        dialog = dialogBuilder.create();
                dialog.show();

        pop_name = (TextView) contactPopupView.findViewById(R.id.pop_id);
        pop_name.setText(id);

        pop_name = (TextView) contactPopupView.findViewById(R.id.pop_name);
        pop_name.setText(name);

        pop_name = (TextView) contactPopupView.findViewById(R.id.pop_Lname);
        pop_name.setText(last_name);

        pop_name = (TextView) contactPopupView.findViewById(R.id.pop_gender);
        pop_name.setText(sex);

        pop_name = (TextView) contactPopupView.findViewById(R.id.pop_scholar);
        pop_name.setText(scholarship);

        pop_name = (TextView) contactPopupView.findViewById(R.id.pop_bp);
        pop_name.setText(bp);




    }


}
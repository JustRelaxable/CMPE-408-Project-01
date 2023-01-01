package com.rc2.cmpe408project01;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class update_student extends AppCompatActivity {

    EditText studentID, studentName, studentSurname, studentFaculty, studentDepartment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Update Students");

        studentID = findViewById(R.id.student_id);
        studentName = findViewById(R.id.student_name);
        studentSurname = findViewById(R.id.student_surname);
        studentDepartment = findViewById(R.id.student_department);
        studentFaculty = findViewById(R.id.student_faculty);
        findViewById(R.id.update_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StudentModel updatedStudent = new StudentModel(studentID.getText().toString(),
                        studentName.getText().toString(),
                        studentSurname.getText().toString(),
                        studentFaculty.getText().toString(),
                        studentDepartment.getText().toString());
                StudentDatabaseHandler.instance.updateStudent(updatedStudent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
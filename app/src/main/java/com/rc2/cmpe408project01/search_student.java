package com.rc2.cmpe408project01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class search_student extends AppCompatActivity {

    TextView studentName,studentID,studentFaculty,studentDepartment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_student);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Search Students");

        studentName = findViewById(R.id.student_full_name);
        studentID = findViewById(R.id.student_id);
        studentFaculty = findViewById(R.id.student_faculty);
        studentDepartment = findViewById(R.id.student_department);

        ((Button)findViewById(R.id.student_search_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText studentIDField = findViewById(R.id.student_id_field);
                StudentModel student = StudentDatabaseHandler.instance.getStudentByID(studentIDField.getText().toString());
                FillStudentFields(student);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    private void FillStudentFields(StudentModel student){
        studentName.setText("Student name: "+student.getStudentName()+" "+student.getStudentSurname());
        studentID.setText("Student ID: "+student.getStudentID());
        studentFaculty.setText("Student Faculty:"+student.getStudentFaculty());
        studentDepartment.setText("Student Department:"+student.getStudentDepartment());
    }
}
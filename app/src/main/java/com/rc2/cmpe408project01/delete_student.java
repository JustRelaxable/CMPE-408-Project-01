package com.rc2.cmpe408project01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class delete_student extends AppCompatActivity {

    EditText studentIDEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_student);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Delete Students");

        studentIDEditText = findViewById(R.id.student_id);
        ((Button)findViewById(R.id.delete_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StudentDatabaseHandler.instance.deleteStudentByID(studentIDEditText.getText().toString());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
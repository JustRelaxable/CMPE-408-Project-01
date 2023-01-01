package com.rc2.cmpe408project01;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class display_students extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_students);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Display Students");

        List<StudentModel> students = StudentDatabaseHandler.instance.getStudents();
        StudentListAdapter studentListAdapter = new StudentListAdapter(this, new ArrayList<StudentModel>(students));
        ListView studentListView = findViewById(R.id.student_list_view);
        studentListView.setAdapter(studentListAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
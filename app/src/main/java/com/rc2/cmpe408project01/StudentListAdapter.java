package com.rc2.cmpe408project01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentListAdapter extends BaseAdapter {

    private ArrayList<StudentModel> students;
    private LayoutInflater layoutInflater;

    public StudentListAdapter(Context context, ArrayList<StudentModel> students) {
        this.students = students;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int i) {
        return students.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View customView = layoutInflater.inflate(R.layout.student_list_instance, null);
        TextView fullNameField = (TextView) customView.findViewById(R.id.student_list_instance_full_name);
        fullNameField.setText(students.get(i).getStudentName() + " " + students.get(i).getStudentSurname());
        TextView idField = customView.findViewById(R.id.student_list_instance_id);
        idField.setText("ID:" + students.get(i).getStudentID());
        TextView departmentField = customView.findViewById(R.id.student_list_instance_department);
        departmentField.setText("Department:" + String.valueOf(students.get(i).getStudentDepartment()));
        return customView;
    }
}

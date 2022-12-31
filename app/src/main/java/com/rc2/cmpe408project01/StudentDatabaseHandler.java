package com.rc2.cmpe408project01;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class StudentDatabaseHandler extends SQLiteOpenHelper {
    public static StudentDatabaseHandler instance;
    private String tableName = "STUDENTS";

    public StudentDatabaseHandler(@Nullable Context context) {
        super(context, "studentDB", null, 1);
        instance = this;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlQuery = "CREATE TABLE "+ tableName+ "(StudentID varchar(255), StudentName varchar(255),StudentSurname varchar(255),studentFaculty int,studentDepartment int)";
        sqLiteDatabase.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addStudentToDatabase(StudentModel studentModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("StudentID",studentModel.getStudentID());
        cv.put("StudentName",studentModel.getStudentName());
        cv.put("StudentSurname",studentModel.getStudentSurname());
        cv.put("StudentFaculty",studentModel.getStudentFaculty());
        cv.put("StudentDepartment",studentModel.getStudentDepartment());
        db.insert(tableName,null,cv);
    }

    public List<StudentModel> getStudents(){
        SQLiteDatabase db = this.getWritableDatabase();
        List<StudentModel> studentList = new ArrayList<StudentModel>();
        String selectQuery = "SELECT * FROM " + tableName;
        Cursor cursor = db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()) {
            do {
                String studentID = cursor.getString(0);
                String studentName = cursor.getString(1);
                String studentSurname = cursor.getString(2);
                String studentFaculty = cursor.getString(3);
                String studentDepartment = cursor.getString(4);
                studentList.add(new StudentModel(studentID,studentName,studentSurname,studentFaculty,studentDepartment));
            } while (cursor.moveToNext());
        }
        return studentList;
    }
}

package com.rc2.cmpe408project01;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class StudentDatabaseHandler extends SQLiteOpenHelper {
    private String tableName = "STUDENTS";

    public StudentDatabaseHandler(@Nullable Context context) {
        super(context, "studentDB", null, 1);
        //String sqlQuery = "CREATE TABLE "+ tableName+ "(StudentID int)";
        //Toast.makeText(context, sqlQuery, Toast.LENGTH_SHORT).show();
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
}

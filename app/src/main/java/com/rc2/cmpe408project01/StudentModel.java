package com.rc2.cmpe408project01;

public class StudentModel {
    private String studentID,studentName,studentSurname,studentFaculty,studentDepartment;

    public StudentModel(String studentID,String studentName,String studentSurname,String studentFaculty,String studentDepartment){
        this.studentID = studentID;
        this.studentName = studentName;
        this.studentSurname = studentSurname;
        this.studentFaculty = studentFaculty;
        this.studentDepartment = studentDepartment;
    }

    public String getStudentDepartment() {
        return studentDepartment;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentSurname() {
        return studentSurname;
    }

    public String getStudentFaculty() {
        return studentFaculty;
    }
}

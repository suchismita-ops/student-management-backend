package com.studentmanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Student {
    
    @Id
    private int rollNo;
    
    private String username;
    private String name;
    private String department;
    private int semester;
    
    private double javaMarks;
    private double dbmsMarks;
    private double daaMarks;
    private double osMarks;
    private double webMarks;
    private double attendance;
    
    public Student() {
    }

    public Student(int rollNo, String username, String name, String department, int semester, double javaMarks, double dbmsMarks, double daaMarks, double osMarks, double webMarks, double attendance) {
        this.rollNo = rollNo;
        this.username = username;
        this.name = name;
        this.department = department;
        this.semester = semester;
        this.javaMarks = javaMarks;
        this.dbmsMarks = dbmsMarks;
        this.daaMarks = daaMarks;
        this.osMarks = osMarks;
        this.webMarks = webMarks;
        this.attendance = attendance;
    }

    public double calculateTotalMarks() {
        return javaMarks + dbmsMarks + daaMarks + osMarks + webMarks;
    }
    
    public double calculatePercentage() {
        return calculateTotalMarks() / 5.0;
    }
    
    public String calculateGrade() {
        double percentage = calculatePercentage();
        if (percentage >= 90) return "A+";
        if (percentage >= 80) return "A";
        if (percentage >= 70) return "B";
        if (percentage >= 60) return "C";
        if (percentage >= 50) return "D";
        return "F";
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public double getJavaMarks() {
        return javaMarks;
    }

    public void setJavaMarks(double javaMarks) {
        this.javaMarks = javaMarks;
    }

    public double getDbmsMarks() {
        return dbmsMarks;
    }

    public void setDbmsMarks(double dbmsMarks) {
        this.dbmsMarks = dbmsMarks;
    }

    public double getDaaMarks() {
        return daaMarks;
    }

    public void setDaaMarks(double daaMarks) {
        this.daaMarks = daaMarks;
    }

    public double getOsMarks() {
        return osMarks;
    }

    public void setOsMarks(double osMarks) {
        this.osMarks = osMarks;
    }

    public double getWebMarks() {
        return webMarks;
    }

    public void setWebMarks(double webMarks) {
        this.webMarks = webMarks;
    }

    public double getAttendance() {
        return attendance;
    }

    public void setAttendance(double attendance) {
        this.attendance = attendance;
    }
}

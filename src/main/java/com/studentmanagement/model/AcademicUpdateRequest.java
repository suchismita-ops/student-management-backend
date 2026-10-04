package com.studentmanagement.model;

public class AcademicUpdateRequest {
    private double javaMarks;
    private double dbmsMarks;
    private double daaMarks;
    private double osMarks;
    private double webMarks;
    private double attendance;
    
    public AcademicUpdateRequest() {}

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

package com.resumecraft.model;

public class Education {

    private String degree;
    private String institution;
    private String location;
    private String startDate;
    private String endDate;
    private String grade;

    public Education() {
    }

    public Education(String degree, String institution, String location,
                     String startDate, String endDate, String grade) {

        this.degree = degree;
        this.institution = institution;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.grade = grade;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
package com.ayush.attendance.model;

import java.time.LocalDate;

public class AttendanceRecord {

    private int studentId;
    private LocalDate date;
    private boolean present;

    public AttendanceRecord(int studentId, LocalDate date, boolean present) {
        this.studentId = studentId;
        this.date = date;
        this.present = present;
    }

    public int getStudentId() {
        return studentId;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isPresent() {
        return present;
    }

    @Override
    public String toString() {
        return "AttendanceRecord {" +
                "Student ID = " + studentId +
                ", Date = " + date +
                ", Present = " + present +
                '}';
    }
}

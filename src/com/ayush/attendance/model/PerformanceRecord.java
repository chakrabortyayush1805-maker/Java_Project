package com.ayush.attendance.model;

public class PerformanceRecord {

    private int studentId;
    private String subject;
    private double marksObtained;
    private double maxMarks;

    public PerformanceRecord(int studentId, String subject, double marksObtained, double maxMarks) {
        this.studentId = studentId;
        this.subject = subject;
        this.marksObtained = marksObtained;
        this.maxMarks = maxMarks;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getSubject() {
        return subject;
    }

    public double getMarksObtained() {
        return marksObtained;
    }

    public double getMaxMarks() {
        return maxMarks;
    }

    public double getPercentage() {
        if (maxMarks == 0) {
            return 0.0;
        }
        return (marksObtained / maxMarks) * 100.0;
    }

    @Override
    public String toString() {
        return "PerformanceRecord {" +
                "Student ID = " + studentId +
                ", Subject = '" + subject + '\'' +
                ", Marks = " + marksObtained +
                "/" + maxMarks +
                " (" + String.format("%.2f", getPercentage()) + "%)" +
                '}';
    }
}

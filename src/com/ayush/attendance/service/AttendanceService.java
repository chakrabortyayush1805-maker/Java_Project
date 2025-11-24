package com.ayush.attendance.service;

import com.ayush.attendance.model.AttendanceRecord;
import com.ayush.attendance.model.Student;
import com.ayush.attendance.model.StudentNotFoundException;
import java.util.Scanner;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class AttendanceService {

    private ArrayList<AttendanceRecord> attendanceRecords = new ArrayList<>();
    private StudentService studentService;

    // Constructor
    public AttendanceService(StudentService studentService) {
        this.studentService = studentService;
    }

    // Mark attendance for a student on a given date
    public void markAttendance(int studentId, LocalDate date, boolean present) {
        try {
            Student s = studentService.getStudentById(studentId);

            AttendanceRecord record = new AttendanceRecord(studentId, date, present);
            attendanceRecords.add(record);
            System.out.println("Attendance marked successfully for student ID " + studentId + " on " + date);

        } catch (StudentNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // View attendance for a specific student
    public void viewAttendanceForStudent(int studentId) {
        try {
            Student s = studentService.getStudentById(studentId);

            System.out.println("\nAttendance for: " + s.getName() + " (ID: " + studentId + ")");
            boolean found = false;

            for (AttendanceRecord record : attendanceRecords) {
                if (record.getStudentId() == studentId) {
                    System.out.println(record);
                    found = true;
                }
            }

            if (!found) {
                System.out.println("No attendance records found for this student.");
            }

        } catch (StudentNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // NEW: Load attendance from a file
    public void loadAttendanceFromFile(String fileName) {
        try (Scanner sc = new Scanner(new java.io.File(fileName))) {

            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                if (!line.contains("Student ID")) {
                    continue;
                }

                // Example:
                // AttendanceRecord {Student ID = 1, Date = 2025-01-12, Present = true}

                String[] parts = line.split(",");

                int studentId = Integer.parseInt(parts[0].split("=")[1].trim());
                LocalDate date = LocalDate.parse(parts[1].split("=")[1].trim());
                boolean present = Boolean.parseBoolean(parts[2].split("=")[1].replace("}", "").trim());

                AttendanceRecord record = new AttendanceRecord(studentId, date, present);
                attendanceRecords.add(record);
            }

            System.out.println("Attendance loaded from file.");

        } catch (Exception e) {
            System.out.println("No existing attendance found or file unreadable.");
        }
    }


    // NEW: Export attendance records to a text file
    public void exportAttendanceToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

            if (attendanceRecords.isEmpty()) {
                writer.write("No attendance records found.");
            } else {
                for (AttendanceRecord record : attendanceRecords) {
                    writer.write(record.toString());
                    writer.newLine();
                }
            }

            System.out.println("Attendance exported to file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error while writing attendance to file: " + e.getMessage());
        }
    }
}

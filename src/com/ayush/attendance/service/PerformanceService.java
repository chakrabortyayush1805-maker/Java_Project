package com.ayush.attendance.service;

import com.ayush.attendance.model.PerformanceRecord;
import com.ayush.attendance.model.Student;
import com.ayush.attendance.model.StudentNotFoundException;
import java.util.Scanner;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PerformanceService {

    private ArrayList<PerformanceRecord> performanceRecords = new ArrayList<>();
    private StudentService studentService;

    public PerformanceService(StudentService studentService) {
        this.studentService = studentService;
    }

    // Add marks for a student
    public void addMarks(int studentId, String subject, double marksObtained, double maxMarks) {
        try {
            Student s = studentService.getStudentById(studentId);

            if (marksObtained < 0 || maxMarks <= 0 || marksObtained > maxMarks) {
                System.out.println("Error: Invalid marks entered.");
                return;
            }

            PerformanceRecord record = new PerformanceRecord(studentId, subject, marksObtained, maxMarks);
            performanceRecords.add(record);
            System.out.println("Marks added successfully for student ID " + studentId + " in subject " + subject);

        } catch (StudentNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // View all marks for a specific student
    public void viewMarksForStudent(int studentId) {
        try {
            Student s = studentService.getStudentById(studentId);

            System.out.println("\nPerformance for: " + s.getName() + " (ID: " + studentId + ")");
            boolean found = false;

            double totalPercentage = 0.0;
            int count = 0;

            for (PerformanceRecord record : performanceRecords) {
                if (record.getStudentId() == studentId) {
                    System.out.println(record);
                    totalPercentage += record.getPercentage();
                    count++;
                    found = true;
                }
            }

            if (!found) {
                System.out.println("No performance records found for this student.");
            } else {
                double avg = totalPercentage / count;
                System.out.printf("Average Percentage: %.2f%%\n", avg);
            }

        } catch (StudentNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // NEW: Load performance records from a file
    public void loadPerformanceFromFile(String fileName) {
        try (Scanner sc = new Scanner(new java.io.File(fileName))) {

            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                if (!line.contains("Student ID")) {
                    continue;
                }

                // Example:
                // PerformanceRecord {Student ID = 1, Subject = 'Java', Marks = 80/100 (80.00%)}

                int studentId = Integer.parseInt(line.split("ID =")[1].split(",")[0].trim());
                String subject = line.split("Subject = '")[1].split("'")[0];

                String marksPart = line.split("Marks = ")[1].split("\\(")[0].trim();
                double marksObtained = Double.parseDouble(marksPart.split("/")[0]);
                double maxMarks = Double.parseDouble(marksPart.split("/")[1]);

                PerformanceRecord record =
                        new PerformanceRecord(studentId, subject, marksObtained, maxMarks);

                performanceRecords.add(record);
            }

            System.out.println("Performance records loaded from file.");

        } catch (Exception e) {
            System.out.println("No existing performance records found or file unreadable.");
        }
    }

    // NEW: Export performance records to a text file
    public void exportPerformanceToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

            if (performanceRecords.isEmpty()) {
                writer.write("No performance records found.");
            } else {
                for (PerformanceRecord record : performanceRecords) {
                    writer.write(record.toString());
                    writer.newLine();
                }
            }

            System.out.println("Performance exported to file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error while writing performance to file: " + e.getMessage());
        }
    }
}

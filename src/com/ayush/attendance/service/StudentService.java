package com.ayush.attendance.service;

import com.ayush.attendance.model.Student;
import com.ayush.attendance.model.StudentNotFoundException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentService {

    private ArrayList<Student> students = new ArrayList<>();

    // Add a student
    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Student added successfully!");
    }

    // List all students
    public void listStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        System.out.println("\n--- Student List ---");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    // Find student by ID or throw exception
    public Student getStudentById(int id) throws StudentNotFoundException {
        for (Student s : students) {
            if (s.getId() == id) {
                return s;
            }
        }
        throw new StudentNotFoundException("No student found with ID " + id);
    }

    // 🔹 NEW: Update student details
    public void updateStudent(int id, String newName, String newDept, int newSem)
            throws StudentNotFoundException {

        Student s = getStudentById(id); // may throw StudentNotFoundException

        s.setName(newName);
        s.setDepartment(newDept);
        s.setSemester(newSem);

        System.out.println("Student with ID " + id + " updated successfully.");
    }

    // 🔹 NEW: Delete student
    public void deleteStudent(int id) throws StudentNotFoundException {
        Student s = getStudentById(id); // will throw if not found

        students.remove(s);
        System.out.println("Student with ID " + id + " deleted successfully.");
    }

    // Export all students to a text file
    public void exportStudentsToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

            if (students.isEmpty()) {
                writer.write("No students found.");
            } else {
                for (Student s : students) {
                    writer.write(s.toString());
                    writer.newLine();
                }
            }

            System.out.println("Students exported to file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error while writing students to file: " + e.getMessage());
        }
    }

    // Load students from a file
    public void loadStudentsFromFile(String fileName) {
        try (Scanner sc = new Scanner(new java.io.File(fileName))) {

            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                // Skip empty or invalid lines
                if (!line.contains("ID =")) {
                    continue;
                }

                // Example line:
                // Student {ID = 1, Name = 'Ayush', Department = 'CSE', Semester = 2}
                String[] parts = line.split(",");

                int id = Integer.parseInt(parts[0].split("=")[1].trim());
                String name = parts[1].split("'")[1];
                String dept = parts[2].split("'")[1];
                int sem = Integer.parseInt(parts[3].replaceAll("[^0-9]", ""));

                Student s = new Student(id, name, dept, sem);
                students.add(s);
            }

            System.out.println("Students loaded from file.");

        } catch (Exception e) {
            System.out.println("No existing students found or file unreadable.");
        }
    }
}

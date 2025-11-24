package com.ayush.attendance;

import com.ayush.attendance.model.Student;
import com.ayush.attendance.model.StudentNotFoundException;
import com.ayush.attendance.service.AttendanceService;
import com.ayush.attendance.service.PerformanceService;
import com.ayush.attendance.service.StudentService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        StudentService studentService = new StudentService();
        AttendanceService attendanceService = new AttendanceService(studentService);
        PerformanceService performanceService = new PerformanceService(studentService);

        // Load existing data (if files exist)
        studentService.loadStudentsFromFile("students.txt");
        attendanceService.loadAttendanceFromFile("attendance.txt");
        performanceService.loadPerformanceFromFile("performance.txt");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Student Attendance & Performance Tracker ---");
            System.out.println("1. Add Student");
            System.out.println("2. List Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Mark Attendance");
            System.out.println("6. View Attendance for a Student");
            System.out.println("7. Add Marks / Performance");
            System.out.println("8. View Performance for a Student");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter Department: ");
                    String dept = scanner.nextLine();

                    System.out.print("Enter Semester: ");
                    int sem = scanner.nextInt();

                    Student s = new Student(id, name, dept, sem);
                    studentService.addStudent(s);
                    break;

                case 2:
                    studentService.listStudents();
                    break;

                case 3:
                    System.out.print("Enter Student ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter New Name: ");
                    String newName = scanner.nextLine();

                    System.out.print("Enter New Department: ");
                    String newDept = scanner.nextLine();

                    System.out.print("Enter New Semester: ");
                    int newSem = scanner.nextInt();
                    scanner.nextLine();

                    try {
                        studentService.updateStudent(updateId, newName, newDept, newSem);
                    } catch (StudentNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.print("Enter Student ID to delete: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Are you sure you want to delete this student? (y/n): ");
                    String confirm = scanner.nextLine().trim().toLowerCase();
                    if (confirm.equals("y") || confirm.equals("yes")) {
                        try {
                            studentService.deleteStudent(deleteId);
                        } catch (StudentNotFoundException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Delete operation cancelled.");
                    }
                    break;

                case 5:
                    System.out.print("Enter Student ID to mark attendance: ");
                    int sid = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter date (YYYY-MM-DD), or press Enter for today: ");
                    String dateInput = scanner.nextLine();

                    LocalDate date;
                    if (dateInput.isBlank()) {
                        date = LocalDate.now();
                    } else {
                        try {
                            date = LocalDate.parse(dateInput);
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid date format. Using today's date instead.");
                            date = LocalDate.now();
                        }
                    }

                    System.out.print("Is the student present? (y/n): ");
                    String presentInput = scanner.nextLine().trim().toLowerCase();
                    boolean present = presentInput.equals("y") || presentInput.equals("yes");

                    attendanceService.markAttendance(sid, date, present);
                    break;

                case 6:
                    System.out.print("Enter Student ID to view attendance: ");
                    int sidView = scanner.nextInt();
                    scanner.nextLine();

                    attendanceService.viewAttendanceForStudent(sidView);
                    break;

                case 7:
                    System.out.print("Enter Student ID to add marks: ");
                    int sidMarks = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter Subject Name: ");
                    String subject = scanner.nextLine();

                    System.out.print("Enter Marks Obtained: ");
                    double marksObtained = scanner.nextDouble();

                    System.out.print("Enter Maximum Marks: ");
                    double maxMarks = scanner.nextDouble();
                    scanner.nextLine();

                    performanceService.addMarks(sidMarks, subject, marksObtained, maxMarks);
                    break;

                case 8:
                    System.out.print("Enter Student ID to view performance: ");
                    int sidPerf = scanner.nextInt();
                    scanner.nextLine();

                    performanceService.viewMarksForStudent(sidPerf);
                    break;

                case 9:
                    System.out.println("Exporting data to files...");
                    studentService.exportStudentsToFile("students.txt");
                    attendanceService.exportAttendanceToFile("attendance.txt");
                    performanceService.exportPerformanceToFile("performance.txt");
                    System.out.println("Exiting... Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

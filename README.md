# Student Attendance & Performance Tracker

A console-based Java application to manage students, track their attendance, and record their academic performance.  
Built as part of the **Programming in Java** course (CSE2006). :contentReference[oaicite:1]{index=1}

---

## 📌 Features

- **Student Management**
    - Add new students (ID, name, department, semester)
    - List all students
    - Update student details
    - Delete student records

- **Attendance Management**
    - Mark attendance for a student on a given date
    - View attendance history for a specific student

- **Performance / Marks Management**
    - Add subject-wise marks and maximum marks
    - View all performance records for a student
    - Display average percentage for the student

- **Persistence with File I/O**
    - Automatically loads students, attendance, and performance data from text files on startup
    - Automatically exports all data to text files on exit:
        - `students.txt`
        - `attendance.txt`
        - `performance.txt`

- **Exception Handling**
    - Custom exception `StudentNotFoundException`
    - Graceful error messages when student IDs are invalid
    - Handling invalid date formats and invalid marks

---

## 🛠️ Technologies / Tools Used

- **Language:** Java (Oracle JDK 25)
- **IDE:** IntelliJ IDEA
- **Concepts Used:**
    - Classes, objects, constructors, encapsulation, inheritance (custom exception)
    - Collections (`ArrayList`)
    - Exception handling (`try-catch`, custom exceptions)
    - Java I/O Streams (`FileWriter`, `BufferedWriter`, `Scanner`)
    - Basic layered architecture (Model, Service, UI)

---

## 🗂️ Project Structure

```text
StudentAttendanceTracker/
  ├── src/
  │   └── com/ayush/attendance/
  │       ├── Main.java
  │       ├── model/
  │       │   ├── Student.java
  │       │   ├── AttendanceRecord.java
  │       │   ├── PerformanceRecord.java
  │       │   └── StudentNotFoundException.java
  │       └── service/
  │           ├── StudentService.java
  │           ├── AttendanceService.java
  │           └── PerformanceService.java
  ├── students.txt
  ├── attendance.txt
  ├── performance.txt
  ├── README.md
  └── statement.md

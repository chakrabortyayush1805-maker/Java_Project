# Problem Statement

In many colleges, student attendance and academic performance are tracked manually in registers or in scattered spreadsheets. This makes it difficult for faculty or class coordinators to:

- Quickly view a student's overall attendance and marks
- Maintain consistent, error-free records
- Get a combined view of attendance and performance in one place

There is a need for a simple desktop-based tool that can help manage students, record their attendance, store their marks, and provide basic reporting from a single system.

# Scope of the Project

This project focuses on building a console-based Java application that:

- Manages basic student information (ID, name, department, semester)
- Records and views attendance for individual students
- Records and views performance/marks for each student
- Supports update and delete operations on students
- Persists data across runs using text files

The current scope does not include:

- Graphical user interface (GUI)
- Multi-user login system
- Cloud deployment

These can be added as future enhancements.

# Target Users

- Class coordinators
- Faculty members
- Lab instructors
- Academic assistants

# High-level Features

- Add, list, update, and delete student records
- Mark attendance for students on specific dates
- View attendance history for a given student
- Add subject-wise marks for a student
- View all performance records and average percentage for a student
- Automatic saving of students, attendance, and performance data into text files on exit
- Automatic loading of existing data when the application starts
- Basic validation and error handling using custom exceptions


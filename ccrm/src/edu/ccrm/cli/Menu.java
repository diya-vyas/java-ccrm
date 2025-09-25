package edu.ccrm.cli;

import edu.ccrm.service.*;
import edu.ccrm.domain.*;
import edu.ccrm.io.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Menu {
    private final StudentService studentService = new StudentService();
    private final CourseService courseService = new CourseService();
    private final EnrollmentService enrollmentService = new EnrollmentService();
    private final ImportExportService importExportService = new ImportExportService(studentService, courseService,
            enrollmentService);

    private final BackupService backupService = new BackupService();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        int choice;
        MAIN_LOOP: // labeled loop for demonstration
        do {
            System.out.println("\n===== Campus Course & Records Manager =====");
            System.out.println("1. Manage Students");
            System.out.println("2. Manage Courses");
            System.out.println("3. Enrollment & Grades");
            System.out.println("4. Import/Export Data");
            System.out.println("5. Backup & Reports");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Invalid input, try again!");
                continue; // demonstrate continue
            }

            // Classic switch used here
            switch (choice) {
                case 1:
                    manageStudents();
                    break;
                case 2:
                    manageCourses();
                    break;
                case 3:
                    manageEnrollment();
                    break;
                case 4:
                    manageImportExport();
                    break;
                case 5:
                    manageBackupReports();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break MAIN_LOOP; // labeled break
                default:
                    System.out.println("Invalid choice!");
            }

        } while (true); // demonstrate do-while loop
    }

    private void manageEnrollment() {
        System.out.println("\n--- Enrollment Management ---");
        System.out.println("1. Enroll Student to Course");
        System.out.println("2. Unenroll Student from Course");
        System.out.println("3. Record Marks");
        System.out.println("4. List Enrollments");
        System.out.println("5. Print Transcript");
        System.out.print("Enter choice: ");
        int option = Integer.parseInt(scanner.nextLine());

        switch (option) {
            case 1 -> {
                System.out.print("Enter Student ID: ");
                String sid = scanner.nextLine();
                System.out.print("Enter Course Code: ");
                String code = scanner.nextLine();
                Student s = studentService.getStudent(sid);
                Course c = courseService.getCourse(code);
                if (s != null && c != null) {
                    enrollmentService.enroll(s, c);
                } else {
                    System.out.println("⚠️ Invalid Student ID or Course Code");
                }
            }
            case 2 -> {
                System.out.print("Enter Student ID: ");
                String sid = scanner.nextLine();
                System.out.print("Enter Course Code: ");
                String code = scanner.nextLine();
                Student s = studentService.getStudent(sid);
                Course c = courseService.getCourse(code);
                if (s != null && c != null) {
                    enrollmentService.unenroll(s, c);
                } else {
                    System.out.println("⚠️ Invalid Student ID or Course Code");
                }
            }
            case 3 -> {
                System.out.print("Enter Student ID: ");
                String sid = scanner.nextLine();
                System.out.print("Enter Course Code: ");
                String code = scanner.nextLine();
                System.out.print("Enter Marks: ");
                int marks = Integer.parseInt(scanner.nextLine());
                Student s = studentService.getStudent(sid);
                Course c = courseService.getCourse(code);
                if (s != null && c != null) {
                    enrollmentService.recordMarks(s, c, marks);
                } else {
                    System.out.println("⚠️ Invalid Student ID or Course Code");
                }
            }
            case 4 -> enrollmentService.listEnrollments();
            case 5 -> {
                System.out.print("Enter Student ID: ");
                String sid = scanner.nextLine();
                Student s = studentService.getStudent(sid);
                if (s != null) {
                    enrollmentService.printTranscript(s);
                } else {
                    System.out.println("⚠️ Invalid Student ID");
                }
            }
            default -> System.out.println("Invalid option");
        }
    }

    private void manageImportExport() {
        System.out.println("\n--- Import/Export ---");
        System.out.println("1. Import Students");
        System.out.println("2. Import Courses");
        System.out.println("3. Export Students");
        System.out.println("4. Export Courses");
        System.out.println("5. Export Enrollments");
        System.out.print("Enter choice: ");
        int option = Integer.parseInt(scanner.nextLine());

        switch (option) {
            case 1 -> importExportService.importStudents();
            case 2 -> importExportService.importCourses();
            case 3 -> importExportService.exportStudents();
            case 4 -> importExportService.exportCourses();
            case 5 -> importExportService.exportEnrollments();
            default -> System.out.println("Invalid option");
        }
    }

    private void manageBackupReports() {
        System.out.println("\n--- Backup & Reports ---");
        System.out.println("1. Backup Data");
        System.out.println("2. Compute Backup Directory Size");
        System.out.println("3. List Backup Files (depth 2)");
        
        int option = Integer.parseInt(scanner.nextLine());

        switch (option) {
            case 1 -> backupService.backup();
            case 2 -> {
                Path dir = Paths.get("backup");
                long size = backupService.computeDirSize(dir);
                System.out.println("Total Backup Size: " + size + " bytes");
            }
            case 3 -> backupService.listFilesByDepth(Paths.get("backup"), 2);
            default -> System.out.println("Invalid option");
        }
    }

    private void manageStudents() {
        System.out.println("\n--- Student Management ---");
        System.out.println("1. Add Student");
        System.out.println("2. List Students");
        System.out.println("3. Update Student");
        System.out.println("4. Deactivate Student");
        System.out.println("5. Print Student Profile & Transcript");
        System.out.print("Enter choice: ");
        int option = Integer.parseInt(scanner.nextLine());

        // Enhanced switch 
        switch (option) {
            case 1 -> {
                System.out.print("Enter ID: ");
                String id = scanner.nextLine();
                System.out.print("Enter Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter Email: ");
                String email = scanner.nextLine();
                System.out.print("Enter RegNo: ");
                String regNo = scanner.nextLine();
                Student s = new Student(id, name, email, regNo);
                studentService.addStudent(s);
                System.out.println("Student added: " + s);
            }
            case 2 -> {
                // Demonstrate enhanced-for loop
                System.out.println("Listing students with enhanced-for:");
                for (Student s : studentService.getAll()) {
                    System.out.println(s);
                }
            }
            case 3 -> {
                System.out.print("Enter ID to update: ");
                String id = scanner.nextLine();
                System.out.print("Enter New Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter New Email: ");
                String email = scanner.nextLine();
                studentService.updateStudent(id, name, email);
            }
            case 4 -> {
                System.out.print("Enter ID to deactivate: ");
                String id = scanner.nextLine();
                studentService.deactivateStudent(id);
            }
            case 5 -> {
                System.out.print("Enter ID to view profile: ");
                String id = scanner.nextLine();
                studentService.printProfile(id);
            }
            default -> System.out.println("Invalid option");
        }
    }

    private void manageCourses() {
        System.out.println("\n--- Course Management ---");
        System.out.println("1. Add Course");
        System.out.println("2. List Courses");
        System.out.println("3. Update Course");
        System.out.println("4. Deactivate Course");
        System.out.println("5. Search by Instructor");
        System.out.println("6. Search by Department");
        System.out.println("7. Search by Semester");
        System.out.print("Enter choice: ");
        int option = Integer.parseInt(scanner.nextLine());

        switch (option) {
            case 1 -> {
                System.out.print("Enter Code: ");
                String code = scanner.nextLine();
                System.out.print("Enter Title: ");
                String title = scanner.nextLine();
                System.out.print("Enter Credits: ");
                int credits = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter Instructor: ");
                String instructor = scanner.nextLine();
                System.out.print("Enter Semester (SPRING/SUMMER/FALL): ");
                Semester semester = Semester.valueOf(scanner.nextLine().toUpperCase());
                System.out.print("Enter Department: ");
                String dept = scanner.nextLine();

                Course c = new Course.Builder()
                        .code(code).title(title).credits(credits).instructor(instructor)
                        .semester(semester).department(dept).build();
                courseService.addCourse(c);
                System.out.println("✅ Course added: " + c);
            }
            case 2 -> courseService.listCourses();
            case 3 -> {
                System.out.print("Enter Course Code to update: ");
                String code = scanner.nextLine();
                System.out.print("Enter New Title: ");
                String title = scanner.nextLine();
                System.out.print("Enter New Credits: ");
                int credits = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter New Instructor: ");
                String instructor = scanner.nextLine();
                System.out.print("Enter New Semester (SPRING/SUMMER/FALL): ");
                Semester semester = Semester.valueOf(scanner.nextLine().toUpperCase());
                System.out.print("Enter New Department: ");
                String dept = scanner.nextLine();
                courseService.updateCourse(code, title, credits, instructor, semester, dept);
            }
            case 4 -> {
                System.out.print("Enter Course Code to deactivate: ");
                String code = scanner.nextLine();
                courseService.deactivateCourse(code);
            }
            case 5 -> {
                System.out.print("Enter Instructor: ");
                String instructor = scanner.nextLine();
                courseService.searchByInstructor(instructor);
            }
            case 6 -> {
                System.out.print("Enter Department: ");
                String dept = scanner.nextLine();
                courseService.searchByDepartment(dept);
            }
            case 7 -> {
                System.out.print("Enter Semester (SPRING/SUMMER/FALL): ");
                Semester semester = Semester.valueOf(scanner.nextLine().toUpperCase());
                courseService.searchBySemester(semester);
            }
            default -> System.out.println("Invalid option");
        }
    }

}

package edu.ccrm.io;

import edu.ccrm.domain.*;
import edu.ccrm.service.*;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class ImportExportService {
    private final Path dataPath = Paths.get("data");
    private final StudentService studentService;
    private final CourseService courseService;
    private final EnrollmentService enrollmentService;

    public ImportExportService(StudentService ss, CourseService cs, EnrollmentService es) {
        this.studentService = ss;
        this.courseService = cs;
        this.enrollmentService = es;
    }

    // ===== Import Students =====
    public void importStudents() {
        Path file = dataPath.resolve("students.csv");
        if (!Files.exists(file)) {
            System.out.println("⚠️ students.csv not found");
            return;
        }
        try {
            Files.lines(file).skip(1).forEach(line -> {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    Student s = new Student(parts[0], parts[1], parts[2], parts[3]);
                    studentService.addStudent(s);
                }
            });
            System.out.println("✅ Students imported.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ===== Import Courses =====
    public void importCourses() {
        Path file = dataPath.resolve("courses.csv");
        if (!Files.exists(file)) {
            System.out.println("⚠️ courses.csv not found");
            return;
        }
        try {
            Files.lines(file).skip(1).forEach(line -> {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    Course c = new Course.Builder()
                            .code(parts[0])
                            .title(parts[1])
                            .credits(Integer.parseInt(parts[2]))
                            .instructor(parts[3])
                            .semester(Semester.valueOf(parts[4].toUpperCase()))
                            .department(parts.length > 5 ? parts[5] : "General")
                            .build();
                    courseService.addCourse(c);
                }
            });
            System.out.println("✅ Courses imported.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ===== Export Students =====
    public void exportStudents() {
        Path file = dataPath.resolve("students_export.csv");
        try {
            Files.createDirectories(dataPath);
            List<String> lines = new ArrayList<>();
            lines.add("id,fullName,email,regNo");
            studentService.getAll().forEach(s ->
                    lines.add(String.join(",", s.getId(), s.getFullName(), s.getEmail(), s.getRegNo())));
            Files.write(file, lines);
            System.out.println("✅ Students exported to " + file.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ===== Export Courses =====
    public void exportCourses() {
        Path file = dataPath.resolve("courses_export.csv");
        try {
            Files.createDirectories(dataPath);
            List<String> lines = new ArrayList<>();
            lines.add("code,title,credits,instructor,semester,department");
            courseService.getAll().forEach(c ->
                    lines.add(String.join(",",
                            c.getCode(), c.getTitle(), String.valueOf(c.getCredits()),
                            c.getInstructor(), c.getSemester().toString(), c.getDepartment())));
            Files.write(file, lines);
            System.out.println("✅ Courses exported to " + file.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ===== Export Enrollments =====
    public void exportEnrollments() {
        Path file = dataPath.resolve("enrollments_export.csv");
        try {
            Files.createDirectories(dataPath);
            List<String> lines = new ArrayList<>();
            lines.add("studentId,courseCode,marks,grade");
            enrollmentService.getAll().forEach(e ->
                    lines.add(String.join(",",
                            e.getStudent().getId(), e.getCourse().getCode(),
                            String.valueOf(e.getMarks()), String.valueOf(e.getGrade()))));
            Files.write(file, lines);
            System.out.println("✅ Enrollments exported to " + file.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package edu.ccrm.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Student extends Person {
    private String regNo;
    private boolean active = true;
    private LocalDate enrollmentDate;
    private List<Course> enrolledCourses = new ArrayList<>();

    public Student(String id, String fullName, String email, String regNo) {
        super(id, fullName, email);
        this.regNo = regNo;
        this.enrollmentDate = LocalDate.now();
    }

    @Override
    public String getRole() {
        return "Student";
    }

    public String getRegNo() { return regNo; }
    public LocalDate getEnrollmentDate() { return enrollmentDate; }

    public boolean isActive() { return active; }
    public void deactivate() { this.active = false; }

    public void update(String fullName, String email) {
        this.fullName = fullName;
        this.email = email;
    }

    public void enrollCourse(Course course) {
        enrolledCourses.add(course);
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void printTranscript() {
        System.out.println("\n===== Transcript for " + fullName + " =====");
        if (enrolledCourses.isEmpty()) {
            System.out.println("No courses enrolled.");
        } else {
            enrolledCourses.forEach(c -> System.out.println("- " + c));
        }
    }

    @Override
    public String toString() {
        return "[ID=" + id +
               ", Name=" + fullName +
               ", Email=" + email +
               ", RegNo=" + regNo +
               ", Active=" + active +
               ", EnrolledOn=" + enrollmentDate +
               "]";
    }
}

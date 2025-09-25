package edu.ccrm.service;

import edu.ccrm.domain.*;
import java.util.*;

public class EnrollmentService {
    private final Map<String, List<Enrollment>> enrollments = new HashMap<>();
    private static final int MAX_CREDITS_PER_SEM = 20;

    public void enroll(Student s, Course c) {
        List<Enrollment> list = enrollments.computeIfAbsent(s.getId(), k -> new ArrayList<>());

        // Business rule: check max credits per semester
        int currentCredits = list.stream()
                .filter(e -> e.getCourse().getSemester() == c.getSemester())
                .mapToInt(e -> e.getCourse().getCredits())
                .sum();

        if (currentCredits + c.getCredits() > MAX_CREDITS_PER_SEM) {
            System.out.println("⚠️ Cannot enroll: exceeds max credits per semester (" + MAX_CREDITS_PER_SEM + ")");
            return;
        }

        Enrollment e = new Enrollment(s, c);
        list.add(e);
        s.enrollCourse(c);
        System.out.println("✅ Enrolled " + s.getId() + " into " + c.getCode());
    }

    public Collection<Enrollment> getAll() {
        return enrollments.values().stream().flatMap(List::stream).toList();
    }

    public void unenroll(Student s, Course c) {
        List<Enrollment> list = enrollments.get(s.getId());
        if (list != null) {
            list.removeIf(e -> e.getCourse().getCode().equals(c.getCode()));
            System.out.println("✅ Unenrolled " + s.getId() + " from " + c.getCode());
        } else {
            System.out.println("⚠️ Student not enrolled in any course");
        }
    }

    public void recordMarks(Student s, Course c, int marks) {
        List<Enrollment> list = enrollments.get(s.getId());
        if (list != null) {
            for (Enrollment e : list) {
                if (e.getCourse().getCode().equals(c.getCode())) {
                    e.recordMarks(marks);
                    System.out.println("✅ Marks recorded. Grade: " + e.getGrade());
                    return;
                }
            }
        }
        System.out.println("⚠️ Enrollment not found!");
    }

    public void listEnrollments() {
        enrollments.values().stream().flatMap(List::stream).forEach(System.out::println);
    }

    public void printTranscript(Student s) {
        List<Enrollment> list = enrollments.get(s.getId());
        if (list == null || list.isEmpty()) {
            System.out.println("⚠️ No courses for " + s.getId());
            return;
        }

        System.out.println("\n===== Transcript for " + s.getId() + " =====");
        int totalCredits = 0;
        int totalPoints = 0;

        for (Enrollment e : list) {
            System.out.println(e);
            if (e.getGrade() != null) {
                totalCredits += e.getCourse().getCredits();
                totalPoints += e.getCreditPoints();
            }
        }

        if (totalCredits > 0) {
            double gpa = (double) totalPoints / totalCredits;
            System.out.printf("GPA: %.2f%n", gpa);
        } else {
            System.out.println("No graded courses yet.");
        }
    }
}

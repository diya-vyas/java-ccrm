package edu.ccrm.service;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Semester;
import java.util.*;
import java.util.stream.Collectors;

public class CourseService {
    private final Map<String, Course> courses = new HashMap<>();

    public void addCourse(Course c) {
        courses.put(c.getCode(), c);
    }

    public Course getCourse(String code) {
        return courses.get(code);
    }

    public Collection<Course> getAll() {
        return courses.values();
    }

    public void listCourses() {
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
        } else {
            courses.values().forEach(System.out::println);
        }
    }

    public void updateCourse(String code, String title, int credits, String instructor, Semester semester,
            String department) {
        Course c = courses.get(code);
        if (c != null) {
            c.update(title, credits, instructor, semester, department);
            System.out.println("✅ Course updated: " + c);
        } else {
            System.out.println("⚠️ Course not found!");
        }
    }

    public void deactivateCourse(String code) {
        Course c = courses.get(code);
        if (c != null) {
            c.deactivate();
            System.out.println("✅ Course deactivated: " + c);
        } else {
            System.out.println("⚠️ Course not found!");
        }
    }

    // ===== Stream API Filters =====
    public void searchByInstructor(String instructor) {
        List<Course> result = courses.values().stream()
                .filter(c -> c.getInstructor().equalsIgnoreCase(instructor))
                .collect(Collectors.toList());
        result.forEach(System.out::println);
        if (result.isEmpty())
            System.out.println("⚠️ No courses found for instructor " + instructor);
    }

    public void searchByDepartment(String department) {
        List<Course> result = courses.values().stream()
                .filter(c -> c.getDepartment().equalsIgnoreCase(department))
                .collect(Collectors.toList());
        result.forEach(System.out::println);
        if (result.isEmpty())
            System.out.println("⚠️ No courses found for department " + department);
    }

    public void searchBySemester(Semester semester) {
        List<Course> result = courses.values().stream()
                .filter(c -> c.getSemester() == semester)
                .collect(Collectors.toList());
        result.forEach(System.out::println);
        if (result.isEmpty())
            System.out.println("⚠️ No courses found for semester " + semester);
    }
}

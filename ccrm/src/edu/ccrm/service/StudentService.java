package edu.ccrm.service;

import edu.ccrm.domain.Student;
import java.util.*;

public class StudentService {
    private final Map<String, Student> students = new HashMap<>();

    public void addStudent(Student s) {
        students.put(s.getId(), s);
    }

    public Student getStudent(String id) {
        return students.get(id);
    }

    public Collection<Student> getAll() {
        return students.values();
    }

    public void listStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            students.values().forEach(System.out::println);
        }
    }

    public void updateStudent(String id, String name, String email) {
        Student s = students.get(id);
        if (s != null) {
            s.update(name, email);
            System.out.println("✅ Student updated: " + s);
        } else {
            System.out.println("⚠️ Student not found!");
        }
    }

    public void deactivateStudent(String id) {
        Student s = students.get(id);
        if (s != null) {
            s.deactivate();
            System.out.println("✅ Student deactivated: " + s);
        } else {
            System.out.println("⚠️ Student not found!");
        }
    }

    public void printProfile(String id) {
        Student s = students.get(id);
        if (s != null) {
            System.out.println("\n===== Student Profile =====");
            System.out.println(s);
            s.printTranscript();
        } else {
            System.out.println("⚠️ Student not found!");
        }
    }
}

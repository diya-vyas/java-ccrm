package edu.ccrm.domain;

public class Enrollment {
    private final Student student;
    private final Course course;
    private int marks = -1; // -1 means not yet graded
    private Grade grade;

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    public void recordMarks(int marks) {
        this.marks = marks;
        this.grade = Grade.fromMarks(marks);
    }

    public int getMarks() { return marks; }
    public Grade getGrade() { return grade; }
    public Course getCourse() { return course; }
    public Student getStudent() { return student; }

    public int getCreditPoints() {
        if (grade == null) return 0;
        return grade.getPoints() * course.getCredits();
    }

    @Override
    public String toString() {
        return student.getId() + " enrolled in " + course.getCode() +
                " | Marks=" + (marks == -1 ? "NA" : marks) +
                " | Grade=" + (grade == null ? "NA" : grade);
    }
}

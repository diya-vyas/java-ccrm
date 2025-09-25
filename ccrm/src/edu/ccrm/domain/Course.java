package edu.ccrm.domain;

public class Course {
    private String code;
    private String title;
    private int credits;
    private String instructor;
    private Semester semester;
    private String department;
    private boolean active = true;

    private Course(Builder builder) {
        this.code = builder.code;
        this.title = builder.title;
        this.credits = builder.credits;
        this.instructor = builder.instructor;
        this.semester = builder.semester;
        this.department = builder.department;
    }

    // ===== Builder Pattern =====
    public static class Builder {
        private String code;
        private String title;
        private int credits;
        private String instructor;
        private Semester semester;
        private String department;

        public Builder code(String code) { this.code = code; return this; }
        public Builder title(String title) { this.title = title; return this; }
        public Builder credits(int credits) { this.credits = credits; return this; }
        public Builder instructor(String instructor) { this.instructor = instructor; return this; }
        public Builder semester(Semester semester) { this.semester = semester; return this; }
        public Builder department(String department) { this.department = department; return this; }

        public Course build() { return new Course(this); }
    }

    // ===== Getters =====
    public String getCode() { return code; }
    public String getTitle() { return title; }
    public int getCredits() { return credits; }           // <-- important getter
    public String getInstructor() { return instructor; }
    public Semester getSemester() { return semester; }
    public String getDepartment() { return department; }
    public boolean isActive() { return active; }

    // ===== Mutators / helpers =====
    public void deactivate() { this.active = false; }

    public void update(String title, int credits, String instructor, Semester semester, String department) {
        this.title = title;
        this.credits = credits;
        this.instructor = instructor;
        this.semester = semester;
        this.department = department;
    }

    @Override
    public String toString() {
        return "[Code=" + code +
               ", Title=" + title +
               ", Credits=" + credits +
               ", Instructor=" + instructor +
               ", Semester=" + semester +
               ", Department=" + department +
               ", Active=" + active +
               "]";
    }
}

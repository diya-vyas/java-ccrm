package edu.ccrm.domain;

public abstract class Person {
    protected String id;
    protected String fullName;
    protected String email;

    public Person(String id, String fullName, String email) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }

    // New Getters
    public String getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }

    public abstract String getRole();

    @Override
    public String toString() {
        return getRole() + ": " + fullName + " (" + email + ")";
    }
}

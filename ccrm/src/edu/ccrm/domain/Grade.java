package edu.ccrm.domain;

public enum Grade {
    S(10), A(9), B(8), C(7), D(6), E(5), F(0);

    private final int points;

    Grade(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public static Grade fromMarks(int marks) {
        if (marks >= 90) return S;
        else if (marks >= 80) return A;
        else if (marks >= 70) return B;
        else if (marks >= 60) return C;
        else if (marks >= 50) return D;
        else if (marks >= 40) return E;
        else return F;
    }
}

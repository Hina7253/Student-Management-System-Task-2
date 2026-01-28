// Student.java
public class Student {
    private int id;
    private String name;
    private double marks;

    public Student(int id, String name, double marks, String course) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMarks() {
        return marks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public String getGrade() {
        if (marks >= 90) return "A+";
        else if (marks >= 75) return "A";
        else if (marks >= 60) return "B";
        else return "C";
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | Marks: %.2f", id, name, marks);
    }

    public void setCourse(String course) {
    }
}


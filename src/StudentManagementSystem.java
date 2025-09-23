// StudentManagementSystem.java
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class StudentManagementSystem {
    private ArrayList<Student> students = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new StudentManagementSystem().run();
    }

    private void run() {
        while (true) {
            showMenu();
            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewAllStudents();
                case 3 -> updateStudent();
                case 4 -> deleteStudent();
                case 5 -> searchStudent();
                case 6 -> {
                    System.out.println("Exiting. Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void showMenu() {
        System.out.println("\n=== Student Record Management ===");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Update Student");
        System.out.println("4. Delete Student");
        System.out.println("5. Search Student by ID");
        System.out.println("6. Exit");
    }

    private void addStudent() {
        int id = readInt("Enter student ID: ");
        if (findById(id) != null) {
            System.out.println("A student with this ID already exists.");
            return;
        }
        String name = readLine("Enter name: ");
        double marks = readDouble("Enter marks: ");
        students.add(new Student(id, name, marks));
        System.out.println("Student added successfully.");
    }

    private void viewAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }
        System.out.println("\nAll Students:");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    private void updateStudent() {
        int id = readInt("Enter ID of student to update: ");
        Student s = findById(id);
        if (s == null) {
            System.out.println("Student not found with ID: " + id);
            return;
        }
        System.out.println("Current: " + s);
        String newName = readLine("Enter new name (leave blank to keep): ");
        if (!newName.isEmpty()) s.setName(newName);

        String marksInput = readLine("Enter new marks (leave blank to keep): ");
        if (!marksInput.isEmpty()) {
            try {
                double newMarks = Double.parseDouble(marksInput);
                s.setMarks(newMarks);
            } catch (NumberFormatException e) {
                System.out.println("Invalid marks input. Keeping old marks.");
            }
        }
        System.out.println("Student updated: " + s);
    }

    private void deleteStudent() {
        int id = readInt("Enter ID of student to delete: ");
        Iterator<Student> it = students.iterator();
        while (it.hasNext()) {
            Student s = it.next();
            if (s.getId() == id) {
                String confirm = readLine("Are you sure you want to delete " + s.getName() + " ? (y/n): ");
                if (confirm.equalsIgnoreCase("y")) {
                    it.remove();
                    System.out.println("Student deleted.");
                } else {
                    System.out.println("Delete cancelled.");
                }
                return;
            }
        }
        System.out.println("Student not found with ID: " + id);
    }

    private void searchStudent() {
        int id = readInt("Enter ID to search: ");
        Student s = findById(id);
        if (s == null) {
            System.out.println("No student with ID: " + id);
        } else {
            System.out.println("Found: " + s);
        }
    }

    private Student findById(int id) {
        for (Student s : students) {
            if (s.getId() == id) return s;
        }
        return null;
    }

    // Helper input methods using nextLine for safe input handling
    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number (e.g., 78.5).");
            }
        }
    }

    private String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}

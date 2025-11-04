import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class StudentManagementSystem {
    private static List<Student> students = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Update Student Details");
            System.out.println("5. Delete Student");
            System.out.println("6. Display Top 3 Students");
            System.out.println("7. Save Data to File");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewStudents();
                case 3 -> searchStudent();
                case 4 -> updateStudent();
                case 5 -> deleteStudent();
                case 6 -> displayTopStudents();
                case 7 -> saveToFile();
                case 8 -> System.out.println("👋 Exiting... Thank you!");
                default -> System.out.println("❌ Invalid choice!");
            }
        } while (choice != 8);
    }

    private static void addStudent() {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Marks: ");
        double marks = sc.nextDouble();
        sc.nextLine();
        System.out.print("Enter Course: ");
        String course = sc.nextLine();

        students.add(new Student(id, name, marks, course));
        System.out.println("✅ Student added successfully!");
    }

    private static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available!");
            return;
        }
        for (Student s : students) {
            System.out.println(s);
        }
    }

    private static void searchStudent() {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        for (Student s : students) {
            if (s.getId() == id) {
                System.out.println(s);
                return;
            }
        }
        System.out.println("❌ Student not found!");
    }

    private static void updateStudent() {
        System.out.print("Enter Student ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        for (Student s : students) {
            if (s.getId() == id) {
                System.out.print("Enter new Name: ");
                String name = sc.nextLine();
                System.out.print("Enter new Marks: ");
                double marks = sc.nextDouble();
                sc.nextLine();
                System.out.print("Enter new Course: ");
                String course = sc.nextLine();
                s.setName(name);
                s.setMarks(marks);
                s.setCourse(course);
                System.out.println("✅ Student updated successfully!");
                return;
            }
        }
        System.out.println("❌ Student not found!");
    }

    private static void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        int id = sc.nextInt();
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student s = iterator.next();
            if (s.getId() == id) {
                iterator.remove();
                System.out.println("🗑️ Student deleted!");
                return;
            }
        }
        System.out.println("❌ Student not found!");
    }

    private static void displayTopStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available!");
            return;
        }
        students.sort(Comparator.comparing(Student::getMarks).reversed());
        System.out.println("🏆 Top Students:");
        for (int i = 0; i < Math.min(3, students.size()); i++) {
            System.out.println(students.get(i));
        }
    }

    private static void saveToFile() {
        try {
            FileWriter writer = new FileWriter("students.txt");
            for (Student s : students) {
                writer.write(s.toString() + "\n");
            }
            writer.close();
            System.out.println("💾 Data saved successfully in 'students.txt'!");
        } catch (IOException e) {
            System.out.println("❌ Error saving data!");
        }
    }
}

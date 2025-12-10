import java.util.Collection;
import java.util.Scanner;

public class StudentManagementApp {

    private static Scanner scanner = new Scanner(System.in);
    private static StudentManager manager = new StudentManager();

    public static void main(String[] args) {
        int choice;

        do {
            printMenu();
            choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    searchStudentById();
                    break;
                case 4:
                    updateStudent();
                    break;
                case 5:
                    deleteStudent();
                    break;
                case 6:
                    System.out.println("Exiting the application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }

            System.out.println();

        } while (choice != 6);
    }

    private static void printMenu() {
        System.out.println("===== Student Management System =====");
        System.out.println("1. Add New Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student by ID");
        System.out.println("4. Update Student Details");
        System.out.println("5. Delete Student");
        System.out.println("6. Exit");
        System.out.println("=====================================");
    }

    private static void addStudent() {
        System.out.println("--- Add New Student ---");
        int id = readInt("Enter student ID: ");
        scanner.nextLine();

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        int age = readInt("Enter age: ");
        scanner.nextLine();

        System.out.print("Enter course: ");
        String course = scanner.nextLine();

        double marks = readDouble("Enter marks: ");

        Student student = new Student(id, name, age, course, marks);
        boolean added = manager.addStudent(student);

        if (added) {
            System.out.println("Student added successfully.");
        } else {
            System.out.println("Student with this ID already exists. Try another ID.");
        }
    }

    private static void viewAllStudents() {
        System.out.println("--- All Students ---");
        Collection<Student> students = manager.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        for (Student s : students) {
            System.out.println(s);
        }
    }

    private static void searchStudentById() {
        System.out.println("--- Search Student ---");
        int id = readInt("Enter student ID to search: ");

        Student student = manager.getStudentById(id);
        if (student == null) {
            System.out.println("Student not found with ID: " + id);
        } else {
            System.out.println("Student found:");
            System.out.println(student);
        }
    }

    private static void updateStudent() {
        System.out.println("--- Update Student ---");
        int id = readInt("Enter student ID to update: ");
        scanner.nextLine();

        Student existing = manager.getStudentById(id);
        if (existing == null) {
            System.out.println("No student found with ID: " + id);
            return;
        }

        System.out.println("Existing details: " + existing);

        System.out.print("Enter new name: ");
        String name = scanner.nextLine();

        int age = readInt("Enter new age: ");
        scanner.nextLine();

        System.out.print("Enter new course: ");
        String course = scanner.nextLine();

        double marks = readDouble("Enter new marks: ");

        boolean updated = manager.updateStudent(id, name, age, course, marks);

        if (updated) {
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Update failed. Student not found.");
        }
    }

    private static void deleteStudent() {
        System.out.println("--- Delete Student ---");
        int id = readInt("Enter student ID to delete: ");

        boolean deleted = manager.deleteStudent(id);
        if (deleted) {
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("No student found with ID: " + id);
        }
    }

    private static int readInt(String message) {
        while (true) {
            System.out.print(message);
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next();
            }
        }
    }

    private static double readDouble(String message) {
        while (true) {
            System.out.print(message);
            if (scanner.hasNextDouble()) {
                return scanner.nextDouble();
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
        }
    }
}

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class StudentManager {

    private Map<Integer, Student> students;

    public StudentManager() {
        this.students = new HashMap<>();
    }

    public boolean addStudent(Student student) {
        if (students.containsKey(student.getId())) {
            return false;
        }
        students.put(student.getId(), student);
        return true;
    }

    public Collection<Student> getAllStudents() {
        return students.values();
    }

    public Student getStudentById(int id) {
        return students.get(id);
    }

    public boolean updateStudent(int id, String name, int age, String course, double marks) {
        Student existing = students.get(id);
        if (existing == null) {
            return false;
        }
        existing.setName(name);
        existing.setAge(age);
        existing.setCourse(course);
        existing.setMarks(marks);
        return true;
    }

    public boolean deleteStudent(int id) {
        return students.remove(id) != null;
    }
}

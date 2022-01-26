package lux.task.jface;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * This class created student list in the table for save it in file
 * 
 * @author
 *
 */
public class StudentList {

    private HashMap<Integer, Student> students = new HashMap<Integer, Student>();
    private Set<IStudentListListener> changeListeners = new HashSet<>();
    private int nextId = 0;

    /**
     * get all students in the table
     * 
     * @return students data
     */
    public Collection<Student> getStudents() {
        return students.values();
    }

    /**
     * added new student
     * 
     * @return new student
     */
    public Student create() {
        return new Student(nextId++);
    }

    /**
     * saved new student or update student data
     * 
     * @param student
     */
    public void saveStudent(Student student) {
        if (!(students.containsKey(student.getId()))) {
            students.put(student.getId(), student);
            changeListeners.forEach(l -> l.studentAdded(student));
        } else {
            changeListeners.forEach(l -> l.studentUpdated(student));
        }
    }

    /**
     * removed student
     * 
     * @param student
     */
    public void removeStudent(Student student) {
        students.remove(student.getId());
        changeListeners.forEach(l -> l.studentRemoved(student));
    }

    /**
     * removed listener
     * 
     * @param listener
     */
    public void removeChangeListener(IStudentListListener listener) {
        changeListeners.remove(listener);
    }

    /**
     * added listener
     * 
     * @param listener
     */
    public void addChangeListener(IStudentListListener listener) {
        changeListeners.add(listener);
    }

}

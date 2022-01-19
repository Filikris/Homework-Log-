package lux.task.jface;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.swt.widgets.Listener;

public class StudentList {

    private List<Student> students = new ArrayList<Student>();
    private Set<IStudentListListener> changeListeners = new HashSet<>();
    private static final String[] STUDENTS_ARRAY = { "?", "Nancy", "Larry", "Joe" };
    private Student selectedStudent;

    public StudentList() {
	this.initData();
    }

    private void initData() {
	Student student;
	for (int i = 0; i < 10; i++) {
	    student = new Student();
	    student.setName(STUDENTS_ARRAY[i % 3]);
	    student.setGroup("Group " + i);
	    students.add(student);
	}
    };

    public List<Student> getStudents() {
	return students;
    }

    public void addStudent() {
	Student student = new Student();
	students.add(students.size(), student);
	changeListeners.forEach(l -> l.studentAdded(student));
    }

    public void removeStudent(Student student) {
	students.remove(student);
	changeListeners.forEach(l -> l.studentRemoved(student));
    }

    public void studentChanged(Student student) {
	changeListeners.forEach(l -> l.studentUpdated(student));
    }

    public void removeChangeListener(IStudentListListener listener) {
	changeListeners.remove(listener);
    }

    public void addChangeListener(IStudentListListener listener) {
	changeListeners.add(listener);
    }
    
    public void selectStudent(Student student) {
	selectedStudent = student;
	
    }
    
    public Student getSelected() {
	return selectedStudent;
    }

}

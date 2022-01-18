package lux.task.jface;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class StudentList {

    private List<Student> students = new ArrayList<Student>();
    private Set changeListeners = new HashSet();
    private static final String[] STUDENTS_ARRAY = { "?", "Nancy", "Larry", "Joe" };

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
	Iterator iterator = changeListeners.iterator();
	while (iterator.hasNext())
	    ((IStudentListViewer) iterator.next()).addStudent(student);
    }

    public void removeStudent(Student student) {
	students.remove(student);
	Iterator iterator = changeListeners.iterator();
	while (iterator.hasNext())
	    ((IStudentListViewer) iterator.next()).removeStudent(student);
    }

    public void studentChanged(Student student) {
	Iterator iterator = changeListeners.iterator();
	while (iterator.hasNext())
	    ((IStudentListViewer) iterator.next()).updateStudent(student);
    }

    public void removeChangeListener(IStudentListViewer viewer) {
	changeListeners.remove(viewer);
    }

    public void addChangeListener(IStudentListViewer viewer) {
	changeListeners.add(viewer);
    }

}

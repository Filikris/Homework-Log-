package lux.task.jface;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class StudentList {

    private HashMap<Integer, Student> students = new HashMap<Integer, Student>();
    private Set<IStudentListListener> changeListeners = new HashSet<>();
    private static final String[] STUDENTS_ARRAY = { "?", "Nancy", "Larry", "Joe" };
    private int nextId = 0;

    public StudentList() {
	this.initData();
    }

    private void initData() {
	Student student;
	for (int i = 0; i < 10; i++) {
	    student = new Student(nextId++);
	    student.setName(STUDENTS_ARRAY[i % 3]);
	    student.setGroup("Group " + i);
	    students.put(student.getId(), student);
	}
    };

    public Collection<Student> getStudents() {
	return students.values();
    }

    public Student create() {
	return new Student(nextId++);
    }

    public void saveStudent(Student student) {
	if (!(students.containsKey(student.getId()))) {
	    students.put(student.getId(), student);
	    changeListeners.forEach(l -> l.studentAdded(student));
	} else {
	    changeListeners.forEach(l -> l.studentUpdated(student));
	}
    }

    public void removeStudent(Student student) {
	students.remove(student.getId());
	changeListeners.forEach(l -> l.studentRemoved(student));
    }

    public void removeChangeListener(IStudentListListener listener) {
	changeListeners.remove(listener);
    }

    public void addChangeListener(IStudentListListener listener) {
	changeListeners.add(listener);
    }

}

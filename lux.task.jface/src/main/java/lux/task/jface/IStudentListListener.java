package lux.task.jface;

/**
 * An interface to student list
 * 
 * @author
 *
 */

public interface IStudentListListener {

    public void studentAdded(Student student);

    public void studentRemoved(Student student);

    public void studentUpdated(Student student);

}

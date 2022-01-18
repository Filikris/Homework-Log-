package lux.task.jface;

public interface IStudentListListener {
    
    public void studentAdded(Student student);

    public void studentRemoved(Student student);

    public void studentUpdated(Student student);

}

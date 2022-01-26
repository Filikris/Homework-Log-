package lux.task.jface;

/**
 * This class create student
 * 
 * @author
 *
 */

public class Student {
    private String name;
    private String group;
    private boolean isDone;
    private int id;

    /**
     * constructor
     * 
     * @param id
     */
    Student(int id) {
        this.id = id;
        name = "";
        group = "";
        isDone = false;
    }

    /**
     * get student name
     * 
     * @return student name
     */
    public String getName() {
        return name;
    }

    /**
     * get student group
     * 
     * @return student group
     */
    public String getGroup() {
        return group;
    }

    /**
     * get task status
     * 
     * @return task status
     */
    public boolean isTaskDone() {
        return isDone;
    }

    /**
     * put student name
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * put student group
     * 
     * @param group
     */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * put task status
     * 
     * @param done
     */
    public void setIsTaskDone(boolean done) {
        isDone = done;
    }

    /**
     * get student id
     * 
     * @return student id
     */
    public int getId() {
        return id;
    }

}

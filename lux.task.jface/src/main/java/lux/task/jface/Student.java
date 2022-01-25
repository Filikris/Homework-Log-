package lux.task.jface;

public class Student {
    private String name;
    private String group;
    private boolean isDone;
    private int id;

    Student(int id) {
	this.id = id;
	name = "";
	group = "";
	isDone = false;
    }

    public String getName() {
	return name;
    }

    public String getGroup() {
	return group;
    }

    public boolean isTaskDone() {
	return isDone;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setGroup(String group) {
	this.group = group;
    }

    public void setIsTaskDone(boolean done) {
	isDone = done;
    }
    
    public int getId() {
        return id;
    }

}

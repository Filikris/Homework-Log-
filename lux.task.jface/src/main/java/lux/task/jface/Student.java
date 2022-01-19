package lux.task.jface;

public class Student {
    private String name;
    private String group;
    private boolean isDone;

    Student() {
    }

    public String getName() {
	return name;
    }

    public String getGroup() {
	return group;
    }

    public boolean isDone() {
	return isDone;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setGroup(String group) {
	this.group = group;
    }

    public void setIsDone(boolean done) {
	isDone = done;
    }

}

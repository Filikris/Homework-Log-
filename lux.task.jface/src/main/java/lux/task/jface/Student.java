package lux.task.jface;

public class Student {
	private String name;
    private String group;
    private Boolean taskDone;

    Student(){}
    
    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }
    
    public Boolean getTaskDone() {
    	return taskDone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroup(String group) {
        this.group = group;
    }
    
    public void setTaskDone(Boolean done) {
    	taskDone = done;
    }

}

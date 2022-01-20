package lux.task.jface;

import org.eclipse.jface.action.Action;

public interface IStudentActionProvider {
    
    Action getAction(StudentAction key);
}

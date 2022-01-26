package lux.task.jface;

import org.eclipse.jface.action.Action;

/**
 * An interface to action providers for get student action
 * 
 * @author
 *
 */

public interface IStudentActionProvider {

    Action getAction(StudentAction key);
}

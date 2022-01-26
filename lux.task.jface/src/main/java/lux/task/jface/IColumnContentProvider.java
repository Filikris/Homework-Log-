package lux.task.jface;

/**
 * An interface to content providers for compare column
 * 
 * @author
 *
 */

public interface IColumnContentProvider {
    Comparable getValue(Object element, int column);
}

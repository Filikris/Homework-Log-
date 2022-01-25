package lux.task.jface;

public interface IColumnContentProvider {
    Comparable getValue(Object element, int column);
}

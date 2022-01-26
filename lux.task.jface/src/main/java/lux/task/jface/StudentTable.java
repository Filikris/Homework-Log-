package lux.task.jface;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

/**
 * This class create table with student data
 * 
 * @author
 *
 */
public class StudentTable extends TableViewer {

    /**
     * constructor setup the TableViewer
     * 
     * @param parent
     * @param style
     */

    public StudentTable(Composite parent, int style) {
        super(parent, style);
        Table table = getTable();

        table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        createColumns();

        new TableSorter(this);

        setContentProvider(new StudentContentProvider());
    }

    /**
     * create column in the table
     */
    private void createColumns() {

        // Column 1 : Description (student name)
        TableViewerColumn nameCol = new TableViewerColumn(this, SWT.LEFT, 0);
        nameCol.getColumn().setText("Name");
        nameCol.getColumn().setWidth(100);
        nameCol.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                Student student = (Student) element;
                return student.getName();
            }
        });

        // Column 2 : Description (group number)
        TableViewerColumn groupCol = new TableViewerColumn(this, SWT.LEFT, 1);
        groupCol.getColumn().setText("Group");
        groupCol.getColumn().setWidth(100);
        groupCol.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                Student student = (Student) element;
                return student.getGroup();
            }
        });

        // Column 1 : Completed (Checkbox)
        TableViewerColumn doneCol = new TableViewerColumn(this, SWT.LEFT, 2);
        doneCol.getColumn().setText("SWT done");
        doneCol.getColumn().setWidth(100);
        doneCol.setLabelProvider(new StudentCheckBoxLabelProvider(doneCol.getViewer()));
    }

    /**
     * InnerClass that acts as a proxy for the StudentList providing content for the
     * Table. It implements the IStudentListListener interface since it must
     * register changeListeners with the StudentList. It implements the
     * IColumnContentProvider interface since it must compare elements in the table.
     */

    class StudentContentProvider implements IStructuredContentProvider, IStudentListListener, IColumnContentProvider {

        /**
         * Return the students as an array of Objects
         */
        public Object[] getElements(Object inputElement) {
            return ((StudentList) inputElement).getStudents().toArray();
        }

        public void inputChanged(Viewer v, Object oldInput, Object newInput) {
            if (newInput != null)
                ((StudentList) newInput).addChangeListener(this);
            if (oldInput != null)
                ((StudentList) oldInput).removeChangeListener(this);
        }

        public void dispose() {
            ((StudentList) getInput()).removeChangeListener(this);
        }

        /**
         * added student
         */
        public void studentAdded(Student student) {
            add(student);
        }

        /**
         * removed student
         */
        public void studentRemoved(Student student) {
            remove(student);
        }

        /**
         * updated student data
         */
        public void studentUpdated(Student student) {
            update(student, null);
        }

        /**
         * get value from table for compare column
         */
        @Override
        public Comparable getValue(Object element, int column) {
            Student student = (Student) element;
            switch (column) {
            case 0:
                return student.getName();
            case 1:
                return student.getGroup();
            case 2:
                return student.isTaskDone();
            default:
                return null;
            }
        }

    }
}

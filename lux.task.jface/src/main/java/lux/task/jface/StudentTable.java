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

public class StudentTable extends TableViewer {

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

    private void createColumns() {
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

        TableViewerColumn doneCol = new TableViewerColumn(this, SWT.LEFT, 2);
        doneCol.getColumn().setText("SWT done");
        doneCol.getColumn().setWidth(100);
        doneCol.setLabelProvider(new StudentCheckBoxLabelProvider(doneCol.getViewer()));
    }

    class StudentContentProvider implements IStructuredContentProvider, IStudentListListener, IColumnContentProvider {

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

        public void studentAdded(Student student) {
            add(student);
        }

        public void studentRemoved(Student student) {
            remove(student);
        }

        public void studentUpdated(Student student) {
            update(student, null);
        }

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

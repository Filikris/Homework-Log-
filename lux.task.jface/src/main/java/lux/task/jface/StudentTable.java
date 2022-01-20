package lux.task.jface;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class StudentTable extends TableViewer {

    public StudentTable(Composite parent, int style) {
	super(parent, style);
	Table table = getTable();

	table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	table.setHeaderVisible(true);
	table.setLinesVisible(true);

	createColumns();

	setContentProvider(new StudentContentProvider());

	table.addListener(SWT.Resize, new Listener() {

	    public void handleEvent(final Event event) {
		for (TableColumn c : getTable().getColumns()) {
		    c.pack();
		}
	    }
	});
    }

    private void createColumns() {
	TableViewerColumn nameCol = new TableViewerColumn(this, SWT.LEFT, 0);
	nameCol.getColumn().setText("Name");
	nameCol.setLabelProvider(new ColumnLabelProvider() {
	    @Override
	    public String getText(Object element) {
		Student student = (Student) element;
		return student.getName();
	    }
	});

	TableViewerColumn groupCol = new TableViewerColumn(this, SWT.LEFT, 1);
	groupCol.getColumn().setText("Group");
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
	doneCol.setLabelProvider(new EmulatedNativeCheckBoxLabelProvider(doneCol.getViewer()) {
	    @Override
	    protected boolean isChecked(Object element) {
		Student student = (Student) element;
		return student.isTaskDone();
	    }
	});
    }

    class StudentContentProvider implements IStructuredContentProvider, IStudentListListener {

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

    }

}

package lux.task.jface;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class StudentTable extends TableViewer {

    private StudentList studentList = new StudentList();

    public StudentTable(Composite parent, int style) {
	super(parent, style);

	this.getTable().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	this.getTable().setHeaderVisible(true);
	this.getTable().setLinesVisible(true);
	this.setContentProvider(new StudentContentProvider());
	this.setInput(studentList);

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
	doneCol.setLabelProvider(new ColumnLabelProvider() {
	    @Override
	    public void update(ViewerCell cell) {
		TableItem item = (TableItem) cell.getItem();
		Button button = new Button((Composite) cell.getViewerRow().getControl(), SWT.CHECK);
		button.setSelection(true);
		TableEditor editor = new TableEditor(item.getParent());
		editor.grabHorizontal = true;
		editor.grabVertical = true;
		editor.setEditor(button, item, cell.getColumnIndex());
		editor.layout();
	    }
	});

	this.getTable().addListener(SWT.Resize, new Listener() {

	    public void handleEvent(final Event event) {
		for (TableColumn c : getTable().getColumns()) {
		    c.pack();
		}
	    }
	});

    }

    class StudentContentProvider implements IStructuredContentProvider, IStudentListViewer {

	public Object[] getElements(Object inputElement) {
	    return studentList.getStudents().toArray();
	}

	public void inputChanged(Viewer v, Object oldInput, Object newInput) {
	    if (newInput != null)
		((StudentList) newInput).addChangeListener(this);
	    if (oldInput != null)
		((StudentList) oldInput).removeChangeListener(this);
	}

	public void dispose() {
	    studentList.removeChangeListener(this);
	}

	public void addStudent(Student student) {
	    add(student);
	}

	public void removeStudent(Student student) {
	    remove(student);
	}

	public void updateStudent(Student student) {
	    update(student, null);
	}

    }

}

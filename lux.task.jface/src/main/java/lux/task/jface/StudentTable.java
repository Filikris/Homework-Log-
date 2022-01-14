package lux.task.jface;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class StudentTable extends TableViewer {
    public StudentTable(Composite parent, int style) {
	super(parent, style);

	this.getTable().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	this.getTable().setHeaderVisible(true);
	this.getTable().setLinesVisible(true);
	this.setContentProvider(new ArrayContentProvider());

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
}

package lux.task.jface;

import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class StudentPanel extends Composite {
    private Student student;
    private Text nameText;
    private Text groupText;
    private Button taskDone;

    public StudentPanel(Composite parent, int style, StudentList studentList, IStudentActionProvider provider) {
	super(parent, style);

	this.setLayout(new GridLayout(3, false));
	this.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

	Label nameLabel = new Label(this, SWT.LEFT);
	nameLabel.setText("Name");
	nameLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	nameText = new Text(this, SWT.SINGLE | SWT.BORDER);
	GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
	gridData.horizontalSpan = 2;
	nameText.setLayoutData(gridData);
	Label groupLabel = new Label(this, SWT.LEFT);
	groupLabel.setText("Group");
	groupLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	groupText = new Text(this, SWT.SINGLE | SWT.BORDER);
	gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
	gridData.horizontalSpan = 2;
	groupText.setLayoutData(gridData);

	taskDone = new Button(this, SWT.CHECK);
	taskDone.setText("SWT task done");
	gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
	gridData.horizontalSpan = 3;
	taskDone.setLayoutData(gridData);

	Composite compositeButtons = new Composite(this, SWT.NONE);
	compositeButtons.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
	compositeButtons.setLayout(new FillLayout());

	ActionContributionItem item = new ActionContributionItem(provider.getAction(StudentAction.NEW));
	item.fill(compositeButtons);
	Button addNew = (Button) item.getWidget();
	addNew.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));

	item = new ActionContributionItem(provider.getAction(StudentAction.SAVE));
	item.fill(compositeButtons);
	Button save = (Button) item.getWidget();
	save.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));

	item = new ActionContributionItem(provider.getAction(StudentAction.DELETE));
	item.fill(compositeButtons);
	Button delete = (Button) item.getWidget();
	delete.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));

	item = new ActionContributionItem(provider.getAction(StudentAction.CANCEL));
	item.fill(compositeButtons);
	Button cancel = (Button) item.getWidget();
	cancel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));
    }

    public void setStudent(Student student) {
	this.student = student;
	if (student == null) {
	    nameText.setText("");
	    groupText.setText("");
	    taskDone.setSelection(false);
	} else {
	    nameText.setText(student.getName());
	    groupText.setText(student.getGroup());
	    taskDone.setSelection(student.isTaskDone());
	}
    }

    public boolean isChanged() {
	if (student == null) {
	    return false;
	}
	if (!nameText.getText().equals(student.getName())) {
	    return true;
	}
	if (!groupText.getText().equals(student.getGroup())) {
	    return true;
	}
	if (taskDone.getSelection() != student.isTaskDone()) {
	    return true;
	}
	return false;
    }

    public Student getStudent() {
	return student;
    }

    public void applyChanges() {
	student.setName(nameText.getText());
	student.setGroup(groupText.getText());
	student.setIsTaskDone(taskDone.getSelection());

    }

    public void cancelChanges() {
	nameText.setText("");
	groupText.setText("");
	taskDone.setSelection(false);

    }

}

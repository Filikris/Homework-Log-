package lux.task.jface;

import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * This class create tab with student data
 * In this user can changes/ update student data or add new student
 * @author 
 *
 */
public class StudentPanel extends Composite {
    private Student student;
    private Text nameText;
    private Text groupText;
    private Button taskDone;
    private Button save;
    private Button delete;
    private Button cancel;
    private IStudentActionProvider provider;
    private ModifyListener modifyListener = new ModifyListener() {
        public void modifyText(ModifyEvent e) {
            updateButtons();
        }
    };
    
    /**
     * constructor create buttons and field for enter student data
     * 
     * @param parent
     * @param style
     * @param studentList
     * @param provider
     */

    public StudentPanel(Composite parent, int style, StudentList studentList, IStudentActionProvider provider) {
        super(parent, style);
        this.provider = provider;

        this.setLayout(new GridLayout(2, false));

        Label nameLabel = new Label(this, SWT.LEFT);
        nameLabel.setText("Name : ");
        nameLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
        nameText = new Text(this, SWT.SINGLE | SWT.BORDER);
        GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
        nameText.setLayoutData(gridData);
        Label groupLabel = new Label(this, SWT.LEFT);
        groupLabel.setText("Group : ");
        groupLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
        groupText = new Text(this, SWT.SINGLE | SWT.BORDER);
        gridData = new GridData(GridData.FILL_HORIZONTAL);
        groupText.setLayoutData(gridData);

        taskDone = new Button(this, SWT.CHECK);
        taskDone.setText("SWT task done");
        gridData = new GridData(GridData.FILL_HORIZONTAL);
        gridData.horizontalSpan = 2;
        taskDone.setLayoutData(gridData);

        Composite compositeButtons = new Composite(this, SWT.NONE);
        gridData = new GridData(GridData.FILL_HORIZONTAL);
        gridData.horizontalSpan = 2;
        compositeButtons.setLayoutData(gridData);
        compositeButtons.setLayout(new FillLayout());

        ActionContributionItem item = new ActionContributionItem(provider.getAction(StudentAction.NEW));
        item.fill(compositeButtons);
        Button addNew = (Button) item.getWidget();
        addNew.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));

        item = new ActionContributionItem(provider.getAction(StudentAction.SAVE));
        item.fill(compositeButtons);
        save = (Button) item.getWidget();
        save.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));

        item = new ActionContributionItem(provider.getAction(StudentAction.DELETE));
        item.fill(compositeButtons);
        delete = (Button) item.getWidget();
        delete.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));

        item = new ActionContributionItem(provider.getAction(StudentAction.CANCEL));
        item.fill(compositeButtons);
        cancel = (Button) item.getWidget();
        cancel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));

        nameText.addModifyListener(modifyListener);
        groupText.addModifyListener(modifyListener);
        taskDone.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                updateButtons();
            }
        });

        updateButtons();
    }
    
    /**
     * add new Student in the table
     * @param student new student
     */

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
        updateButtons();
    }
    
    /**
     * checking for changes in student data
     * @return boolean true if data changes
     */

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
    
    /**
     * get selected student in the table
     * @return selected student
     */

    public boolean isMissingText() {
        return nameText.getText().length() == 0 || groupText.getText().length() == 0;
    }

    public Student getStudent() {
        return student;
    }
    
    /**
     * apply changes to student data
     */

    public void applyChanges() {
        student.setName(nameText.getText());
        student.setGroup(groupText.getText());
        student.setIsTaskDone(taskDone.getSelection());
    }
    
    /**
     * canceled changes in student data
     */

    public void cancelChanges() {
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
    
    /**
     * enabled/disabled buttons depending on user actions
     */

    public void updateButtons() {
        provider.getAction(StudentAction.SAVE).setEnabled(!isMissingText() && isChanged());
        provider.getAction(StudentAction.CANCEL).setEnabled(isChanged());
        provider.getAction(StudentAction.DELETE).setEnabled(student != null);
    }
}

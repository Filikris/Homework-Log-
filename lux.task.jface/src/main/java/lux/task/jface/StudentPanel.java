package lux.task.jface;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class StudentPanel extends Composite {
	
	public StudentPanel(Composite parent, int style) {
		super(parent, style);

		this.setLayout(new GridLayout(3, false));
		this.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		Label nameLabel = new Label(this, SWT.LEFT);
		nameLabel.setText("Name");
		nameLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		Text nameText = new Text(this, SWT.SINGLE | SWT.BORDER);
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.horizontalSpan = 2;
		nameText.setLayoutData(gridData);
		Label groupLabel = new Label(this, SWT.LEFT);
		groupLabel.setText("Group");
		groupLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		Text groupText = new Text(this, SWT.SINGLE | SWT.BORDER);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.horizontalSpan = 2;
		groupText.setLayoutData(gridData);
		  
		Button taskDone = new Button(this, SWT.CHECK);
		taskDone.setText("SWT task done");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.horizontalSpan = 3;
		taskDone.setLayoutData(gridData);
		  
		Button addNew = new Button(this,SWT.NONE);
		addNew.setText("New");
		addNew.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));
		  
		Button save = new Button(this,SWT.NONE);
		save.setText("Save");
		save.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));
		  
		Button cancel = new Button(this,SWT.NONE);
		cancel.setText("Cancel");
		cancel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));
	}

}
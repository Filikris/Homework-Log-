package lux.task.jface;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class Main extends ApplicationWindow {

	  public Main() {	    
		  super(null);

		  createMenu();
	  }

	  protected Control createContents(Composite parent) {
		  getShell().setText("JFace homework log");

		  SashForm mainPane = new SashForm(parent, SWT.HORIZONTAL);
		  Composite tableComposite = new Composite(mainPane, SWT.NONE);
		  tableComposite.setLayout(new GridLayout());
		  
		  StudentTable table = new StudentTable(tableComposite,SWT.BORDER | SWT.FULL_SELECTION);
		  
		  Student one = new Student();
		  one.setName("Yaroslav");
		  one.setGroup("1");
		  one.setTaskDone(true);
		  
		  Student two = new Student();
		  two.setName("Kristina");
		  two.setGroup("7");
		  two.setTaskDone(null);
		  
		  List <Student> students = new ArrayList <Student>();
		  students.add(one);
		  students.add(two);
		  
		  table.setInput(students);
		 		  
		  Composite studentComposite = new Composite(mainPane, SWT.NONE);
		  studentComposite.setLayout(new GridLayout(3, false));
		  studentComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		  Label nameLabel = new Label(studentComposite, SWT.LEFT);
		  nameLabel.setText("Name");
		  nameLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		  Text nameText = new Text(studentComposite, SWT.SINGLE | SWT.BORDER);
		  GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		  gridData.horizontalSpan = 2;
		  nameText.setLayoutData(gridData);
		  
		  Label groupLabel = new Label(studentComposite, SWT.LEFT);
		  groupLabel.setText("Group");
		  groupLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		  Text groupText = new Text(studentComposite, SWT.SINGLE | SWT.BORDER);
		  gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		  gridData.horizontalSpan = 2;
		  groupText.setLayoutData(gridData);
		  
		  Button taskDone = new Button(studentComposite, SWT.CHECK);
		  taskDone.setText("SWT task done");
		  gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		  gridData.horizontalSpan = 3;
		  taskDone.setLayoutData(gridData);
		  
		  Button addNew = new Button(studentComposite,SWT.NONE);
		  addNew.setText("New");
		  addNew.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));
		  
		  Button save = new Button(studentComposite,SWT.NONE);
		  save.setText("Save");
		  save.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));
		  
		  Button cancel = new Button(studentComposite,SWT.NONE);
		  cancel.setText("Cancel");
		  cancel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));

		  parent.pack();
	    
		  return parent;
	  }
	  
	  private void createMenu() {
		  addMenuBar();
		  
		  MenuManager fileMenu = new MenuManager("&File");
		  getMenuBarManager().add(fileMenu);
		  fileMenu.add(new Separator());
		  fileMenu.add(new Action("&Select ..."){
			  
			  @Override
			  public void run() {
				  //TODO
			  }
		  });
		  
		  MenuManager editMenu = new MenuManager("&Edit");
		  getMenuBarManager().add(editMenu);
		  editMenu.add(new Separator());
		  editMenu.add(new Action("&New"){
			  @Override
			  public void run() {
				  //TODO
			  }
		  });
		  editMenu.add(new Action("&Save"){	  
			  @Override
			  public void run() {
				  //TODO
			  }
		  });
		  editMenu.add(new Action("&Delete"){  
			  @Override
			  public void run() {
				  //TODO
			  }
		  });
		  editMenu.add(new Action("&Cancel"){ 
			  @Override
			  public void run() {
				  //TODO
			  }
		  });

		  MenuManager helpMenu = new MenuManager("&Help");
		  getMenuBarManager().add(helpMenu);
		  helpMenu.add(new Separator());
		  helpMenu.add(new Action("&About") {
			  @Override
			  public void run() {
				  //TODO
			  }
		  });  
	  }

	  public static void main(String[] args) {
		  Main awin = new Main();	    
		  awin.setBlockOnOpen(true);	    
		  awin.open();

		  Display.getCurrent().dispose();
	  }
	}


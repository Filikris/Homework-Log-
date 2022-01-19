package lux.task.jface;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Main extends ApplicationWindow {
    private Action quitAction;
    private Action selectAction;
    private Action newAction;
    private Action saveAction;
    private Action deleteAction;
    private Action cancelAction;
    private Action aboutAction;
    private StudentList studentList = new StudentList();

    public Main() {
	super(null);

	createActions();

	addMenuBar();
    }

    @Override
    protected void configureShell(Shell shell) {
	super.configureShell(shell);

	shell.setText("JFace homework log");
    }

    @Override
    protected Control createContents(Composite parent) {
	SashForm mainPane = new SashForm(parent, SWT.HORIZONTAL);

	StudentTable table = new StudentTable(mainPane, SWT.BORDER | SWT.FULL_SELECTION);
	table.setInput(studentList);

	new StudentPanel(mainPane, SWT.NONE, studentList);

	parent.pack();

	return parent;
    }

    @Override
    protected MenuManager createMenuManager() {
	MenuManager manager = new MenuManager();

	manager.add(createFileMenuManager());
	manager.add(createEditMenuManager());
	manager.add(createHelpMenuManager());

	return manager;
    }

    private MenuManager createFileMenuManager() {
	MenuManager manager = new MenuManager("&File");

	manager.add(selectAction);
	manager.add(new Separator());
	manager.add(quitAction);

	return manager;
    }

    private MenuManager createEditMenuManager() {
	MenuManager manager = new MenuManager("&Edit");

	manager.add(newAction);
	manager.add(saveAction);
	manager.add(new Separator());
	manager.add(deleteAction);
	manager.add(cancelAction);

	return manager;
    }

    private MenuManager createHelpMenuManager() {
	MenuManager manager = new MenuManager("&Help");

	manager.add(aboutAction);

	return manager;
    }

    private void createActions() {
	quitAction = new Action("&Quit") {
	    public void run() {
		close();
	    }
	};
	quitAction.setAccelerator(SWT.CTRL + 'Q');

	selectAction = new Action("&Select...") {
	    public void run() {
		// TODO
	    }
	};

	newAction = new Action("&New") {
	    public void run() {	
		studentList.addStudent();
	    }
	};

	saveAction = new Action("&Save") {
	    public void run() {
		// TODO
	    }
	};

	deleteAction = new Action("&Delete") {
	    public void run() {
		// TODO
	    }
	};

	cancelAction = new Action("&Cancel") {
	    public void run() {
		// TODO
	    }
	};

	aboutAction = new Action("&About") {
	    public void run() {
		// TODO
	    }
	};
    }
//
//    private List<Student> loadData() {
//	Student one = new Student();
//	one.setName("Yaroslav");
//	one.setGroup("1");
//
//	Student two = new Student();
//	two.setName("Kristina");
//	two.setGroup("7");
//
//	List<Student> students = new ArrayList<Student>();
//	students.add(one);
//	students.add(two);
//
//	return students;
//
//    }

    public static void main(String[] args) {
	Main awin = new Main();
	awin.setBlockOnOpen(true);
	awin.open();

	Display.getCurrent().dispose();
    }
}

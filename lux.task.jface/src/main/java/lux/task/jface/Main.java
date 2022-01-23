package lux.task.jface;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;

public class Main extends ApplicationWindow implements IStudentActionProvider {
    private Action quitAction;
    private Action selectAction;
    private Action newAction;
    private Action saveAction;
    private Action deleteAction;
    private Action cancelAction;
    private Action aboutAction;
    private StudentList studentList = new StudentList();
    private StudentPanel panel;
    private StudentTable table;
    private StudentFileManager fileManager = new StudentFileManager(studentList);

    public Main() {
        super(null);
        
        try {
            fileManager.readFile();
        } catch (IOException e) {
            fileManager.initData();
        }
        
        studentList.addChangeListener(fileManager);

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

        table = new StudentTable(mainPane, SWT.BORDER | SWT.FULL_SELECTION);
        table.setInput(studentList);

        panel = new StudentPanel(mainPane, SWT.NONE, studentList, this);

        table.addSelectionChangedListener((SelectionChangedEvent event) -> {
            promptSaveChanges();

            Student student = (Student) ((IStructuredSelection) event.getSelection()).getFirstElement();
            panel.setStudent(student);
        });

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
                promptSaveChanges();
                
                FileDialog dialog = new FileDialog(getShell(), SWT.SAVE);
                dialog.setFilterNames(new String[] { "CSV Files", "All Files (*.*)" });
                dialog.setFilterExtensions(new String[] { "*.csv", "*.*" });
                String filePath = dialog.open();
                if (filePath == null) {
                    return;
                }
                
                try {
                    fileManager.setPath(filePath);
                } catch (IOException e) {
                    MessageBox msg = new MessageBox(getShell(), SWT.ICON_ERROR);
                    msg.setMessage("Error");
                    msg.open();
                }
            }
        };

        newAction = new Action("&New") {
            public void run() {
                promptSaveChanges();

                panel.setStudent(studentList.create());
            }
        };

        saveAction = new Action("&Save") {
            public void run() {
                panel.applyChanges();
                studentList.saveStudent(panel.getStudent());
            }
        };

        deleteAction = new Action("&Delete") {
            public void run() {
                promptSaveChanges();

                studentList.removeStudent(panel.getStudent());
            }
        };

        cancelAction = new Action("&Cancel") {
            public void run() {
                panel.cancelChanges();
            }
        };

        aboutAction = new Action("&About") {
            public void run() {

                MessageBox msg = new MessageBox(getShell(), SWT.ICON_INFORMATION);
                msg.setMessage("Created by Kristina and Andrey");
                msg.open();
            }
        };
    }

    private void promptSaveChanges() {
        if (panel.isChanged()) {
            MessageBox msg = new MessageBox(getShell(), SWT.ICON_QUESTION | SWT.YES | SWT.NO);
            msg.setMessage("Wanna save your changes?");
            if (msg.open() == SWT.YES) {
                panel.applyChanges();
                studentList.saveStudent(panel.getStudent());
            }
        }

    }

    public static void main(String[] args) {
        Main awin = new Main();
        awin.setBlockOnOpen(true);
        awin.open();

        Display.getCurrent().dispose();
    }

    @Override
    public Action getAction(StudentAction key) {
        if (key == StudentAction.SELECT) {
            return selectAction;
        }
        if (key == StudentAction.NEW) {
            return newAction;
        }
        if (key == StudentAction.SAVE) {
            return saveAction;
        }
        if (key == StudentAction.DELETE) {
            return deleteAction;
        }
        if (key == StudentAction.CANCEL) {
            return cancelAction;
        }
        if (key == StudentAction.ABOUT) {
            return aboutAction;
        }
        return null;
    }
}

package lux.task.jface;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TableColumn;


public class Main extends ApplicationWindow {

	  public Main() {	    
		  super(null);
		  createMenu();
	  }

	  protected Control createContents(Composite parent) {
		  getShell().setText("JFace homework log");
		  final TableViewer viewer = new TableViewer(parent,
				  SWT.BORDER | SWT.FULL_SELECTION);
		  TableLayout layout = new TableLayout();
		  layout.addColumnData(new ColumnWeightData(33, true));
		  layout.addColumnData(new ColumnWeightData(33, true));
		  layout.addColumnData(new ColumnWeightData(33, true));
		  viewer.getTable().setLayout(layout);
		  viewer.getTable().setLinesVisible(true);
		  viewer.getTable().setHeaderVisible(true);
		  
		  TableColumn column1 = new TableColumn(viewer.getTable(),SWT.CENTER);
		  column1.setText("Name");
		  column1.setWidth(400);
		  TableColumn column2 = new TableColumn(viewer.getTable(), SWT.CENTER);
		  column2.setText("Group");
		  TableColumn column3 = new TableColumn(viewer.getTable(), SWT.CENTER);
		  column3.setText("SWT done");

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


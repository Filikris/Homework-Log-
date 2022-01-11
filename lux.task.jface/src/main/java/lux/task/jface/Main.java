package lux.task.jface;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;


public class Main extends ApplicationWindow {

	  public Main() {	    
		  super(null);
		  createMenu();
	  }

	  protected Control createContents(Composite parent) {
		  getShell().setText("JFace homework log");

		  parent.pack();
	    
		  return parent;
	  }
	  
	  private void createMenu() {
		  addMenuBar();
		  
		  MenuManager fileMenu = new MenuManager("&File");
		  getMenuBarManager().add(fileMenu);
		  fileMenu.add(new Separator());
		  fileMenu.add(new Action("&New"){
			  
			  @Override
			  public void run() {
				  //TODO
			  }
		  });
		  
		  fileMenu.add(new Action("&Save"){
			  
			  @Override
			  public void run() {
				  //TODO
			  }
		  });
		  
		  fileMenu.add(new Action("&Delete"){
			  
			  @Override
			  public void run() {
				  //TODO
			  }
		  });
		  
		  fileMenu.add(new Action("&Cancel"){
			  
			  @Override
			  public void run() {
				  //TODO
			  }
		  });
		  
		  MenuManager editMenu = new MenuManager("&Edit");
		  getMenuBarManager().add(editMenu);
		  editMenu.add(new Separator());
		  editMenu.add(new Action("&Smth") {
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


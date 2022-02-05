package controllers;

import java.awt.SystemTray;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;

import controllers.gamepage.GamePageController;
import controllers.homepage.HomePageController;
import utilities.Handlers.FileHandler;
import utilities.constants.EventsUI;
import views.MainFrame;

public class MainFrameController implements PropertyChangeListener{
	
	private MainFrame view;
	static SystemTray tray = SystemTray.getSystemTray();
	private HomePageController homePageController;
	private GamePageController gamePageController;
	
	
	public MainFrameController(MainFrame view) {
		this.view = view;;
		this.initControllers();
		this.initWindowListener();
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals(EventsUI.GO_TO_NEWGAME)) {
			this.view.showPanel(this.view.gamePage);
			this.gamePageController.updateGraphic();
			int mode = (int) evt.getNewValue();
			this.gamePageController.gameBoardController.model.getSetting().setMode(mode);
		}
	}
	
	private void initControllers() {
		this.homePageController = new HomePageController(this.view.homePage);
		this.homePageController.register(this);
		this.gamePageController = new GamePageController(this.view.gamePage);
		this.gamePageController.register(this);
	}
	
	private void initWindowListener(){
	    view.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowDeiconified(WindowEvent e) {
		    	view.setExtendedState(JFrame.MAXIMIZED_BOTH);
		    }
	    });
	}

}

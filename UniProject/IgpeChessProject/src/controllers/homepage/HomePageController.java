package controllers.homepage;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import controllers.shared.TopRightCornerController;
import utilities.constants.Constants;
import utilities.constants.EventsUI;
import views.pages.HomePage;

public class HomePageController{

	private HomePage view;
	private PropertyChangeSupport trigger;
	
	public HomePageController(HomePage view) {
		this.view = view;
		this.initHomePageButtonsControllers();
		this.trigger = new PropertyChangeSupport(this);
		TopRightCornerController t = new TopRightCornerController(this.view.topRightCorner);
	}
	
	public void initHomePageButtonsControllers() {
		
		((Component) this.view.versusPl.component).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PropertyChangeEvent pce = new PropertyChangeEvent(this, EventsUI.GO_TO_NEWGAME, null, Constants.MULTIPLAYER);
				trigger.firePropertyChange(pce);
			}
		});
		
		((Component) this.view.versusPC.component).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PropertyChangeEvent pce = new PropertyChangeEvent(this, EventsUI.GO_TO_NEWGAME, null, Constants.SINGLEPLAYER);
				trigger.firePropertyChange(pce);
			}
		});
	}
	
	public void register(PropertyChangeListener observer) {
		this.trigger.addPropertyChangeListener(observer);
	}
}

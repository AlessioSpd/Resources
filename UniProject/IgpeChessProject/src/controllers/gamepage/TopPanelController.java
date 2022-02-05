package controllers.gamepage;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import controllers.shared.TopRightCornerController;
import controllers.shared.myBarMenuController;
import utilities.constants.EventsUI;
import views.gamepage.TopPanel;

public class TopPanelController implements PropertyChangeListener{
	private TopPanel view;
	private PropertyChangeSupport trigger;
	
	public TopPanelController(TopPanel view) {
		this.view = view;
		this.trigger = new PropertyChangeSupport(this);
		this.initControllers();
	}
	
	public void register(PropertyChangeListener observer) { 
		 this.trigger.addPropertyChangeListener( observer ); 
	} 
	
	public void initControllers() {
		TopRightCornerController t = new TopRightCornerController(this.view.topRightCorner);
		t.register(this);
		myBarMenuController m = new myBarMenuController(this.view.myMenu);
		m.register(this);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals(EventsUI.UPDATE_GRAPHIC_SETTINGS)) {
			PropertyChangeEvent pce = new PropertyChangeEvent(this, EventsUI.UPDATE_GRAPHIC_SETTINGS, null, null);
			trigger.firePropertyChange(pce);
		}
		else if(evt.getPropertyName().equals(EventsUI.NEWGAME)) {
			PropertyChangeEvent pce = new PropertyChangeEvent(this, EventsUI.NEWGAME, null, evt.getNewValue());
			trigger.firePropertyChange(pce);
		}
		else if(evt.getPropertyName().equals(EventsUI.SAVE)) {
			PropertyChangeEvent pce = new PropertyChangeEvent(this, EventsUI.SAVE, null, evt.getNewValue());
			trigger.firePropertyChange(pce);
		}
		else if(evt.getPropertyName().equals(EventsUI.LOAD)) {
			PropertyChangeEvent pce = new PropertyChangeEvent(this, EventsUI.LOAD, null, null);
			trigger.firePropertyChange(pce);
		}
		else if(evt.getPropertyName().equals(EventsUI.REPLAY)) {
			PropertyChangeEvent pce = new PropertyChangeEvent(this, EventsUI.REPLAY, null, null);
			trigger.firePropertyChange(pce);
		}
	}
}
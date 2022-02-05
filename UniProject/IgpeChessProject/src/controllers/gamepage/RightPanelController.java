package controllers.gamepage;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JButton;

import utilities.constants.EventsUI;
import views.gamepage.right.RightPanel;

public class RightPanelController {
	
	public RightPanel view;
	private PropertyChangeSupport trigger;
	
	public RightPanelController(RightPanel view) {
		this.view = view;
		this.trigger = new PropertyChangeSupport(this);
		this.initControllers();
	}
	
	public void register(PropertyChangeListener observer) { 
		 this.trigger.addPropertyChangeListener( observer ); 
	} 
	
	public void initControllers() {
		this.view.top.undo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if( ((JButton)e.getSource()).isEnabled() ) {
					PropertyChangeEvent pce = new PropertyChangeEvent(this, EventsUI.UNDO, null, null);
					trigger.firePropertyChange(pce);
				}
			}
			
		});
		this.view.top.prev.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if( ((JButton)e.getSource()).isEnabled() ) {
					PropertyChangeEvent pce = new PropertyChangeEvent(this, EventsUI.PREV, null, null);
					trigger.firePropertyChange(pce);
				}
			}
			
		});
		this.view.top.next.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if( ((JButton)e.getSource()).isEnabled() ) {
					PropertyChangeEvent pce = new PropertyChangeEvent(this, EventsUI.NEXT, null, null);
					trigger.firePropertyChange(pce);
				}
			}
			
		});
		this.view.top.surrender.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if( ((JButton)e.getSource()).isEnabled() ) {
					PropertyChangeEvent pce = new PropertyChangeEvent(this, EventsUI.SURRENDER, null, null);
					trigger.firePropertyChange(pce);
				}
			}
			
		});
	}
	
}

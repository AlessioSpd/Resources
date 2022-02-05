package controllers.gameboard;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import models.board.Square;
import utilities.constants.EventsUI;
import views.gameboard.SquareView;

public class SquareController {
	
	SquareView view;
	Square model;
	private PropertyChangeSupport trigger;
	public MouseListener mouseList;
	
	public SquareController(Square model, SquareView view) {
		this.view = view;
		this.model = model;
		this.trigger = new PropertyChangeSupport(this);
		this.initViewMouseListener();
	}
	
	public void register(PropertyChangeListener observer) { 
		 this.trigger.addPropertyChangeListener( observer ); 
	} 
	
	private void initViewMouseListener() {
		mouseList = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PropertyChangeEvent pce = new PropertyChangeEvent(this, EventsUI.SELECT, null, model.getPosition());
				trigger.firePropertyChange(pce);
			}
		};
		this.view.addMouseListener(mouseList);
	}
	
	public Square getModel() {
		return this.model;
	}
	
	public SquareView getView() {
		return this.view;
	}
}

package controllers.gamepage;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import javax.swing.JButton;

import models.board.GameBoard;
import models.pieces.Piece;
import utilities.constants.Constants;
import utilities.constants.EventsUI;
import views.dialogs.EndGameDialog;
import views.gamepage.left.LeftPanel;

public class LeftPanelController  implements PropertyChangeListener{

	public LeftPanel view;
	private PropertyChangeSupport trigger;
	
	public LeftPanelController(LeftPanel view) {
		this.view = view;
		this.trigger = new PropertyChangeSupport(this);
		this.view.light.register(this);
		this.initControllers();
	}
	
	public void register(PropertyChangeListener observer) { 
		 this.trigger.addPropertyChangeListener( observer ); 
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals(EventsUI.TIME_LOSE)) {
			PropertyChangeEvent pce = new PropertyChangeEvent(this, EventsUI.TIME_LOSE, null, null);
			trigger.firePropertyChange(pce);
		}
	} 
	
	private void initControllers() {
		this.view.center.startStop.addMouseListener(new MouseAdapter() {			
			@Override
			public void mouseClicked(MouseEvent e) {
				if( ((JButton)e.getSource()).isEnabled() ) {
					PropertyChangeEvent pce = new PropertyChangeEvent(this, EventsUI.PLAYPAUSEGAME, null, null);
					trigger.firePropertyChange(pce);
				}
			}
		});
	}
	
	public void addPlaySeconds(int turno) {
		turno = turno * -1;
		if(turno == Constants.LIGHT) {
			this.view.light.seconds = this.view.light.seconds+3;
			this.view.light.setTimeValue(this.view.light.secondsToTime(this.view.light.seconds));
		}
		else if(turno == Constants.DARK) {
			this.view.dark.seconds = this.view.light.seconds+3;
			this.view.dark.setTimeValue(this.view.dark.secondsToTime(this.view.dark.seconds));
		}
	}
	
	public void updateCountLabels(GameBoard board) {
		ArrayList<Piece> eatensL = board.getPieceManger().getEatenPiecesOfColoreness(Constants.LIGHT);
		ArrayList<Piece> eatensD = board.getPieceManger().getEatenPiecesOfColoreness(Constants.DARK);
		this.view.dark.countEaten.setCount(eatensL);
		this.view.light.countEaten.setCount(eatensD);
	}
	
	public void resetCountLabels() {
		this.view.dark.countEaten.setCount(new ArrayList<Piece>());
		this.view.light.countEaten.setCount(new ArrayList<Piece>());
	}
}

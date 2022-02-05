package controllers.dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;


import utilities.constants.EventsUI;
import views.dialogs.EndGameDialog;
import views.dialogs.UpdatePawnDialog;

public class UpdatePawnDialogController {
	
	private PropertyChangeSupport trigger;
	private UpdatePawnDialog view;
	
	public UpdatePawnDialogController(UpdatePawnDialog view) {
		this.view = view;
		this.trigger = new PropertyChangeSupport(this);
		initControllers();
	}
	
	public void register(PropertyChangeListener observer) { 
		 this.trigger.addPropertyChangeListener( observer ); 
	}
	
private void initControllers() {
		
		this.view.rookChoose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Object> value = new ArrayList <Object>();
				value.add(view.pawn);
				value.add(view.team == 1 ? 'R' : 'r');
				PropertyChangeEvent pce = new PropertyChangeEvent(this, EventsUI.UPGRADE_PAWN, null, value);
				trigger.firePropertyChange(pce);
				view.dispose();
			}
		});
		
		this.view.queenChoose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Object> value = new ArrayList <Object>();
				value.add(view.pawn);
				value.add(view.team == 1 ? 'Q' : 'q');
				PropertyChangeEvent pce = new PropertyChangeEvent(this, EventsUI.UPGRADE_PAWN, null, value);
				trigger.firePropertyChange(pce);
				view.dispose();
			}
		});
		
		this.view.knightChoose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Object> value = new ArrayList <Object>();
				value.add(view.pawn);
				value.add(view.team == 1 ? 'N' : 'n');
				PropertyChangeEvent pce = new PropertyChangeEvent(this, EventsUI.UPGRADE_PAWN, null, value);
				trigger.firePropertyChange(pce);
				view.dispose();
			}
		});
		
		this.view.bishopChoose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Object> value = new ArrayList <Object>();
				value.add(view.pawn);
				value.add(view.team == 1 ? 'B' : 'b');
				PropertyChangeEvent pce = new PropertyChangeEvent(this, EventsUI.UPGRADE_PAWN, null, value);
				trigger.firePropertyChange(pce);
				view.dispose();
			}
		});
	}
}

package controllers.dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;

import javax.swing.JFileChooser;

import utilities.constants.Constants;
import utilities.constants.EventsUI;
import views.dialogs.EndGameDialog;
import views.dialogs.SettingsDialog;

public class EndGameDialogController {
	
	private PropertyChangeSupport trigger;
	private EndGameDialog view;
	
	public EndGameDialogController(EndGameDialog view) {
		this.view = view;
		this.trigger = new PropertyChangeSupport(this);
		initControllers();
	}
	
	public void register(PropertyChangeListener observer) { 
		 this.trigger.addPropertyChangeListener( observer ); 
	} 
	
	private void initControllers() {
		
		this.view.save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setAcceptAllFileFilterUsed(false);
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				
				int n = fileChooser.showOpenDialog(view.getParent());

				String folderPath = null;
				if (n == JFileChooser.APPROVE_OPTION) {
					File f = fileChooser.getSelectedFile();
					folderPath = f.getAbsolutePath();
					PropertyChangeEvent pce = new PropertyChangeEvent(this, EventsUI.SAVE, null, folderPath);
					trigger.firePropertyChange(pce);
				}
			}
		});
		
		this.view.newGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PropertyChangeEvent pce = new PropertyChangeEvent(this, EventsUI.NEWGAME, null, null);
				trigger.firePropertyChange(pce);
			}
		});
		
		this.view.replay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PropertyChangeEvent pce = new PropertyChangeEvent(this, EventsUI.REPLAY, null, EventsUI.REPLAYFROMDIALOG);
				trigger.firePropertyChange(pce);
			}
		});
	}
}

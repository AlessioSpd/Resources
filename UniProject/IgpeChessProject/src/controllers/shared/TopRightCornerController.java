package controllers.shared;

import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controllers.dialogs.SettingsDialogController;
import utilities.constants.EventsUI;
import views.dialogs.HelpDialog;
import views.dialogs.SettingsDialog;
import views.shared.TopRightCorner;

public class TopRightCornerController implements PropertyChangeListener {

	public TopRightCorner view;
	private PropertyChangeSupport trigger;
	private TopRightCornerController self;
	
	public TopRightCornerController(TopRightCorner view) {
		this.view = view;
		this.trigger = new PropertyChangeSupport(this);
		this.initControllers();
		this.self = this;
	}
	
	public void register(PropertyChangeListener observer) { 
		 this.trigger.addPropertyChangeListener( observer ); 
	} 
	
	private void initControllers() {
		this.view.exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int confirm = JOptionPane.showConfirmDialog (null, "Are you sure you want to leave the game?","Warning", JOptionPane.YES_NO_OPTION);
		        if(confirm == JOptionPane.YES_OPTION)
		           System.exit(0);
			}
		});
		

		this.view.helpButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HelpDialog h = new HelpDialog();
				h.pack();
				h.setLocationRelativeTo(null);
				h.setVisible(true);
			}
		});

		this.view.minimize.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame mainFrame = ((JFrame) view.getRootPane().getParent());
				mainFrame.setExtendedState(Frame.ICONIFIED);
			}
		});
		

		this.view.settingsButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SettingsDialog s = new SettingsDialog();
				SettingsDialogController c = new SettingsDialogController(s);
				c.register(self);
				s.pack();
				s.setLocationRelativeTo(null);
				s.setVisible(true);
			}
		});
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals(EventsUI.UPDATE_GRAPHIC_SETTINGS)) {
			PropertyChangeEvent pce = new PropertyChangeEvent(this, EventsUI.UPDATE_GRAPHIC_SETTINGS, null, null);
			trigger.firePropertyChange(pce);
		}
		
	}
}

package controllers.dialogs;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.colorchooser.AbstractColorChooserPanel;

import audio.MediaPlayer;
import config.Settings;
import utilities.Handlers.FileHandler;
import utilities.constants.Constants;
import utilities.constants.EventsUI;
import views.dialogs.SettingsDialog;
import views.shared.myColorChooser;

public class SettingsDialogController {
	
	private final int LIGHT = 0;
	private final int DARK = 1;
	private final int SELECT = 2;
	private int currentC = -1;
	private PropertyChangeSupport trigger;
	SettingsDialog view;
	
	private myColorChooser chooser;
	private Color tempLight;
	private Color tempDark;
	private Color tempSelect;
	private JDialog d;
	ActionListener okActionListener;
	
	public SettingsDialogController(SettingsDialog view) {
		this.view = view;
		this.initControllersColorChooser();
		this.initOthersButtons();
		this.chooser = new myColorChooser();
		this.trigger = new PropertyChangeSupport(this);
		
		this.okActionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.print("Color selected");
				Color color = chooser.getColor();
				System.out.println(color);
				if(currentC == LIGHT) {
					tempLight = color;
					view.lightButt.setBackground(color);
				}
				else if(currentC == DARK) {
					tempDark = color;
					view.darkButt.setBackground(color);
				}
				else if(currentC == SELECT) {
					tempSelect = color;
					view.selectButt.setBackground(color);
				}
				currentC = -1;
			}
		};
		d = JColorChooser.createDialog(null, "", true, chooser, okActionListener, null);
	}
	
	public void register(PropertyChangeListener observer) { 
		 this.trigger.addPropertyChangeListener( observer ); 
	} 
	
	private void initControllersColorChooser() {
		
		
		
		this.view.lightButt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				currentC = LIGHT;
			    
			    d.setVisible(true);
			}
		});
		this.view.darkButt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				currentC = DARK;
			    d.setVisible(true);
			}
		});
		this.view.selectButt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				currentC = SELECT;
			    d.setVisible(true);
			}
		});

	}
	private void initOthersButtons() {
		this.view.resetButt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				view.p1NameText.setText("Player1");
				view.p2NameText.setText("Player2");
				view.lightButt.setBackground(new Color(108,117,125));
				view.darkButt.setBackground(new Color(52,58,64));
				view.selectButt.setBackground(new Color(105,205,197));
				view.undoValue.setSelected(true);
				view.timeValue.setSelectedItem(10);
				view.sfxSlider.setValue(Constants.DEFAULT_VOLUME);				
				Settings.lightSquare = new Color(108,117,125);
				Settings.darkSquare = new Color(52,58,64);
				Settings.selectedSquare = new Color(105,205,197);
				Settings.namePlayer1 = "Player1";
				Settings.namePlayer2 = "Player2";
				Settings.playerTime = 10;
				Settings.sfxVolume = Constants.DEFAULT_VOLUME;
				Settings.undo = true;
			}
		});
		this.view.saveButt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tempLight != null) view.lightButt.setBackground(tempLight);
				if(tempDark != null) view.darkButt.setBackground(tempDark);
				if(tempSelect != null) view.selectButt.setBackground(tempSelect);
				if(tempLight != null) Settings.lightSquare = tempLight;
				if(tempDark != null) Settings.darkSquare = tempDark;
				if(tempSelect != null) Settings.selectedSquare = tempSelect;
				Settings.namePlayer1 = view.p1NameText.getText();
				Settings.namePlayer2 = view.p2NameText.getText();
				Settings.playerTime = (int) view.timeValue.getSelectedItem();
				Settings.sfxVolume = view.sfxSlider.getValue();
				Settings.undo = (boolean) view.undoValue.isSelected();
				FileHandler.writeNewGraphicSetting();
				PropertyChangeEvent pce = new PropertyChangeEvent(this, EventsUI.UPDATE_GRAPHIC_SETTINGS, null, null);
				trigger.firePropertyChange(pce);
				view.dispose();
			}
		});
	}
	
}

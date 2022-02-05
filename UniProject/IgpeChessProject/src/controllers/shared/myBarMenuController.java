package controllers.shared;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Console;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import audio.SFXPlayer;
import utilities.Handlers.FileHandler;
import utilities.constants.Constants;
import utilities.constants.EventsUI;
import utilities.constants.Path;
import views.shared.myBarMenu;

public class myBarMenuController {
	private myBarMenu view;
	private PropertyChangeSupport trigger;
	
	public myBarMenuController(myBarMenu view) {
		this.view = view;
		this.trigger = new PropertyChangeSupport(this);
		this.initControllers();
		
	}
	
	private void initControllers() {
		this.view.chessVsPc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PropertyChangeEvent pce = new PropertyChangeEvent(this, EventsUI.NEWGAME, null, Constants.SINGLEPLAYER);
				trigger.firePropertyChange(pce);
			}
		});
		this.view.chessVsHuman.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PropertyChangeEvent pce = new PropertyChangeEvent(this, EventsUI.NEWGAME, null, Constants.MULTIPLAYER);
				trigger.firePropertyChange(pce);
			}
		});
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
		this.view.load.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					PropertyChangeEvent pce = new PropertyChangeEvent(this, EventsUI.LOAD, null, null);
					trigger.firePropertyChange(pce);
				}
		});
		this.view.replay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					PropertyChangeEvent pce = new PropertyChangeEvent(this, EventsUI.REPLAY, null, null);
					trigger.firePropertyChange(pce);
				}
		});
	}
	
	public static ArrayList<String> loadDialog(Component view){
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
		fileChooser.setFileFilter(filter);
		
		int n = fileChooser.showOpenDialog(view.getParent());

		String filePath = null;
		if (n == JFileChooser.APPROVE_OPTION) {
			File f = fileChooser.getSelectedFile();
			filePath = f.getAbsolutePath();
			ArrayList<String> logFile = FileHandler.getLinesOfFile(filePath);
			String lastLine = logFile.get(logFile.size()-1);
			
			if(lastLine.equals("END")) {
				SFXPlayer.play(Path.POPUP_SFX);
				JOptionPane.showMessageDialog(null,"This file cannot be loaded correctly");
				return null;
			}
			return logFile;
		}
		
		return null;
	}
	
	public static ArrayList<String> replayDialog(Component view){
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
		fileChooser.setFileFilter(filter);

		int n = fileChooser.showOpenDialog(view.getParent());

		String filePath = null;
		if (n == JFileChooser.APPROVE_OPTION) {
			File f = fileChooser.getSelectedFile();
			filePath = f.getAbsolutePath();
			ArrayList<String> logFile = FileHandler.getLinesOfFile(filePath);
			String lastLine = logFile.get(logFile.size()-1);
			
			if(!lastLine.equals("END")) {
				SFXPlayer.play(Path.POPUP_SFX);
				JOptionPane.showMessageDialog(null,"This file cannot be loaded correctly");
				return null;
			}
			return logFile;
		}
		return null;
	}
	
	public void register(PropertyChangeListener observer) {
		this.trigger.addPropertyChangeListener(observer);
	}
}
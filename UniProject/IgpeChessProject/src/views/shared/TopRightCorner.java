package views.shared;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


import config.Settings;
import utilities.Handlers.ImageHandler;
import utilities.constants.Path;

public class TopRightCorner extends JPanel{
	
    public JButton exitButton;
    public JButton settingsButton;
    public JButton helpButton;
    public JButton minimize;
    int iconDim;
    
	public TopRightCorner() {
		super(new FlowLayout());
		this.iconDim = Settings.screenHeight/65;
		initButtons();
	}
	
	private void initButtons() {

    	exitButton = new JButton();
    	exitButton.setOpaque(false);
    	exitButton.setContentAreaFilled(false);
    	exitButton.setBorderPainted(false);
    	exitButton.setFocusPainted(false);
    	
    	settingsButton = new JButton();
    	settingsButton.setOpaque(false);
    	settingsButton.setContentAreaFilled(false);
    	settingsButton.setBorderPainted(false);
    	settingsButton.setFocusPainted(false);
    	
    	helpButton = new JButton();
    	helpButton.setOpaque(false);
    	helpButton.setContentAreaFilled(false);
    	helpButton.setBorderPainted(false);
    	helpButton.setFocusPainted(false);
    	
    	minimize = new JButton();
    	minimize.setOpaque(false);
    	minimize.setContentAreaFilled(false);
    	minimize.setBorderPainted(false);
    	minimize.setFocusPainted(false);
    	
		ImageIcon exitIcon = ImageHandler.getScaledIcon(Path.CLOSE_IMG, iconDim, iconDim);
		exitButton.setIcon(exitIcon);

		ImageIcon settingIcon = ImageHandler.getScaledIcon(Path.SETTING_IMG, iconDim, iconDim);
		settingsButton.setIcon(settingIcon);

		ImageIcon helpIcon = ImageHandler.getScaledIcon(Path.HELP_IMG, iconDim, iconDim);
		helpButton.setIcon(helpIcon);
		
		ImageIcon minimizeIcon = ImageHandler.getScaledIcon(Path.MINIMIZE_IMG, iconDim, iconDim);
		minimize.setIcon(minimizeIcon);

    	this.add(helpButton);
    	this.add(settingsButton);
    	this.add(minimize);
    	this.add(exitButton);
	}
	
	
}

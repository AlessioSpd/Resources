package views.homepage;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import config.Settings;
import utilities.Handlers.ImageHandler;
import utilities.constants.Path;

public class HomeTopPanel  extends JPanel{
	
	public JButton settingButton;
    public JButton exitButton;
    private int iconDim;
    
    public HomeTopPanel(){
        super(new BorderLayout());
        initPanel();
        this.iconDim = Settings.screenHeight/65;
    }
    
    private void initPanel() {
    	exitButton = new JButton();
    	exitButton.setOpaque(false);
    	exitButton.setContentAreaFilled(false);
    	exitButton.setBorderPainted(false);
    	exitButton.setFocusPainted(false);
    	
    	settingButton = new JButton();
    	settingButton.setOpaque(false);
    	settingButton.setContentAreaFilled(false);
    	settingButton.setBorderPainted(false);
    	settingButton.setFocusPainted(false);
    	
		ImageIcon icon = ImageHandler.getScaledIcon(Path.CLOSE_IMG, iconDim, iconDim);
		exitButton.setIcon(icon);
		
		icon = ImageHandler.getScaledIcon(Path.SETTING_IMG, iconDim, iconDim);
		settingButton.setIcon(icon);
		JPanel p = new JPanel();
		p.add(settingButton);
		p.add(exitButton);
		
    	this.add(p, BorderLayout.EAST);
    }
}

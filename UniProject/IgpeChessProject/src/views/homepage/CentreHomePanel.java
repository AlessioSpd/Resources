package views.homepage;

import java.awt.Graphics;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import config.Settings;
import utilities.Handlers.ImageHandler;
import utilities.constants.Path;

public class CentreHomePanel extends JPanel{

	public CentreHomePanel() {
		super(new GridBagLayout());
		this.paintComponents(getGraphics());
	}	
	
	@Override
	protected void paintComponent(Graphics g) {		
		super.paintComponent(g);
		ImageIcon icon = ImageHandler.getScaledIcon(Path.WALL_IMG, Settings.screenWidth, Settings.screenHeight);
	    g.drawImage(icon.getImage(), 0,0, null);	    
	}
}

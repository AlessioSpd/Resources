package views.gamepage.left;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import models.pieces.Piece;

import config.Settings;

public class LeftPanel extends JPanel{

	public LeftTopPanel light;
	public LeftCenterPanel center;
	public LeftTopPanel dark;
	
	public LeftPanel() {
		super(new GridLayout(3,1,0,0));
		this.initPanel();
	}
	
	private void initPanel() {

		this.setPreferredSize(new Dimension((Settings.screenWidth-Settings.screenHeight) / 2, Settings.screenHeight));
		
		this.light = new LeftTopPanel("LIGHT");
		this.center = new LeftCenterPanel();
		this.dark = new LeftTopPanel("DARK");
		
		this.add(light);
		this.add(center);
		this.add(dark);
	}
	
	public void loadNewSettings() {
		light.setName(Settings.namePlayer1);
		dark.setName(Settings.namePlayer2);
		
		light.reloadSettings();
		dark.reloadSettings();
	}
	
	public void setTurno() {
		this.light.setTurnLabel();
		this.dark.setTurnLabel();
	}
	
	public void resetCount() {
		this.light.countEaten.setCount(null);
		this.dark.countEaten.setCount(null);
	}
}

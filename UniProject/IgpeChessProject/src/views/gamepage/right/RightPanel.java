package views.gamepage.right;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import config.Settings;

public class RightPanel extends JPanel{
	
	public RightTopPanel top;
	public RightDownPanel center;
	
	public RightPanel() {
		super(new BorderLayout());
		this.initPanel();
	}
	
	public void initPanel() {
		this.setPreferredSize(new Dimension((Settings.screenWidth-Settings.screenHeight) / 2, Settings.screenHeight));
		this.top = new RightTopPanel();
		this.center = new RightDownPanel();
		this.add(top, BorderLayout.NORTH);
		this.add(center, BorderLayout.CENTER);
	}
}

package views.gamepage.left;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import config.Settings;
import utilities.Handlers.ImageHandler;
import utilities.Handlers.TemplateHandler;
import utilities.constants.Path;
import views.dialogs.EndGameDialog;

public class LeftCenterPanel extends JPanel{

	private int width;
	private int height;
	public JButton startStop;
	public ImageIcon startIcon;
	public ImageIcon stopIcon;
	
	public LeftCenterPanel() {
		super(new BorderLayout());
		this.width = (Settings.screenWidth- Settings.screenHeight)/2;
		this.height =  Settings.screenHeight/5;
		startStop = new JButton();
		this.startIcon = ImageHandler.getScaledIcon(Path.START_IMG, this.height/2, this.height/2);
		this.stopIcon = ImageHandler.getScaledIcon(Path.STOP_IMG, this.height/2, this.height/2);
		startStop.setIcon(this.startIcon);
		this.initPanel();
	}
	
	public void changeColor() {
		this.setBackground(TemplateHandler.blend(Settings.lightSquare, Settings.darkSquare));
		startStop.setOpaque(false);
		startStop.setContentAreaFilled(false);
		startStop.setFocusPainted(false);
	}
	
	
	private void initPanel() {
		this.setPreferredSize(new Dimension(width, height));
		changeColor();
		this.add(startStop, BorderLayout.CENTER);
		
	}
}

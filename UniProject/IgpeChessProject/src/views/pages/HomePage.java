package views.pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import config.Settings;
import utilities.Item;
import utilities.Handlers.ImageHandler;
import utilities.constants.Path;
import views.homepage.CentreHomePanel;
import views.shared.TopRightCorner;

public class HomePage extends JPanel{
	
	public Item versusPl;
	public Item versusPC;
	public TopRightCorner topRightCorner;
	public PropertyChangeSupport trigger;
	public CentreHomePanel centrePanel;
	
	
	public HomePage() {
		trigger = new PropertyChangeSupport(new JButton());
		this.setBackground(Color.gray);
		this.setLayout(new BorderLayout());
		this.centrePanel = new CentreHomePanel();
		
		initPanel();
	}
	
	private void initPanel() {
		int w = Settings.screenHeight/3;
		int h = Settings.screenHeight/2;
		JPanel northPanel = new JPanel(new BorderLayout());
		topRightCorner = new TopRightCorner();
		northPanel.add(topRightCorner, BorderLayout.EAST);
		
		centrePanel.paintComponents(getGraphics());
		
		ImageIcon img = ImageHandler.getScaledIcon(Path.PVP_IMG, w, h);
		
		//versus Player button
		versusPl = new Item(new JButton());
		versusPl.gbc.gridx = 0;
		versusPl.gbc.gridy = 1;
		versusPl.gbc.insets = new Insets(0, -Settings.screenWidth/5, 0, 0);
		
		((Component) versusPl.component).setPreferredSize(new Dimension(w, h));
		((AbstractButton) versusPl.component).setIcon(img);
		centrePanel.add((Component) versusPl.component, versusPl.gbc);
		
		img = ImageHandler.getScaledIcon(Path.PVC_IMG, w, h);
		versusPC = new Item(new JButton());
		versusPC.gbc.gridx = 1;
		versusPC.gbc.gridy = 1;
		versusPC.gbc.insets = new Insets(0, 0, 0, -Settings.screenWidth/5);
		
		((Component) versusPC.component).setPreferredSize(new Dimension(w, h));
		((AbstractButton) versusPC.component).setIcon(img);
		centrePanel.add((Component) versusPC.component, versusPC.gbc);
		
		this.add(northPanel, BorderLayout.NORTH);
		this.add(centrePanel, BorderLayout.CENTER);
	}
}

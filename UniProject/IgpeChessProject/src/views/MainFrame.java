package views;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import config.Settings;
import views.pages.GamePage;
import views.pages.HomePage;

public class MainFrame extends JFrame {
	
	public HomePage homePage;
	public GamePage gamePage;
	
	public MainFrame(String name){
		super(name);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();
		Settings.screenHeight = height;
		Settings.screenWidth = width;
		
		this.homePage = new HomePage();
		this.gamePage = new GamePage();
		this.showPanel(homePage);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.setUndecorated(true);
		this.setVisible(true);
		
	}
	
	public void showPanel(JPanel obj) {
		this.setContentPane(obj); 
		this.repaint();             
		this.revalidate();
	}

}

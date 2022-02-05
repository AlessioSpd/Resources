package views.shared;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.FileHandler;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import config.Settings;
import utilities.Handlers.TemplateHandler;

public class myBarMenu extends JMenuBar{
	
	public JMenu newGame;
	public JMenu file;
	
	public JMenuItem chessVsPc;
	public JMenuItem chessVsHuman;
	public JMenuItem save;
	public JMenuItem load;
	public JMenuItem replay;
	
	public myBarMenu() {
		super();
		
		newGame = new JMenu("Play");
			chessVsPc = new JMenuItem("Player vs PC");
			chessVsHuman = new JMenuItem("Player vs Player");
			
			newGame.add(chessVsPc);
			newGame.add(chessVsHuman);
		
		file = new JMenu("File");
			save = new JMenuItem("Save");
			load = new JMenuItem("Load");
			replay = new JMenuItem("Replay");
			
			file.add(save);
			file.add(load);
			file.add(replay);
			
		
		this.add(newGame);
		this.add(file);
	}
}
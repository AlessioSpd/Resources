package views.dialogs;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utilities.constants.Constants;
import utilities.constants.Path;

import config.Settings;
import utilities.Item;
import utilities.Handlers.ImageHandler;

public class EndGameDialog extends JDialog{
	
	private final Font font = new Font("Arial", Font.PLAIN, Settings.screenHeight/35);
	
	private int width;
	private int height;
	
	private ImageIcon winnerIcon;
	
	private JLabel winnerIconLabel;
	private JLabel winText;
	private JLabel nullRow;
	public JButton newGame;
	public JButton save;
	private JPanel packer;
	public JButton replay;
	
	private int teamLoser;
	GridBagConstraints gbc;
	
	public EndGameDialog(int team) {
		super();
		this.teamLoser = team;
		initIcon();
		initFrame();
		initComponent();
	}
	
	private void initIcon() {
		int dim = (int)(Settings.screenHeight/3.6);
		
		switch(teamLoser) {
			case 1:
				winnerIcon = ImageHandler.getScaledIcon(Path.WIN_BLACK_IMG, dim, dim);
				break;
			
			case -1:
				winnerIcon = ImageHandler.getScaledIcon(Path.WIN_WHITE_IMG, dim, dim);
				break;
			
			case 0:
				winnerIcon = ImageHandler.getScaledIcon(Path.DRAW_IMG, dim, dim);
				break;
		}
	}
	
	private void initFrame() {
		this.packer = new JPanel(new GridBagLayout());
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModal(true);
		this.width = (int) (Settings.screenHeight/2.15);
		this.height = width;
		packer.setPreferredSize(new Dimension(width,height));
		this.setResizable(false);
		this.setPreferredSize(new Dimension(width,height));
		
	}
	
	private void initComponent() {
		gbc = new GridBagConstraints();
		
		winnerIconLabel = new JLabel();
		winnerIconLabel.setIcon(winnerIcon);
		gbc.gridx = 1;
		gbc.gridy = 0;
		packer.add(winnerIconLabel, gbc);
		
		
		switch(teamLoser) {
			case 1:
				winText = new JLabel("Dark player wins!");
				break;
			
			case -1:
				winText = new JLabel("Light player wins!");
				break;
			
			case 0:
				winText = new JLabel("Both players win!");
				break;
		}
		
		winText.setFont(font);
		gbc.gridx = 1;
		gbc.gridy = 1;
		packer.add(winText, gbc);
		
		nullRow = new JLabel(" \n \n");
		gbc.gridx = 1;
		gbc.gridy = 2;
		packer.add(nullRow, gbc);
		
		save = new JButton("SAVE");
		gbc.gridx = 0;
		gbc.gridy = 3;
		packer.add(save, gbc);
		
		newGame = new JButton("PLAY AGAIN");
		gbc.gridx = 1;
		gbc.gridy = 3;
		packer.add(newGame, gbc);
		
		replay = new JButton("REPLAY");
		gbc.gridx = 2;
		gbc.gridy = 3;
		packer.add(replay, gbc);
		
		this.add(packer);
	}
}

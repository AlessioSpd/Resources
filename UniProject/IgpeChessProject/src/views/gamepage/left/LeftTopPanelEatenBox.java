package views.gamepage.left;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import config.Settings;
import utilities.Handlers.ImageHandler;
import utilities.Handlers.TemplateHandler;
import utilities.constants.Path;

public class LeftTopPanelEatenBox extends JPanel {
	
	private boolean team;
	private int iconWidth;
	private int iconHeight;
	private JLabel rook;
	private JLabel bishop;
	private JLabel queen;
	private JLabel king;
	private JLabel knight;
	private JLabel pawn;
	
	
	public LeftTopPanelEatenBox(boolean team, Color back) {
		super(new GridLayout(1, 12));
		this.team = team;
		this.setPreferredSize(new Dimension((Settings.screenWidth-Settings.screenHeight)/2, (Settings.screenHeight/3-100)/2));
		this.iconWidth = Settings.screenWidth/50;
		this.iconHeight = iconWidth;
		initLabel();
		initPanel(back);
	}
	
	private void initLabel() {
		rook = new JLabel(ImageHandler.getScaledIcon(!team ? Path.LIGHT_ROOK_IMG : Path.DARK_ROOK_IMG, iconWidth, iconHeight));
		bishop = new JLabel(ImageHandler.getScaledIcon(!team ? Path.LIGHT_BISHOP_IMG : Path.DARK_BISHOP_IMG, iconWidth, iconHeight));
		queen = new JLabel(ImageHandler.getScaledIcon(!team ? Path.LIGHT_QUEEN_IMG : Path.DARK_QUEEN_IMG, iconWidth, iconHeight));
		king = new JLabel(ImageHandler.getScaledIcon(!team ? Path.LIGHT_KING_IMG : Path.DARK_KING_IMG, iconWidth, iconHeight));
		knight = new JLabel(ImageHandler.getScaledIcon(!team ? Path.LIGHT_KNIGHT_IMG : Path.DARK_KNIGHT_IMG, iconWidth, iconHeight));
		pawn = new JLabel(ImageHandler.getScaledIcon(!team ? Path.LIGHT_PAWN_IMG : Path.DARK_PAWN_IMG, iconWidth, iconHeight));
		
		if(!team) {
			rook.setVerticalAlignment(SwingConstants.BOTTOM);
			bishop.setVerticalAlignment(SwingConstants.BOTTOM);
			queen.setVerticalAlignment(SwingConstants.BOTTOM);
			king.setVerticalAlignment(SwingConstants.BOTTOM);
			knight.setVerticalAlignment(SwingConstants.BOTTOM);
			pawn.setVerticalAlignment(SwingConstants.BOTTOM);
		}else {
			rook.setVerticalAlignment(SwingConstants.TOP);
			bishop.setVerticalAlignment(SwingConstants.TOP);
			queen.setVerticalAlignment(SwingConstants.TOP);
			king.setVerticalAlignment(SwingConstants.TOP);
			knight.setVerticalAlignment(SwingConstants.TOP);
			pawn.setVerticalAlignment(SwingConstants.TOP);
		}
	}
	
	public void setBack(Color color){
		this.setBackground(color);
	}
	
	private void initPanel(Color back) {
		this.setPreferredSize(new Dimension((Settings.screenWidth-Settings.screenHeight)/2, Settings.screenHeight/5*4));
		this.setBackground(back);
		
		this.add(rook);
		this.add(new JLabel());
		
		this.add(bishop);
		this.add(new JLabel());
		
		this.add(queen);
		this.add(new JLabel());
		
		this.add(king);
		this.add(new JLabel());
		
		this.add(knight);
		this.add(new JLabel());
		
		this.add(pawn);
	}
}

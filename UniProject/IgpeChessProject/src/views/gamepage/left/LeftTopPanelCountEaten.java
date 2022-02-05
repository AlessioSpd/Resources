package views.gamepage.left;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import config.Settings;
import models.pieces.Piece;
import utilities.Handlers.TemplateHandler;

public class LeftTopPanelCountEaten extends JPanel{

	private Color back;
	private Color textColor;
	private JLabel rookCount;
	private JLabel bishopCount;
	private JLabel queenCount;
	private JLabel kingCount;
	private JLabel knightCount;
	private JLabel pawnCount;
	private Font font;
	
	private ArrayList<Filler> fillers;
	
	
	private class Filler extends JLabel{
		public Filler(Color color) {
			this.setOpaque(true);
			this.setBackground(color);
		}
	};
	
	public LeftTopPanelCountEaten(boolean team, Color back) {
		super(new GridLayout(1, 12));
		this.fillers = new ArrayList<Filler>();
		
		
		this.setPreferredSize(new Dimension((Settings.screenWidth-Settings.screenHeight)/2, (Settings.screenHeight/3-100)/2));
		this.textColor = team ? Settings.darkSquare : Settings.lightSquare;
		this.setBackground(back);
		this.back = back;
		
		for(int i = 0; i < 5; ++i) {
			Filler filler = new Filler(back);
			fillers.add(filler);
		}
		
		initFont();
		initLabel();
		initPanel();
	}
	
	public void setBack(Color color,  boolean team) {
		this.textColor = team ? Settings.darkSquare : Settings.lightSquare;
		this.setBackground(color);
		
		rookCount.setForeground(textColor);
		rookCount.setBackground(color);
		
		bishopCount.setForeground(textColor);
		bishopCount.setBackground(color);
		
		queenCount.setForeground(textColor);
		queenCount.setBackground(color);
		
		kingCount.setForeground(textColor);
		kingCount.setBackground(color);
		
		knightCount.setForeground(textColor);
		knightCount.setBackground(color);
		
		pawnCount.setForeground(textColor);
		pawnCount.setBackground(color);
		
		for(int i = 0; i < 5; ++i)
			fillers.get(i).setBackground(color);
	}
	
	public void setCount(ArrayList<Piece> vec) {

		int rookC = 0;
		int bishopC = 0;
		int knightC = 0;
		int kingC = 0;
		int queenC = 0;
		int pawnC = 0;

		if(vec != null) {
			for (int i = 0; i < vec.size(); i++) {
				Piece eatenPiece = vec.get(i);
	
				switch (eatenPiece.getType()) {
				case ROOK: rookC++; break;
				case BISHOP: bishopC++; break;
				case QUEEN: queenC++; break;
				case KING: kingC++; break;
				case KNIGHT: knightC++; break;
				case PAWN: pawnC++; break;
				default:break;
				}
	
			}
		}

		rookCount.setText(String.valueOf(rookC));
		bishopCount.setText(String.valueOf(bishopC));
		queenCount.setText(String.valueOf(queenC));
		kingCount.setText(String.valueOf(kingC));
		knightCount.setText(String.valueOf(knightC));
		pawnCount.setText(String.valueOf(pawnC));

	}
	
	private void initFont(){
		this.font = new Font("roboto", Font.BOLD, Settings.screenWidth/110);	
	}
	
	private void initPanel() {
		this.add(rookCount);
		this.add(fillers.get(0));
		
		this.add(bishopCount);
		this.add(fillers.get(1));
		
		this.add(queenCount);
		this.add(fillers.get(2));
		
		this.add(kingCount);
		this.add(fillers.get(3));
		
		this.add(knightCount);
		this.add(fillers.get(4));
		
		this.add(pawnCount);	
	}
	
	private void initLabel() {
		rookCount = new JLabel("0", JLabel.CENTER);
		rookCount.setOpaque(true);
		rookCount.setForeground(textColor);
		rookCount.setBackground(back);
		rookCount.setFont(font);
		
		bishopCount = new JLabel("0", JLabel.CENTER);
		bishopCount.setOpaque(true);
		bishopCount.setForeground(textColor);
		bishopCount.setBackground(back);
		bishopCount.setFont(font);
		
		queenCount = new JLabel("0", JLabel.CENTER);
		queenCount.setOpaque(true);
		queenCount.setForeground(textColor);
		queenCount.setBackground(back);
		queenCount.setFont(font);
		
		kingCount = new JLabel("0", JLabel.CENTER);
		kingCount.setOpaque(true);
		kingCount.setForeground(textColor);
		kingCount.setBackground(back);
		kingCount.setFont(font);
		
		knightCount = new JLabel("0", JLabel.CENTER);
		knightCount.setOpaque(true);
		knightCount.setForeground(textColor);
		knightCount.setBackground(back);
		knightCount.setFont(font);
		
		pawnCount = new JLabel("0", JLabel.CENTER);
		pawnCount.setOpaque(true);
		pawnCount.setForeground(textColor);
		pawnCount.setBackground(back);
		pawnCount.setFont(font);
	}
}

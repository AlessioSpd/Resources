package views.gameboard;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import config.Settings;
import models.board.Square;
import utilities.Handlers.TemplateHandler;
import utilities.constants.Constants;

public class SquareView extends JButton {
	
	private Square model;
	private PieceView pieceView;
	private boolean selected;
	Font font;
	
	public SquareView(Square model) {
		super();
		this.model = model;
		this.selected = false;
		this.font = new Font("roboto", Font.BOLD, Settings.screenHeight/48);
		this.pieceView = new PieceView(this.model.getPiece());
		this.initSquareView();
	}
	
	private void initSquareView() {
		this.setFocusPainted(false);
		this.setLayout(new BorderLayout());
		this.setBackground(this.model.getColoreness() == Constants.LIGHT ? Settings.lightSquare : Settings.darkSquare);
		this.setPreferredSize(new Dimension(Settings.screenHeight/8, Settings.screenHeight/8));
		this.add(this.pieceView, BorderLayout.CENTER);
	}
	
	public void addMouseListener(ActionListener actionListener) {
		this.addMouseListener(actionListener);
	}
	
	public void setModel(Square model) {
		this.model = model;
	}	
	
	public void updateView() {
		if(this.selected) this.setBackground(Settings.selectedSquare);
		else if(this.model.getColoreness() == Constants.LIGHT) {
			this.setBackground(Settings.lightSquare);
			this.setForeground(Settings.darkSquare);
		}
		else if(this.model.getColoreness() == Constants.DARK) {
			this.setBackground(Settings.darkSquare);
			this.setForeground(Settings.lightSquare);
		}
		this.pieceView.setPiece(this.model.getPiece());
		this.pieceView.updateView();
	}
	
	public Square getSquare() {
		return this.model;
	}
	public PieceView getPieceView() {
		return this.pieceView;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public void addPos(Object obj) {
		if(obj instanceof Character) {
			this.setVerticalAlignment(BOTTOM);
			this.setHorizontalAlignment(RIGHT);
			this.setText(obj.toString().toUpperCase());
			this.setForeground(TemplateHandler.retrieveColorTextButton(this.model));
		}
		else if(obj instanceof Integer) {
			this.setVerticalAlignment(TOP);
			this.setHorizontalAlignment(LEFT);
			this.setText(obj.toString());
			this.setForeground(TemplateHandler.retrieveColorTextButton(this.model));
		}
		this.setFont(font);
	}
	
	public void addPosDouble(int num, char ch) {
		this.setVerticalAlignment(BOTTOM);
		this.setHorizontalAlignment(LEFT);
		String s = Integer.toString(num) + " " + Character.toString(ch).toUpperCase();
		this.setText(s);
		this.setFont(font);
		this.setForeground(TemplateHandler.retrieveColorTextButton(this.model));
		
	}
}

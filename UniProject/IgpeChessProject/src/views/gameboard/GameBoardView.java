package views.gameboard;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import config.Settings;
import models.board.GameBoard;
import models.pieces.Position;

public class GameBoardView extends JPanel{

	public GameBoard model;
	public SquareView[][] tiles;
	
	public GameBoardView(GameBoard model) {
		super(new GridLayout(8,8,0,0));
		this.model = model;
		this.tiles = new SquareView[8][8];
		this.initGameBoardView();
	}

	private void initGameBoardView() {
		
		this.setPreferredSize(new Dimension(Settings.screenHeight, Settings.screenHeight));
		char[] chars = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h' };
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Object obj;
				this.tiles[i][j] = new SquareView(this.model.getTableAt(i,j));
				this.add(this.tiles[i][j]);
				if(i == 7 && j == 0) {
					this.tiles[i][j].addPosDouble(1, 'a');
				}else if(j == 0){
					this.tiles[i][j].addPos(8-i);
				}
				else if(i == 7) {
					this.tiles[i][j].addPos(chars[j]);
				}
			}
		}
		this.updateView(null);
	}
	
	public void updateView(ArrayList<Position> legalMoves) {
		
		if(legalMoves == null || legalMoves.size() == 0 ) {
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				this.tiles[i][j].updateView();
		}
		else {
		for (Position move : legalMoves) {
			int i = move.getX();
			int j = move.getY();
			tiles[i][j].updateView();
		}
		}
	}
	
	public SquareView[][] getTiles() {
		return this.tiles;
	}
}

package models.pieces;

import java.util.ArrayList;
import models.board.GameBoard;
import utilities.constants.Enums.*;

public class Bishop extends Piece{

	public Bishop(PieceType type, int coloreness) {
		super(type, coloreness);
	}
	
	@Override
	public ArrayList<Position> legalMoves(GameBoard board) {
		
		ArrayList<Position> moves = new ArrayList<Position>();
		int x = this.getCoordinateX();
		int y = this.getCoordinateY();
		int NorthE = 0, NorthO = 0, SouthO = 0, SouthE = 0;
		
		for(int i = 1; i < 8; ++i) {
			if(x-i >= 0 && y-i >= 0 && NorthE == 0) NorthE = searchOn(board, x-i, y-i, moves);
			if(x+i < 8 && y+i < 8 && SouthO == 0) SouthO = searchOn(board, x+i, y+i, moves);
			if(x-i >= 0 && y+i < 8 && NorthO == 0) NorthO = searchOn(board, x-i, y+i, moves);
			if(x+i < 8 && y-i >= 0 && SouthE == 0) SouthE = searchOn(board, x+i, y-i, moves);
		}
		return moves;
	}
}

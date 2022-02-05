package models.pieces;

import java.util.ArrayList;
import models.board.GameBoard;
import utilities.constants.Enums.*;

public class Rook extends Piece{

	public Rook(PieceType type, int coloreness) {
		super(type, coloreness);
	}
	
	@Override
	public ArrayList<Position> legalMoves(GameBoard board) {
		
		ArrayList<Position> moves = new ArrayList<Position>();
		int x = this.getCoordinateX();
		int y = this.getCoordinateY();
		int North = 0, East = 0, South = 0, Ovest = 0;
		
		for(int i = 1; i < 8; ++i) {			
			if(x+i < 8 && South == 0) South = searchOn(board, x+i, y, moves);
			if(x-i >= 0 && North == 0) North = searchOn(board, x-i, y, moves);
			if(y-i >= 0 && East == 0) East = searchOn(board, x, y-i, moves);
			if(y+i < 8 && Ovest == 0) Ovest = searchOn(board, x, y+i, moves);
		}		
		return moves;
	}
}

package models.pieces;

import java.util.ArrayList;
import models.board.GameBoard;
import utilities.constants.Enums.*;

public class Knight extends Piece{

	public Knight(PieceType type, int coloreness) {
		super(type, coloreness);
	}


	@Override
	public ArrayList<Position> legalMoves(GameBoard board) {
		
		ArrayList<Position> moves = new ArrayList<Position>();
		int x = this.getCoordinateX();
		int y = this.getCoordinateY();
		int[] X = {2, 1, -1, -2, -2, -1, 1, 2};
	    int[] Y = {1, 2, 2, 1, -1, -2, -2, -1};
	  
	    for (int i = 0; i < 8; i++) {
	  
	        int k = x + X[i];
	        int z = y + Y[i];
	        
	        boolean isValid = k >= 0 && z >= 0 && k < 8 && z < 8;
	        
	        if(isValid) {
		        Piece piece = board.getPieceAt(k, z);
	        	if(piece == null || (piece != null && piece.coloreness != this.coloreness))
		        	moves.add(new Position(k,z));
	        }
	    }
	    
	    return moves;
	}
}

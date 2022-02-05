package models.pieces;

import java.util.ArrayList;

import models.board.GameBoard;
import utilities.PieceFactory;
import utilities.constants.Constants;
import utilities.constants.Enums.*;

public class Queen extends Piece{

	public Queen(PieceType type, int coloreness) {
		super(type, coloreness);
	}

	@Override
	public ArrayList<Position> legalMoves(GameBoard board) {
		
		ArrayList<Position> moves = new ArrayList<Position>();

		boolean isDark = this.coloreness == Constants.DARK;
		
		Rook tempRook = isDark ? (Rook)PieceFactory.getNewIstanceOfPiece('r') : (Rook)PieceFactory.getNewIstanceOfPiece('R');
		Bishop tempBishop = isDark ? (Bishop)PieceFactory.getNewIstanceOfPiece('b') : (Bishop)PieceFactory.getNewIstanceOfPiece('B');
		
		tempRook.position = new Position(this.position);
		tempBishop.position = new Position(this.position);
		
		board.setPieceAt(tempRook.position, tempRook);
		moves.addAll(tempRook.legalMoves(board));
		
		board.setPieceAt(tempBishop.position, tempBishop);
		for(Position position : tempBishop.legalMoves(board))
			if(!moves.contains(position))
				moves.add(position);

		board.setPieceAt(this.position, this);
		
		return moves;
	}
}

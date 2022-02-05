package models.pieces;

import java.util.ArrayList;
import models.board.GameBoard;
import models.board.Mover;
import utilities.constants.Enums.*;

public abstract class Piece {

	protected PieceType type;
	protected final int coloreness;
	protected Position position;
	protected boolean hasMoved;
	
	public Piece(PieceType type, int coloreness) {
		this.type = type;
		this.coloreness = coloreness;
		this.hasMoved = false;
	}
	
	public void  deleteDangerMove(GameBoard board, ArrayList<Position> moves){
		
		King myKing = null;
		Position myCopy = new Position(this.position);
		ArrayList<Piece> allies = board.getPieceManger().getPiecesOfColoreness(this.coloreness);
		
		for(Piece piece : allies )
			if(piece instanceof King)
				myKing = (King)piece;
		
		ArrayList<Position> tempMoves = new ArrayList<>();
		
		for(Position move : moves) {
			Piece box = board.getPieceAt(move);
			Mover.moveSimulation(board, this, move);
			boolean isInDanger =  myKing.amIGonnaDie(board);
			if(box != null) board.getPieceManger().reviveEatenPiece(box);
			Mover.moveSimulation(board, this, myCopy);
			board.setPieceAt(move, box);
			if(isInDanger) tempMoves.add(move);
		}
		
		moves.removeAll(tempMoves);
	}
	
	//usata solo in Rook e Bishop
	protected int searchOn(GameBoard board, int x, int y, ArrayList<Position> moves) {
		Piece piece = board.getPieceAt(x, y);
		Position position = new Position(x, y);
		if(piece == null) {
			moves.add(position);
			return 0;
		}else if(piece.coloreness != this.coloreness) {
			moves.add(position);
			return 1;
		}else if(piece.coloreness == this.coloreness) {
			return 1;
		}
		return -1;
	}
	
	public abstract ArrayList<Position> legalMoves(GameBoard board);

	public PieceType getType() {return this.type;}
	public int getColoreness() {return this.coloreness;}
	public Position getPosition() {return position;}
	public int getCoordinateX() {return this.position.getX();}
	public int getCoordinateY() {return this.position.getY();}
	public boolean getHasMoved() {return this.hasMoved;};
	public void setType(PieceType type) {this.type = type;}
	public void setPosition(Position position) {this.position = position;}
	public void setHasMoved(boolean hasMoved) {this.hasMoved = hasMoved;};
	
	@Override
	public String toString() {
		return this.type.toString() + Integer.toString(coloreness) + this.position;
	}
}

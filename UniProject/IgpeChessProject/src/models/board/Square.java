package models.board;

import models.pieces.Piece;
import models.pieces.Position;

public class Square {

	private Piece piece;
	private final int coloreness;
	private final Position position;

	public Square(Piece piece, int coloreness, Position position) {
		this.piece = piece;
		this.coloreness = coloreness;
		this.position = position;
	}
	
	public Piece getPiece() {return piece;}
	public Position getPosition() {return position;}
	public int getColoreness() {return coloreness;}
	
	public void setPiece(Piece piece) {this.piece = piece;}
	
	@Override
	public String toString(){
		return "square " + coloreness + ", pos " + this.position;
	}
}


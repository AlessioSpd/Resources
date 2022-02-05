package models.board;

import java.util.ArrayList;

import models.pieces.Piece;
import utilities.constants.Constants;

public class PieceManager {

	private ArrayList<Piece> lightPieces;
	private ArrayList<Piece> darkPieces;
	private ArrayList<Piece> eatenLightPieces;
	private ArrayList<Piece> eatenDarkPieces;
	
	public PieceManager() {
		this.lightPieces = new ArrayList<Piece>();
		this.darkPieces = new ArrayList<Piece>();
		this.eatenLightPieces = new ArrayList<Piece>();
		this.eatenDarkPieces = new ArrayList<Piece>();
	}
	
	public PieceManager(PieceManager original) {
		this.lightPieces = new ArrayList<Piece>(original.lightPieces);
		this.darkPieces = new ArrayList<Piece>(original.darkPieces);
		this.eatenLightPieces = new ArrayList<Piece>(original.eatenLightPieces);
		this.eatenDarkPieces = new ArrayList<Piece>(original.eatenDarkPieces);
	}
	
	public boolean addPiece(Piece piece){
		int coloreness = piece.getColoreness();
		return coloreness == Constants.LIGHT 
			? this.lightPieces.add(piece)
			: this.darkPieces.add(piece);
	}

	public void addEatenPiece(Piece piece) {
		int coloreness = piece.getColoreness();
		if(coloreness == Constants.LIGHT) {
			this.lightPieces.remove(piece);
			this.eatenLightPieces.add(piece);
		}else {
			this.darkPieces.remove(piece);
			this.eatenDarkPieces.add(piece);
		}
	}
	
	public void reviveEatenPiece(Piece piece) {
		int coloreness = piece.getColoreness();
		boolean z = coloreness == Constants.LIGHT
		? this.eatenLightPieces.remove(piece)
		: this.eatenDarkPieces.remove(piece);
		this.addPiece(piece);
	}
	
	public ArrayList<Piece> getPiecesOfColoreness(int coloreness){
		return coloreness == Constants.DARK ? darkPieces : lightPieces;
	}
	
	public ArrayList<Piece> getPiecesOfOppositeColoreness(int coloreness){
		return coloreness == Constants.LIGHT ? this.darkPieces : this.lightPieces;
	}
	
	public ArrayList<Piece> getEatenPiecesOfColoreness(int coloreness) {
		return coloreness == Constants.LIGHT ? this.eatenLightPieces : this.eatenDarkPieces;
	}
	
	
}

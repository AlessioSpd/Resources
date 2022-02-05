package models.pieces;

import models.board.GameBoard;
import utilities.constants.Constants;
import utilities.constants.Enums.*;

import java.util.ArrayList;

public class Pawn extends Piece{

	private final int upgradeRow;
	
	public Pawn(PieceType type, int coloreness) {
		super(type, coloreness);
		this.upgradeRow = coloreness == Constants.DARK ? 7 : 0;
	}
	
	public int getUpgradeRow() {return this.upgradeRow;}
	
	private ArrayList<Position> searchDiagonal(GameBoard board) {
		ArrayList<Position> moves = new ArrayList<Position>();
		int x = this.getCoordinateX();
		int y = this.getCoordinateY();
		boolean amIDark = this.coloreness == Constants.DARK;
		
		int rowToSearch =  amIDark? x+1 :x-1;
		boolean isRowValid = rowToSearch >= 0 && rowToSearch < 8;
		boolean isLeftValid = y-1 >= 0 && isRowValid ;
		boolean isRightValid = y+1 < 8 && isRowValid;
	
		Piece boxL = isLeftValid ? board.getPieceAt(rowToSearch, y-1) : null;
		Piece boxR = isRightValid ? board.getPieceAt(rowToSearch, y+1) : null;
		
		if(isLeftValid && boxL != null && boxL.getColoreness() != this.coloreness)
			moves.add(new Position(rowToSearch, y-1));
	
		if(isRightValid && boxR != null && boxR.getColoreness() != this.coloreness)
			moves.add(new Position(rowToSearch, y+1));
		
		return moves;
	}

	@Override
	public ArrayList<Position> legalMoves(GameBoard board) {
		
		ArrayList<Position> moves = new ArrayList<Position>();
		
		int x = this.getCoordinateX();
		int y = this.getCoordinateY();
		
		boolean amIDark = this.coloreness == Constants.DARK;
		boolean isFirstMove = (amIDark && x == 1) || (!amIDark && x == 6);
		
		moves.addAll(this.searchDiagonal(board));
		
		ArrayList<Integer> listOfRowsToSearch = new ArrayList<Integer>();
		
		if(amIDark) listOfRowsToSearch.add(1);
		if(!amIDark) listOfRowsToSearch.add(-1);
		if(isFirstMove) listOfRowsToSearch.add(listOfRowsToSearch.get(0)+listOfRowsToSearch.get(0));
		
		for (int i = 0; i < listOfRowsToSearch.size(); i++) {
			int rowToSearch = listOfRowsToSearch.get(i);
			if(x + rowToSearch >= 0 && x + rowToSearch < 8) {
				Piece box = board.getPieceAt(x+rowToSearch, y);
				if(box == null) moves.add(new Position(x+rowToSearch, y));
				else break;
			}
		}
		return moves;
	}
}
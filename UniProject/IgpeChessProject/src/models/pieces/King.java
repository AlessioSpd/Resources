package models.pieces;

import java.util.ArrayList;
import models.board.GameBoard;
import models.board.Mover;
import utilities.constants.Enums.*;

public class King extends Piece{
	
	public King(PieceType type, int coloreness) {
		super(type, coloreness);
	}

	public ArrayList<Position> getDistrict(){
		ArrayList<Position> district = new ArrayList<Position>();
		
		int x = this.getCoordinateX();
		int y = this.getCoordinateY();
		
		int[] X = {-1, -1, 0, 1, 1,  1,  0, -1};
		int[] Y = { 0,  1, 1, 1, 0, -1, -1, -1};
		
		for(int i = 0; i < 8; ++i) {
			int k = x + X[i];
			int j = y + Y[i];
			
			boolean isValid = k >= 0 && k < 8 && j >=0 && j < 8;	
			if(isValid) district.add(new Position(k, j));
		}
		
		return district;
	}

	public boolean amIGonnaDie(GameBoard board) {
		
		ArrayList<Piece> enemies = new ArrayList<Piece>(board.getPieceManger().getPiecesOfOppositeColoreness(this.coloreness));
		
		for(Piece enemy : enemies) {
			
			if(enemy instanceof King) {
				if(((King) enemy).getDistrict().contains(this.position))
					return true;
			}else if(enemy.legalMoves(board).contains(this.position)) return true;
		}
		
		return false;
	}
	
	public boolean amIDead(GameBoard board) {

		ArrayList<Piece> allies = new ArrayList<>(board.getPieceManger().getPiecesOfColoreness(this.coloreness));
		boolean die;
	
		for(Piece ally : allies) {
			Position copy = new Position(ally.position);
			ArrayList<Position> moves = new ArrayList<>(ally.legalMoves(board));
			for(Position move : moves) { 
				Piece box = board.getPieceAt(move);//salvi l eventuale pezzo mangiato
				Mover.moveSimulation(board, ally, move);//simuli la mossa
				System.out.println("simulo " + ally + " in " + move);
				die = amIGonnaDie(board);//controlli lo scacco
				System.out.println("muoio ? " + die);
				if(box != null) board.getPieceManger().reviveEatenPiece(box); //rimetti piece (mangiato eventualmente nel suo array)
				Mover.moveSimulation(board, ally, copy);//torna indietro
				board.setPieceAt(move, box);//risetti l eventuale pezzo mangiato
				if(!die) return false;
			}
		}
		return true;
	}
	
	private ArrayList<Position> checkArrocco(GameBoard board){
		ArrayList<Position> moves = new ArrayList<Position>();
		
		int x = this.getCoordinateX();
		int y = this.getCoordinateY();
		
		if(!this.hasMoved) {
			
			boolean checkLeftRook = board.getPieceAt(x, 0) != null && board.getPieceAt(x, 0).getType() == PieceType.ROOK && !board.getPieceAt(x, 0).hasMoved;
			boolean checkRightRook = board.getPieceAt(x, 7) != null && board.getPieceAt(x, 7).getType() == PieceType.ROOK && !board.getPieceAt(x, 7).hasMoved;
			
			if(checkLeftRook) 
				if(checkEmpty(board, 1, 4)) 
					moves.add(new Position(x,2));			
			
			if(checkRightRook) 
				if(checkEmpty(board, y+1, 7)) 
					moves.add(new Position(x,6));
		}
		return moves;
	}
	
	private boolean checkEmpty(GameBoard board, int start, int end) 
	{
		for(int j = start; j < end; ++j)
			if(board.getPieceAt(this.getCoordinateX(), j) != null)
				return false;
		return true;
	}
	
	
	@Override
	public ArrayList<Position> legalMoves(GameBoard board) {

		ArrayList<Position> moves = new ArrayList<>(this.getDistrict());
		ArrayList<Position> movesToRemove = new ArrayList<>();
		
		for(Position move : moves) { 
			Position copy = new Position(this.position);
			Piece box = board.getPieceAt(move);
			if(box != null && box.getColoreness() == this.coloreness) {
				movesToRemove.add(move);
			}
			else {
				Mover.moveSimulation(board, this, move);
				boolean willDie = amIGonnaDie(board);
				if(box != null) board.getPieceManger().reviveEatenPiece(box);
				Mover.moveSimulation(board, this, copy);
				board.setPieceAt(move, box);
				if(willDie) movesToRemove.add(move);
			}
		}
		
		if(!amIGonnaDie(board)) {
			ArrayList<Position> arrocco = new ArrayList<>(checkArrocco(board));
			moves.addAll(arrocco);
		}
		moves.removeAll(movesToRemove);
		return moves;
	}
}

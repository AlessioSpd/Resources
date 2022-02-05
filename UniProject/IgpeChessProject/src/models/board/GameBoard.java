package models.board;

import java.util.ArrayList;
import java.util.Scanner;


import models.pieces.King;
import models.pieces.Piece;
import models.pieces.Position;
import utilities.PieceFactory;
import utilities.Handlers.FileHandler;
import utilities.constants.Constants;
import utilities.constants.Enums.*;

public class GameBoard {
	
	private Position selectedPos = null;
	private ArrayList<Position> legalMovesOfSelected = null;
	private Square[][] table;
	private PieceManager pieceManager;
	private GameSetting gameSetting;
	private boolean moving = false;

	public GameBoard() {
		this.pieceManager = new PieceManager();
		table = new Square[8][8];
		this.gameSetting = new GameSetting();
	}
	
	public void initTable(String fileName) {
		ArrayList<String> tableLines = FileHandler.getLinesOfFile(fileName);
		int coloreness = Constants.LIGHT;
		for (int i = 0; i < 8; i++) {
			coloreness *= -1;
			for (int j = 0; j < 8; j++) {
				coloreness *= -1;
				Piece piece = PieceFactory.getNewIstanceOfPiece(tableLines.get(i).charAt(j));
				if (piece != null) {
					piece.setPosition(new Position(i, j));
					this.pieceManager.addPiece(piece);
				}
				Square square = new Square(piece, coloreness, new Position(i, j));
				this.table[i][j] = square;
			}
		}
	}	

	public ArrayList<Position> select(Position position) {
		
		if (this.selectedPos == null) {
			if(this.getPieceAt(position) == null) return null;
			if(this.getPieceAt(position).getColoreness() != this.gameSetting.getTurno()) return null;
			this.selectedPos = position;
			legalMovesOfSelected = this.getPieceAt(selectedPos).legalMoves(this);
			this.getPieceAt(selectedPos).deleteDangerMove(this, legalMovesOfSelected);
			return legalMovesOfSelected;
			
		} else {
			boolean isItLegal = legalMovesOfSelected.contains(position) && this.selectedPos != position && position != null;
			ArrayList<Position> temp = null;
			temp = new ArrayList<Position>(legalMovesOfSelected);
			if (isItLegal) {
				temp.addAll(Mover.move(this, this.getPieceAt(this.selectedPos), position));
				this.moving = true;
			}
			this.legalMovesOfSelected.clear();
			this.selectedPos = null;
			return temp;
		}
	}
	
	public CheckType controllaCheck() {
		ArrayList<Piece> allies = this.getPieceManger().getPiecesOfColoreness(gameSetting.getTurno());

		King kingToCheck = null;

		for (int i = 0; i < allies.size(); i++) {
			Piece piece = allies.get(i);
			if (piece instanceof King)
				kingToCheck = (King) piece;
		}

		boolean check = kingToCheck.amIGonnaDie(this);
		System.out.println("sotto scacco? " + check);
		if (check) {
			boolean mate = kingToCheck.amIDead(this);
			if (mate) {
				System.out.println("SCACCO MATTO DUDE");
				FileHandler.closeFile(this);
				return CheckType.MATE;
			}
			else return CheckType.CHECK;
		} 
		else return CheckType.NONE;
	}

	public void modifyRound(int x){gameSetting.setRound(gameSetting.getRound() + x);}
	public void setTableAt(int i, int j, Square s) {this.table[i][j] = s;}
	public void setSelected(Position selected) {this.selectedPos = selected;}
	public boolean getMoving() {return this.moving;}
	public void setMoving(boolean moving) {this.moving = moving;}
	public PieceManager getPieceManger() {return this.pieceManager;}
	public GameSetting getSetting() {return gameSetting;}
	public boolean isFirstMove() {return this.selectedPos == null;}
	public Square getTableAt(int i, int j) {return table[i][j];}
	public Square getTableAt(Position position) {return table[position.getX()][position.getY()];}
	public Piece getPieceAt(int i, int j) {return getTableAt(i, j).getPiece();}
	public Piece getPieceAt(Position position) {return getTableAt(position).getPiece();}
	public Position getSelectedPos() {return this.selectedPos;}
	public void setPieceAt(Position position, Piece piece) { getTableAt(position).setPiece(piece); }
	
	// da eliminare
	public void disposizione1() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Piece piece = this.getPieceAt(i, j);
				if (piece != null) {
					int x = piece.getCoordinateX();
					int y = piece.getCoordinateY();
					PieceType type = piece.getType();
					char color = piece.getColoreness() == Constants.LIGHT ? 'L' : 'D';

					if (type == PieceType.KING)
						System.out.print(
								"(" + color + ")" + type + "(" + x + "," + y + ")" + "    ");
					if (this.getPieceAt(i, j).getType() == PieceType.PAWN)
						System.out.print(
								"(" + color + ")" + type + "(" + x + "," + y + ")" + "    ");
					if (type == PieceType.ROOK)
						System.out.print(
								"(" + color + ")" + type + "(" + x + "," + y + ")" + "    ");
					if (type == PieceType.QUEEN)
						System.out
								.print("(" + color + ")" + type + "(" + x + "," + y + ")" + "   ");
					if (type == PieceType.KNIGHT)
						System.out
								.print("(" + color + ")" + type + "(" + x + "," + y + ")" + "  ");
					if (type == PieceType.BISHOP)
						System.out
								.print("(" + color + ")" + type + "(" + x + "," + y + ")" + "  ");
				} else
					System.out.print("   NULL(" + i + "," + j + ")" + "    ");
			}
			System.out.println("\n");
		}
	}
}

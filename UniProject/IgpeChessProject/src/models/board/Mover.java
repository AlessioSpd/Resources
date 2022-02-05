package models.board;

import java.util.ArrayList;
import java.util.Random;

import models.pieces.King;
import models.pieces.Pawn;
import models.pieces.Piece;
import models.pieces.Position;
import utilities.PieceFactory;
import utilities.Handlers.FileHandler;
import utilities.constants.Constants;
import utilities.constants.Enums.PieceType;

public class Mover {
	
	public static boolean undoSingle = false;
	
	public static void moveSimulation(GameBoard board, Piece piece, Position target) {
		Piece eaten = board.getPieceAt(target);
		Position originalPos = new Position(piece.getPosition());
		if (eaten != null) board.getPieceManger().addEatenPiece(eaten);
		piece.setPosition(target);
		board.setPieceAt(target, piece);
		board.setPieceAt(originalPos, null);
	}

	public static ArrayList<Position> move(GameBoard board, Piece piece, Position target) {
		
		System.out.println("sono al round " + Integer.toString(board.getSetting().getRound()));
		
		System.out.println("devo muovere " + piece + " in " + target);
		
		ArrayList<Position> helper = new ArrayList<Position>();
		
		if(board.getSetting().getMode() != Constants.REPLAY) {
			System.out.println("dentro");
			FileHandler.saveMove(board, piece.getPosition(), target);
		}
		
		if (piece.getType() == PieceType.KING || piece.getType() == PieceType.ROOK)
			piece.setHasMoved(true);

		Piece eaten = board.getPieceAt(target);//null
		Position originalPos = new Position(piece.getPosition());//7,6
		if (eaten != null) board.getPieceManger().addEatenPiece(eaten);

		if (piece instanceof King) {
			
			Position newRookPos = getRookPositionIfArrocco(piece, target);
			Position oldRookPos;

			if (newRookPos != null) {
				int newRookY = newRookPos.getY();
				int x = piece.getCoordinateX();
				//gestisci sia l undo che l arrocco
				
				int oldRookY = newRookY == 3 ? 0 : 7;
				if(newRookY == 3) oldRookY = 0;
				else if(newRookY == 5) oldRookY = 7;
				else if(newRookY == 0) oldRookY = 3;
				else if(newRookY == 7) oldRookY = 5;
				boolean undoArrocco = false;
				if(newRookY == 0 || newRookY == 7) undoArrocco = true;
				oldRookPos = board.getPieceAt(x,oldRookY).getPosition();
				arroccoMoveHelpher(board, piece, oldRookPos, newRookPos);
				if(undoArrocco) {
					board.getPieceAt(newRookPos).setHasMoved(false);
					piece.setHasMoved(false);
					
					int oldDisp = board.getSetting().getArrocco();
					if(oldDisp == Constants.LIGHT || oldDisp == Constants.DARK) board.getSetting().setArrocco(Constants.BOTH);
					else board.getSetting().setArrocco(board.getSetting().getTurno() * -1);
					FileHandler.writeNewSetting(board);
					
				}
				for (int j = 0; j < 8; j++)
					helper.add(new Position(piece.getCoordinateX(), j));
				board.getSetting().updateDisponibilityOf(Constants.ARROCCO);
				FileHandler.writeNewSetting(board);
			}
		}

		piece.setPosition(target);
		board.setPieceAt(target, piece);
		board.setPieceAt(originalPos, null);

		board.getSetting().changeTurno();
		board.modifyRound(1);
		
		board.getSetting().setCountUndo(0);
		return helper;
		
	}
	
	private static void arroccoMoveHelpher(GameBoard board, Piece king, Position oldRookPos, Position newRookPos) {	
		board.getPieceAt(oldRookPos).setPosition(newRookPos);
		board.setPieceAt(newRookPos, board.getPieceAt(oldRookPos));
		board.setPieceAt(oldRookPos, null);
	}
	
	
	private static Position getRookPositionIfArrocco(Piece piece, Position target) {
		int x = piece.getCoordinateX();
		int y = piece.getCoordinateY();
		if (Math.abs(target.getY() - y) == 2) {
			System.out.println("entro qui dentro");
			if(target.getY() == 2) return new Position(x, 3);
			else if(target.getY() == 6) return new Position(x, 5);
			
			//gestore caso undo incorporato qui
			else if(target.getY() == 4) return new Position(x,7);
			else if(target.getY() == 2) return new Position(x,4);
		}
		return null;
	}
	
	public static ArrayList<Position> moveFromLine(GameBoard board, String line) {
		
		Position startPos = Position.reverseTranslate(line.substring(0, 2));
		Position endPos = Position.reverseTranslate(line.substring(8, 10));
		Piece piece = board.getPieceAt(startPos);
		Position target = endPos;
		ArrayList<Position> legals = Mover.move(board, piece, target);
		if(line.charAt(line.length()-1) == ':') {
			System.out.println(line.charAt(line.length()-2));
			board.setPieceAt(target, PieceFactory.getNewIstanceOfPiece(line.charAt(line.length()-2)));
			board.getPieceAt(target).setPosition(target);
		}
		return legals;
	}

	public static ArrayList<Position> undo(GameBoard board, boolean forced) {

		int turno = board.getSetting().getTurno();
		int undoType = board.getSetting().getUndo();
		int round = board.getSetting().getRound();
		int count = board.getSetting().getCountUndo();
		
		Piece eatenPiece = null;
		
		ArrayList<Piece> eatenPieces = board.getPieceManger().getEatenPiecesOfColoreness(turno);;
		boolean isEmpty = eatenPieces.size() == 0;
		boolean isValid = undoType == Constants.BOTH || turno == undoType*-1 || forced;
		
		if(count == 1 || round == 0 || !isValid) return null;
		
		ArrayList<Position> temp = new ArrayList<Position>();
		ArrayList<String> log = (board.getSetting().getLogFile());
		String lastLine = log.get(log.size()-1);
		Position endPos = Position.reverseTranslate(lastLine.substring(0,2));
		Position startPos = Position.reverseTranslate(lastLine.substring(8,10));
		
		if(!isEmpty && lastLine.charAt(11) != '-') eatenPiece = eatenPieces.get(eatenPieces.size()-1);
		
		if(lastLine.charAt(lastLine.length()-1) == ':') {
			
			ArrayList<Piece> specialAllies = board.getPieceManger().getPiecesOfColoreness(board.getPieceAt(startPos).getColoreness());
			specialAllies.remove(board.getPieceAt(startPos));
			
			board.setPieceAt(startPos, PieceFactory.getNewIstanceOfPiece(turno == -1 ? 'P' : 'p'));
			board.getPieceAt(startPos).setPosition(startPos);
			
			specialAllies.add(board.getPieceAt(startPos));
		}
		
		
		temp.addAll(Mover.move(board, board.getPieceAt(startPos), endPos));
		board.modifyRound(-2);
		
		FileHandler.deleteNLine(board, 2);

		if (!isEmpty && lastLine.charAt(11) != '-') {
			board.getPieceManger().reviveEatenPiece(eatenPiece);
			board.setPieceAt(startPos, eatenPiece);
		}
		
		board.getSetting().updateDisponibilityOf(Constants.UNDO);
		board.getSetting().setCountUndo(1);
		FileHandler.writeNewSetting(board);
		
		if(board.getSetting().getMode() == Constants.SINGLEPLAYER && !undoSingle) {
			Mover.undoSingle = true;
			board.getSetting().setCountUndo(0);
			temp.addAll(undo(board, true));
		}
		
		return temp;
	}	
}

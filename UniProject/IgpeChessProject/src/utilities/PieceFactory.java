package utilities;

import models.pieces.*;
import utilities.constants.Constants;
import utilities.constants.Enums.*;

public class PieceFactory {
	
	public PieceFactory() {};
	
    public static Piece getNewIstanceOfPiece (char letter){
    	Piece piece;
        switch (letter){
            case 'r' : piece = new Rook(PieceType.ROOK, Constants.DARK); break;
            case 'n' : piece = new Knight(PieceType.KNIGHT, Constants.DARK); break;
            case 'b' : piece = new Bishop(PieceType.BISHOP, Constants.DARK); break;
            case 'k' : piece = new King(PieceType.KING, Constants.DARK); break;
            case 'q' : piece = new Queen(PieceType.QUEEN, Constants.DARK); break;
            case 'p' : piece = new Pawn(PieceType.PAWN, Constants.DARK); break;
            case 'R' : piece = new Rook(PieceType.ROOK, Constants.LIGHT); break;
            case 'N' : piece = new Knight(PieceType.KNIGHT, Constants.LIGHT); break;
            case 'B' : piece = new Bishop(PieceType.BISHOP, Constants.LIGHT); break;
            case 'K' : piece = new King(PieceType.KING, Constants.LIGHT); break;
            case 'Q' : piece = new Queen(PieceType.QUEEN, Constants.LIGHT); break;
            case 'P' : piece = new Pawn(PieceType.PAWN, Constants.LIGHT); break;
            case '-' : piece = null; break;
            default : throw new IllegalArgumentException("Unexpected value: " + letter);
        }
        return piece;
    }
    
    public static Piece getNewIstanceOfPiece (Piece piece) {
    	if(piece == null) return null;
    	Piece newPiece = null;
    	PieceType type = piece.getType();
    	int coloreness = piece.getColoreness();
    	switch (type) {
    		case ROOK : newPiece = coloreness == Constants.DARK ? getNewIstanceOfPiece('r') : getNewIstanceOfPiece('R');
    		case KNIGHT : newPiece = coloreness == Constants.DARK ? getNewIstanceOfPiece('n') : getNewIstanceOfPiece('N');
    		case BISHOP : newPiece = coloreness == Constants.DARK ? getNewIstanceOfPiece('b') : getNewIstanceOfPiece('B');
    		case KING : newPiece = coloreness == Constants.DARK ? getNewIstanceOfPiece('k') : getNewIstanceOfPiece('K');
    		case QUEEN : newPiece = coloreness == Constants.DARK ? getNewIstanceOfPiece('q') : getNewIstanceOfPiece('Q');
    		case PAWN : newPiece = coloreness == Constants.DARK ? getNewIstanceOfPiece('p') : getNewIstanceOfPiece('P');
    		case MAN : newPiece = coloreness == Constants.DARK ? getNewIstanceOfPiece('m') : getNewIstanceOfPiece('M');
    		case DAMA : newPiece = coloreness == Constants.DARK ? getNewIstanceOfPiece('d') : getNewIstanceOfPiece('D');
    	}
    	newPiece.setPosition(new Position(piece.getPosition()));
    	return newPiece;
    }
    
    public static PieceType getPieceType (Character c) {
    	PieceType type = null;
    	switch (c) {
	        case 'r' : type = PieceType.ROOK; break;
	        case 'R' : type = PieceType.ROOK; break;
	        case 'n' : type = PieceType.KNIGHT; break;
	        case 'N' : type = PieceType.KNIGHT; break;
	        case 'b' : type = PieceType.BISHOP; break;
	        case 'B' : type = PieceType.BISHOP; break;
	        case 'k' : type = PieceType.KING; break;
	        case 'K' : type = PieceType.KING; break;
	        case 'q' : type = PieceType.QUEEN; break;
	        case 'Q' : type = PieceType.QUEEN; break;
	        case 'p' : type = PieceType.PAWN; break;
	        case 'P' : type = PieceType.PAWN; break;
	        case '-' : type = null; break;
    	}
    	return type;
    }
}

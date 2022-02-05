package utilities.Handlers;

import java.awt.Color;

import config.Settings;
import models.board.Square;
import models.pieces.Piece;
import utilities.PieceFactory;
import utilities.constants.Constants;

public class TemplateHandler {
	
	public static String retrieveImgPath(Piece piece) {
		if(piece == null) return null;
		String coloreness = Constants.map.get(piece.getColoreness());
		String type = piece.getType().toString();
		String pieceToStr = coloreness + "_" + type;
		String location = "src/resources/images/pieces/";
		String fileExtension = ".png";
		String imgPath = location + pieceToStr + fileExtension;
		return imgPath;
	}
	
	public static String retrieveImgPath(Character c) {
		String coloreness = Character.isLowerCase(c) ? "DARK" : "LIGHT";
		String type = PieceFactory.getPieceType(c).toString();
		String pieceToStr = coloreness + "_" + type;
		String location = "src/resources/images/pieces/";
		String fileExtension = ".png";
		String imgPath = location + pieceToStr + fileExtension;
		return imgPath;
	}

	public static Color retrieveColorTextButton(Square square) {
		if(square.getColoreness() == Constants.LIGHT) return Settings.darkSquare;
		else return Settings.lightSquare;
	}
	
	public static Color blend(Color c0, Color c1) {
	    double totalAlpha = c0.getAlpha() + c1.getAlpha();
	    double weight0 = c0.getAlpha() / totalAlpha;
	    double weight1 = c1.getAlpha() / totalAlpha;

	    double r = weight0 * c0.getRed() + weight1 * c1.getRed();
	    double g = weight0 * c0.getGreen() + weight1 * c1.getGreen();
	    double b = weight0 * c0.getBlue() + weight1 * c1.getBlue();
	    double a = Math.max(c0.getAlpha(), c1.getAlpha());

	    return new Color((int) r, (int) g, (int) b, (int) a);
	}
}

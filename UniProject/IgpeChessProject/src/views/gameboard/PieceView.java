package views.gameboard;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import config.Settings;
import models.pieces.Piece;
import utilities.Handlers.ImageHandler;
import utilities.Handlers.TemplateHandler;

public class PieceView extends JLabel{

	public Piece piece;
	public String path;
	
	public PieceView(Piece piece){
		super();
		this.piece = piece;
		initPieceView();
	}
	
	private void initPieceView() {
		this.setPreferredSize(new Dimension(Settings.screenHeight/8, Settings.screenHeight/8));
	    this.setHorizontalAlignment(JLabel.CENTER);
	    this.setVerticalAlignment(JLabel.CENTER);
		this.updateView();
	}
	
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	
	public void updateView(){
		if(piece != null){
			path = TemplateHandler.retrieveImgPath(this.piece);
			ImageIcon pieceIcon = ImageHandler.getScaledIcon(path, Settings.screenHeight/16, Settings.screenHeight/16);
			this.setIcon(pieceIcon);
		}
		else this.setIcon(null);
	}
}
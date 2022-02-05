package views.dialogs;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import config.Settings;
import models.pieces.Piece;
import utilities.Handlers.ImageHandler;
import utilities.constants.Path;

public class UpdatePawnDialog extends JDialog{
	
	private int iconSize;
	public JButton rookChoose;
	public JButton queenChoose;
	public JButton bishopChoose;
	public JButton knightChoose;
	private JPanel piecesRow;
	public int team;
	public Piece pawn;
	
	public UpdatePawnDialog(Piece pawn) {
		super();
		this.pawn = pawn;
		this.setModal(true);
		this.setUndecorated(true);
		this.team = pawn.getColoreness();
		int w = (Settings.screenWidth-Settings.screenHeight)/2;
		this.setPreferredSize(new Dimension(w, w/2));
		this.iconSize = w/5;
		initButton();
		initPiecesRow();
	}
	
	private void initButton() {
		
		rookChoose = new JButton();
		queenChoose = new JButton();
		bishopChoose = new JButton();
		knightChoose = new JButton();
		
		rookChoose.setBorderPainted(false);
		rookChoose.setContentAreaFilled(false);

		queenChoose.setBorderPainted(false);
		queenChoose.setContentAreaFilled(false);
		
		bishopChoose.setBorderPainted(false);
		bishopChoose.setContentAreaFilled(false);
		
		knightChoose.setBorderPainted(false);
		knightChoose.setContentAreaFilled(false);
		
		rookChoose.setIcon(ImageHandler.getScaledIcon(team == 1 ? Path.LIGHT_ROOK_IMG : Path.DARK_ROOK_IMG, iconSize, iconSize));
		queenChoose.setIcon(ImageHandler.getScaledIcon(team == 1 ? Path.LIGHT_QUEEN_IMG : Path.DARK_QUEEN_IMG, iconSize, iconSize));
		bishopChoose.setIcon(ImageHandler.getScaledIcon(team == 1 ? Path.LIGHT_BISHOP_IMG : Path.DARK_BISHOP_IMG, iconSize, iconSize));
		knightChoose.setIcon(ImageHandler.getScaledIcon(team == 1 ? Path.LIGHT_KNIGHT_IMG : Path.DARK_KNIGHT_IMG, iconSize, iconSize));
	}
	
	private void initPiecesRow() {
		piecesRow = new JPanel(new GridLayout(1,4));
		piecesRow.add(rookChoose);
		piecesRow.add(queenChoose);
		piecesRow.add(bishopChoose);
		piecesRow.add(knightChoose);
		this.add(piecesRow);
	}
}

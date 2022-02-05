package views.pages;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import audio.MediaPlayerView;
import models.board.GameBoard;
import models.board.Mover;
import utilities.Handlers.FileHandler;
import utilities.constants.Constants;
import utilities.constants.Path;
import views.gameboard.GameBoardView;
import views.gamepage.TopPanel;
import views.gamepage.left.LeftPanel;
import views.gamepage.right.RightPanel;
import views.shared.myBarMenu;

public class GamePage extends JPanel{

	public myBarMenu myMenu;
	public MediaPlayerView audioPanel;
	public TopPanel top;
	public LeftPanel left;
	public RightPanel right;

	GameBoard boardModel = new GameBoard();
	public GameBoardView center;
	
	public GamePage() {
		super(new BorderLayout());
		
		this.initBoard();
		this.initComposition();
	}
	
	public void initBoard() {
		boardModel = new GameBoard();
		boardModel.initTable(Path.CHESS_BOARD_FILE);
		center = new GameBoardView(boardModel);
	}
	
	private void initComposition() {
		this.left = new LeftPanel();
		this.top = new TopPanel();
		this.right = new RightPanel();		
		this.audioPanel = new MediaPlayerView();

		this.add(top, BorderLayout.NORTH);
		this.add(left, BorderLayout.WEST);
		this.add(center, BorderLayout.CENTER);
		this.add(right, BorderLayout.EAST);
		this.add(audioPanel, BorderLayout.SOUTH);	
	}
	
	public void restoreBoard(int mode) {
		this.remove(center);
		boardModel = new GameBoard();
		boardModel.initTable(Path.CHESS_BOARD_FILE);
		boardModel.getSetting().setMode(mode);
		center = new GameBoardView(boardModel);
		this.add(center);
	}
	public void loadBoard(ArrayList<String> logFile) {
		this.remove(center);
		boardModel = new GameBoard();
		boardModel.initTable(Path.CHESS_BOARD_FILE);
			
		String modeS = logFile.get(2).split(":")[1];
		int mode = modeS.equals("PC") ? Constants.SINGLEPLAYER : Constants.MULTIPLAYER;
		boardModel.getSetting().setMode(mode);
		
		for (int i = 4; i < logFile.size(); i++) {
			Mover.moveFromLine(boardModel, logFile.get(i));
		}
		center = new GameBoardView(boardModel);
		this.add(center);
	}
}

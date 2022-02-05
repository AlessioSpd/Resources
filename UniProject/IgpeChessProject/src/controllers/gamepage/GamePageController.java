package controllers.gamepage;

import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import audio.MediaPlayerController;
import audio.SFXPlayer;
import config.Settings;
import controllers.dialogs.EndGameDialogController;
import controllers.dialogs.UpdatePawnDialogController;
import controllers.gameboard.GameBoardController;
import controllers.shared.myBarMenuController;
import models.board.GameBoard;
import models.pieces.Piece;
import models.pieces.Position;
import utilities.PieceFactory;
import utilities.Handlers.FileHandler;
import utilities.Handlers.ImageHandler;
import utilities.constants.Constants;
import utilities.constants.Enums.PieceType;
import utilities.constants.EventsUI;
import utilities.constants.Path;
import views.dialogs.EndGameDialog;
import views.dialogs.UpdatePawnDialog;
import views.gameboard.GameBoardView;
import views.gameboard.SquareView;
import views.gamepage.right.myLogger;
import views.pages.GamePage;

public class GamePageController implements PropertyChangeListener {

	private GamePage view;
	private PropertyChangeSupport trigger;

	public LeftPanelController leftPanelController;
	public RightPanelController rightPanelController;
	public TopPanelController topPanelController;
	public GameBoardController gameBoardController;
	public MediaPlayerController audioPanelController;
	public EndGameDialogController endGameDialogController;
	EndGameDialog endGame = null;
	EndGameDialogController endGameController = null;

	public GamePageController(GamePage view) {
		this.view = view;
		this.trigger = new PropertyChangeSupport(this);
		this.initControllers();
	}

	private void initControllers() {
		this.leftPanelController = new LeftPanelController(this.view.left);
		leftPanelController.register(this);
		this.rightPanelController = new RightPanelController(this.view.right);
		rightPanelController.register(this);
		this.topPanelController = new TopPanelController(this.view.top);
		topPanelController.register(this);
		this.audioPanelController = new MediaPlayerController(this.view.audioPanel, this.view.audioPanel.model);
		this.gameBoardController = new GameBoardController(this.view.center.model, this.view.center);
		gameBoardController.register(this);

	}
	
	public void register(PropertyChangeListener observer) { 
		 this.trigger.addPropertyChangeListener( observer ); 
	} 

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(EventsUI.UNDO)) {

			boolean undoMade = this.gameBoardController.undo();
			
			if (undoMade) {

				SFXPlayer.play(Path.TURN_SFX);
				this.switchTimers();
				if(this.gameBoardController.view.model.getSetting().getMode() == Constants.SINGLEPLAYER) {
					this.view.right.center.logger.removeLast();
					this.leftPanelController.view.setTurno();
				}
				
				this.view.right.center.logger.removeLast();
				this.leftPanelController.view.setTurno();
				
				this.leftPanelController.updateCountLabels(this.gameBoardController.model);
				
			} else if (!undoMade) {
				SFXPlayer.play(Path.POPUP_SFX);
				if(!Settings.undo)JOptionPane.showMessageDialog(this.view, "Undo is disabled from settings!");
				else JOptionPane.showMessageDialog(this.view, "You cannot do UNDO right now!");
				
			}
			this.gameBoardController.view.updateView(null);
		} else if (evt.getPropertyName().equals(EventsUI.MOVED)) {
			this.addTime();
			this.switchTimers();
			Position chosen = (Position) evt.getNewValue();
			this.addMoveToLogger(chosen);
			this.gameBoardController.model.disposizione1();
			this.leftPanelController.updateCountLabels(this.gameBoardController.model);
			this.leftPanelController.view.setTurno();
		} else if (evt.getPropertyName().equals(EventsUI.TIME_LOSE)) {
			int teamLoser = Constants.DARK;
			if (this.leftPanelController.view.light.seconds <= 0)
				teamLoser = Constants.LIGHT;
			this.setActiveTimers(false);
			this.disableStuffOnFinish();
			FileHandler.closeFile(this.gameBoardController.model);
			this.openEndGameDialog(teamLoser);

		} else if (evt.getPropertyName().equals(EventsUI.UPDATE_GRAPHIC_SETTINGS)) {
			this.updateGraphic();
		} else if (evt.getPropertyName().equals(EventsUI.NEWGAME)) {

			this.leftPanelController.view.resetCount();
			
			Object mode = evt.getNewValue();
			if(mode == null) //newgame da endgamecontroller
				mode = this.gameBoardController.model.getSetting().getMode();
			else {
				boolean proceed = this.askConfirmationIfNecessary();
				if(!proceed) return;
			}
			this.view.restoreBoard((int)mode);
			this.reInitGBController();

			this.leftPanelController.view.light.setTurnLabel("IT'S YOUR TURN");
			this.leftPanelController.view.dark.setTurnLabel("");
			
			this.closeAndResetEndGameDialog();
			this.sharedReset();
			this.gameBoardController.view.updateView(null);
			SFXPlayer.play(Path.SWIPE_SFX);
			this.leftPanelController.resetCountLabels();
			
		} else if (evt.getPropertyName().equals(EventsUI.CHECKMATE)) {
			int loser = (int) evt.getNewValue();
			this.setActiveTimers(false);
			this.disableStuffOnFinish();
			this.openEndGameDialog(loser);
		} else if (evt.getPropertyName().equals(EventsUI.SAVE)) {

			String directory = (String) evt.getNewValue();
			String nameOfFile = JOptionPane.showInputDialog("Name of file:");
			
			ArrayList<String> logFile = this.gameBoardController.model.getSetting().getLogFile();
			FileHandler.saveFile(logFile, directory + "/" + nameOfFile);
			

		} else if (evt.getPropertyName().equals(EventsUI.PLAYPAUSEGAME)) {
			if (this.gameBoardController.model.getSetting().getGameRunning()) {
				this.gameBoardController.updateGameRunning(false);
				this.setActiveTimers(false);
				this.leftPanelController.view.center.startStop.setIcon(this.leftPanelController.view.center.startIcon);
			} else {
				if (this.gameBoardController.model.getSetting().getTurno() == Constants.LIGHT)
					this.leftPanelController.view.light.getTimer().start();
				else
					this.leftPanelController.view.dark.getTimer().start();
				this.gameBoardController.updateGameRunning(true);
				this.leftPanelController.view.center.startStop.setIcon(this.leftPanelController.view.center.stopIcon);
			}

		} else if (evt.getPropertyName().equals(EventsUI.LOAD)) {
			
			this.leftPanelController.view.resetCount();
			
			boolean proceed = this.askConfirmationIfNecessary();
			if(!proceed) return;
			ArrayList<String> logFile = myBarMenuController.loadDialog(this.view);
			if(logFile == null) return;
			
			FileHandler.parseAndUpdateSettings(this.gameBoardController.model, logFile);
			this.view.loadBoard(logFile);

			if (this.leftPanelController.view.light.turnValue.getText().equals("")) {
				String lastLine = logFile.get(logFile.size() - 1);
				if (!Character.isLowerCase(lastLine.charAt(3)))
					this.leftPanelController.view.setTurno();
			}
			this.sharedReset();
			this.reInitGBController();
			this.fillLogger(logFile);
			this.leftPanelController.updateCountLabels(this.gameBoardController.model);
			this.gameBoardController.view.updateView(null);
			

		} else if (evt.getPropertyName().equals(EventsUI.REPLAY)) {
			this.leftPanelController.view.resetCount();
			
			String ev = (String) evt.getNewValue();
			ArrayList<String> logFile = null;
			if(ev == null) {//replay from top
				boolean proceed = this.askConfirmationIfNecessary();
				if(!proceed) return;
				logFile = myBarMenuController.replayDialog(this.view);
				if(logFile == null) return;
			}
			else {//events replay from dialog
				logFile = this.gameBoardController.model.getSetting().getLogFile();
			}
			
			this.view.restoreBoard(Constants.REPLAY);
			this.reInitGBController();
			this.gameBoardController.model.getSetting().setLogFile(logFile);
			this.sharedReset();
			this.gameBoardController.view.updateView(null);
			this.leftPanelController.view.dark.setTurnLabel("");
			this.leftPanelController.view.light.setTurnLabel("");
			this.fillLogger(logFile);
			if(ev.equals(EventsUI.REPLAYFROMDIALOG)) this.closeAndResetEndGameDialog();
			
		}else if (evt.getPropertyName().equals(EventsUI.PREV)) {
			ArrayList<String> log = this.gameBoardController.model.getSetting().getLogFile();
			String move = FileHandler.stepper(this.gameBoardController, EventsUI.PREV, log);
			SFXPlayer.play(Path.MOVE_SFX);
			this.gameBoardController.view.updateView(null);
			
		}else if (evt.getPropertyName().equals(EventsUI.NEXT)) {
			ArrayList<String> log = this.gameBoardController.model.getSetting().getLogFile();
			String move = FileHandler.stepper(this.gameBoardController, EventsUI.NEXT, log);
			SFXPlayer.play(Path.MOVE_SFX);
			this.gameBoardController.view.updateView(null);
			
		}else if(evt.getPropertyName().equals(EventsUI.SURRENDER)) {
			int loser = this.gameBoardController.model.getSetting().getTurno();
			this.setActiveTimers(false);
			this.disableStuffOnFinish();
			FileHandler.closeFile(this.gameBoardController.model);
			this.openEndGameDialog(loser);
			
		}else if(evt.getPropertyName().equals(EventsUI.DRAW)) {
			this.setActiveTimers(false);
			this.disableStuffOnFinish();
			FileHandler.closeFile(this.gameBoardController.model);
			this.openEndGameDialog(0);
		}
	}
	private void fillLogger(ArrayList<String> logFile) {
		myLogger logger = this.rightPanelController.view.center.logger;
		for (int i = 4; i < logFile.size()-1; i++) {
			String move = logFile.get(i);
			ImageIcon pieceImg = ImageHandler.getPieceImg(move.charAt(3), Settings.screenHeight / 40, Settings.screenHeight / 40);
			logger.addMove(pieceImg, move);
		}
	}
	
	private void addTime() {
		if(this.gameBoardController.model.getSetting().getMode() == Constants.MULTIPLAYER || this.gameBoardController.model.getSetting().getTurno() == Constants.DARK)
			this.leftPanelController.addPlaySeconds(this.gameBoardController.model.getSetting().getTurno());
	}
	
	private void addMoveToLogger(Position chosen) {

		int x = chosen.getX();
		int y = chosen.getY();
		Icon icon = ImageHandler.getScaledIcon(this.gameBoardController.view.tiles[x][y].getPieceView().path, Settings.screenHeight / 40, Settings.screenHeight / 40);
		ArrayList<String> log = this.gameBoardController.model.getSetting().getLogFile();
		System.out.println(log);
		String move = log.get(log.size() - 1);
		this.view.right.center.logger.addMove(icon, move);

		GameBoard model = this.view.center.model;
	}
	
	private void switchTimers() {
		Timer lightT = this.leftPanelController.view.light.getTimer();
		Timer darkT = this.leftPanelController.view.dark.getTimer();
		
		if (lightT.isRunning()) {
			lightT.stop();
			darkT.start();
		} else {
			darkT.stop();
			lightT.start();
		}
	}
	
	
	private boolean askConfirmationIfNecessary() {
		boolean finished = this.gameBoardController.model.getSetting().getFinished();
		int mode = this.gameBoardController.model.getSetting().getMode();
		if(mode == Constants.REPLAY) return true;
		if(!finished) {
			SFXPlayer.play(Path.POPUP_SFX);
			int confirm = JOptionPane.showConfirmDialog (null, 
					"Are you sure to start a new game?",
					"Warning", 
					JOptionPane.YES_NO_OPTION);
	        return confirm == JOptionPane.YES_OPTION;
		}
		return true;
	}

	private void reInitGBController() {
		this.gameBoardController.unRegister(this);
		this.gameBoardController = new GameBoardController(this.view.center.model, this.view.center);
		this.gameBoardController.register(this);
	}

	private void setActiveTimers(boolean mode) {

		if (mode) {
			this.leftPanelController.view.light.getTimer().start();
			this.leftPanelController.view.dark.getTimer().start();
		} else {
			this.leftPanelController.view.light.getTimer().stop();
			this.leftPanelController.view.dark.getTimer().stop();
		}
	}

	private void openEndGameDialog(int loser) {
		SFXPlayer.play(Path.FINISH_SFX);
		endGame = new EndGameDialog(loser);
		endGameController = new EndGameDialogController(endGame);
		endGameController.register(this);
		endGame.pack();
		endGame.setLocationRelativeTo(null);
		endGame.setVisible(true);
	}
	
	private void closeAndResetEndGameDialog() {
		System.out.println("there");
		if(endGame != null) {

			System.out.println("here =(");
			endGame.dispose();
		
		}
		endGame = null;
		endGameController = null;
	}

	private void disableStuffOnFinish() {
		this.gameBoardController.model.getSetting().setFinished();
		this.gameBoardController.model.getSetting().setGameRunning(false);
		this.leftPanelController.view.center.startStop.setEnabled(false);
		this.rightPanelController.view.top.undo.setEnabled(false);
		this.rightPanelController.view.top.prev.setEnabled(false);
		this.rightPanelController.view.top.next.setEnabled(false);
	}

	private void sharedReset() {
		System.out.println("entro in sharereset");
		System.out.println("mode = " + this.gameBoardController.model.getSetting().getMode() );
		this.rightPanelController.view.center.logger.clearList();
		this.setActiveTimers(false);

		this.leftPanelController.view.light.seconds = Settings.playerTime * 60;
		this.leftPanelController.view.dark.seconds = Settings.playerTime * 60;

		this.leftPanelController.view.light.setTimeValue(this.leftPanelController.view.light.secondsToTime(Settings.playerTime * 60));
		this.leftPanelController.view.dark.setTimeValue(this.leftPanelController.view.dark.secondsToTime(Settings.playerTime * 60));
		this.leftPanelController.view.center.startStop.setIcon(this.leftPanelController.view.center.startIcon);
		
		int mode = this.gameBoardController.model.getSetting().getMode();
		if(mode == Constants.REPLAY) {
			this.leftPanelController.view.center.startStop.setEnabled(false);
			this.rightPanelController.view.top.setButtonsStatus(true);
			this.rightPanelController.view.top.undo.setEnabled(false);
		}else {
			this.leftPanelController.view.center.startStop.setEnabled(true);
			this.rightPanelController.view.top.setButtonsStatus(false);
			this.rightPanelController.view.top.undo.setEnabled(true);
		}
	}

	public void updateGraphic() {
		SquareView tiles[][] = this.gameBoardController.view.tiles;
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				tiles[i][j].updateView();

		this.leftPanelController.view.light.setName(Settings.namePlayer1);
		this.leftPanelController.view.dark.setName(Settings.namePlayer2);
		this.leftPanelController.view.loadNewSettings();
		this.leftPanelController.view.center.changeColor();
		this.rightPanelController.view.center.logger.updateColor();
		this.rightPanelController.view.top.updateColor();
		
		int mode = this.gameBoardController.model.getSetting().getMode();
		if(mode == Constants.REPLAY) {
			this.leftPanelController.view.center.startStop.setEnabled(false);
			this.rightPanelController.view.top.setButtonsStatus(true);
		}else {
			this.leftPanelController.view.center.startStop.setEnabled(true);
			this.rightPanelController.view.top.setButtonsStatus(false);
		}
	}

}

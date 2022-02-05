package models.board;

import java.util.ArrayList;

import config.Settings;
import models.pieces.Piece;
import models.pieces.Position;
import utilities.PieceFactory;
import utilities.Handlers.FileHandler;
import utilities.constants.Constants;
import utilities.constants.Enums.*;

public class GameSetting {
	
	private int undo;
	private int arrocco;
	private int round;
	private int turno;
	private String logFileName;
	private ArrayList<String> logFile;
	private int countUndo;
	private int mode;
	private boolean gameRunning;
	public boolean gameFinished;
	
	public GameSetting() {
		this.undo = Settings.undo ? Constants.BOTH : Constants.NONE;
		this.arrocco = Constants.BOTH;
		this.countUndo = 0;
		this.round = 0;
		this.gameRunning = false;
		this.gameFinished = false;
		this.turno = Constants.LIGHT;
		this.logFile = new ArrayList<>();
	}
	
	public void updateDisponibilityOf(int what) {
		if(what == Constants.UNDO) {
			if(undo == Constants.BOTH) undo = turno*-1;
			else undo = Constants.NONE;
		}else if(what == Constants.ARROCCO) {
			if(arrocco == Constants.BOTH) arrocco = turno*-1;
			else arrocco = Constants.NONE;
		}
	}

	public void setGameRunning(boolean mode) {this.gameRunning = mode;}
	public boolean getGameRunning() {return this.gameRunning;}
	public void setMode(int mode) {this.mode = mode;}
	public int getMode() {return this.mode;}
	public void setFinished() {this.gameFinished = true;}
	public boolean getFinished() {return this.gameFinished;}
	public int getUndo() {return undo;}
	public int getArrocco() {return arrocco;}
	public void setUndo(int undo) {this.undo = undo;}
	public void setArrocco(int arrocco) {this.arrocco = arrocco;}
	public int getRound() {return round;}
	public void setRound(int round) {this.round = round;}
	public int getTurno() {return turno;}
	public void changeTurno() { this.turno *= -1; }
	public String getLogFileName() {return logFileName;}
	public ArrayList<String> getLogFile() {return this.logFile;}
	public void setLogFileName(String logFileName) {this.logFileName = logFileName;}
	public int getCountUndo() {return countUndo;}
	public void setCountUndo(int countUndo) {this.countUndo = countUndo;}
	public void setLogFile(ArrayList<String> logFile) {
		this.logFile = new ArrayList<>(logFile);
	}
}











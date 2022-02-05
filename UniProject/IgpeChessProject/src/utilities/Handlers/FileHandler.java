package utilities.Handlers;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import javax.swing.JFileChooser;

import config.Settings;
import controllers.gameboard.GameBoardController;
import models.board.GameBoard;
import models.board.Mover;
import models.board.PieceManager;
import models.pieces.Piece;
import models.pieces.Position;
import utilities.PieceFactory;
import utilities.constants.Constants;
import utilities.constants.Enums.*;
import utilities.constants.EventsUI;
import utilities.constants.Path;

public class FileHandler {

	public static boolean wasItArrocco(GameBoard board) {
		int round = board.getSetting().getRound();
		if(round == 0) return false;
		ArrayList<String> log = board.getSetting().getLogFile();
		String lastLine = log.get(log.size()-1);
		String lastLastLine = log.get(log.size()-2);
		boolean isValid = !lastLastLine.equals("START");//da togliere.. non potrà capitare in partita
		char pMovedLL = lastLine.charAt(3);
		char pMovedLLL = lastLastLine.charAt(3);
		boolean wasArrocco = ((Character.isUpperCase(pMovedLL) && Character.isUpperCase(pMovedLLL))
							|| (Character.isLowerCase(pMovedLL) && Character.isLowerCase(pMovedLLL)))
							&& isValid;
		return wasArrocco;
	}
	
	public static ArrayList<String> tracksList(){

        String[] pathnames;
        File f = new File(Path.TRACKS_FOLDER);
 
        pathnames = f.list();

        ArrayList<String> temp = new ArrayList<String>();

        for(var path : pathnames)
            temp.add(path);

        return temp;
    }
	
	public static String stepper(GameBoardController boardController, String mode, ArrayList<String> log) {
		
		GameBoard board = boardController.model;
		String move;
		int nLine;
		
		System.out.println(" --- " + log);
		
		if(mode.equals(EventsUI.NEXT)) {
			nLine = board.getSetting().getRound()+4;
			if(nLine == log.size()) return null;
			move = log.get(nLine);
			if(move.equals("END")) return null;
		}
		else if(mode.equals(EventsUI.PREV)){
			nLine = board.getSetting().getRound()+3;
			if(nLine <= 3) return null;
			move = log.get(nLine);
		}else return null;
		
		Position startPos, endPos;
		char eaten;
		
		System.out.println("move = " + move);
		startPos = Position.reverseTranslate(move.substring(0, 2));
		endPos = Position.reverseTranslate(move.substring(8, 10));
		eaten = move.charAt(11);
		
		System.out.println("startPos = " + startPos);
		
		Piece piece;
		Position target;
		if(mode.equals("NEXT")) {
			piece = board.getPieceAt(startPos);
			target = endPos;
			Mover.move(board, piece, target);
			

			if(move.charAt(move.length()-1) == ':') {
				Piece newPiece = PieceFactory.getNewIstanceOfPiece(move.charAt(move.length()-2));
				newPiece.setPosition(target);
				boardController.model.setPieceAt(target, newPiece);
			}
			
			
			return move;
		}
		else if(mode.equals("PREV")){
			Piece eatenPiece;
			
			if(eaten == '-') {
				eatenPiece = null;
			}else {
				PieceManager pieceManager = board.getPieceManger();
				ArrayList<Piece> eatenPieces = pieceManager.getEatenPiecesOfColoreness(board.getSetting().getTurno());
				eatenPiece = eatenPieces.get(eatenPieces.size()-1);
				pieceManager.reviveEatenPiece(eatenPiece);
			}
			
			piece = board.getPieceAt(endPos);
			target = startPos;
			Mover.move(board, piece, target);
			
			if(move.charAt(move.length()-1) == ':') {
				board.setPieceAt(startPos, PieceFactory.getNewIstanceOfPiece(board.getSetting().getTurno()*-1 == -1 ? 'P' : 'p'));
				board.getPieceAt(startPos).setPosition(startPos);
			}
			
			board.disposizione1();
			board.setPieceAt(endPos, eatenPiece);
			board.modifyRound(-2);
			return move;
		}
		else return null;
	}
	
	public static void copyLog(String from, String to) {
		File fromFile = new File(from);
		File toFile = new File(to);
		
		try {
			Files.copy(fromFile.toPath(), toFile.toPath());
		} catch (IOException e) {
			System.out.println("Errore");
		}
	}
	
	public static void writeNewGraphicSetting() {
		ArrayList<String> vec = new ArrayList<>();
		Color light = Settings.lightSquare;
		Color dark = Settings.darkSquare;
		Color selected = Settings.selectedSquare;
		vec.add("undo=" + Settings.undo);
		vec.add("playerTime=" + Settings.playerTime);
		vec.add("namePlayer1=" + Settings.namePlayer1);
		vec.add("namePlayer2=" + Settings.namePlayer2);
		vec.add("lightSquare=" + light.getRed() + "," + light.getGreen() + "," + light.getBlue());
		vec.add("darkSquare=" + dark.getRed() + "," + dark.getGreen() + "," + dark.getBlue());
		vec.add("selectedSquare=" + selected.getRed() + "," + selected.getGreen() + "," + selected.getBlue());
		vec.add("sfxVolume=" + Settings.sfxVolume);
		FileHandler.saveSettings(vec, Path.SETTINGS_FILE);
	}
	
	public static ArrayList<String> getLinesOfFile(String fileName) {
		ArrayList<String> lines = new ArrayList<String>();
		BufferedReader buffer;
		try {
			buffer = new BufferedReader(new FileReader(fileName));
			while (buffer.ready())
				lines.add(buffer.readLine());
			buffer.close();
		} catch (IOException e) {
			System.out.println("Errore nella gestione del file");
		}
		return lines;
	}
	
	public static void closeFile(GameBoard table) {
		
		ArrayList<String> logFile = table.getSetting().getLogFile();
		logFile.add("END");
	}

	public static char toChar(Piece piece) {

		if (piece == null)
			return '-';

		String type = piece.getType().toString();

		if (type.equals("KNIGHT"))
			type = "NIGHT";

		if (piece.getColoreness() == Constants.DARK)
			type = type.toLowerCase();

		return type.charAt(0);
	}
	
	public static void saveMove(GameBoard table, Position start, Position target) {
		
		ArrayList<String> logFile = table.getSetting().getLogFile();

		Piece startPiece = table.getPieceAt(start);
		Piece targetPiece = table.getPieceAt(target);
		String toWrite = start.translate() + ":" + toChar(startPiece) + " -> " + target.translate() + ":" + toChar(targetPiece);		
		
		if (logFile.size() == 0) {
			String undoType = Constants.map.get(table.getSetting().getUndo());
			String undo = "UNDO:"+undoType;
			String arrocco = "ARROCCO:BOTH";
			String vs = "";
			vs = table.getSetting().getMode() == Constants.SINGLEPLAYER ? "VS:PC" : "VS:PLAYER";
			String beginning = "START";
			logFile.add(undo);
			logFile.add(arrocco);
			logFile.add(vs);
			logFile.add(beginning);
		}
		
		logFile.add(toWrite);
	}
	
	public static void writeNewSetting(GameBoard table) {
		
		ArrayList<String> logFile = table.getSetting().getLogFile();
		
		String undoType = Constants.map.get(table.getSetting().getUndo());
		String arroccoType = Constants.map.get(table.getSetting().getArrocco());
		String undo = "UNDO:" + undoType;
		String arrocco = "ARROCCO:" + arroccoType;
		
		logFile.set(0, undo);
		logFile.set(1, arrocco);
	}
	
	public static void parseAndUpdateSettings(GameBoard table, ArrayList<String> lines) {		
		String undo = lines.get(0).split(":")[1];
		String arrocco = lines.get(1).split(":")[1];
		int dUndo = FileHandler.settingsConverter(undo);
		int dArrocco = FileHandler.settingsConverter(arrocco);
		String vs = lines.get(2).split(":")[1];
		int mode = vs.equals("PC") ? Constants.SINGLEPLAYER : Constants.MULTIPLAYER;
		table.getSetting().setUndo(dUndo);
		table.getSetting().setMode(mode);
		table.getSetting().setArrocco(dArrocco);
	}
	
	public static void parseAndUpdateGraphicSettings() {
		ArrayList<String> lines = new ArrayList<String>();
		BufferedReader buffer;
		try {
			buffer = new BufferedReader(new FileReader(Path.SETTINGS_FILE));
			while (buffer.ready())
				lines.add(buffer.readLine());
			buffer.close();
		} catch (IOException e) {
			System.out.println("Errore nella gestione del file");
		}
		Settings.undo = Boolean.parseBoolean(lines.get(0).split("=")[1]);
		Settings.playerTime = Integer.parseInt(lines.get(1).split("=")[1]);
		Settings.namePlayer1 = lines.get(2).split("=")[1];
		Settings.namePlayer2 = lines.get(3).split("=")[1];
		
		int r,g,b;
		String rgbL = lines.get(4).split("=")[1];
		String rgbD = lines.get(5).split("=")[1];
		String rgbS = lines.get(6).split("=")[1];
		
		String[] rgvLSplitted = rgbL.split(",");
		r = Integer.parseInt(rgvLSplitted[0]);
		g = Integer.parseInt(rgvLSplitted[1]);
		b = Integer.parseInt(rgvLSplitted[2]);
		Settings.lightSquare = new Color(r,g,b);
		
		String[] rgvDSplitted = rgbD.split(",");
		r = Integer.parseInt(rgvDSplitted[0]);
		g = Integer.parseInt(rgvDSplitted[1]);
		b = Integer.parseInt(rgvDSplitted[2]);
		Settings.darkSquare = new Color(r,g,b);
		
		String[] rgvSSplitted = rgbS.split(",");
		r = Integer.parseInt(rgvSSplitted[0]);
		g = Integer.parseInt(rgvSSplitted[1]);
		b = Integer.parseInt(rgvSSplitted[2]);
		Settings.selectedSquare = new Color(r,g,b);
		
		Settings.sfxVolume = Integer.parseInt(lines.get(7).split("=")[1]);
		
	}
	
	public static int settingsConverter(String setting) {
		Integer value = null;
		if(setting.equals("NONE")) 		 value = Constants.NONE;
		else if(setting.equals("BOTH"))  value = Constants.BOTH;
		else if(setting.equals("LIGHT")) value = Constants.LIGHT;
		else if(setting.equals("DARK"))  value = Constants.DARK;
		return value;
	}

	public static void saveFile(ArrayList<String> vec, String fileName) {
		
		if(!fileName.subSequence(fileName.length()-4, fileName.length()).equals(".txt"))
			fileName = fileName + ".txt";
		
		BufferedWriter bw;
		
		try {
			bw = new BufferedWriter(new FileWriter(fileName, false));
			for (int i = 0; i < vec.size(); i++) {
				String toWrite = vec.get(i);
				if(i!=0) toWrite = System.lineSeparator()+toWrite;
				bw.write(toWrite);
			}
			bw.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public static void saveSettings(ArrayList<String> vec, String path)
	{
		System.out.println(vec);
		
		BufferedWriter bw;
		
		try {
			bw = new BufferedWriter(new FileWriter(path, false));
			for (int i = 0; i < vec.size(); i++) {
				String toWrite = vec.get(i);
				if(i!=0) toWrite = System.lineSeparator()+toWrite;
				bw.write(toWrite);
			}
			bw.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	public static String choosePath(int toSee) {
		JFileChooser f = new JFileChooser();
	    f.setFileSelectionMode(toSee);
	    f.showSaveDialog(null);
	    
	    if(f.getSelectedFile() == null)
	    	return null;
	    else return f.getSelectedFile().toString();
	}
	
	public static void deleteNLine(GameBoard table, int nLine) {
		ArrayList<String> logFile = table.getSetting().getLogFile();
		for(int i = 0; i < nLine; ++i) {
			logFile.remove(logFile.size()-1);
		}
	}
	
	
}
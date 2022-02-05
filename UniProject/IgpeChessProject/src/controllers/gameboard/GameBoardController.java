package controllers.gameboard;

import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import audio.SFXPlayer;
import controllers.dialogs.UpdatePawnDialogController;
import models.board.GameBoard;
import models.board.Mover;
import models.board.Square;
import models.pieces.Piece;
import models.pieces.Position;
import utilities.PieceFactory;
import utilities.Handlers.FileHandler;
import utilities.constants.Constants;
import utilities.constants.EventsUI;
import utilities.constants.Path;
import utilities.constants.Enums.CheckType;
import utilities.constants.Enums.PieceType;
import views.dialogs.UpdatePawnDialog;
import views.gameboard.GameBoardView;
import views.gameboard.SquareView;

public class GameBoardController implements PropertyChangeListener{
	
	public GameBoard model;
	public GameBoardView view;
	private PropertyChangeSupport trigger;
	SquareController[][] tileController;
	UpdatePawnDialog updatePawnDialog = null;
	UpdatePawnDialogController updatePawnDialogController;
	
	public GameBoardController(GameBoard model, GameBoardView view) {
		this.model = model;
		this.view = view;
		this.trigger = new PropertyChangeSupport(this);
		this.tileController = new SquareController[8][8];
		this.initListeners();
	}
	
	public void register(PropertyChangeListener observer) { 
		 this.trigger.addPropertyChangeListener( observer ); 
	} 
	
	public void unRegister(PropertyChangeListener observer) { 
		 this.trigger.removePropertyChangeListener(observer);
	} 
	
	public void setSelected(ArrayList<Position> toSelect, boolean value) {
		for(Position pos : toSelect) {
			int x = pos.getX();
			int y = pos.getY();
			this.view.getTiles()[x][y].setSelected(value);
		}
	}
	
	public void updateGameRunning(boolean mode) {
		this.model.getSetting().setGameRunning(mode);
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
		if(evt.getPropertyName().equals(EventsUI.SELECT)) {
			
			if(this.model.getSetting().getGameRunning() == false) return;

			ArrayList<Position> legals = null;
			Position choosen = (Position) evt.getNewValue();
			legals = this.model.select(choosen);

			if(legals == null) return;

			if(this.model.getSetting().getMode() == Constants.MULTIPLAYER || this.model.getSetting().getTurno() == Constants.LIGHT)
				SFXPlayer.play(Path.MOVE_SFX);
			
			if(!this.model.isFirstMove()) {
				legals.add(choosen);
				this.setSelected(legals, true);
			}
			else {
				this.setSelected(legals, false);
			}
			this.view.updateView(legals);

			
			if(this.model.getMoving()) {
				SFXPlayer.play(Path.TURN_SFX);
				if (this.view.model.getPieceAt(choosen).getType() == PieceType.PAWN && (choosen.getX() == 0 || choosen.getX() == 7)) {
					if(this.view.model.getSetting().getMode() == Constants.SINGLEPLAYER && this.model.getSetting().getTurno() == Constants.LIGHT) {
						List<Object> value = new ArrayList <Object>();
						value.add(this.view.model.getPieceAt(choosen));
						value.add('q');
						this.upgradePawn(value);
					}else {
						openUpdatePawnDialog(this.view.model.getPieceAt(choosen));
					}
				}
				

				this.checkEventTrigger();
				
				if(checkDraw()) {
					PropertyChangeEvent pce = new PropertyChangeEvent(this, EventsUI.DRAW, null, null);
					trigger.firePropertyChange(pce);
					this.model.setMoving(false);
				}
				
				PropertyChangeEvent pce = new PropertyChangeEvent(this, EventsUI.MOVED, null, choosen);
				trigger.firePropertyChange(pce);
				this.model.setMoving(false);

				
				if(this.model.getSetting().getTurno() == Constants.DARK && this.model.getSetting().getMode() == Constants.SINGLEPLAYER) {
					this.movePc(model);
					this.checkEventTrigger();
					if(checkDraw()) {
						PropertyChangeEvent pce2 = new PropertyChangeEvent(this, EventsUI.DRAW, null, null);
						trigger.firePropertyChange(pce2);
						this.model.setMoving(false);
					}
				}
				
			}	
		}else if(evt.getPropertyName().equals(EventsUI.UPGRADE_PAWN)) {
			this.upgradePawn((List<Object>)evt.getNewValue());
		}
		
	}
	
	private boolean checkDraw() {
		return this.model.getPieceManger().getPiecesOfColoreness(Constants.LIGHT).size() == 1 && this.model.getPieceManger().getPiecesOfColoreness(Constants.DARK).size() == 1;
	}
	
	private void checkEventTrigger() {
		CheckType check = this.model.controllaCheck();
		if(check == CheckType.MATE) {
			int loser = this.model.getSetting().getTurno();
			this.model.getSetting().setFinished();
			PropertyChangeEvent pce2 = new PropertyChangeEvent(this, EventsUI.CHECKMATE, null, loser);
			trigger.firePropertyChange(pce2);
		}
	}

	private void upgradePawn(List<Object> value) {
		Piece pawnToUpdate = (Piece)value.get(0);
		char updatedPiece = (char)value.get(1);
		
		Piece newPiece = PieceFactory.getNewIstanceOfPiece(updatedPiece);
		newPiece.setPosition(pawnToUpdate.getPosition());
		this.view.model.setPieceAt(pawnToUpdate.getPosition(), newPiece);
		
		ArrayList<Piece> pawnAllies = this.view.model.getPieceManger().getPiecesOfColoreness(pawnToUpdate.getColoreness());
		pawnAllies.remove(pawnToUpdate);
		this.view.model.getPieceManger().addPiece(newPiece);
		
		this.view.updateView(null);
		
		//aggiusto l'ultima riga del log
		String lastLine = this.view.model.getSetting().getLogFile().get(this.view.model.getSetting().getLogFile().size()-1);
		lastLine = lastLine + "=" + updatedPiece + ":";
		this.view.model.getSetting().getLogFile().remove(this.view.model.getSetting().getLogFile().size()-1);
		this.view.model.getSetting().getLogFile().add(lastLine);
	}
	

	private void openUpdatePawnDialog(Piece pawn) {
		SFXPlayer.play(Path.POPUP_SFX);
		updatePawnDialog = new UpdatePawnDialog(pawn);
		updatePawnDialogController = new UpdatePawnDialogController(updatePawnDialog);
		updatePawnDialogController.register(this);
		updatePawnDialog.pack();
		updatePawnDialog.setLocationRelativeTo(null);
		updatePawnDialog.setVisible(true);
	}
	
	
	private int canEat(ArrayList<Position> legals, GameBoard board) {
		for (int i = 0; i < legals.size(); i++) {
			Position pos = legals.get(i);
			if(board.getPieceAt(pos) != null && board.getPieceAt(pos).getColoreness() == Constants.LIGHT) {
				return i;
			}
		}
		return -1;
	}
	
	public void movePc(GameBoard board) {
		ArrayList<Piece> darkPieces = board.getPieceManger().getPiecesOfColoreness(Constants.DARK);
		Collections.shuffle(darkPieces);
		Random random = new Random();
		Position from = null;
		Position to = null;
		boolean found = false;
		boolean canEat = false;
		int fallback = -1;
		for (int i = 0; i < darkPieces.size(); i++) {
			Piece p = darkPieces.get(i);
			ArrayList<Position> legals = p.legalMoves(board);
			p.deleteDangerMove(board, legals);
			int indCanEat = canEat(legals, board);
			if(indCanEat != -1) {
				from = p.getPosition();
				to = legals.get(indCanEat);
				canEat = true;
				found = true;
				break;
			}
			else if(legals.size() > 0) {
				fallback = i;
				found = true;
			}
		}
			
		if(!found) {
			System.out.println("scacco matto");
			return;
		}
		
		if(!canEat) {
			System.out.println("fallback -> nessuno puo mangiare");
			Piece p = darkPieces.get(fallback);
			ArrayList<Position> legals = p.legalMoves(board);
			p.deleteDangerMove(board, legals);
			from = p.getPosition();
			to = legals.get(random.nextInt(legals.size()));
		}
		
		int x1,y1,x2,y2;
		x1 = from.getX();
		y1 = from.getY();
		x2 = to.getX();
		y2 = to.getY();
		
		MouseEvent e1 = new MouseEvent(this.tileController[x1][y1].getView(), 1234, 1, 0, 0, 0, 0, false, 0);
		this.tileController[x1][y1].mouseList.mouseClicked(e1);
		
		MouseEvent e2 = new MouseEvent(this.tileController[x2][y2].getView(), 1234, 1, 0, 0, 0, 0, false, 0);
		this.tileController[x2][y2].mouseList.mouseClicked(e2);
	}
	
	public boolean undo() {
		ArrayList<Position> legals = null;
		legals = Mover.undo(this.model, false);
		this.view.updateView(legals);
		if(this.model.getSetting().getMode() == Constants.SINGLEPLAYER && legals != null)
			Mover.undoSingle = false;			
		return legals != null;
			
	}
	
	public void save() {
        String path = FileHandler.choosePath(JFileChooser.DIRECTORIES_ONLY);
        
        if(path == null){
        	System.out.println("nessun percorso selezionato");
        	return;
        }
        
        String from = this.model.getSetting().getLogFileName();	        
        
        String input = JOptionPane.showInputDialog("Nome del file") + ".txt";
        
        String to = path + File.separator + input;
        
        FileHandler.copyLog(from, to);
	}
	
	
	private void initListeners() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Square tileModel = model.getTableAt(i,j);
				SquareView tileView = this.view.getTiles()[i][j];
				tileController[i][j] = new SquareController(tileModel, tileView);
				tileController[i][j].register(this);
			}
		}
	}
}


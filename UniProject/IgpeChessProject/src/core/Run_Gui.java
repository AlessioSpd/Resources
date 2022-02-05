package core;

import controllers.MainFrameController;
import models.board.GameBoard;
import utilities.Handlers.FileHandler;
import views.MainFrame;

public class Run_Gui {
	public static void main(String[] args) {
		FileHandler.parseAndUpdateGraphicSettings();
		MainFrame mainFrame = new MainFrame("Game");
		MainFrameController controller = new MainFrameController(mainFrame);
	}
}

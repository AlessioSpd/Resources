package views.gamepage.right;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import config.Settings;
import utilities.Handlers.ImageHandler;
import utilities.Handlers.TemplateHandler;
import utilities.constants.Path;

public class RightTopPanel extends JPanel{

	public JButton undo;
	public JButton prev;
	public JButton next;
	public JButton surrender;
	
	public RightTopPanel() {
		super(new GridLayout(1,4));
		this.initPanel();
		this.updateColor();
	}
	
	public void setButtonsStatus(boolean mode){
		this.prev.setEnabled(mode);
		this.next.setEnabled(mode);
		this.undo.setEnabled(!mode);
		this.surrender.setEnabled(!mode);
	}
	
	public void initPanel() {

		int dim = Settings.screenHeight/50;
		
		prev = new JButton("PREV");
		ImageIcon prevIcon = ImageHandler.getScaledIcon(Path.PREV_IMG, dim, dim);
		prev.setIcon(prevIcon);
		
		undo = new JButton("UNDO");
		ImageIcon undoIcon = ImageHandler.getScaledIcon(Path.UNDO_IMG, dim, dim);
		undo.setIcon(undoIcon);
		
		next = new JButton("NEXT");
		ImageIcon nextIcon = ImageHandler.getScaledIcon(Path.NEXT_IMG, dim, dim);
		next.setIcon(nextIcon);
		
		surrender = new JButton("FORFEIT");
		ImageIcon surrenderIcon = ImageHandler.getScaledIcon(Path.SURRENDER_IMG, dim, dim);
		surrender.setIcon(surrenderIcon);
		
		this.add(prev);
		
		this.add(undo);
		
		this.add(next);
		
		this.add(surrender);
	}
	
	public void updateColor() {
		Color mid = TemplateHandler.blend(Settings.lightSquare, Settings.darkSquare);
		this.setBackground(mid);
		this.prev.setBackground(Settings.lightSquare);
		this.prev.setForeground(Settings.darkSquare);
		this.undo.setBackground(Settings.lightSquare);
		this.undo.setForeground(Settings.darkSquare);
		this.next.setBackground(Settings.lightSquare);
		this.next.setForeground(Settings.darkSquare);
		this.surrender.setBackground(Settings.lightSquare);
		this.surrender.setForeground(Settings.darkSquare);
	}
}

package views.gamepage.right;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import config.Settings;
import utilities.Handlers.TemplateHandler;

public class RightDownPanel extends JPanel {
	
	public myLogger logger;
	JPanel info;
	JLabel hashLab;
	JLabel fromLab;
	JLabel toLabel;

	public RightDownPanel() {
		super(new BorderLayout());
		this.initPanel();
	}
	
	public void initPanel() {

		info = new JPanel(new GridLayout(1, 3));
		hashLab = new JLabel("#");
		hashLab.setHorizontalAlignment(JLabel.CENTER);
		fromLab = new JLabel("From");
		fromLab.setHorizontalAlignment(JLabel.CENTER);
		toLabel = new JLabel("To");
		toLabel.setHorizontalAlignment(JLabel.CENTER);
		info.add(hashLab);
		info.add(fromLab);
		info.add(toLabel);
		
		this.add(info, BorderLayout.NORTH);
		logger = new myLogger();

		this.add(logger, BorderLayout.CENTER);
	}
}

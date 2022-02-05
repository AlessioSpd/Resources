package views.gamepage.left;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import config.Settings;
import utilities.Handlers.TemplateHandler;
import utilities.constants.EventsUI;

public class LeftTopPanel extends JPanel{

	public final String turn = "IT'S YOUR TURN!";
	
	private int width;
	private int height;
	private JLabel nameValue;
	private JLabel timeValue;
	private Font nameFont;
	private Font timeFont;
	private Timer timer;
	public int seconds;
	private PropertyChangeSupport trigger;
	private boolean team;
	
	private LeftTopPanelEatenBox eatenBox;
	public LeftTopPanelCountEaten countEaten;
	private JPanel dataBox;
	private JPanel turnBar;
	private JPanel playerBox;
	public JLabel turnValue;
	private Color blendColor;
	private Color dark;
	private Color light;
	private JPanel infoPanel;

	private JPanel leftNull;
	private JPanel rightNull;
	private JPanel bottomNull;
	private JPanel upNull;


	public LeftTopPanel(String team) {
		super(new BorderLayout());
		this.team = team.toUpperCase() == "LIGHT" ? true : false;
		this.setBackground(Color.red);
		this.trigger = new PropertyChangeSupport(this);
		
		this.width = (Settings.screenWidth- Settings.screenHeight)/2;
		this.height =  Settings.screenHeight/5*4;
		this.seconds = Settings.playerTime*60;
		this.initColors();
		this.initFont();
		this.initValuableLabels();
		this.initDataBox();
		this.initPanel();
		this.initTimer();
	}
	
	private void initColors() {
		 this.dark = Settings.darkSquare;
		 this.light = Settings.lightSquare;
		 blendColor = TemplateHandler.blend(this.dark, this.light);
	}
	
	public Timer getTimer() { return this.timer;}
	
	private void initFont() {
		this.nameFont = new Font("roboto", Font.PLAIN, Settings.screenWidth/55);
		this.timeFont = new Font("roboto", Font.PLAIN, Settings.screenWidth/75);
	}
	
	public void register(PropertyChangeListener observer) { 
		 this.trigger.addPropertyChangeListener( observer ); 
	} 
	
	private void initTimer() {
		timer = new Timer(1000, new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	seconds -= 1;
	        	timeValue.setText(secondsToTime(seconds));
	        	if(seconds <= 0) {
					PropertyChangeEvent pce = new PropertyChangeEvent(this, EventsUI.TIME_LOSE, null, null);
					trigger.firePropertyChange(pce);
	        	}
	        }
	    });
	}
	
	public void setTimeValue(String value) {
		timeValue.setText(value);
	}
	
	public String secondsToTime(int seconds) {
		int m = seconds / 60;
		int s = seconds % 60;
		String time = Integer.toString(m) + ":" + Integer.toString(s);
		return time;
	}
	
	public void setTurnLabel() {
		turnValue.setText( turnValue.getText().equals("") ? turn : "");
		turnValue.setForeground(team ? light : dark);
	}
	public void setTurnLabel(String text) {
		turnValue.setText(text);
		turnValue.setForeground(team ? light : dark);
	}
	
	private void initValuableLabels() {
		this.nameValue = new JLabel(team ? Settings.namePlayer1 : Settings.namePlayer2);
		this.nameValue.setFont(nameFont);
		this.nameValue.setForeground(team ? dark : light);
		this.nameValue.setHorizontalAlignment(JLabel.CENTER);
		this.nameValue.setPreferredSize(new Dimension(width/2, height/4));

		this.timeValue = new JLabel(this.secondsToTime(seconds));
		this.timeValue.setFont(timeFont);
		this.timeValue.setForeground(team ? dark : light);
		this.timeValue.setHorizontalAlignment(JLabel.CENTER);
		this.timeValue.setPreferredSize(new Dimension(width/2, height/4));
	}

	
	private void initDataBox() {
		dataBox = new JPanel();
		dataBox.setLayout(new BoxLayout(dataBox, BoxLayout.Y_AXIS));
		dataBox.setPreferredSize(new Dimension((Settings.screenWidth-Settings.screenHeight)/2, Settings.screenHeight/3-100));
				
		eatenBox = new LeftTopPanelEatenBox(team, team ? light : dark);
		countEaten = new LeftTopPanelCountEaten(team, team ? light : dark);
		
		infoPanel = new JPanel(new GridLayout(2,1));
		infoPanel.setPreferredSize(new Dimension((Settings.screenWidth-Settings.screenHeight)/2, Settings.screenHeight/5*4));
		infoPanel.setBackground(team ? light : dark);
		
		
		if(!team) {
			infoPanel.add(nameValue, BorderLayout.WEST);
			infoPanel.add(timeValue, BorderLayout.EAST);
			dataBox.add(infoPanel);
			dataBox.add(eatenBox);
			dataBox.add(countEaten);
		}else {
			infoPanel.add(timeValue, BorderLayout.EAST);
			infoPanel.add(nameValue, BorderLayout.WEST);
			dataBox.add(countEaten);
			dataBox.add(eatenBox);
			dataBox.add(infoPanel);
		}		
	}
	
	public void reloadSettings() {
		this.nameValue.setText(team ? Settings.namePlayer1 : Settings.namePlayer2);
		
		initColors();
		turnValue.setForeground(team ? light : dark);
		timeValue.setText(secondsToTime(seconds));
		turnBar.setBackground(!team ? light : dark);		
		
		leftNull.setBackground(blendColor);		
		rightNull.setBackground(blendColor);
		bottomNull.setBackground(blendColor);
		upNull.setBackground(blendColor);
		
		this.timeValue.setForeground(team ? dark : light);
		this.nameValue.setForeground(team ? dark : light);
		
		infoPanel.setBackground(team ? light : dark);
		eatenBox.setBack(team ? light : dark);
		countEaten.setBack(team ? light : dark, team);
	}
	
	public void incrementCount() {
		
	}
	
	private void initPanel() {
		this.setPreferredSize(new Dimension(width, height));
		
		playerBox = new JPanel();
		playerBox.setLayout(new BoxLayout(playerBox, BoxLayout.Y_AXIS));
		
		turnBar = new JPanel();
		turnBar.setBackground(!team ? light : dark);
		turnBar.setPreferredSize(new Dimension((Settings.screenWidth-Settings.screenHeight)/2, Settings.screenHeight/36));
		
		if(team)
			turnValue = new JLabel(turn);
		else turnValue = new JLabel();

		turnValue.setFont(new Font("roboto", Font.BOLD, Settings.screenWidth/100));
		
		turnBar.add(turnValue, BorderLayout.CENTER);
		
		if(team) {
			playerBox.add(turnBar);
			playerBox.add(dataBox);
		}else if(!team){
			playerBox.add(dataBox);
			playerBox.add(turnBar);
		}
		
		leftNull = new JPanel();
		leftNull.setBackground(blendColor);
		
		rightNull = new JPanel();
		rightNull.setBackground(blendColor);
		
		bottomNull = new JPanel();
		bottomNull.setBackground(blendColor);
		
		upNull = new JPanel();
		upNull.setBackground(blendColor);
		
		this.add(playerBox, BorderLayout.CENTER);
		this.add(leftNull, BorderLayout.EAST);
		this.add(rightNull, BorderLayout.WEST);
		this.add(bottomNull, BorderLayout.SOUTH);
		this.add(upNull, BorderLayout.NORTH);		
	}	
}

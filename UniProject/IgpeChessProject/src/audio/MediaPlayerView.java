package audio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import config.Settings;
import utilities.Handlers.ImageHandler;
import utilities.Handlers.TemplateHandler;
import utilities.constants.Constants;
import utilities.constants.Path;

public class MediaPlayerView extends JPanel{
	
	private static final long serialVersionUID = 1L;
	public JButton startAndStop;
	public JButton salva;
	public JButton nextTrack;
	public JButton prevTrack;
	public JButton soundButton;
	public JSlider sliderTraccia;
	public JSlider sliderVolume;
	public MediaPlayer model;
	private ImageIcon iconStart;
	private ImageIcon iconStop;
	private ImageIcon iconSound;
	private ImageIcon iconMute;
	public JLabel trackName;
	
	public MediaPlayerView() {
		super(new GridBagLayout());
		iconStart = ImageHandler.getScaledIcon(Path.START_IMG, Settings.screenHeight/67, Settings.screenHeight/67);
		iconStop = ImageHandler.getScaledIcon(Path.STOP_IMG, Settings.screenHeight/67, Settings.screenHeight/67);
		iconSound = ImageHandler.getScaledIcon(Path.SOUND_IMG, Settings.screenHeight/67, Settings.screenHeight/67);
		iconMute = ImageHandler.getScaledIcon(Path.MUTE_IMG, Settings.screenHeight/67, Settings.screenHeight/67);
		initPanel();		
	}
	
	public void setStartStopIcon(boolean isStop) {
		if(isStop) {
			if(startAndStop.getIcon() == iconStart)
				startAndStop.setIcon(iconStop);
			else
				startAndStop.setIcon(iconStart);
		}else startAndStop.setIcon(iconStop);
	}

	public void setSoundIcon() {
		if(soundButton.getIcon() == iconSound)
			soundButton.setIcon(iconMute);
		else
			soundButton.setIcon(iconSound);
	}
	
	private void initPanel() {
		
		model = new MediaPlayer();
		this.trackName = new JLabel(model.getCurrentClipName());
		ImageIcon icon;
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridy = 0;
		
		//Media control Button
		icon = ImageHandler.getScaledIcon(Path.PREV_IMG, Settings.screenHeight/67, Settings.screenHeight/67);
		prevTrack = new JButton(icon);
		prevTrack.setPreferredSize(new Dimension(35, 20));
		prevTrack.setOpaque(false);
		prevTrack.setContentAreaFilled(false);
		prevTrack.setBorderPainted(false);
		prevTrack.setFocusPainted(false);
		prevTrack.setToolTipText("Previous track");
		
		startAndStop = new JButton(iconStart);
		startAndStop.setPreferredSize(new Dimension(Settings.screenHeight/30, Settings.screenHeight/54));
		startAndStop.setOpaque(false);
		startAndStop.setContentAreaFilled(false);
		startAndStop.setBorderPainted(false);
		startAndStop.setFocusPainted(false);
		startAndStop.setToolTipText("Start media player");
		
		icon = ImageHandler.getScaledIcon(Path.NEXT_IMG, Settings.screenHeight/67, Settings.screenHeight/67);
		nextTrack = new JButton(icon);
		nextTrack.setPreferredSize(new Dimension(Settings.screenHeight/30, Settings.screenHeight/54));
		nextTrack.setOpaque(false);
		nextTrack.setContentAreaFilled(false);
		nextTrack.setBorderPainted(false);
		nextTrack.setFocusPainted(false);
		nextTrack.setToolTipText("Next track");
		
		JPanel mediaButton = new JPanel();
		mediaButton.add(prevTrack);
		mediaButton.add(startAndStop);
		mediaButton.add(nextTrack);
		
		//Volume track slider
		sliderVolume = new JSlider();
		sliderVolume.setMaximum(Constants.MAX_VOLUME);
		sliderVolume.setMinimum(Constants.NO_SOUND_VALUE);
		sliderVolume.setValue(Constants.DEFAULT_VOLUME);
		
		JPanel volumeSlider = new JPanel(new BorderLayout());
		
		soundButton = new JButton(iconSound);
		soundButton.setOpaque(false);
		soundButton.setContentAreaFilled(false);
		soundButton.setBorderPainted(false);
		soundButton.setFocusPainted(false);
		volumeSlider.add(soundButton, BorderLayout.WEST);
		volumeSlider.add(sliderVolume, BorderLayout.CENTER);
		soundButton.setToolTipText("Disable sound");
		
		//Track slider
		sliderTraccia = new JSlider();
		sliderTraccia.setMaximum(100);
		sliderTraccia.setMinimum(0);
		sliderTraccia.setValue(0);
		
		JPanel trackSlider = new JPanel(new BorderLayout());
		trackSlider.add(sliderTraccia, BorderLayout.CENTER);
		trackSlider.add(trackName, BorderLayout.EAST);
		
		
		
		//Set component in AudioPanel
		gbc.gridx = 0;
		this.add(mediaButton, gbc);
		
		gbc.gridx = 1;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(trackSlider, gbc);
		
		gbc.gridx = 2;
		gbc.weightx = 0;
		this.add(volumeSlider, gbc);
	}
	
}
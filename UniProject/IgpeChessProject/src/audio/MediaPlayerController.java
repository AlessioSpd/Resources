package audio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import utilities.constants.Constants;

public class MediaPlayerController {

	private MediaPlayerView view;
	private MediaPlayer model;
	Timer playTimer;
	
	public MediaPlayerController(MediaPlayerView view, MediaPlayer model) {
		this.view = view;
		this.model = model;
		this.initControllers();
		this.initTimer();
	}
	
	public void updateState() {
        int frame = this.model.clip.getFramePosition();
        int progress = (int) (((double) frame / (double) this.model.getFrameCount()) * 100);
        if(progress == this.view.sliderTraccia.getMaximum())
        	this.nextTrakAction(1);
        this.view.sliderTraccia.setValue(progress);
        this.view.sliderTraccia.repaint();
    }
	
	public void initTimer() {
		playTimer = new Timer(100, new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            updateState();
	        }
	    });
		
	}
	
	public void initControllers() {		
		
		this.view.startAndStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!model.isActive()) {
					model.start();
					playTimer.start();
				}else{
					playTimer.stop();
					model.stop();
				}
				
				view.startAndStop.setToolTipText( view.startAndStop.getToolTipText() == "Start media player" ? "Stop media player" : "Stop media player");
				
				view.setStartStopIcon(true);
			}
		});
		
		//next and prev		
		this.view.nextTrack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextTrakAction(1);
			}
		});
		
		this.view.prevTrack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextTrakAction(-1);
			}
		});
		
		
		//sound
		this.view.soundButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.setSoundIcon();
				if(view.sliderVolume.getValue() == Constants.NO_SOUND_VALUE)
					view.sliderVolume.setValue(Constants.DEFAULT_VOLUME);
				else
					view.sliderVolume.setValue(Constants.NO_SOUND_VALUE);
				
				view.soundButton.setToolTipText( view.soundButton.getToolTipText() == "Active sound" ? "Disable sound" : "Active sound");
			}
		});
		
		this.view.sliderVolume.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int value = view.sliderVolume.getValue();
				if(value == Constants.NO_SOUND_VALUE)
					value = Constants.MIN_VOLUME;
				model.setVolume(value);
			}
			
		});
		
		this.view.sliderTraccia.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if(!playTimer.isRunning()) {
					int progress = view.sliderTraccia.getValue();
		            double frame = ((double) model.getFrameCount() * ((double) progress / 100.0));
					model.clip.setFramePosition((int) frame);
				}
			}
			
		});
	}
	
	private void nextTrakAction(int mode) {
		
		if(mode == 1) {
			model.nextTrack();
			model.setVolume(view.sliderVolume.getValue());
			view.trackName.setText(model.getCurrentClipName());
			view.setStartStopIcon(false);
		}else {
			model.prevTrack();
			model.setVolume(view.sliderVolume.getValue());
			view.trackName.setText(model.getCurrentClipName());
			view.setStartStopIcon(false);
		}
	}
}

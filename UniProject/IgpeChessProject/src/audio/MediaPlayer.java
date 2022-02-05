package audio;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import utilities.Handlers.FileHandler;
import utilities.constants.Constants;
import utilities.constants.Path;

public class MediaPlayer {

	private AudioInputStream audioIn;
	public Clip clip;
	private File file;
	private int clipId;
	private boolean playing;
	ArrayList<String> tracksList;
	private AudioFormat format;
	private long frameCount;
	public FloatControl gainControl;
	
	public MediaPlayer() {
		tracksList = new ArrayList<String>(FileHandler.tracksList());
		this.playing = false;
		this.clipId = 0;
		takeTrack(clipId);
		gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(Constants.DEFAULT_VOLUME);
	}
	
	public String getCurrentClipName() {
		String name = tracksList.get(clipId);
		return name.substring(0, name.length()-4);
	}
	
	public long getFrameCount() {
		return this.frameCount;
	}
	
	public int getCurrentFrame() {
		return clip.getFramePosition();
	}
	
	private void takeTrack(int n) {		
		try {
			file = new File(Path.TRACKS_FOLDER + tracksList.get(n));
			audioIn = AudioSystem.getAudioInputStream(file);
			format = audioIn.getFormat();
			frameCount = audioIn.getFrameLength();
			
			clip = AudioSystem.getClip();
			clip.open(audioIn);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			clip = null;
			e.printStackTrace();
		}
	}
	
	public boolean isActive() {
		return playing;
	}

	public void start() {
		if (clip != null) {
			if (clip.getFramePosition() == clip.getFrameLength())
				clip.setFramePosition(0);
			playing = true;
			clip.start();
		}
	}

	public void stop() {
		if (clip != null) {
			clip.stop();
			playing = false;
		}
	}

	public void restart() {
		if (clip != null) {
			clip.stop();
			clip.setFramePosition(0);
			clip.start();
			playing = true;
		}
	}
	
	public void setVolume(int value) {
		if (clip != null) {				
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			if (value >= gainControl.getMinimum() && value <= gainControl.getMaximum())
				gainControl.setValue(value);
		}
	}
	
	public void nextTrack() {
		if(clip != null) {
			clipId++;
			if(clipId == tracksList.size())
				clipId = 0;
			clip.stop();
			clip.setFramePosition(0);
			takeTrack(clipId);
			start();
		}else{
			clip.setFramePosition(0);
			takeTrack(clipId);
			start();
		}		
	}
	
	public void prevTrack() {
		if(clip != null) {
			clipId--;
			if(clipId < 0)
				clipId = tracksList.size()-1;
			clip.stop();
			clip.setFramePosition(0);
			takeTrack(clipId);
			start();
		}else{
			clip.setFramePosition(0);
			takeTrack(clipId);
			start();
		}	
	}
}
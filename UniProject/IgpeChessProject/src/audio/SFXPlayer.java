package audio;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import config.Settings;
import utilities.constants.Constants;

public class SFXPlayer {

	public static void play(String path) {
		try {
			File file = new File(path);
		    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
		    Clip clip = AudioSystem.getClip();
		    clip.open(audioInputStream);
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(Settings.sfxVolume);
		    clip.start();
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
	}	
}

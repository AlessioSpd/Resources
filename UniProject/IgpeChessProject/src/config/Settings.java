package config;

import java.awt.Color;

import audio.MediaPlayer;
import utilities.constants.Constants;

public class Settings {
	public static int screenWidth = 1920;
	public static int screenHeight = 1080;
	public static boolean undo = true;
	public static int playerTime = 10;
	public static String namePlayer1 = "Player1";
	public static String namePlayer2 = "Player2";
	public static Color lightSquare = new Color(108,117,125);
	public static Color darkSquare = new Color(52,58,64);
	public static Color selectedSquare = new Color(105,205,197);
	public static int sfxVolume = Constants.DEFAULT_VOLUME;
}

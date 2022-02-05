package utilities.constants;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Constants {
	public static final int UNDO = 1;
	public static final int ARROCCO = 2;
	public static final int SINGLEPLAYER = 1;
	public static final int MULTIPLAYER = 2;
	public static final int REPLAY = 3;
	public static final int DARK = -1;
	public static final int LIGHT = 1;
	public static final int NONE = 0;
	public static final int BOTH = 2;
	public static final int DEFAULT_VOLUME = -24;
	public static final int MAX_VOLUME = 6;
	public static final int NO_SOUND_VALUE = -50;
	public static final int MIN_VOLUME = -80;
	public static final  Map<Integer, String> map = Stream.of(new Object[][] { 
	     {-1, "DARK"}, 
	     { 1, "LIGHT"}, 
	     { 0, "NONE"}, 
	     { 2, "BOTH"}, 
	}).collect(Collectors.toMap(data -> (Integer) data[0], data -> (String) data[1]));
	
	
	
}

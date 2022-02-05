package utilities.Handlers;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import utilities.constants.Path;

public class ImageHandler {
    
	public static ImageIcon getScaledIcon(ImageIcon original, int w, int h) {
		Image image = original.getImage();
		Image scaledImage = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		return scaledIcon;
	}
	
	public static ImageIcon getScaledIcon(String path, int w, int h) {
		ImageIcon icon = new ImageIcon(path);
		return getScaledIcon(icon, w, h);
	}
	public static ImageIcon getPieceImg(Character c, int w, int h) {
		String path = TemplateHandler.retrieveImgPath(c);
		return getScaledIcon(path, w, h);
	}
}

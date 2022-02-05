package views.shared;

import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.colorchooser.AbstractColorChooserPanel;

public class myColorChooser extends JColorChooser{
	
	public myColorChooser() {
		super();
		AbstractColorChooserPanel[] panels = this.getChooserPanels();
		for (AbstractColorChooserPanel panel : panels) {
		    String name = panel.getDisplayName();
		    if (!"RGB".equals(name))
		    	this.removeChooserPanel(panel);
		}
		this.setPreviewPanel(new JLabel());
	}
}

package views.gamepage.right;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;

import config.Settings;
import utilities.Handlers.TemplateHandler;

public class myRowRenderer extends Container implements ListCellRenderer<myRow> {

	public myRowRenderer() {
		super();
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends myRow> list, myRow value, int index, boolean isSelected, boolean cellHasFocus) {
		if(value.move.length() < 5) {
			JLabel fake = new JLabel();
			fake.setVisible(false);
			return fake;
		}
		JPanel p = new JPanel(new GridLayout(1,2,0,0));
		p.setPreferredSize(new Dimension((Settings.screenWidth-Settings.screenHeight)/2, Settings.screenHeight/40));
		p.add(new JLabel(value.icon));
		p.add(new JLabel(value.move.substring(0,2), JLabel.CENTER));
		p.add(new JLabel(value.move.substring(8,10), JLabel.CENTER));
		Color mid = TemplateHandler.blend(Settings.lightSquare, Settings.darkSquare);
		p.setBackground(mid);
		p.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		return p;
	}
	
}

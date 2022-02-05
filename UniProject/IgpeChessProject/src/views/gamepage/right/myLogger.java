package views.gamepage.right;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import config.Settings;
import utilities.Handlers.TemplateHandler;

public class myLogger extends JPanel{
	
    private JList<myRow> list;
    private DefaultListModel<myRow> listModel;
    JScrollPane scrollpane;
	
	public myLogger() {
		super();
		this.listModel = new DefaultListModel<myRow>();
		this.list = new JList<myRow>(listModel);
		this.list.setLayoutOrientation(JList.VERTICAL);
		myRowRenderer renderer = new myRowRenderer();
		list.setCellRenderer(renderer);
		this.initScrollPane();
		this.updateColor();
	}
	
	public void clearList() {
		this.listModel.clear();
	}
	
	public void addMove(Icon icon, String move) {
		myRow r = new myRow(icon, move);
		this.listModel.addElement(r);
	}
	
	public void removeLast() {
		this.listModel.remove(this.listModel.size()-1);
	}
	
	public void initScrollPane() {
		scrollpane = new JScrollPane(list);
		scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollpane.setPreferredSize(new Dimension((Settings.screenWidth-Settings.screenHeight)/2, Settings.screenHeight-50));
		this.add(scrollpane);
	}
	
	public void updateColor() {
		Color mid = TemplateHandler.blend(Settings.lightSquare, Settings.darkSquare);
		this.list.setBackground(mid);
	}
	
}

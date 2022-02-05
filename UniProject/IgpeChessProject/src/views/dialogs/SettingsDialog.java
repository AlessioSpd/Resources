package views.dialogs;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import config.Settings;
import utilities.Handlers.ImageHandler;
import utilities.constants.Constants;
import utilities.constants.Path;

public class SettingsDialog extends JDialog{

	private final Font font = new Font("roboto", Font.PLAIN, Settings.screenHeight/40);

	private int width;
	private int height;

	private JPanel packer;
	JScrollPane container;
	public JComboBox<Integer> timeValue;
	public JTextField p1NameText;
	public JTextField p2NameText;
	public JCheckBox undoValue;
	public JButton lightButt;
	public JButton darkButt;
	public JButton selectButt;
	public JButton resetButt;
	public JButton saveButt;
	public JSlider sfxSlider;
	
	public SettingsDialog() {
		super();
		initFrame();
		initComponent();
	}
	
	private void initFrame() {
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setTitle("Settings page");
		this.setModal(true);
		this.width = (int) (Settings.screenWidth/3.5);
		this.height = width;
		this.setPreferredSize(new Dimension(width,height));
	}
	
	private void initComponent() {
		
		int labelW = (int)(width/2.5);
		int h = height/11;
		int buttonDim = width/11;

		packer = new JPanel();
		packer.setLayout(new BoxLayout(packer, BoxLayout.Y_AXIS));
		packer.setPreferredSize(new Dimension(width, height));
		
		JPanel wp1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		wp1.setPreferredSize(new Dimension(width, h));
		JLabel time = new JLabel("Time for player  ");
        time.setFont(font);
		time.setPreferredSize(new Dimension(labelW, h));
        wp1.add(time);
		Integer [] cbValues = {5,10,20,30,60};
		timeValue = new JComboBox<Integer>(cbValues);
        timeValue.setFont(font);
        timeValue.setSelectedItem(Settings.playerTime);
        wp1.add(timeValue);

		JPanel wp2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		wp2.setPreferredSize(new Dimension(width, h));
        JLabel p1Name = new JLabel("Player 1 name:");
        p1Name.setFont(font);
		p1Name.setPreferredSize(new Dimension(labelW, h));
        wp2.add(p1Name);
        p1NameText = new JTextField(Settings.screenHeight/100);
        p1NameText.setFont(font);
        p1NameText.setText(Settings.namePlayer1);
        wp2.add(p1NameText);


		JPanel wp3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		wp3.setPreferredSize(new Dimension(width, h));
        JLabel p2Name = new JLabel("Player 2 name:");
        p2Name.setFont(font);
		p2Name.setPreferredSize(new Dimension(labelW, h));
        wp3.add(p2Name);
        p2NameText = new JTextField(Settings.screenHeight/100);
        p2NameText.setFont(font);
        p2NameText.setText(Settings.namePlayer2);
        wp3.add(p2NameText);

		JPanel wp4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		wp4.setPreferredSize(new Dimension(width, h));
        JLabel undo = new JLabel("Enable undo");
        undo.setFont(font);
		undo.setPreferredSize(new Dimension(labelW, h));
        wp4.add(undo);
        undoValue = new JCheckBox();
        undoValue.setFont(font);
        undoValue.setSelected(Settings.undo);
        wp4.add(undoValue);
        
		JPanel wp5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		wp5.setPreferredSize(new Dimension(width, h));
		JLabel light = new JLabel("Light Square:");
		light.setFont(font);
		light.setPreferredSize(new Dimension(labelW, h));
		wp5.add(light);
		lightButt = new JButton();
		lightButt.setOpaque(true);
		lightButt.setPreferredSize(new Dimension(buttonDim, h));
		lightButt.setBackground(Settings.lightSquare);
		wp5.add(lightButt);
		
		JPanel wp6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		wp6.setPreferredSize(new Dimension(width, h));
		JLabel dark = new JLabel("Dark Square:");
		dark.setPreferredSize(new Dimension(labelW, h));
		dark.setFont(font);
		wp6.add(dark);
		darkButt = new JButton();
		darkButt.setOpaque(true);
		darkButt.setPreferredSize(new Dimension(buttonDim, h));
		darkButt.setBackground(Settings.darkSquare);
		wp6.add(darkButt);
		
		JPanel wp7 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		wp7.setPreferredSize(new Dimension(width, h));
		JLabel select = new JLabel("Moves Square: ");
		select.setPreferredSize(new Dimension(labelW, h));
		select.setFont(font);
		wp7.add(select);
		selectButt = new JButton();
		selectButt.setOpaque(true);
		selectButt.setPreferredSize(new Dimension(buttonDim, h));
		selectButt.setBackground(Settings.selectedSquare);
		wp7.add(selectButt);
		
		JPanel wp8 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		wp8.setPreferredSize(new Dimension(width, h));
		JLabel reset = new JLabel("Reset:");
		reset.setFont(font);
		reset.setPreferredSize(new Dimension(labelW, h));
		wp8.add(reset);
		resetButt = new JButton();
		ImageIcon iconReset = ImageHandler.getScaledIcon(Path.UNDO_IMG, buttonDim, h);
		resetButt.setPreferredSize(new Dimension(buttonDim, h));
		resetButt.setIcon(iconReset);
		wp8.add(resetButt);

		JPanel wp10 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		wp10.setPreferredSize(new Dimension(width, h));
		JLabel volume = new JLabel("SFX");
		ImageIcon volumeIcon = ImageHandler.getScaledIcon(Path.SOUND_IMG, buttonDim, h);
		volume.setIcon(volumeIcon);
		wp10.add(volume);
		sfxSlider = new JSlider();
		sfxSlider.setMinimum(Constants.MIN_VOLUME);
		sfxSlider.setMaximum(Constants.MAX_VOLUME);
		sfxSlider.setValue(Settings.sfxVolume);
		wp10.add(sfxSlider);
		
		JPanel wp9 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		wp9.setPreferredSize(new Dimension(width, h));
		JLabel save = new JLabel("Save:");
		save.setFont(font);
		save.setPreferredSize(new Dimension(labelW, h));
		wp9.add(save);
		saveButt = new JButton();
		ImageIcon saveIcon = ImageHandler.getScaledIcon(Path.SAVE_IMG, buttonDim, h);
		saveButt.setPreferredSize(new Dimension(buttonDim, h));
		saveButt.setIcon(saveIcon);
		wp9.add(saveButt);
		
		JPanel wp11 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel note1 = new JLabel("Note: player time and undo will require to start a new game in order to work!");
		wp11.add(note1);
        
	    packer.add(wp1);
	    packer.add(wp2);
	    packer.add(wp3);
	    packer.add(wp4);
	    packer.add(wp5);
	    packer.add(wp6);
	    packer.add(wp7);
	    packer.add(wp10);
	    packer.add(wp8);
	    packer.add(wp9);
	    packer.add(wp11);
	    
	   container = new JScrollPane(packer);
	   container.setPreferredSize(new Dimension(width,height));
	   
	   this.add(container);
	}
}

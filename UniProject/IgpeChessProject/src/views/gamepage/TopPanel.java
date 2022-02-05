package views.gamepage;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import views.shared.TopRightCorner;
import views.shared.myBarMenu;


public class TopPanel extends JPanel{

    public myBarMenu myMenu;
    public TopRightCorner topRightCorner;
    
    public TopPanel(){
        super(new BorderLayout());
        initPanel();
    }
    
    private void initPanel() {
    	
    	myMenu = new myBarMenu();
    	topRightCorner = new TopRightCorner();
    	
		this.add(myMenu, BorderLayout.CENTER);	
		this.add(topRightCorner, BorderLayout.EAST);
    }
}

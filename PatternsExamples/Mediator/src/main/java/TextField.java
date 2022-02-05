import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TextField extends JTextField implements Component {
    private Mediator mediator;
    private String name;

    public TextField(String name){
        super(10);

        if(name != null)
            this.name = name;
        else this.name = "textField";

        this.setToolTipText("Insert value");

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                mediator.updateValue();
            }
        });
    }

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public String getName() {
        return name;
    }


}

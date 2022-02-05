import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ComboBox extends JComboBox implements Component {
    private Mediator mediator;
    private String name;

    public ComboBox(String name){
        super(Constants.valute);

        if(name != null)
            this.name = name;
        else this.name = "textField";

        this.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    mediator.updateValuta(getName());
                }
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

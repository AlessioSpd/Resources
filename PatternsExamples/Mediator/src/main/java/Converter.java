import javax.swing.*;
import java.awt.*;

public class Converter implements Mediator{

    private TextField toConvert;
    private TextField converted;
    private ComboBox valueToConvert;
    private ComboBox valueConverted;
    private StringBuffer converterLaw;

    public Converter(){
        converterLaw = new StringBuffer("EURtoEUR");
    }

    private String convertValue(){
        Double moltiplicatore = Constants.valoreValute.get(converterLaw.toString());
        return String.format("%." + Constants.approximation + "f", Double.parseDouble(toConvert.getText()) * moltiplicatore);
    }

    @Override
    public void updateValue(){
        if(toConvert.getText().equals("")){
            converted.setText("0");
            return;
        }

        converted.setText(convertValue());
    }

    @Override
    public void updateValuta(String verso) {
        if(verso.equals("valueToConvert")){
            String newValuta = valueToConvert.getSelectedItem().toString();
            converterLaw.replace(0, 3, newValuta);
        }else if(verso.equals("valueConverted")){
            String newValuta = valueConverted.getSelectedItem().toString();
            converterLaw.replace(converterLaw.length()-3, converterLaw.length(), newValuta);
        }
        updateValue();
    }

    @Override
    public void recComponent(Component component){
        component.setMediator(this);

        switch (component.getName()) {
            case "toConvert" -> toConvert = (TextField) component;
            case "converted" -> converted = (TextField) component;
            case "valueToConvert" -> valueToConvert = (ComboBox) component;
            case "valueConverted" -> valueConverted = (ComboBox) component;
        }
    }

    @Override
    public void createInterface() {
        JFrame myFrame = new JFrame("test");
        myFrame.setSize(500,200);
        myFrame.setPreferredSize(new Dimension(500,200));
        myFrame.setResizable(false);
        myFrame.setVisible(true);
        myFrame.setLayout(new GridLayout(1,2));

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        leftPanel.add(valueToConvert,gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        leftPanel.add(toConvert,gbc);


        JPanel rightPanel = new JPanel(new GridBagLayout());

        rightPanel.add(valueConverted);
        rightPanel.add(converted);

        myFrame.add(leftPanel);
        myFrame.add(rightPanel);
        myFrame.pack();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

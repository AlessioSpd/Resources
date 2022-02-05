public class Driver {
    public static void main(String[] args) {
        Mediator mediator = new Converter();

        mediator.recComponent(new TextField("toConvert"));
        mediator.recComponent(new TextField("converted"));
        mediator.recComponent(new ComboBox("valueToConvert"));
        mediator.recComponent(new ComboBox("valueConverted"));
        mediator.createInterface();
    }
}

public class ExtraMaionese extends ExtraDecorator {

    public ExtraMaionese(Consumazione consumation){
        this.consumation = consumation;
    }

    @Override
    public String getProductName() {
        return consumation.getProductName()+ " con extra maionese";
    }

    @Override
    public double getPrice() {
        return consumation.getPrice() + 2.00;
    }
}
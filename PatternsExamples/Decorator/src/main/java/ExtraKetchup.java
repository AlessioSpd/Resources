public class ExtraKetchup extends ExtraDecorator {
    public ExtraKetchup(Consumazione consumation){
        this.consumation = consumation;
    }
    @Override
    public String getProductName() {
        return consumation.getProductName()+ " con extra ketchup";
    }
    @Override
    public double getPrice() {
        return consumation.getPrice() + 1.00;
    }
}
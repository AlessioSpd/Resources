public class CheeseBurger extends Consumazione {

    public CheeseBurger() {
        this.nomeProdotto = "CheeseBurger";
    }

    @Override
    public double getPrice() {
        return 2.50;
    }
}
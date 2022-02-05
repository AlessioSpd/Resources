public class Hamburger extends Consumazione {

    public Hamburger() {
        this.nomeProdotto = "Hamburger";
    }

    @Override
    public double getPrice() {
        return 2.00;
    }
}
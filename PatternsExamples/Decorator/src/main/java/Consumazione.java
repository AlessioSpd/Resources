public abstract class Consumazione {
    protected String nomeProdotto = "";

    public String getProductName() {
        return nomeProdotto;
    }

    public abstract double getPrice();
}
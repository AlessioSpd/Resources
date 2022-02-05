public class Main {
    public static void main(String[] args) {
        //Hamburger
        Consumazione hamburger = new Hamburger();
        System.out.println("Prodotto:" + hamburger.getProductName() + " di prezzo " + hamburger.getPrice());

        Consumazione cheeseburger = new CheeseBurger();

        //voglio aggiungere la maionese al burger
        hamburger = new ExtraMaionese(hamburger);
        System.out.println("Prodotto:" + hamburger.getProductName() + " di prezzo " + hamburger.getPrice());

        //voglio aggiungere il ketchup al burger
        hamburger = new ExtraKetchup(hamburger);
        System.out.println("Prodotto:" + hamburger.getProductName() + " di prezzo " + hamburger.getPrice());

        //aggiungo maionese al cheesburger
        cheeseburger = new ExtraMaionese(cheeseburger);
        System.out.println("Prodotto:" + cheeseburger.getProductName() + " di prezzo " + cheeseburger.getPrice());

        //faccio una pizza alla maionese
        Consumazione pizza = new Pizza();

        pizza = new ExtraMaionese(pizza);
        System.out.println("Prodotto:" + pizza.getProductName() + " di prezzo " + pizza.getPrice());
        pizza = new ExtraKetchup(pizza);
        System.out.println("Prodotto:" + pizza.getProductName() + " di prezzo " + pizza.getPrice());

    }
}
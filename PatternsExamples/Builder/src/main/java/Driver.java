public class Driver {
    public static void main(String[] args) {

        Pizza pizzaMargherita = new Cameriere(new CuocoPizzaMargherita()).creaPizza();
        Pizza pizzaCapricciosa = new Cameriere(new CuocoPizzaCapricciosa()).creaPizza();

        System.out.println(pizzaMargherita.getIngredienti());
        System.out.println(pizzaCapricciosa.getIngredienti());
        
    }
}

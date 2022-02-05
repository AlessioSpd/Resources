package esempio;

public class esescuzione{
    public static void main(String[] args) {
        FiguraFactory factory = new FiguraFactory();

        Figura fig1 = factory.getFigura("cerchio");
        fig1.stampa();

        fig1 = factory.getFigura("rettangolo");
        fig1.stampa();

        fig1 = factory.getFigura("quadrato");
        fig1.stampa();
    }
}

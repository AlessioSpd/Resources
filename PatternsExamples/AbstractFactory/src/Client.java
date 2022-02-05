public class Client {
    public static void main(String[] args) {
        AbstractFactory FiguraFactory = FactoryProducer.getFactory(false);

        Figura Figura1 = FiguraFactory.getFigura("rettangolo");
        Figura1.stampa();

        Figura Figura2 = FiguraFactory.getFigura("quadrato");
        Figura2.stampa();

        AbstractFactory FiguraFactory1 = FactoryProducer.getFactory(true);

        Figura Figura3 = FiguraFactory1.getFigura("rettangolo");
        Figura3.stampa();

        Figura Figura4 = FiguraFactory1.getFigura("quadrato");
        Figura4.stampa();
    }
}
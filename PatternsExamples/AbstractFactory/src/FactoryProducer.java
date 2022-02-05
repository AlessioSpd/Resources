public class FactoryProducer {
    public static AbstractFactory getFactory(boolean d){
        if(d)
            return new FiguraFactory3d();
        else
            return new FiguraFactory();
    }
}

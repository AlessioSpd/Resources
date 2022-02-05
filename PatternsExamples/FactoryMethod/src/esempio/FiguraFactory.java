package esempio;

public class FiguraFactory {

    public Figura getFigura(String tipo){

        if(tipo == null)
            return null;
        else if(tipo.equalsIgnoreCase("CERCHIO"))
            return new Cerchio();
        else if(tipo.equalsIgnoreCase("RETTANGOLO"))
            return new Rettangolo();
        else if(tipo.equalsIgnoreCase("QUADRATO"))
            return new Quadrato();

        return null;
    }
}

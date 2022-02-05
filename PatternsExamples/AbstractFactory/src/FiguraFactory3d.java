public class FiguraFactory3d extends AbstractFactory{
    @Override
    Figura getFigura(String tipoFigura) {
        if(tipoFigura == null)
            return null;
        if(tipoFigura.equalsIgnoreCase("rettangolo"))
            return new Rettangolo3D();
        else if(tipoFigura.equalsIgnoreCase("quadrato"))
            return new Quadrato3D();

        return null;
    }
}

public class FiguraFactory extends AbstractFactory {
    @Override
    Figura getFigura(String tipoFigura) {

        if(tipoFigura == null){
            return null;
        }

        if(tipoFigura.equalsIgnoreCase("rettangolo")){
            return new Rettangolo();
        }else if(tipoFigura.equalsIgnoreCase("quadrato")){
            return new Quadrato();
        }
        return null;
    }
}

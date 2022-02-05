public class StampanteColore extends Stampante{

    public StampanteColore(Integer fogli, String name) {
        super(fogli, name);
        this.next = null;
    }

    @Override
    public void stampa(int tipo) {
        System.out.println("Sono la stampante: " + name);

        if(this.getFogli() > 0){
            switch (tipo){
                case 0 -> {
                    setFogli(getFogli() - 1);
                    System.out.println(name + " a colori ha stampato un foglio bianco e nero");
                }
                case 1 -> {
                    setFogli(getFogli() - 1);
                    System.out.println(name + " a colori ha stampato un foglio a colori");
                }
            }
        }else{
            if(next == null)
                System.out.println("Fogli finiti, ma non posso mandare alla prossima stampante,la catena è finita");
            else{
                System.out.println("Fogli finiti, mando alla prossima stampante " +  next.name);
                next.stampa(tipo);
            }
        }
    }
}

public abstract class Stampante {
    Stampante next;
    Integer fogli;
    String name;

    public Stampante(Integer fogli, String name) {
        this.fogli = fogli;
        this.name = name;
    }

    public void setNext(Stampante next){ this.next = next; }
    public abstract void stampa(int tipo);
    protected Integer getFogli(){ return fogli; };
    protected void setFogli(Integer fogli){ this.fogli = fogli; };
}

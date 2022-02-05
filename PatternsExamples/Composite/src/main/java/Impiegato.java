import java.util.ArrayList;
import java.util.List;

public class Impiegato {
    private String nome;
    private String dipartimento;
    private int stipendio;
    private List<Impiegato> subordinates;

    // constructor
    public Impiegato(String name,String dept, int sal) {
        this.nome = name;
        this.dipartimento = dept;
        this.stipendio = sal;
        subordinates = new ArrayList<Impiegato>();
    }

    public void add(Impiegato e) {
        subordinates.add(e);
    }

    public void remove(Impiegato e) {
        subordinates.remove(e);
    }

    public List<Impiegato> getSubordinates(){
        return subordinates;
    }

    public String toString(){
        return ("Employee :[ Name : " + nome + ", dept : " + dipartimento + ", salary :" + stipendio+" ]");
    }
}
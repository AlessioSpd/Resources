import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Integer colore = 1;
        Integer biancoNero = 0;

        Stampante s1 = new StampanteColore(1, "s1");
        Stampante s2 = new StampanteBiancoNero(1, "s2");
        Stampante s3 = new StampanteColore(1, "s3");
        Stampante s4 = new StampanteBiancoNero(1, "s4");

        s1.setNext(s2);
        s2.setNext(s3);
        s3.setNext(s4);

        int ris = 1;
        while(ris != 0){
            System.out.println("1.Stampa a colori\n2.Stampa in bianco e nero\n3.Stampa fogli\n#.Esci");
            ris = new Scanner(System.in).nextInt();

            switch (ris) {
                case 1 -> s1.stampa(colore);
                case 2 -> s1.stampa(biancoNero);
                case 3 -> {
                    System.out.println("\nFogli: "+ s1.name + " = " + s1.getFogli());
                    System.out.println("Fogli: "+ s2.name + " = " + s2.getFogli());
                    System.out.println("Fogli: "+ s3.name + " = " + s3.getFogli());
                    System.out.println("Fogli: "+ s4.name + " = " + s4.getFogli() + "\n");
                }
                default -> ris = 0;
            }
        }
    }
}

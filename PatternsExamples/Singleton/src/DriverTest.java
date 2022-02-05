public class DriverTest {
    public static void main(String[] args) {
        Singleton s = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();

        if(s == s2){
            System.out.println("ok");
        }
    }
}

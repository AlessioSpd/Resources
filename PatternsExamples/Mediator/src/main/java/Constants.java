import java.util.HashMap;

public class Constants {
    public static final String[] valute = {"EUR", "GBP", "USD"};
    public static final Integer approximation = 2;
    public static HashMap<String, Double> valoreValute;
    static {
        valoreValute = new HashMap<>();
        valoreValute.put("EURtoUSD", 1.13);
        valoreValute.put("USDtoEUR", 0.89);
        valoreValute.put("EURtoGBP", 0.85);
        valoreValute.put("GBPtoEUR", 1.18);
        valoreValute.put("USDtoGBP", 0.75);
        valoreValute.put("GBPtoUSD", 1.33);
        valoreValute.put("EURtoEUR", 1.00);
        valoreValute.put("GBPtoGBP", 1.00);
        valoreValute.put("USDtoUSD", 1.00);
    }
}

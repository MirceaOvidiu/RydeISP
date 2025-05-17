import java.util.ArrayList;

public class Utils {
    public static void initBikes(int noOfBikes, Admin admin, ArrayList<Bicicleta> biciclete){
        Bicicleta bicicleta = new Bicicleta();
        bicicleta.setId(String.valueOf(noOfBikes + 1));
        int randomLocation = (int) (Math.random() * Statii.values().length);
        bicicleta.setLocation(Statii.values()[randomLocation].getStationName());
        bicicleta.setBatteryStatus("100%");
        bicicleta.setAvailable(true);
        bicicleta.setUser(admin);
        biciclete.add(bicicleta);
    }
}

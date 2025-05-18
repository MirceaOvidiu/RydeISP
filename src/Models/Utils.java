package Models;

import java.util.ArrayList;

public class Utils {
    public static void initBikes(int noOfBikes, Admin admin, ArrayList<Bicicleta> biciclete) {
        for (int i = 0; i < noOfBikes; i++) {
            Bicicleta bicicleta = new Bicicleta();
            bicicleta.setId(String.valueOf(biciclete.size() + 1));

            int randomLocation = (int) (Math.random() * Statii.values().length);
            bicicleta.setLocation(Statii.values()[randomLocation].getStationName());

            bicicleta.setBatteryStatus(generateRandomBattery());

            bicicleta.setAvailable(Math.random() < 0.7); // 70% chance of being available

            bicicleta.setUser(admin);

            biciclete.add(bicicleta);
        }
    }

    private static String generateRandomBattery() {
        // Generate random battery between 50-100%
        int battery = 50 + (int) (Math.random() * 51);
        return battery + "%";
    }
}

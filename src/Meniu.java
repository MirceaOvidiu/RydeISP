import java.util.Scanner;

public class Meniu {
    public void afisareMeniu() {
        System.out.println("1. Afisare biciclete disponibile");
        System.out.println("2. Rezervare bicicleta");
        System.out.println("3. Incepere cursa");
        System.out.println("4. Finalizare cursa");
        System.out.println("5. Afisare detalii cursa");
        System.out.println("6. Iesire");

        //create cursa null
        Cursa cursa = new Cursa();

        // create one user
        User user = new User();
        user.setId("1");
        user.setName("John Doe");
        user.setEmail("");
        user.setPass("password123");
//        user.afisare();

        // create 10 bickes
        Bicicleta[] biciclete = new Bicicleta[10];
        for (int i = 0; i < biciclete.length; i++) {
            biciclete[i] = new Bicicleta();
            biciclete[i].setId(String.valueOf(i + 1));
            // randomize location
            int randomLocation = (int) (Math.random() * Statii.values().length);
            biciclete[i].setLocation(Statii.values()[randomLocation].getStationName());
            biciclete[i].setBatteryStatus("100%");
            biciclete[i].setAvailable(true);
            biciclete[i].setUser(user);
        }

        boolean running = true;
        Scanner scanner = new Scanner(System.in);
        while (running) {
            System.out.print("Alege o optiune: ");
            int optiune = scanner.nextInt();
            switch (optiune) {
                case 1:
                    afisareBicicleteDisponibile(biciclete);
                    break;
                case 2:
                    System.out.print("Introduceti ID-ul bicicletei: ");
                    String bicicletaID = scanner.next();
                    System.out.print("Introduceti numele statiei: ");
                    String locatieNume = scanner.next();

                    rezervareBicicleta(bicicletaID, user, Statii.valueOf(locatieNume), biciclete);
                    break;
                case 3:
                    System.out.print("Introduceti ID-ul bicicletei: ");
                    bicicletaID = scanner.next();
                    System.out.print("Introduceti numele statiei de start: ");
                    String startLocationNume = scanner.next();
                    System.out.print("Introduceti numele statiei de final: ");

                    // initialize the cursa
                    cursa.setId(bicicletaID);
                    cursa.setStartLocation(startLocationNume);
                    cursa.setUserId(user.getId());
                    cursa.setBikeId(bicicletaID);
                    cursa.setStartTime(String.valueOf(System.currentTimeMillis()));

                    startCursa(bicicletaID, user, Statii.valueOf(startLocationNume), biciclete, cursa);
                    break;
                case 4:
                    System.out.print("Introduceti numele statiei de final: ");
                    String endLocationNume = scanner.next();
                    terminareCursa(cursa, Statii.valueOf(endLocationNume), biciclete, user);
                    break;
                case 5:
                    cursa.afisareDetalii();
                    break;
                case 6:
                    System.out.println("Iesire");
                    running = false;
                    break;
                default:
                    System.out.println("Optiune invalida");
            }
        }
        scanner.close();
    }


    public void afisareBicicleteDisponibile(Bicicleta[] biciclete) {
        for (Bicicleta bicicleta : biciclete) {
            if (bicicleta.isAvailable()) {
                bicicleta.afisareDisponibilitate();
            }
        }
    }

    public void rezervareBicicleta(String bicicletaID, User user, Statii locatie, Bicicleta[] biciclete) {
        // get the bike by id
        Bicicleta bicicleta = null;
        for (Bicicleta b : biciclete) {
            if (b.getId().equals(bicicletaID)) {
                bicicleta = b;
                break;
            }
        }
        if (bicicleta != null) {
            bicicleta.rezervare(bicicletaID, user);
            bicicleta.setLocation(locatie.getStationName());
        } else {
            System.out.println("Bicicleta cu ID-ul " + bicicletaID + " nu a fost gasita.");
        }
    }


    public void startCursa(String bicicletaID, User user, Statii locatie, Bicicleta[] biciclete, Cursa cursa) {
        // get the bike by id
        Bicicleta bicicleta = null;
        for (Bicicleta b : biciclete) {
            if (b.getId().equals(bicicletaID)) {
                bicicleta = b;
                break;
            }
        }
        if (bicicleta != null) {
            cursa.setStartLocation(locatie.getStationName());
            cursa.setUserId(user.getId());
            cursa.setBikeId(bicicleta.getId());
            cursa.setStartTime(String.valueOf(System.currentTimeMillis()));
            cursa.startCursa();
        } else {
            System.out.println("Bicicleta cu ID-ul " + bicicletaID + " nu a fost gasita.");
        }
    }

    public void afisareDetaliiCursa(Cursa cursa) {
        cursa.afisareDetalii();
    }

    public void terminareCursa(Cursa cursa, Statii endLocation, Bicicleta[] biciclete, User user) {
        cursa.setEndLocation(endLocation.getStationName());
        cursa.setEndTime(String.valueOf(System.currentTimeMillis()));
        cursa.endCursa();

        int disatance = (int)((Long.parseLong(cursa.getEndTime()) - Long.parseLong(cursa.getStartTime())) * 5);
        cursa.setDistance(disatance);
        // write the cursa to a file
        cursa.writeCursaToFile(cursa);
        // eliberare bicicleta
        Bicicleta bicicleta = null;
        for (Bicicleta b : biciclete) {
            if (b.getId().equals(cursa.getBikeId())) {
                bicicleta = b;
                break;
            }
        }
        if (bicicleta != null) {
            bicicleta.eliberare(user, endLocation);
        } else {
            System.out.println("Bicicleta cu ID-ul " + cursa.getBikeId() + " nu a fost gasita.");
        }
    }
}

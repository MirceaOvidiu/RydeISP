import java.util.ArrayList;
import java.util.Scanner;

public class Meniu {
    private User currentUser;

    public static void listMenuOptions() {
        System.out.println("\n=== Meniu Principal ===");
        System.out.println("1. Autentificare");
        System.out.println("2. Creare cont nou");
        System.out.println("3. Afisare biciclete disponibile");
        System.out.println("4. Rezervare bicicleta");
        System.out.println("5. Incepere cursa");
        System.out.println("6. Finalizare cursa");
        System.out.println("7. Afisare detalii cursa");
        System.out.println("8. Vizualizare profil");
        System.out.println("9. Editare profil");
        System.out.println("10. Iesire");
    }

    public void afisareMeniu() {
        // Init required variables using ArrayList
        ArrayList<Bicicleta> biciclete = new ArrayList<>();
        ArrayList<User> users = new ArrayList<>();

        // Pre existing admin user
        User admin = new User();
        admin.setId("1");
        admin.setName("admin");
        admin.setEmail("");
        admin.setPass("admin");
        admin.afisare();
        users.add(admin);

        // Initialize bikes
        for (int i = 0; i < 10; i++) {
            Bicicleta bicicleta = new Bicicleta();
            bicicleta.setId(String.valueOf(i + 1));
            int randomLocation = (int) (Math.random() * Statii.values().length);
            bicicleta.setLocation(Statii.values()[randomLocation].getStationName());
            bicicleta.setBatteryStatus("100%");
            bicicleta.setAvailable(true);
            bicicleta.setUser(admin);
            biciclete.add(bicicleta);
        }

        //create cursa null
        Cursa cursa = new Cursa();
        boolean running = true;
        Scanner scanner = new Scanner(System.in);

        while (running) {

            listMenuOptions();

            int optiune = scanner.nextInt();

            switch (optiune) {
                case 1:
                    currentUser = login(scanner, users);
                    break;
                case 2:
                    currentUser = createNewUser(scanner, users);
                    break;
                case 3:
                    afisareBicicleteDisponibile(biciclete);
                    break;
                case 4:
                    if (currentUser == null) {
                        System.out.println("Trebuie sa va autentificati mai intai!");
                        break;
                    }
                    System.out.print("Introduceti ID-ul bicicletei: ");
                    String bicicletaID = scanner.next();
                    System.out.print("Introduceti numele statiei: ");
                    String locatieNume = scanner.next();
                    rezervareBicicleta(bicicletaID, currentUser, Statii.valueOf(locatieNume), biciclete);
                    break;
                case 5:
                    if (currentUser == null) {
                        System.out.println("Trebuie sa va autentificati mai intai!");
                        break;
                    }
                    System.out.print("Introduceti ID-ul bicicletei: ");
                    bicicletaID = scanner.next();
                    System.out.print("Introduceti numele statiei de start: ");
                    String startLocationNume = scanner.next();

                    cursa.setId(bicicletaID);
                    cursa.setStartLocation(startLocationNume);
                    cursa.setUserId(currentUser.getId());
                    cursa.setBikeId(bicicletaID);
                    cursa.setStartTime(String.valueOf(System.currentTimeMillis()));

                    startCursa(bicicletaID, currentUser, Statii.valueOf(startLocationNume), biciclete, cursa);
                    break;
                case 6:
                    if (currentUser == null) {
                        System.out.println("Trebuie sa va autentificati mai intai!");
                        break;
                    }
                    System.out.print("Introduceti numele statiei de final: ");
                    String endLocationNume = scanner.next();
                    terminareCursa(cursa, Statii.valueOf(endLocationNume), biciclete, currentUser);
                    break;
                case 7:
                    if (currentUser == null) {
                        System.out.println("Trebuie sa va autentificati mai intai!");
                        break;
                    }
                    cursa.afisareDetalii();
                    break;
                case 8:
                    if (currentUser == null) {
                        System.out.println("Trebuie sa va autentificati mai intai!");
                        break;
                    }
                    showUserProfile(currentUser);
                    break;
                case 9:
                    if (currentUser == null) {
                        System.out.println("Trebuie sa va autentificati mai intai!");
                        break;
                    }
                    editUserProfile(scanner, currentUser);
                    break;
                case 10: // Changed from 8
                    System.out.println("La revedere!");
                    running = false;
                    break;
                default:
                    System.out.println("Optiune invalida");
            }
        }
        scanner.close();
    }


    // User related functions
    private User login(Scanner scanner, ArrayList<User> users) {
        System.out.print("Username: ");
        String username = scanner.next();
        System.out.print("Password: ");
        String password = scanner.next();

        for (User user : users) {
            if (user.getName().equals(username) && user.getPass().equals(password)) {
                System.out.println("Login successful!");
                return user;
            }
        }
        System.out.println("Invalid credentials!");
        return null;
    }

    private User createNewUser(Scanner scanner, ArrayList<User> users) {
        System.out.println("\n=== Creare cont nou ===");
        System.out.print("Username: ");
        String username = scanner.next();
        System.out.print("Email: ");
        String email = scanner.next();
        System.out.print("Password: ");
        String password = scanner.next();

        // Generate new ID based on users size
        String newId = String.valueOf(users.size() + 1);

        User newUser = new User(newId, username, email, password);
        users.add(newUser);
        System.out.println("Cont creat cu succes!");
        return newUser;
    }

    private void showUserProfile(User user) {
        System.out.println("\n=== Profilul Meu ===");
        System.out.println("ID: " + user.getId());
        System.out.println("Nume: " + user.getName());
        System.out.println("Email: " + user.getEmail());
    }

    private void editUserProfile(Scanner scanner, User user) {
        System.out.println("\n=== Editare Profil ===");
        System.out.println("1. Schimbare nume");
        System.out.println("2. Schimbare email");
        System.out.println("3. Schimbare parola");
        System.out.print("Alege o optiune: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                System.out.print("Noul nume: ");
                String newName = scanner.nextLine();
                user.setName(newName);
                System.out.println("Numele a fost actualizat!");
                break;
            case 2:
                System.out.print("Noul email: ");
                String newEmail = scanner.nextLine();
                user.setEmail(newEmail);
                System.out.println("Email-ul a fost actualizat!");
                break;
            case 3:
                System.out.print("Noua parola: ");
                String newPassword = scanner.nextLine();
                user.setPass(newPassword);
                System.out.println("Parola a fost actualizata!");
                break;
            default:
                System.out.println("Optiune invalida!");
        }
    }

    private void afisareBicicleteDisponibile(ArrayList<Bicicleta> biciclete) {
        for (Bicicleta bicicleta : biciclete) {
            if (bicicleta.isAvailable()) {
                bicicleta.afisareDisponibilitate();
            }
        }
    }

    private void rezervareBicicleta(String bicicletaID, User user, Statii locatie, ArrayList<Bicicleta> biciclete) {
        Bicicleta bicicleta = biciclete.stream()
                .filter(b -> b.getId().equals(bicicletaID))
                .findFirst()
                .orElse(null);

        if (bicicleta != null) {
            bicicleta.rezervare(bicicletaID, user);
            bicicleta.setLocation(locatie.getStationName());
        } else {
            System.out.println("Bicicleta cu ID-ul " + bicicletaID + " nu a fost gasita.");
        }
    }

    private void startCursa(String bicicletaID, User user, Statii locatie, ArrayList<Bicicleta> biciclete, Cursa cursa) {
        Bicicleta bicicleta = biciclete.stream()
                .filter(b -> b.getId().equals(bicicletaID))
                .findFirst()
                .orElse(null);

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

    private void terminareCursa(Cursa cursa, Statii endLocation, ArrayList<Bicicleta> biciclete, User user) {
        cursa.setEndLocation(endLocation.getStationName());
        cursa.setEndTime(String.valueOf(System.currentTimeMillis()));
        cursa.endCursa();

        int distance = (int)((Long.parseLong(cursa.getEndTime()) - Long.parseLong(cursa.getStartTime())) * 5);
        cursa.setDistance(distance);
        cursa.writeCursaToFile(cursa);

        Bicicleta bicicleta = biciclete.stream()
                .filter(b -> b.getId().equals(cursa.getBikeId()))
                .findFirst()
                .orElse(null);

        if (bicicleta != null) {
            bicicleta.eliberare(user, endLocation);
        } else {
            System.out.println("Bicicleta cu ID-ul " + cursa.getBikeId() + " nu a fost gasita.");
        }
    }
}
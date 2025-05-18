import java.util.ArrayList;
import java.util.Scanner;

public class Meniu {
    private User currentUser;

    public void listMenuOptions() {
        System.out.println("\n=== Meniu Principal ===");
        System.out.println("1.  Autentificare");
        System.out.println("2.  Creare cont nou");
        System.out.println("3.  Afisare biciclete disponibile");
        System.out.println("4.  Rezervare bicicleta");
        System.out.println("5.  Incepere cursa");
        System.out.println("6.  Finalizare cursa");
        System.out.println("7.  Afisare detalii cursa");
        System.out.println("8.  Vizualizare profil");
        System.out.println("9.  Editare profil");
        System.out.println("10. Iesire");

        ///  Admin Only actions
        if (isAdmin(currentUser)) {
            System.out.println("11. Creare angajat nou");
            System.out.println("12. Stergere angajat");
            System.out.println("13. Editare angajat");
            System.out.println("14. Vizualizare lista angajati");
            System.out.println("15. Teste unitare JUnit");
        }

    }

    public void listEmployeeDetails(Angajat employee) {
        System.out.println("\n=== Detalii Angajat ===");
        System.out.println("ID: " + employee.getId());
        System.out.println("Nume: " + employee.getName());
        System.out.println("Email: " + employee.getEmail());
        System.out.println("Employee ID: " + employee.getEmployeeId());
        System.out.println("Rol: " + employee.getRole());
        System.out.println("Departament: " + employee.getDepartment());
        System.out.println("Salariu: " + employee.getSalary());
    }

    public void afisareMeniu() {
        // Init required variables using ArrayList
        ArrayList<Bicicleta> biciclete = new ArrayList<>();
        ArrayList<User> users = new ArrayList<>();

        // Pre-existing admin user
        Admin admin = new Admin();
        admin.setId("1");
        admin.setName("admin");
        admin.setEmail("");
        admin.setPass("admin");
        admin.afisare();
        users.add(admin);

        // Initialize bikes
        Utils.initBikes(10, admin, biciclete);

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
                case 10:
                    System.out.println("La revedere!");
                    running = false;
                    break;
                case 11:
                    if (!isAdmin(currentUser)) {
                        System.out.println("Acces interzis! Doar administratorii pot accesa aceasta functie.");
                        break;
                    }
                    createEmployee(scanner, users);
                    break;
                case 12:
                    if (!isAdmin(currentUser)) {
                        System.out.println("Acces interzis! Doar administratorii pot accesa aceasta functie.");
                        break;
                    }
                    deleteEmployee(scanner, users);
                    break;
                case 13:
                    if (!isAdmin(currentUser)) {
                        System.out.println("Acces interzis! Doar administratorii pot accesa aceasta functie.");
                        break;
                    }
                    editEmployee(scanner, users);
                    break;
                case 14:
                    if (!isAdmin(currentUser)) {
                        System.out.println("Acces interzis! Doar administratorii pot accesa aceasta functie.");
                        break;
                    }
                    viewAllEmployees(users);
                    break;
                case 15:
                    if (!isAdmin(currentUser)) {
                        System.out.println("Acces interzis! Doar administratorii pot accesa aceasta functie.");
                        break;
                    }
                    runUnitTests(scanner);
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

    // Must be public for JUnit testing
    public User createNewUser(Scanner scanner, ArrayList<User> users) {
        System.out.println("\n=== Creare cont nou ===");

        String username;
        do {
            System.out.print("Username: ");
            username = scanner.next().trim();
            if (username.isEmpty()) {
                System.out.println("Username nu poate fi gol!");
            }
        } while (username.isEmpty());

        System.out.print("Email: ");
        String email = scanner.next();

        String password;
        do {
            System.out.print("Password: ");
            password = scanner.next().trim();
            if (password.isEmpty()) {
                System.out.println("Parola nu poate fi goala!");
            }
        } while (password.isEmpty());

        String newId = String.valueOf(users.size() + 1);

        User newUser = new User(newId, username, email, password);
        users.add(newUser);
        System.out.println("Cont creat cu succes!");
        return newUser;
    }

    private boolean isAdmin(User user) {
        return user != null && user.getName().equals("admin");
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

    public void createEmployee(Scanner scanner, ArrayList<User> users) {
        System.out.println("\n=== Creare Angajat Nou ===");

        String username;
        do {
            System.out.print("Username: ");
            username = scanner.next().trim();
            if (username.isEmpty()) {
                System.out.println("Username nu poate fi gol!");
            }
        } while (username.isEmpty());

        System.out.print("Email: ");
        String email = scanner.next();

        String password;
        do {
            System.out.print("Password: ");
            password = scanner.next().trim();
            if (password.isEmpty()) {
                System.out.println("Parola nu poate fi goala!");
            }
        } while (password.isEmpty());

        System.out.print("Employee ID: ");
        String employeeId = scanner.next();
        System.out.print("Role: ");
        String role = scanner.next();
        System.out.print("Department: ");
        String department = scanner.next();
        System.out.print("Salary: ");
        double salary = scanner.nextDouble();

        String newId = String.valueOf(users.size() + 1);
        Angajat newEmployee = new Angajat(newId, username, email, password, employeeId, role, department, salary);
        users.add(newEmployee);
        System.out.println("Angajat creat cu succes!");
    }

    private void deleteEmployee(Scanner scanner, ArrayList<User> users) {
        System.out.print("Introduceti Employee ID-ul angajatului de sters: ");
        String employeeId = scanner.next();

        users.removeIf(user -> user instanceof Angajat &&
                ((Angajat) user).getEmployeeId().equals(employeeId));
        System.out.println("Angajat sters cu succes!");
    }

    private void editEmployee(Scanner scanner, ArrayList<User> users) {
        System.out.print("Introduceti Employee ID-ul angajatului de modificat: ");
        String employeeId = scanner.next();

        for (User user : users) {
            if (user instanceof Angajat employee && employee.getEmployeeId().equals(employeeId)) {
                System.out.println("\n=== Editare Angajat ===");
                System.out.println("1. Modificare nume");
                System.out.println("2. Modificare email");
                System.out.println("3. Modificare parola");
                System.out.println("4. Modificare rol");
                System.out.println("5. Modificare departament");
                System.out.println("6. Modificare salariu");
                System.out.print("Alege o optiune: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Noul nume: ");
                        employee.setName(scanner.nextLine());
                        break;
                    case 2:
                        System.out.print("Noul email: ");
                        employee.setEmail(scanner.nextLine());
                        break;
                    case 3:
                        System.out.print("Noua parola: ");
                        employee.setPass(scanner.nextLine());
                        break;
                    case 4:
                        System.out.print("Noul rol: ");
                        employee.setRole(scanner.nextLine());
                        break;
                    case 5:
                        System.out.print("Noul departament: ");
                        employee.setDepartment(scanner.nextLine());
                        break;
                    case 6:
                        System.out.print("Noul salariu: ");
                        employee.setSalary(scanner.nextDouble());
                        break;
                    default:
                        System.out.println("Optiune invalida!");
                        return;
                }
                System.out.println("Angajat modificat cu succes!");
                return;
            }
        }
        System.out.println("Angajat negasit!");
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

        int distance = (int) ((Long.parseLong(cursa.getEndTime()) - Long.parseLong(cursa.getStartTime())) * 5);
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

    private void viewEmployeeById(Scanner scanner, ArrayList<User> users) {
        System.out.print("Introduceti Employee ID-ul angajatului: ");
        String employeeId = scanner.next();
        boolean found = false;

        for (User user : users) {
            if (user instanceof Angajat employee && employee.getEmployeeId().equals(employeeId)) {
                listEmployeeDetails(employee);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Angajatul cu ID-ul " + employeeId + " nu a fost gasit!");
        }
    }

    private void viewAllEmployees(ArrayList<User> users) {
        System.out.println("\n=== Lista Angajati ===");
        boolean found = false;

        for (User user : users) {
            if (user instanceof Angajat employee) {
                listEmployeeDetails(employee);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Nu exista angajati in sistem!");
        }
    }

    public void runUnitTests(Scanner scanner) {
        boolean testingMode = true;

        while (testingMode) {
            System.out.println("\n=== Meniu Teste Unitare ===");
            System.out.println("1. Test creare utilizator nou");
            System.out.println("2. Test creare angajat");
            System.out.println("3. Test creare angajat cu date invalide");
            System.out.println("4. Inapoi la meniul principal");

            System.out.print("\nAlegeti testul de rulat: ");
            int choice = scanner.nextInt();

            Tests tests = new Tests();
            tests.setUp(); // Initialize test environment

            try {
                switch (choice) {
                    case 1:
                        System.out.println("\nRulare test: Creare utilizator nou");
                        tests.testCreateNewUser();
                        System.out.println("Test creare utilizator nou: SUCCES");
                        break;

                    case 2:
                        System.out.println("\nRulare test: Creare angajat");
                        tests.testCreateEmployee();
                        System.out.println("Test creare angajat: SUCCES");
                        break;

                    case 3:
                        System.out.println("\nRulare test: Creare angajat invalid");
                        tests.testInvalidEmployeeCreation();
                        System.out.println("Test creare angajat invalid: SUCCES");
                        break;

                    case 4:
                        System.out.println("Iesire din modul testare...");
                        testingMode = false;
                        break;

                    default:
                        System.out.println("Optiune invalida!");
                }
            } catch (AssertionError e) {
                System.out.println("Test ESUAT: " + e.getMessage());
            } finally {
                tests.restoreSystemInput(); // Cleanup after test
            }
        }
    }

}
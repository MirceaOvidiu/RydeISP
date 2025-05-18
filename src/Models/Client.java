package Models;

public class Client extends User {
    private String Iban;

    // Default constructor
    public Client() {
        super();
    }

    // All-args constructor
    public Client(String id, String name, String email, String pass, String Iban) {
        super(id, name, email, pass);
        this.Iban = Iban;
    }

    // Getter and Setter for Iban
    public String getIban() {
        return Iban;
    }

    public void setIban(String Iban) {
        this.Iban = Iban;
    }

    private void rezervare(Bicicleta bicicleta, Statii location) {
        bicicleta.rezervare(bicicleta.getId(), this);

        Cursa cursa = new Cursa();
        cursa.setStartLocation(location.getStationName());
        cursa.setUserId(this.getId());
        cursa.setBikeId(bicicleta.getId());
        cursa.setStartTime(String.valueOf(System.currentTimeMillis()));
        cursa.startCursa();
    }

    private void plata(String location) {
        // Example: calculate price based on distance (dummy value for now)
        double distance = 5.0; // Replace with actual distance calculation
        double pricePerKm = 2.0;
        double totalPrice = distance * pricePerKm;

        // Simulate payment (could integrate with payment API)
        System.out.println("Payment of " + totalPrice + " RON for location: " + location);

        // Save ride to a txt file
        try (java.io.FileWriter writer = new java.io.FileWriter("istoric_curse.txt", true)) {
            writer.write("User: " + this.getId() + ", Location: " + location + ", Price: " + totalPrice + "\n");
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    private void eliberare() {
        // Simulate bike release
        System.out.println("Bicicleta eliberata de catre clientul cu ID: " + this.getId());
    }

    private void cautaBicicleta(String location) {
        // Dummy search logic
        System.out.println("Caut biciclete disponibile la statia: " + location);
        // In real implementation, query available bikes from station
        boolean bikesAvailable = true; // Replace with actual check
        if (bikesAvailable) {
            System.out.println("Biciclete disponibile gasite la statia " + location);
        } else {
            System.out.println("Nu sunt biciclete disponibile la statia " + location);
        }
    }

    private void afisareDetaliiCursa() {
        // Dummy details
        System.out.println("Detalii cursa: UserID=" + this.getId() + ", IBAN=" + this.getIban());
    }

    private void afisareIstoricCurse() {
        // Read and display ride history from file
        try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader("istoric_curse.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (java.io.IOException e) {
            System.out.println("Nu exista istoric de curse sau a aparut o eroare.");
        }
    }
}
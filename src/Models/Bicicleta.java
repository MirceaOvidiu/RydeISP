package Models;

public class Bicicleta {
    private String id;
    private String location;
    private String batteryStatus;
    private boolean isAvailable;
    private User user;
    private String name;

    // Default constructor
    public Bicicleta() {
    }

    // All-args constructor
    public Bicicleta(String id, String location, String batteryStatus, boolean isAvailable, User user, String name) {
        this.id = id;
        this.location = location;
        this.batteryStatus = batteryStatus;
        this.isAvailable = isAvailable;
        this.user = user;
        this.name = name;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBatteryStatus() {
        return batteryStatus;
    }

    public void setBatteryStatus(String batteryStatus) {
        this.batteryStatus = batteryStatus;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void afisareDisponibilitate() {
        if (!this.isAvailable) {
            System.out.println("Models.Bicicleta " + this.getId() + " nu este disponibila.");
            return;
        }
        System.out.println("Models.Bicicleta " + this.getId() + " este disponibila.");
    }

    public void rezervare(String id, User user) {
        if (user == null) {
            System.out.println("Utilizatorul nu este valid.");
            return;
        }

        if (isAvailable) {
            isAvailable = false;
            this.user = user;
            System.out.println("Models.Bicicleta " + this.getId() + " a fost rezervata de utilizatorul " + user.getName() + ".");
        } else {
            System.out.println("Models.Bicicleta " + this.getId() + " nu este disponibila.");
        }
    }

    public void eliberare(User user, Statii location) {
        if (user == null) {
            System.out.println("Utilizatorul nu este valid.");
            return;
        }

        isAvailable = true;
        this.user = null;
        this.setLocation(location.getStationName());
        System.out.println("Models.Bicicleta " + this.getId() + " a fost eliberata de utilizatorul " + user.getName() + ".");
    }

    public void afisareDetalii() {
        System.out.println("ID: " + this.getId());
        System.out.println("Locatie: " + this.getLocation());
        System.out.println("Stare baterie: " + this.getBatteryStatus());
        System.out.println("Disponibilitate: " + (this.isAvailable ? "Disponibila" : "Indisponibila"));
    }
}
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
        // Implement payment logic here
        // Calcul matematic al pretului in functie de distanta
        // Dupa care faci plata pentru bicicleta respectiva
        // Adaugi cursa intr-un fisier txt
    }

    private void eliberare() {
        // Implementation needed
    }

    private void cautaBicicleta(String location) {
        // Implement search logic here
        // Faci o cautare a bicicletelor dupa statii si dupa afisezi bicicletele disponibile
        // Daca nu sunt biciclete disponibile, afisezi un mesaj corespunzator
    }

    private void afisareDetaliiCursa() {
        // Implementation needed
    }

    private void afisareIstoricCurse() {
        // Implement history display logic here
    }
}
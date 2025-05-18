import java.util.ArrayList;
import java.util.List;

public class Administrator extends User {
    private List<Angajat> angajati = new ArrayList<>();
    private List<Bicicleta> biciclete = new ArrayList<>();
    private List<Client> clienti = new ArrayList<>();

    public Administrator(String id, String name, String email, String pass) {
        super(id, name, email, pass);
    }

    public Administrator() {
        super();
    }

    public List<Angajat> getAngajati() {
        return angajati;
    }

    public void setAngajati(List<Angajat> angajati) {
        this.angajati = angajati;
    }

    public List<Bicicleta> getBiciclete() {
        return biciclete;
    }

    public void setBiciclete(List<Bicicleta> biciclete) {
        this.biciclete = biciclete;
    }

    public List<Client> getClienti() {
        return clienti;
    }

    public void setClienti(List<Client> clienti) {
        this.clienti = clienti;
    }

    public boolean autentificare(String user, String pass) {
        return getEmail().equals(user) && getPass().equals(pass);
    }

    public void editeazaCont(String newName, String newEmail, String newPass) {
        if (newPass == null || newPass.isEmpty()) {
            System.out.println("Parola nu poate fi goala.");
            return;
        }
        setName(newName);
        setEmail(newEmail);
        setPass(newPass);
    }

    // Angajat methods
    public void adaugaAngajat(Angajat angajat) {
        if (cautaAngajatDupaId(angajat.getId()) == null) {
            angajati.add(angajat);
        } else {
            System.out.println("Angajatul cu acest ID exista deja.");
        }
    }

    public void stergeAngajat(String id) {
        angajati.removeIf(a -> a.getId().equals(id));
    }

    public void vizualizareAngajati() {
        for (Angajat angajat : angajati) {
            angajat.afisare();
        }
    }

    public Angajat cautaAngajatDupaId(String id) {
        for (Angajat a : angajati) {
            if (a.getId().equals(id)) {
                return a;
            }
        }
        return null;
    }

    public void actualizeazaAngajat(Angajat angajatNou) {
        for (int i = 0; i < angajati.size(); i++) {
            if (angajati.get(i).getId().equals(angajatNou.getId())) {
                angajati.set(i, angajatNou);
                return;
            }
        }
        System.out.println("Angajatul nu a fost gasit pentru actualizare.");
    }

    // Bicicleta methods
    public void adaugaBicicleta(Bicicleta bicicleta) {
        if (cautaBicicletaDupaId(bicicleta.getId()) == null) {
            biciclete.add(bicicleta);
        } else {
            System.out.println("Bicicleta cu acest ID exista deja.");
        }
    }

    public void stergeBicicleta(String id) {
        biciclete.removeIf(b -> b.getId().equals(id));
    }

    public void vizualizareBiciclete() {
        for (Bicicleta bicicleta : biciclete) {
            bicicleta.afisareDetalii();
        }
    }

    public Bicicleta cautaBicicletaDupaId(String id) {
        for (Bicicleta b : biciclete) {
            if (b.getId().equals(id)) {
                return b;
            }
        }
        return null;
    }

    public void actualizeazaBicicleta(Bicicleta bicicletaNoua) {
        for (int i = 0; i < biciclete.size(); i++) {
            if (biciclete.get(i).getId().equals(bicicletaNoua.getId())) {
                biciclete.set(i, bicicletaNoua);
                return;
            }
        }
        System.out.println("Bicicleta nu a fost gasita pentru actualizare.");
    }

    // Client methods
    public void adaugaClient(Client client) {
        if (cautaClientDupaId(client.getId()) == null) {
            clienti.add(client);
        } else {
            System.out.println("Clientul cu acest ID exista deja.");
        }
    }

    public void stergeClient(String id) {
        clienti.removeIf(c -> c.getId().equals(id));
    }

    public void vizualizareClienti() {
        for (Client client : clienti) {
            client.afisare();
        }
    }

    public Client cautaClientDupaId(String id) {
        for (Client c : clienti) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    public void actualizeazaClient(Client clientNou) {
        for (int i = 0; i < clienti.size(); i++) {
            if (clienti.get(i).getId().equals(clientNou.getId())) {
                clienti.set(i, clientNou);
                return;
            }
        }
        System.out.println("Clientul nu a fost gasit pentru actualizare.");
    }

    public static void main(String[] args) {
        Administrator admin = new Administrator("1", "Admin", "admin@email.com", "adminpass");

        Angajat angajat = new Angajat("2", "ion", "ion@email.com", "pass", "E001", "Manager", "HR", 5000);
        admin.adaugaAngajat(angajat);

        Client client = new Client("3", "ioana", "ioana@email.com", "pass", "RO49AAAA1B31007593840000");
        admin.adaugaClient(client);

        Bicicleta bike = new Bicicleta("B1", "Politehnica", "100%", true, null, "Bike1");
        admin.adaugaBicicleta(bike);

        System.out.println("Angajati:");
        admin.vizualizareAngajati();
        System.out.println("Clienti:");
        admin.vizualizareClienti();
        System.out.println("Biciclete:");
        admin.vizualizareBiciclete();
    }
}
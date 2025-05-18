package Models;

import java.util.List;

public class Admin extends User {

        private String role;
        private String department;
        private String employeeId;
        private double salary;
        private List<Models.Angajat> angajati;
        private List<Bicicleta> biciclete;
        private List<Client> clienti;

        public Admin() {
            super();
        }

        public Admin(String id, String name, String email, String password, String employeeId, String role, String department, double salary) {
            super(id, name, email, password);
            this.employeeId = employeeId;
            this.role = role;
            this.department = department;
            this.salary = salary;
        }

        // Getters
        public String getRole() {
            return role;
        }

        public String getDepartment() {
            return department;
        }

        public String getEmployeeId() {
            return employeeId;
        }

        public double getSalary() {
            return salary;
        }

        // Setters
        public void setRole(String role) {
            this.role = role;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public void setEmployeeId(String employeeId) {
            this.employeeId = employeeId;
        }

        public void setSalary(double salary) {
            this.salary = salary;
        }

        @Override
        public void afisare() {
            super.afisare();
            System.out.println("Employee ID: " + employeeId);
            System.out.println("Role: " + role);
            System.out.println("Department: " + department);
            System.out.println("Salary: " + salary);
        }

        public List<Models.Angajat> getAngajati() {
            return angajati;
        }

        public void setAngajati(List<Models.Angajat> angajati) {
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

        // Models.Angajat methods
        public void adaugaAngajat(Models.Angajat angajat) {
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
            for (Models.Angajat angajat : angajati) {
                angajat.afisare();
            }
        }

        public Models.Angajat cautaAngajatDupaId(String id) {
            for (Models.Angajat a : angajati) {
                if (a.getId().equals(id)) {
                    return a;
                }
            }
            return null;
        }

        public void actualizeazaAngajat(Models.Angajat angajatNou) {
            for (int i = 0; i < angajati.size(); i++) {
                if (angajati.get(i).getId().equals(angajatNou.getId())) {
                    angajati.set(i, angajatNou);
                    return;
                }
            }
            System.out.println("Angajatul nu a fost gasit pentru actualizare.");
        }

        // Models.Bicicleta methods
        public void adaugaBicicleta(Bicicleta bicicleta) {
            if (cautaBicicletaDupaId(bicicleta.getId()) == null) {
                biciclete.add(bicicleta);
            } else {
                System.out.println("Models.Bicicleta cu acest ID exista deja.");
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
            System.out.println("Models.Bicicleta nu a fost gasita pentru actualizare.");
        }

        // Models.Client methods
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
    
}

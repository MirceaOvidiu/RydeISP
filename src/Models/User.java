package Models;

public class User {
    private String id;
    private String name;
    private String email;
    private String pass;

    // Default constructor
    public User() {
    }

    // All-args constructor
    public User(String id, String name, String email, String pass) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pass = pass;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void afisare() {
        System.out.println("Tests.Models.User ID: " + id);
        System.out.println("Tests.Models.User Name: " + name);
        System.out.println("Tests.Models.User Email: " + email);
    }
}

package Models;

public enum Roluri {
    USER("User"),
    ADMIN("Admin"),
   CLIENT("Client");

    private final String rol;

    Roluri(String rol) {
        this.rol = rol;
    }

    public String getRol() {
        return rol;
    }
}

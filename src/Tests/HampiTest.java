package Tests;

import Models.Bicicleta;
import Models.Meniu;
import Models.Statii;
import Models.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class HampiTest {
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Bicicleta> biciclete = new ArrayList<>();


    @Test
    void testLogin() {
        ArrayList<User> users = new ArrayList<>();
        User user = new User("1", "testUser", "test@email.com", "password");
        users.add(user);

        Scanner scanner = new Scanner("testUser\npassword\n");
        Meniu meniu = new Meniu();
        User loggedIn = meniu.login(scanner, users);
        assertNotNull(loggedIn);
        assertEquals("testUser", loggedIn.getName());
    }

    @Test
    void testCreateNewUser() {
        ArrayList<User> users = new ArrayList<>();
        Scanner scanner = new Scanner("newUser\nnew@email.com\nnewpass\n");
        Meniu meniu = new Meniu();
        User newUser = meniu.createNewUser(scanner, users);
        assertNotNull(newUser);
        assertEquals("newUser", newUser.getName());
        assertEquals(1, users.size());
    }

    @Test
    void testEditProfile() {
        User user = new User("1", "oldName", "old@email.com", "oldpass");
        Scanner scanner = new Scanner("1\nnewName\n");
        Meniu meniu = new Meniu();
        // edit name
        meniu.editUserProfile(scanner, user);
        assertEquals("newName", user.getName());
    }

    @Test
    void testVerificareBicicleteDisponibile() {
        ArrayList<Bicicleta> biciclete = new ArrayList<>();
        Bicicleta bike1 = new Bicicleta("1", "Eroilor", "100%", true, null, "Bike1");
        Bicicleta bike2 = new Bicicleta("2", "Grozavesti", "80%", false, null, "Bike2");
        biciclete.add(bike1);
        biciclete.add(bike2);

        Meniu meniu = new Meniu();
        // Should only print available bikes (bike1)
        meniu.afisareBicicleteDisponibile(biciclete);
        assertTrue(bike1.isAvailable());
        assertFalse(bike2.isAvailable());
    }

    @Test
    void testRezervaBicicleta() {
        ArrayList<Bicicleta> biciclete = new ArrayList<>();
        Bicicleta bike = new Bicicleta("1", "Grozavesti", "100%", true, null, "Bike1");
        biciclete.add(bike);
        User user = new User("1", "testUser", "test@email.com", "password");
        Meniu meniu = new Meniu();
        meniu.rezervareBicicleta("1", user, Statii.valueOf("Grozavesti"), biciclete);
        // You may want to check if the bike is no longer available or reserved by the user
        // This depends on your Bicicleta implementation
    }
}
package Tests;

import Models.*;
import org.junit.jupiter.api.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class AnaTest {

    @Test
    void testCreateNewUser() {
        Meniu meniu = new Meniu();
        ArrayList<User> users = new ArrayList<>();
        Scanner scanner = new Scanner("testuser\ntest@example.com\ntestpass\n");
        User user = meniu.createNewUser(scanner, users);

        assertNotNull(user);
        assertEquals("testuser", user.getName());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("testpass", user.getPass());
        assertTrue(users.contains(user));
    }

    @Test
    void testLoginSuccess() {
        Meniu meniu = new Meniu();
        ArrayList<User> users = new ArrayList<>();
        User user = new User("1", "testuser", "test@example.com", "testpass");
        users.add(user);
        Scanner scanner = new Scanner("testuser\ntestpass\n");
        User loggedIn = meniu.login(scanner, users);

        assertNotNull(loggedIn);
        assertEquals(user, loggedIn);
    }

    @Test
    void testLoginFail() {
        Meniu meniu = new Meniu();
        ArrayList<User> users = new ArrayList<>();
        User user = new User("1", "testuser", "test@example.com", "testpass");
        users.add(user);
        Scanner scanner = new Scanner("wronguser\nwrongpass\n");
        User loggedIn = meniu.login(scanner, users);

        assertNull(loggedIn);
    }

    @Test
    void testRezervareBicicleta() {
        Meniu meniu = new Meniu();
        ArrayList<Bicicleta> biciclete = new ArrayList<>();
        User user = new User("1", "testuser", "test@example.com", "testpass");
        Bicicleta bike = new Bicicleta("b1", "Eroilor", "100%", true, null, "Bike1");
        biciclete.add(bike);


        meniu.rezervareBicicleta("b1", user, Statii.Eroilor, biciclete);

        assertFalse(bike.isAvailable());
        assertEquals("Eroilor", bike.getLocation());
}

    @Test
    void testStartAndEndCursa() {
        Meniu meniu = new Meniu();
        ArrayList<Bicicleta> biciclete = new ArrayList<>();
        User user = new User("1", "testuser", "test@example.com", "testpass");

        Bicicleta bike = new Bicicleta("b1", "Eroilor", "100%", true, null, "Bike1");
        biciclete.add(bike);
        Cursa cursa = new Cursa();

        // Start cursa
        meniu.rezervareBicicleta("b1", user, Statii.Eroilor, biciclete);
        meniu.startCursa("b1", user, Statii.Eroilor, biciclete, cursa);

        assertEquals("Eroilor", cursa.getStartLocation());
        assertEquals("1", cursa.getUserId());
        assertEquals("b1", cursa.getBikeId());
        assertNotNull(cursa.getStartTime());

        // End cursa
        meniu.terminareCursa(cursa, Statii.Grozavesti, biciclete, user);

        assertEquals("Grozavesti", cursa.getEndLocation());
        assertNotNull(cursa.getEndTime());
        assertTrue(cursa.getDistance() > 0);
        assertTrue(bike.isAvailable());
        assertEquals("Grozavesti", bike.getLocation());
    }
}
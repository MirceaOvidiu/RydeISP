package Tests;

import Models.*;
import org.junit.jupiter.api.*;
import java.util.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

public class MirceaTests {

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

    @Test
    void testCompleteUserRentalFlow() {
        // Setup
        Meniu meniu = new Meniu();
        ArrayList<Bicicleta> biciclete = new ArrayList<>();
        ArrayList<User> users = new ArrayList<>();

        // Create new user
        Scanner scannerCreate = new Scanner("testuser\ntest@example.com\ntestpass\n");
        User createdUser = meniu.createNewUser(scannerCreate, users);
        assertNotNull(createdUser);
        assertEquals("testuser", createdUser.getName());
        assertTrue(users.contains(createdUser));

        // Login
        Scanner scannerLogin = new Scanner("testuser\ntestpass\n");
        User loggedInUser = meniu.login(scannerLogin, users);
        assertNotNull(loggedInUser);
        assertEquals(createdUser, loggedInUser);

        // Setup test bike
        Bicicleta bike = new Bicicleta("b1", "Eroilor", "100%", true, null, "Bike1");
        biciclete.add(bike);

        // Capture and verify bike list output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            meniu.afisareBicicleteDisponibile(biciclete);
            String output = outContent.toString().toLowerCase();
        } finally {
            System.setOut(originalOut);
        }

        // Start rental process
        Cursa cursa = new Cursa();

        // Reserve bike
        meniu.rezervareBicicleta("b1", loggedInUser, Statii.Eroilor, biciclete);
        assertFalse(bike.isAvailable());
        assertEquals(loggedInUser, bike.getUser());

        // Start ride
        meniu.startCursa("b1", loggedInUser, Statii.Eroilor, biciclete, cursa);
        assertEquals("Eroilor", cursa.getStartLocation());
        assertEquals(loggedInUser.getId(), cursa.getUserId());
        assertEquals("b1", cursa.getBikeId());
        assertNotNull(cursa.getStartTime());

        // End ride
        meniu.terminareCursa(cursa, Statii.Grozavesti, biciclete, loggedInUser);
        assertEquals("Grozavesti", cursa.getEndLocation());
        assertNotNull(cursa.getEndTime());
        assertTrue(cursa.getDistance() > 0);
        assertTrue(bike.isAvailable());
        assertEquals("Grozavesti", bike.getLocation());
    }
}
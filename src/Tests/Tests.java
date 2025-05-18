package Tests;

import Models.Angajat;
import Models.Meniu;
import Models.User;
import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {
    private Meniu meniu;
    private ArrayList<User> users;
    private final InputStream systemIn = System.in;

    @BeforeEach
    void setUp() {
        meniu = new Meniu();
        users = new ArrayList<>();
    }

    @AfterEach
    void restoreSystemInput() {
        System.setIn(systemIn);
    }

    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @Test
    void testCreateNewUser() {
        // Arrange
        int initialSize = users.size();
        String id = (initialSize + 1) + "";;

        // Create and set up employee directly

        // Modify user params here.
        // empty name or password are illegal arguments
        User newUser = new User(id,"testUser", "test@email.com", "password");
        users.add(newUser);

        // Assert
        assertNotNull(newUser);
        assertFalse(newUser.getName().isEmpty());
        assertEquals("testUser", newUser.getName());
        assertEquals("test@email.com", newUser.getEmail());
        assertFalse(newUser.getPass().isEmpty());
        assertEquals("password", newUser.getPass());
        assertEquals(String.valueOf(initialSize + 1), newUser.getId());
        assertEquals(initialSize + 1, users.size());
    }

    @Test
    void testCreateEmployee() {
        // Arrange
        int initialSize = users.size();

        // Create and set up employee directly
        Angajat employee = new Angajat();
        employee.setName("testEmployee");
        employee.setEmail("emp@email.com");
        employee.setPass("pass123");
        employee.setEmployeeId("EMP001");
        employee.setRole("Manager");
        employee.setDepartment("HR");
        employee.setSalary(50000.0);
        employee.setId(String.valueOf(initialSize + 1));

        users.add(employee);

        // Assert
        /// Empty username/passwd causes AssertionFailed
        assertFalse(employee.getName().isEmpty(), "Employee name should not be empty");
        assertEquals("testEmployee", employee.getName());

        assertEquals("emp@email.com", employee.getEmail());
        assertFalse(employee.getPass().isEmpty(), "Employee passwd should not be empty");
        assertEquals("pass123", employee.getPass());
        assertEquals("EMP001", employee.getEmployeeId());
        assertEquals("Manager", employee.getRole());
        assertEquals("HR", employee.getDepartment());
        assertEquals(50000.0, employee.getSalary(), 0.01);
    }

}
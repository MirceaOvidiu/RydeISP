package Tests;

import Models.*;

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
    public void setUp() {
        meniu = new Meniu();
        users = new ArrayList<>();
    }

    @AfterEach
    public void restoreSystemInput() {
        System.setIn(systemIn);
    }

    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @Test
    public void testCreateNewUser() {
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
        Assertions.assertFalse(newUser.getName().isEmpty());
        Assertions.assertEquals("testUser", newUser.getName());
        Assertions.assertEquals("test@email.com", newUser.getEmail());
        Assertions.assertFalse(newUser.getPass().isEmpty());
        Assertions.assertEquals("password", newUser.getPass());
        Assertions.assertEquals(String.valueOf(initialSize + 1), newUser.getId());
        assertEquals(initialSize + 1, users.size());
    }

    @Test
    public void testCreateEmployee() {
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
        Assertions.assertFalse(employee.getName().isEmpty(), "Employee name should not be empty");
        Assertions.assertEquals("testEmployee", employee.getName());

        Assertions.assertEquals("emp@email.com", employee.getEmail());
        Assertions.assertFalse(employee.getPass().isEmpty(), "Employee password should not be empty");
        Assertions.assertEquals("pass123", employee.getPass());
        Assertions.assertEquals("EMP001", employee.getEmployeeId());
        Assertions.assertEquals("Manager", employee.getRole());
        Assertions.assertEquals("HR", employee.getDepartment());
        Assertions.assertEquals(50000.0, employee.getSalary(), 0.01);
    }

}
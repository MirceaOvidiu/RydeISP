import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

public class Tests {
    private Meniu meniu;
    private ArrayList<User> users;
    private final InputStream systemIn = System.in;
    private ByteArrayInputStream testIn;

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
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @Test
    void testCreateNewUser() {
        // Arrange
        String input = "testUser\ntest@email.com\npassword123\n";
        provideInput(input);
        Scanner scanner = new Scanner(System.in);
        int initialSize = users.size();

        // Act
        User newUser = meniu.createNewUser(scanner, users);

        // Assert
        assertNotNull(newUser);
        assertEquals("testUser", newUser.getName());
        assertEquals("test@email.com", newUser.getEmail());
        assertEquals("password123", newUser.getPass());
        assertEquals(String.valueOf(initialSize + 1), newUser.getId());
        assertEquals(initialSize + 1, users.size());
    }

    @Test
    void testInvalidUserCreation() {
        // Arrange
        String input = "\n\ntest@email.com\npass123\n";
        provideInput(input);
        Scanner scanner = new Scanner(System.in);
        int initialSize = users.size();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            meniu.createNewUser(scanner, users);
        });
        assertEquals(initialSize, users.size(), "User list size should not change when creation fails");
    }

    @Test
    void testCreateEmployee() {
        // Arrange
        String input = "empUser\nemp@email.com\npass123\nEMP001\nManager\nHR\n50000.0\n";
        provideInput(input);
        Scanner scanner = new Scanner(System.in);
        int initialSize = users.size();

        // Act
        meniu.createEmployee(scanner, users);

        // Assert
        assertEquals(initialSize + 1, users.size());
        User lastUser = users.get(users.size() - 1);
        assertTrue(lastUser instanceof Angajat);
        Angajat employee = (Angajat) lastUser;

        assertEquals("empUser", employee.getName());
        assertEquals("emp@email.com", employee.getEmail());
        assertEquals("pass123", employee.getPass());
        assertEquals("EMP001", employee.getEmployeeId());
        assertEquals("Manager", employee.getRole());
        assertEquals("HR", employee.getDepartment());
        assertEquals(50000.0, employee.getSalary(), 0.01);
    }

    @Test
    void testInvalidEmployeeCreation() {
        // Arrange
        String input = "\n\nemp@email.com\npass123\nEMP001\nManager\nHR\n50000.0\n";
        provideInput(input);
        Scanner scanner = new Scanner(System.in);
        int initialSize = users.size();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            meniu.createEmployee(scanner, users);
        });
        assertEquals(initialSize, users.size(), "User list size should not change when creation fails");
    }
}
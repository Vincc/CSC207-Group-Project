package interface_adapter.cancel;

import org.junit.Test;

import static org.junit.Assert.*;

public class CancelStateTest {

    @Test
    public void testGetUsername() {
        // Arrange
        CancelState cancelState = new CancelState();
        String expectedUsername = "testUser";

        // Act
        cancelState.setUsername(expectedUsername);
        String actualUsername = cancelState.getUsername();

        // Assert
        assertEquals(expectedUsername, actualUsername);
    }

    @Test
    public void testCopyConstructor() {
        // Arrange
        CancelState originalState = new CancelState();
        originalState.setUsername("originalUser");

        // Act
        CancelState copiedState = new CancelState(originalState);

        // Assert
        assertEquals(originalState.getUsername(), copiedState.getUsername());
    }

    @Test
    public void testDefaultConstructor() {
        // Arrange
        CancelState cancelState = new CancelState();

        // Act and Assert
        assertEquals("", cancelState.getUsername());
    }

}
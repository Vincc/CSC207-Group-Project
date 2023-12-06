package interface_adapter.login;

import org.junit.Test;

import static org.junit.Assert.*;

public class LoginStateTest {
    @Test
    public void testDefaultConstructor() {
        LoginState state = new LoginState();
        assertEquals("", state.getUsername());
        assertNull(state.getUsernameError());
        assertEquals("", state.getPassword());
        assertNull(state.getPasswordError());
    }

    @Test
    public void testCopyConstructor() {
        LoginState original = new LoginState();
        original.setUsername("user123");
        original.setUsernameError("Invalid username");
        original.setPassword("password123");
        original.setPasswordError("Invalid password");

        LoginState copy = new LoginState(original);
        assertEquals("user123", copy.getUsername());
        assertEquals("Invalid username", copy.getUsernameError());
        assertEquals("password123", copy.getPassword());
        assertEquals("Invalid password", copy.getPasswordError());
    }

    @Test
    public void testSettersAndGetters() {
        LoginState state = new LoginState();
        state.setUsername("newUser");
        assertEquals("newUser", state.getUsername());
        state.setUsernameError("Username error");
        assertEquals("Username error", state.getUsernameError());
        state.setPassword("newPass");
        assertEquals("newPass", state.getPassword());
        state.setPasswordError("Password error");
        assertEquals("Password error", state.getPasswordError());
    }

}
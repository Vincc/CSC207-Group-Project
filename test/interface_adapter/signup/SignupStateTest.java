package interface_adapter.signup;

import org.junit.Test;

import static org.junit.Assert.*;

public class SignupStateTest {
    public void testGettersAndSetters() {
        SignupState state = new SignupState();

        state.setUsername("testUser");
        state.setPassword("testPass");
        state.setRepeatPassword("testPassRepeat");
        state.setUsernameError("usernameError");
        state.setPasswordError("passwordError");
        state.setRepeatPasswordError("repeatPasswordError");

        assertEquals("testUser", state.getUsername());
        assertEquals("testPass", state.getPassword());
        assertEquals("testPassRepeat", state.getRepeatPassword());
        assertEquals("usernameError", state.getUsernameError());
        assertEquals("passwordError", state.getPasswordError());
        assertEquals("repeatPasswordError", state.getRepeatPasswordError());
    }

    @Test
    public void testCopyConstructor() {
        SignupState original = new SignupState();
        original.setUsername("originalUser");
        original.setPassword("originalPass");
        original.setRepeatPassword("originalPassRepeat");
        original.setUsernameError("originalUsernameError");
        original.setPasswordError("originalPasswordError");
        original.setRepeatPasswordError("originalRepeatPasswordError");

        SignupState copy = new SignupState(original);

        assertEquals("originalUser", copy.getUsername());
        assertEquals("originalPass", copy.getPassword());
        assertEquals("originalPassRepeat", copy.getRepeatPassword());
        assertEquals("originalUsernameError", copy.getUsernameError());
        assertEquals("originalPasswordError", copy.getPasswordError());
        assertEquals("originalRepeatPasswordError", copy.getRepeatPasswordError());
    }
}
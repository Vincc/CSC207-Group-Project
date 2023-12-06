package interface_adapter.createProfile;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CreateProfileStateTest {

    private CreateProfileState createProfileState;

    @Before
    public void setUp() {
        createProfileState = new CreateProfileState();
    }

    @Test
    public void testDefaultUsernameIsEmpty() {
        assertEquals("", createProfileState.getUsername());
    }

    @Test
    public void testSettingUsername() {
        String expectedUsername = "testUser";
        createProfileState.setUsername(expectedUsername);
        assertEquals(expectedUsername, createProfileState.getUsername());
    }

    @Test
    public void testDefaultUserDescriptionIsEmpty() {
        assertEquals("", createProfileState.getUserDescription());
    }

    @Test
    public void testSettingUserDescription() {
        String expectedDescription = "Test Description";
        createProfileState.setUserDescription(expectedDescription);
        assertEquals(expectedDescription, createProfileState.getUserDescription());
    }

}
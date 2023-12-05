package use_case.EditProfile;

import junit.framework.TestCase;

public class EditProfileInputDataTest extends TestCase {

    String username = "user1";
    String description = "good loyal user";

    EditProfileInputData editProfileInputData = new EditProfileInputData(username,description);

    public void testGetUsername() {
        assertEquals(editProfileInputData.getUsername(),username);
    }

    public void testGetUserDescription() {
        assertEquals(editProfileInputData.getUserDescription(),description);
    }
}
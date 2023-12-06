package use_case.CreateProfile;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CreateProfileInputDataTest {
    @Test
    public void testCreateProfileInputData() {

        String username = "Bob";

        String user_description = "I am nice";

        CreateProfileInputData data = new CreateProfileInputData(username, user_description);

        assertEquals(username, data.getUsername());

        assertEquals(user_description, data.getUserDescription());
    }
}

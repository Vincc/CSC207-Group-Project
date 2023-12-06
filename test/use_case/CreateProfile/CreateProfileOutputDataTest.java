package use_case.CreateProfile;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CreateProfileOutputDataTest {
    @Test
    public void testCreateProfileOutputData(){
        String username = "Yoav";
        String user_description = "I'm cool";
        CreateProfileOutputData data = new CreateProfileOutputData(username, user_description);
        assertEquals(username, data.getUsername());
        assertEquals(user_description, data.getUserDescription());
    }
}

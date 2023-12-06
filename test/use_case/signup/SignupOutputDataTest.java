package use_case.signup;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class SignupOutputDataTest {
    @Test
    public void testSignupOutputData() {
        String username = "anonymous";

        String creationTime = LocalDateTime.now().toString();

        boolean useCaseFailed = false;

        SignupOutputData data = new SignupOutputData(username, creationTime, useCaseFailed);

        assertEquals(username, data.getUsername());

        assertEquals(creationTime, data.getCreationTime());

    }
}

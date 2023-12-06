package use_case.signup;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SignupInputDataTest {
    @Test
    public void testSignupInputData() {

        String username = "User207";

        String password = "best207";

        String repeat_password = "best207";

        SignupInputData data = new SignupInputData(username, password, repeat_password);

        assertEquals(username, data.getUsername());

        assertEquals(password, data.getPassword());

        assertEquals(repeat_password, data.getRepeatPassword());
    }
}

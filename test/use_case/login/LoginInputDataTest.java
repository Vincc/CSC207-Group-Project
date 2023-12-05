package use_case.login;

import junit.framework.TestCase;

public class LoginInputDataTest extends TestCase {

    String username = "user1";
    String password = "abra_kadabra";
    LoginInputData loginInputData = new LoginInputData(username,password);

    public void testGetUsername() {
        assertEquals(loginInputData.getUsername(),username);
    }

    public void testGetPassword() {
        assertEquals(loginInputData.getPassword(),password);
    }
}
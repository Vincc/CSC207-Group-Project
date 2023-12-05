package use_case.login;

import junit.framework.TestCase;

public class LoginOutputDataTest extends TestCase {
    String username = "Gandalf the grey";

    Boolean what = Boolean.TRUE;
    LoginOutputData loginOutputData = new LoginOutputData(username,what);
    public void testGetUsername() {
        assertEquals(loginOutputData.getUsername() ,username);
    }
}
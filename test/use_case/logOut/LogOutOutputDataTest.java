package use_case.logOut;

import junit.framework.TestCase;

public class LogOutOutputDataTest extends TestCase {
    LogOutOutputData logOutOutputData = new LogOutOutputData();
    public void test(){
        assertTrue(logOutOutputData instanceof  LogOutOutputData);
    }

}
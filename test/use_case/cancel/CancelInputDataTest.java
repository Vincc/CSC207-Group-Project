package use_case.cancel;

import org.junit.Test;

import static org.junit.Assert.*;

public class CancelInputDataTest {
    @Test
    public void testCancelInputData() {
        String username = "username";
        CancelInputData cancelInputData = new CancelInputData(username);
        assertEquals(username, cancelInputData.getUsername());
    }

}

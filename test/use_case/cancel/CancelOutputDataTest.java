package use_case.cancel;

import org.junit.Test;

import static org.junit.Assert.*;

public class CancelOutputDataTest {
    @Test
    public void testCancelInputData() {
        String username = "username";
        CancelOutputData cancelOutputData = new CancelOutputData(username);
        assertEquals(username, cancelOutputData.getUsername());
    }
}

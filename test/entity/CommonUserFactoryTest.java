package entity;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class CommonUserFactoryTest {
    private CommonUserFactory userFactory;

    @Before
    public void setUp() {
        userFactory = new CommonUserFactory();
    }

    @Test
    public void testCreate() {
        String name = "user1";
        String password = "password1";
        LocalDateTime ltd = LocalDateTime.of(2023, 11, 30, 19, 30, 5);

        User user = userFactory.create(name, password, ltd);

        assertEquals(name, user.getName());
        assertEquals(password, user.getPassword());
        assertEquals(ltd, user.getCreationTime());
    }

}
package data_access;

import entity.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class FileUserDataAccessObjectTest {
    private FileUserDataAccessObject userDAO;

    @Before
    public void setUp() throws IOException {
        String jsonPathUserTest = "test_users.json";
        UserFactory userFactory = new CommonUserFactory();

        userDAO = new FileUserDataAccessObject(jsonPathUserTest, userFactory);

    }

    @Test
    public void save() {

        UserFactory userFactory2 = new CommonUserFactory();

        User user1 = userFactory2.create("user1","password1", LocalDateTime.of(2023,11,30,11,30,5));
        User user2 = userFactory2.create("user2","password2", LocalDateTime.of(2023,11,29,10,25,5));

        userDAO.save(user1);
        userDAO.save(user2);

        assertEquals(user1,userDAO.get("user1"));
        assertEquals(user2,userDAO.get("user2"));

    }

    @Test
    public void existsByName() {
        assertTrue(userDAO.existsByName("user1"));
        assertTrue(userDAO.existsByName("user2"));

        assertFalse(userDAO.existsByName("user3"));

    }
}
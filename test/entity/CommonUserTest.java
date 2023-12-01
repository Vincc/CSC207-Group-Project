package entity;

import entity.User;
import org.junit.Before;
import use_case.login.LoginUserDataAccessInterface;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class CommonUserTest {

    private CommonUser commonUser;

    @Before
    public void setUp() {
        commonUser = new CommonUser("user1", "password1",
                LocalDateTime.of(2023, 11, 30, 19, 30, 5));
    }


    @org.junit.Test
    public void getName() {
        assertEquals("user1", commonUser.getName());
    }

    @org.junit.Test
    public void getPassword() {
        assertEquals("password1", commonUser.getPassword());
    }

    @org.junit.Test
    public void getCreationTime() {
        assertEquals(LocalDateTime.of(2023, 11, 30, 19, 30, 5),commonUser.getCreationTime());
    }

    @org.junit.Test
    public void getJoinedEvents(){
        assertEquals(new ArrayList<String>(), commonUser.getJoinedEvents());
    }

    @org.junit.Test
    public void addEvent() {
        commonUser.addEvent("event1");
        commonUser.addEvent("event2");
        ArrayList<String> expected = new ArrayList<>();
        expected.add("event1");
        expected.add("event2");
        assertEquals(expected, commonUser.getJoinedEvents());

    }

    @org.junit.Test
    public void getUserDescription() {
        assertEquals("", commonUser.getUserDescription());
    }

    @org.junit.Test
    public void getLocation(){
        assertEquals("",commonUser.getLocation());
    }

    @org.junit.Test
    public void setLocation(){
        commonUser.setLocation("Toronto");
        assertEquals("Toronto", commonUser.getLocation());
    }


    @org.junit.Test
    public void setUserDescription(){
        commonUser.setUserDescription("user1's description");
        assertEquals("user1's description",commonUser.getUserDescription());
    }


}
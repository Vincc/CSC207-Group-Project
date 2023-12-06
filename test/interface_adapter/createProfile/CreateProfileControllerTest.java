package interface_adapter.createProfile;

import org.junit.Before;
import org.junit.Test;
import use_case.CreateProfile.CreateProfileInputBoundary;
import use_case.CreateProfile.CreateProfileInputData;
import use_case.EditProfile.EditProfileInputBoundary;
import use_case.EditProfile.EditProfileInputData;

import static org.junit.Assert.*;

public class CreateProfileControllerTest {
    private CreateProfileController createProfileController;
    private MockCreateProfileInputBoundary mockInputBoundary;

    private MockEditProfileInputBoundary mockEditProfileInputBoundary;


    @Before
    public void setUp() {
        // Create a mock input boundary for testing
        mockInputBoundary = new MockCreateProfileInputBoundary();
        mockEditProfileInputBoundary = new MockEditProfileInputBoundary();

        createProfileController = new CreateProfileController(mockInputBoundary, mockEditProfileInputBoundary);
    }

    @Test
    public void testExecuteCreateProfile() {
        // Arrange
        String username = "testUser";
        String userDescription = "Test description";

        // Act
        createProfileController.executeCreateProfile(username, userDescription);

        // Assert
        assertEquals(username, mockInputBoundary.getUsername());
        assertEquals(userDescription, mockInputBoundary.getUserDescription());
    }


    @Test
    public void testExecuteEditProfile(){
        createProfileController.executeEditProfile("u1","abcdefg");
        assertEquals("u1", mockEditProfileInputBoundary.getUsername());
        assertEquals("abcdefg", mockEditProfileInputBoundary.getUserDescription());
    }

    // Mock class to simulate CreateProfileInputBoundary for testing
    private static class MockCreateProfileInputBoundary implements CreateProfileInputBoundary {
        private String username;
        private String userDescription;


        @Override
        public void execute(CreateProfileInputData input) {
            this.username = input.getUsername();
            this.userDescription = input.getUserDescription();
        }

        @Override
        public void execute() {
        }

        public String getUsername() {
            return username;
        }

        public String getUserDescription() {
            return userDescription;
        }
    }

    private class MockEditProfileInputBoundary implements EditProfileInputBoundary{

        private String username;
        private String userdescription;

        @Override
        public void execute(EditProfileInputData editProfileInputData) {
            this.username = editProfileInputData.getUsername();
            this.userdescription = editProfileInputData.getUserDescription();
        }

        public String getUsername() {
            return username;
        }

        public String getUserDescription() {
            return userdescription;
        }
    }



}
package interface_adapter.createProfile;

import org.junit.Before;
import org.junit.Test;
import use_case.CreateProfile.CreateProfileInputBoundary;
import use_case.CreateProfile.CreateProfileInputData;

import static org.junit.Assert.*;

public class CreateProfileControllerTest {
    private CreateProfileController createProfileController;
    private MockCreateProfileInputBoundary mockInputBoundary;

    @Before
    public void setUp() {
        // Create a mock input boundary for testing
        mockInputBoundary = new MockCreateProfileInputBoundary();
        createProfileController = new CreateProfileController(mockInputBoundary);
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

    // Mock class to simulate CreateProfileInputBoundary for testing
    private static class MockCreateProfileInputBoundary implements CreateProfileInputBoundary {
        private String username;
        private String userDescription;

        @Override
        public void execute(CreateProfileInputData input) {
            this.username = input.getUsername();
            this.userDescription = input.getUserDescription();
        }

        public String getUsername() {
            return username;
        }

        public String getUserDescription() {
            return userDescription;
        }
    }

}
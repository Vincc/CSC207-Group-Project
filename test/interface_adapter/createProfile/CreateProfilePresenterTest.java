package interface_adapter.createProfile;

import interface_adapter.ViewManagerModel;
import org.junit.Before;
import org.junit.Test;
import use_case.CreateProfile.CreateProfileOutputData;

import static org.junit.Assert.*;

public class CreateProfilePresenterTest {
    private ViewManagerModelStub viewManagerModelStub;
    private CreateProfileViewModelStub createProfileViewModelStub;
    private CreateProfilePresenter presenter;

    private class ViewManagerModelStub extends ViewManagerModel {
        String activeViewName;

        @Override
        public void setActiveView(String viewName) {
            this.activeViewName = viewName;
        }

        @Override
        public void firePropertyChanged() {
            // Implement this method if necessary for the test
        }
    }

    private class CreateProfileViewModelStub extends CreateProfileViewModel {
        CreateProfileState state;
        boolean propertyChangedFired = false;

        @Override
        public CreateProfileState getState() {
            return state;
        }

        @Override
        public void setState(CreateProfileState state) {
            this.state = state;
        }

        @Override
        public void firePropertyChanged() {
            propertyChangedFired = true;
        }
    }

    @Before
    public void setUp() {
        viewManagerModelStub = new ViewManagerModelStub();
        createProfileViewModelStub = new CreateProfileViewModelStub();
        createProfileViewModelStub.setState(new CreateProfileState());
        presenter = new CreateProfilePresenter(viewManagerModelStub, createProfileViewModelStub);
    }

    @Test
    public void testPrepareSuccessView() {
        // Arrange
        CreateProfileOutputData profileOutputData = new CreateProfileOutputData("username", "description");

        // Act
        presenter.prepareSuccessView(profileOutputData);

        // Assert
        assertEquals("username", createProfileViewModelStub.getState().getUsername());
        assertEquals("description", createProfileViewModelStub.getState().getUserDescription());
        assertTrue(createProfileViewModelStub.propertyChangedFired);
        assertEquals(createProfileViewModelStub.getViewName(), viewManagerModelStub.activeViewName);
    }

}
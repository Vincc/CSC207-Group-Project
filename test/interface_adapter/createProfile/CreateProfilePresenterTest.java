package interface_adapter.createProfile;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import org.junit.Before;
import org.junit.Test;
import use_case.CreateProfile.CreateProfileOutputData;

import static org.junit.Assert.*;

public class CreateProfilePresenterTest {
    private CreateProfilePresenter presenter;
    private StubViewManagerModel viewManagerModel;
    private StubCreateProfileViewModel createProfileViewModel;
    private StubLoggedInViewModel loggedInViewModel;

    @Before
    public void setUp() {
        viewManagerModel = new StubViewManagerModel();
        createProfileViewModel = new StubCreateProfileViewModel();
        loggedInViewModel = new StubLoggedInViewModel();
        presenter = new CreateProfilePresenter(viewManagerModel, createProfileViewModel, loggedInViewModel);
    }

    @Test
    public void testPrepareSuccessViewWithProfileData() {
        CreateProfileOutputData profileOutputData = new CreateProfileOutputData("u1","abc");

        presenter.prepareSuccessView(profileOutputData);

        assertTrue(createProfileViewModel.wasSetStateCalled);
        assertTrue(createProfileViewModel.wasFirePropertyChangedCalled);
        assertEquals(createProfileViewModel.getViewName(), viewManagerModel.activeViewName);
        assertTrue(viewManagerModel.wasFirePropertyChangedCalled);
    }

    @Test
    public void testPrepareSuccessView() {
        presenter.prepareSuccessView();

        assertTrue(loggedInViewModel.wasSetStateCalled);
        assertTrue(loggedInViewModel.wasFirePropertyChangedCalled);
        assertEquals(loggedInViewModel.getViewName(), viewManagerModel.activeViewName);
        assertTrue(viewManagerModel.wasFirePropertyChangedCalled);
    }

    // Stub classes
    class StubViewManagerModel extends ViewManagerModel {
        boolean wasFirePropertyChangedCalled = false;
        String activeViewName = "";

        @Override
        public void setActiveView(String viewName) {
            this.activeViewName = viewName;
        }

        @Override
        public void firePropertyChanged() {
            wasFirePropertyChangedCalled = true;
        }
    }

    class StubCreateProfileViewModel extends CreateProfileViewModel {
        boolean wasSetStateCalled = false;
        boolean wasFirePropertyChangedCalled = false;

        @Override
        public void setState(CreateProfileState state) {
            wasSetStateCalled = true;
        }

        @Override
        public void firePropertyChanged() {
            wasFirePropertyChangedCalled = true;
        }
    }

    class StubLoggedInViewModel extends LoggedInViewModel {
        boolean wasSetStateCalled = false;
        boolean wasFirePropertyChangedCalled = false;

        @Override
        public void setState(LoggedInState state) {
            wasSetStateCalled = true;
        }

        @Override
        public void firePropertyChanged() {
            wasFirePropertyChangedCalled = true;
        }
    }

}
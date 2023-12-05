package use_case.logOut;

import junit.framework.TestCase;

public class LogOutInteractorTest extends TestCase {
    LogoOutOutputBoundary presenter = new LogoOutOutputBoundary() {
        @Override
        public void prepareSuccessView() {
            assertTrue(presenter instanceof  LogoOutOutputBoundary);
        }
    };
    public void testExecute() {
        LogOutInputBoundary interactor = new  LogOutInteractor(presenter);
        interactor.execute();
    }
}
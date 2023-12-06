package use_case.joinEventTest;

import data_access.FileEventDataAccessObject;
import data_access.FileUserDataAccessObject;
import entity.CommonSportsEventFactory;
import entity.CommonUserFactory;
import org.junit.Test;
import use_case.joinEvent.*;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

import java.io.IOException;

import static org.junit.Assert.*;

public class joinEventInteractorTest {
    @Test
    public void testExecute() {
        FileUserDataAccessObject userDataAccessObject;
        FileEventDataAccessObject EventDataAccessObject;
        joinEventDataAccessInterface joinEventDataAccessInterface;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./test_users.json", new CommonUserFactory());
            joinEventDataAccessInterface = new joinEventDataAccessInterface() {
                @Override
                public void addParticipant(String eventname, String username) {
                }
            };
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String eventname = "event1";
        String username = "user1";

        joinEventInputData joinEventInputData = new joinEventInputData(eventname, username);

        joinEventOutputBoundary joinEventSuccessPresenter = new joinEventOutputBoundary() {
            @Override
            public void prepareJoinEventSuccessView(joinEventOutputData user) {
                assertEquals(user.getUsername(), username);
                assertTrue(userDataAccessObject.existsByName(username));
            }
        };
        joinEventInputBoundary interactor = new joinEventInteractor(joinEventDataAccessInterface, joinEventSuccessPresenter);
        interactor.execute(joinEventInputData);
}}
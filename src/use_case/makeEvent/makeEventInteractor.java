package use_case.makeEvent;

import entity.SportsEvent;
import entity.SportsEventFactory;

public class makeEventInteractor implements makeEventInputBoundary{
    final makeEventDataAccessInterface eventDataAccessObject;
    final makeEventUserDataAccessInterface userDataAccessObject;
    final makeEventOutputBoundary createEventPresenter;
    final SportsEventFactory sportsEventFactory;

    public makeEventInteractor(makeEventDataAccessInterface eventDataAccessObject, makeEventUserDataAccessInterface userDataAccessObject, makeEventOutputBoundary createEventPresenter, SportsEventFactory sportsEventFactory) {
        this.eventDataAccessObject = eventDataAccessObject;
        this.userDataAccessObject = userDataAccessObject;
        this.createEventPresenter = createEventPresenter;
        this.sportsEventFactory = sportsEventFactory;
    }

    @Override
    public void execute(makeEventInputData makeEventInputData) {
        if (eventDataAccessObject.existsByName(makeEventInputData.getEventName())) {
            createEventPresenter.prepareMakeEventFailView("Event already exists.");
        } else {
            SportsEvent event = sportsEventFactory.create(makeEventInputData.getEventName(), makeEventInputData.getEventDate(), makeEventInputData.getEventEndDate(), makeEventInputData.getEventTime(), makeEventInputData.getEventEndTime(), makeEventInputData.getOrganiserName(), makeEventInputData.getEventMaxAttendance(), makeEventInputData.getEventLevel(), makeEventInputData.getLocation());
            eventDataAccessObject.save(event);
            eventDataAccessObject.addParticipant(event.getName(), event.getOrganizer());
            userDataAccessObject.addEvent(event.getName(), event.getOrganizer());
            createEventPresenter.prepareMakeEventSuccessView();
        }

    }
}

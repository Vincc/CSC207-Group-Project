package src.use_case.makeEvent;

import entity.SportsEvent;
import entity.SportsEventFactory;
import use_case.makeEvent.makeEventDataAccessInterface;
import use_case.makeEvent.makeEventInputBoundary;
import use_case.makeEvent.makeEventInputData;
import use_case.makeEvent.makeEventOutputBoundary;

public class makeEventInteractor implements makeEventInputBoundary {
    final makeEventDataAccessInterface eventDataAccessObject;
    final makeEventOutputBoundary createEventPresenter;
    final SportsEventFactory sportsEventFactory;

    public makeEventInteractor(makeEventDataAccessInterface eventDataAccessObject, makeEventOutputBoundary createEventPresenter, SportsEventFactory sportsEventFactory) {
        this.eventDataAccessObject = eventDataAccessObject;
        this.createEventPresenter = createEventPresenter;
        this.sportsEventFactory = sportsEventFactory;
    }

    @Override
    public void execute(makeEventInputData makeEventInputData) {
        if (eventDataAccessObject.existsByName(makeEventInputData.getEventName())) {
            createEventPresenter.prepareMakeEventFailView("Event already exists.");
        } else {
            SportsEvent event = sportsEventFactory.create(makeEventInputData.getEventName(), makeEventInputData.getEventDate(), makeEventInputData.getEventTime(), makeEventInputData.getEventLabel(), makeEventInputData.getEventMaxAttendance(), makeEventInputData.getEventLevel(), makeEventInputData.getLocation());
            eventDataAccessObject.save(event);

            createEventPresenter.prepareMakeEventSuccessView();
        }

    }
}

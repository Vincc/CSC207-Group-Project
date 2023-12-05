package use_case.addToCalendar;

import use_case.joinEvent.joinEventInputData;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface addToCalendarInputBoundary {
    void execute(joinEventInputData joinEventInputData)throws GeneralSecurityException, IOException;
}

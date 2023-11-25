package data_access;

import entity.CommonSportsEvent;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.time.LocalTime;

class GoogleCalendarApiDemo {
    public static void main(String[] args) throws GeneralSecurityException, IOException {
        GoogleCalendarApi api = new GoogleCalendarApi();
        CommonSportsEvent footballGame = new CommonSportsEvent(
                "organizer1",
                "toronto football game2",
                LocalDate.of(2023, 11, 22),
                LocalTime.of(17, 0),
                "Toronto2" ,
                7,
                "pro"
                );

        api.addToGoogleCalendar(footballGame);
    }
}
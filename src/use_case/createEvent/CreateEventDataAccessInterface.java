package use_case.createEvent;
import entity.sportsEvent;
import java.time.LocalDateTime;

public interface CreateEventDataAccessInterface {
    boolean eventExists(String name , String place , LocalDateTime date);
    void save(sportsEvent event);
}

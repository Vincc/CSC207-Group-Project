package src.entity;

import java.time.LocalDateTime;
import java.util.List;

public interface User {

    String getName();

    String getPassword();

    LocalDateTime getCreationTime();

    List<String> getJoinedEvents();

    String getUserDescription();

    void setUserDescription(String description);

    void addEvent(String event);

    String getLocation();

    void setLocation(String location);
}

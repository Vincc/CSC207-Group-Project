package data_access;

import entity.SportsEventFactory;
import entity.User;
import entity.UserFactory;
import entity.SportsEvent;
import use_case.createEvent.CreateEventDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileEventDataAccessObject implements CreateEventDataAccessInterface {


    private final File jsonFileEvent;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, SportsEvent> events = new HashMap<>();


    private UserFactory userFactory;

    private SportsEventFactory sportEventFactory;

    public FileEventDataAccessObject(String jsonPathEvent, UserFactory userFactory, SportsEventFactory sportEventFactory) throws IOException{

        this.userFactory = userFactory;
        this.sportEventFactory = sportEventFactory;

        jsonFileEvent = new File(jsonPathEvent);

        if (jsonFileEvent.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(jsonFileEvent))) {
                String header = reader.readLine();


                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String organizer = String.valueOf(col[headers.get("organizer")]);
                    String eventName = String.valueOf(col[headers.get("Event name")]);
                    String eventDescription = String.valueOf(col[headers.get("Event description")]);
                    String eventDatetime = String.valueOf(col[headers.get("Event datetime")]);
                    String eventLocation = String.valueOf(col[headers.get("Event location")]);
                    int maxAttendance = Integer.parseInt(col[headers.get("Max attendance")]);
                    String lvlOfPlay = String.valueOf(col[headers.get("level of play")]);

                    LocalDateTime ldt = LocalDateTime.parse(eventDatetime);


                    SportsEvent event = sportEventFactory.create(eventName, ldt, organizer, maxAttendance, lvlOfPlay, eventLocation);
                    event.setEventDescription(eventDescription);
                    events.put(eventName, event);
                }
            }
        }
    }


    public SportsEvent getSportEvent(String eventName){ return events.get(eventName);}


    public boolean eventExists(String name, String place, LocalDateTime date) {

        if (!events.containsKey(name)){
            return false;
        }
        else {
            SportsEvent event = events.get(name);
            return event.getLocation().equals(place) & event.getDate().equals(date);
        }

    }

    public void save(SportsEvent event) {
        events.put(event.getName(), event);
        this.save();

    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(jsonFileEvent));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (SportsEvent event : events.values()) {
                String line = String.format("%s,%s,%s,%s,%s,%s,%s",
                        event.getOrganizer(), event.getName(), event.getEventDescription(), event.getDate(), event.getLocation(),event.getMaxAttendance(),event.lvlofPlay());
                writer.write(line);
                writer.newLine();
            }
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

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


    private final File csvFileEvent;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, SportsEvent> events = new HashMap<>();


    private UserFactory userFactory;

    private SportsEventFactory sportEventFactory;

    public FileEventDataAccessObject(String csvPathEvent, UserFactory userFactory, SportsEventFactory sportEventFactory) throws IOException{

        this.userFactory = userFactory;
        this.sportEventFactory = sportEventFactory;

        csvFileEvent = new File(csvPathEvent);
        headers.put("organizer", 0);
        headers.put("Event name", 1);
        headers.put("Event description", 2);
        headers.put("Event datetime",3);
        headers.put("Event location",4);
        headers.put("Max attendance",5);
        headers.put("level of play",6);


        if (csvFileEvent.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFileEvent))) {
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


    @Override
    public boolean eventExists(String name, String place, LocalDateTime date) {

        if (!events.containsKey(name)){
            return false;
        }
        else {
            SportsEvent event = events.get(name);
            return event.getLocation().equals(place) & event.getDate().equals(date);
        }

    }

    @Override
    public void save(SportsEvent event) {
        events.put(event.getName(), event);
        this.save();

    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFileEvent));
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

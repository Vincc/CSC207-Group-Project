package data_access;

import entity.SportsEventFactory;
import entity.User;
import entity.UserFactory;
import entity.SportsEvent;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import use_case.createEvent.CreateEventDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FileEventDataAccessObject implements CreateEventDataAccessInterface {


    private final File jsonFileEvent;

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

            try (FileReader reader = new FileReader(jsonFileEvent)) {
                JSONParser parser = new JSONParser();

                Object obj = parser.parse(reader);
                JSONArray eventList = (JSONArray) obj;

                for(Object eventobj: eventList){
                    SportsEvent sportsEvent = parseEventObject((JSONObject) eventobj);
                    events.put(sportsEvent.getName(), sportsEvent);
                }


                //events.put(eventName, event);
                } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        }


    public SportsEvent getSportEvent(String eventName){ return events.get(eventName);}


    public boolean eventExists(String name) {
        return events.containsKey(name);
    }

    public void save(SportsEvent event) {
        events.put(event.getName(), event);
        this.save();

    }

    public void save() {
        try {
            FileWriter writer = new FileWriter(jsonFileEvent);
            JSONArray eventList = new JSONArray();


            for (SportsEvent sportsEvent : events.values()) {
                JSONObject eventObject = new JSONObject();

                eventObject.put("event name", sportsEvent.getName());
                eventObject.put("organizer", sportsEvent.getOrganizer());
                eventObject.put("event description",sportsEvent.getEventDescription() );
                LocalDateTime eventTime = sportsEvent.getDate();
                String formattedEventTime = eventTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                eventObject.put("event date", formattedEventTime);
                eventObject.put("event location", sportsEvent.getLocation());
                eventObject.put("max attendance", sportsEvent.getMaxAttendance());
                eventObject.put("level", sportsEvent.lvlofPlay());
                eventObject.put("attendance", sportsEvent.getAttendance());
                eventList.add(eventObject);

            }
            writer.write(eventList.toJSONString());
            writer.flush();

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private SportsEvent parseEventObject(JSONObject eventjson){
        String eventName = (String) eventjson.get("event name");
        String organizer = (String) eventjson.get("organizer");
        String eventDescription = (String) eventjson.get("event description");
        LocalDateTime eventDate = LocalDateTime.parse((String) eventjson.get("event date"));
        String location = (String) eventjson.get("event location");
        int maxAttendance = (int) eventjson.get("max attendance");
        String  level = (String) eventjson.get("level");

        SportsEvent sportsEvent = sportEventFactory.create(eventName, eventDate, organizer,maxAttendance, level,location);

        JSONArray attendanceArray = (JSONArray) eventjson.get("attendance");
        for(Object attendance : attendanceArray) {
            sportsEvent.addAttendance((String) attendance);
        }

        return sportsEvent;
    }

}

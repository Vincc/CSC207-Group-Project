package data_access;

import entity.SportsEventFactory;
import entity.User;
import entity.UserFactory;
import entity.SportsEvent;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import use_case.makeEvent.makeEventDataAccessInterface;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FileEventDataAccessObject implements makeEventDataAccessInterface {


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


    public boolean existsByName(String name) {
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
                LocalDate eventDate = sportsEvent.getDate();
                LocalTime eventTime = sportsEvent.getTime();
                String formattedEventDate = eventDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
                String formattedEventTime = eventTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
                LocalDate eventEndDate = sportsEvent.getEventEndDate();
                LocalTime eventEndTime = sportsEvent.getEventEndTime();
                String formattedEventEndDate = eventEndDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
                String formattedEventEndTime = eventEndTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
                eventObject.put("event date", formattedEventDate);
                eventObject.put("event time", formattedEventTime);
                eventObject.put("event location", sportsEvent.getLocation());
                eventObject.put("max attendance", sportsEvent.getMaxAttendance());
                eventObject.put("level", sportsEvent.lvlofPlay());
                eventObject.put("attendance", sportsEvent.getAttendance());
                eventObject.put("event end date", formattedEventEndDate);
                eventObject.put("event end time",formattedEventEndTime);
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
        LocalTime eventTime = (LocalTime) LocalTime.parse((String) eventjson.get("event time"), DateTimeFormatter.ISO_LOCAL_TIME);
        LocalDate eventDate = (LocalDate) LocalDate.parse((String) eventjson.get("event date"), DateTimeFormatter.ISO_LOCAL_DATE);
        String location = (String) eventjson.get("event location");
        LocalTime eventEndTime = (LocalTime) LocalTime.parse((String) eventjson.get("event end time"), DateTimeFormatter.ISO_LOCAL_TIME);
        LocalDate eventEndDate = (LocalDate) LocalDate.parse((String) eventjson.get("event end date"), DateTimeFormatter.ISO_LOCAL_DATE);


//        int maxAttendance = (int) eventjson.get("max attendance");

        // Long ?
        long temp = (Long) eventjson.get("max attendance");
        int maxAttendance = (int) temp;
        String  level = (String) eventjson.get("level");

        SportsEvent sportsEvent = sportEventFactory.create(eventName, eventDate, eventEndDate, eventTime, eventEndTime, organizer,maxAttendance, level,location);
        sportsEvent.setEventDescription(eventDescription);

        JSONArray attendanceArray = (JSONArray) eventjson.get("attendance");
        for(Object attendance : attendanceArray) {
            sportsEvent.addAttendance((String) attendance);
        }
        return sportsEvent;
    }

}

package data_access;

import entity.SportsEvent;
import entity.User;
import entity.UserFactory;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import use_case.joinEvent.joinEventUserDataAccessInterface;
import use_case.makeEvent.makeEventUserDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileWriter;


public class FileUserDataAccessObject implements makeEventUserDataAccessInterface, joinEventUserDataAccessInterface, SignupUserDataAccessInterface, LoginUserDataAccessInterface {

    private final File jsonFile;


    private final Map<String, User> accounts = new HashMap<>();

    private UserFactory userFactory;

    public FileUserDataAccessObject(String jsonPath, UserFactory userFactory) throws IOException {
        this.userFactory = userFactory;

        jsonFile = new File(jsonPath);

        if (jsonFile.length() == 0) {
            save();
        } else {

            try (FileReader reader = new FileReader(jsonFile)) {


                JSONParser parser = new JSONParser();

                Object obj = parser.parse(reader);
                JSONArray userList = (JSONArray) obj;

                for(Object userobj: userList){
                    User user = parseUserObject((JSONObject) userobj);
                    accounts.put(user.getName(), user);
                }
                } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        }


    @Override
    public void save(User user) {
        accounts.put(user.getName(), user);
        this.save();
    }

    @Override
    public User get(String username) {
        return accounts.get(username);
    }

    private void save() {

        try {
            FileWriter writer = new FileWriter(jsonFile);
            JSONArray usersList = new JSONArray();

            for (User user : accounts.values()) {
                JSONObject userObject = new JSONObject();
                userObject.put("user name", user.getName());
                userObject.put("password", user.getPassword());
                LocalDateTime creationTime = user.getCreationTime();
                String formattedCreationTime = creationTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                userObject.put("creation time", formattedCreationTime);
                userObject.put("user description", user.getUserDescription());
                userObject.put("joined events", user.getJoinedEvents());
                userObject.put("location", user.getLocation());


                usersList.add(userObject);
            }
            writer.write(usersList.toJSONString());
            writer.flush();

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Return whether a user exists with username identifier.
     * @param identifier the username to check.
     * @return whether a user exists with username identifier
     */
    @Override
    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }

    private User parseUserObject(JSONObject userJson) {
        String username = (String) userJson.get("user name");
        String password = (String) userJson.get("password");
        LocalDateTime creationTime = LocalDateTime.parse((String) userJson.get("creation time"));
        String userDescription = (String) userJson.get("user description");
        String location = (String) userJson.get("location");

        User user = userFactory.create(username, password, creationTime);
        user.setUserDescription(userDescription);
        user.setLocation(location);
        JSONArray eventsArray = (JSONArray) userJson.get("joined events");


        for(Object event: eventsArray){
            user.addEvent((String) event);
        }

        return user;
    }

    @Override
    public void addEvent(String eventname, String username) {
        if (existsByName(username)) {
            User registeredUser = accounts.get(username);
            registeredUser.getJoinedEvents().add(eventname);
            this.save();
        }
    }
}


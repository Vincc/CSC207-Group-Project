package data_access;

import entity.User;
import entity.UserFactory;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileWriter;


public class FileUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface {

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

//                String row;
//                while ((row = reader.readLine()) != null) {
//                    String[] col = row.split(",");
//                    String username = String.valueOf(col[headers.get("username")]);
//                    String password = String.valueOf(col[headers.get("password")]);
//                    String creationTimeText = String.valueOf(col[headers.get("creation_time")]);
//                    LocalDateTime ldt = LocalDateTime.parse(creationTimeText);
//                    User user = userFactory.create(username, password, ldt);
//                    accounts.put(username, user);

//                ArrayList<User> users = new ArrayList<>();
                JSONParser parser = new JSONParser();

                // Parse the JSON file into a JSON array
                Object obj = parser.parse(reader);
                JSONArray userList = (JSONArray) obj;
                //userList.forEach(user -> users.add(parseUserObject((JSONObject) user)));
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
//                JSONArray events = new JSONArray();
//                events.addAll(user.getJoinedEvents());
//                userObject.put("joined events", events);

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
        String username = (String) userJson.get("username");
        String password = (String) userJson.get("password"); // Be cautious with real passwords
        LocalDateTime creationTime = LocalDateTime.parse((String) userJson.get("creation time"));
        //JSONArray events = (JSONArray) userJson.get("events");

        //List<String> even = new ArrayList<>();
        //if (hobbiesJson != null) {
        //   hobbiesJson.forEach(hobby -> hobbies.add((String) hobby));
        //}

        return userFactory.create(username, password, creationTime);
    }

}


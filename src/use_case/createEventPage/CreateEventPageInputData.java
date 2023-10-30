package use_case.createEventPage;

import entity.User;
import use_case.login.LoginInputData;

import java.time.LocalDateTime;

public class CreateEventPageInputData {
    final private String username;

    public CreateEventPageInputData(String username) {
        this.username = username;
    }

    String getUsername() {
        return username;
    }

}

package src.entity;

import entity.User;
import entity.UserFactory;

import java.time.LocalDateTime;

public class CommonUserFactory implements UserFactory {
    /**
     * Requires: password is valid.
     * @param name
     * @param password
     * @return
     */

    @Override
    public User create(String name, String password, LocalDateTime ltd) {
        return new CommonUser(name, password, ltd);
    }
}

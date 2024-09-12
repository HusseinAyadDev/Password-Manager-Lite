package app.user;

import app.utils.SHA256;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Users {
    private final HashMap<String, User> users;

    public Users() {
        users = new HashMap<>();
        deserialize();
    }

    public void addUser(String username, String password) {
        if (users.containsKey(username)) {
            System.out.println("Username taken.");
            return;
        }
        password = SHA256.hashString(password);
        users.put(username, new User(username, password));
    }

    public boolean hasUser(String username) {
        return users.containsKey(username);
    }

    public String getUserPassword(String username) {
        return users.get(username).getPassword();
    }

    public boolean userValidator(String username, String password) {
        if (hasUser(username)) {
            return getUserPassword(username).equals(SHA256.hashString(password));
        }
        return false;
    }

    public User getUser(String username) {
        return users.get(username);
    }

    public void serialize() {
        StringBuilder dir = new StringBuilder(".\\src\\main\\resources\\data\\");
        ObjectMapper mapper = new ObjectMapper();

        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        for (User u : users.values()) {
            try {
                dir.append(u.getUsername()).append(".json");
                File to = new File(dir.toString());
                mapper.writeValue(to, u);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void deserialize() {
        File[] jsonFiles = new File(".\\src\\main\\resources\\data\\").listFiles();
        ObjectMapper mapper = new ObjectMapper();

        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        if (jsonFiles != null) {
            for (File f : jsonFiles) {
                try {
                    User user = mapper.readValue(f, User.class);
                    users.put(user.getUsername(), user);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

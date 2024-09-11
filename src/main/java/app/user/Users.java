package app.user;

import app.utils.JsonFileHandler;
import app.utils.SHA256;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;

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
        addUser(username, password, new HashMap<>());
    }

    public void addUser(String username, String password, HashMap<String, String> passwords) {
        if (users.containsKey(username)) {
            System.out.println("Username taken.");
        }
        password = SHA256.hashString(password);
        users.put(username, new User(username, password, passwords));
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

    public void serialize() {

        for (User user : users.values()) {
            try {
                user.serialize(".\\src\\main\\resources\\data\\");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void deserialize() {
        File[] jsonFiles = new File(".\\src\\main\\resources\\data\\").listFiles();

        if (jsonFiles != null) {
            for (File f : jsonFiles) {
                try {
                    JsonNode jasonNode = JsonFileHandler.jsonFileGetter(f.toString());

                    String username = jasonNode.get("username").asText();
                    String password = jasonNode.get("password").asText();
                    String passwordsData = jasonNode.get("passwords").asText();

                    TypeReference<HashMap<String, String>> typeReference = new TypeReference<>() {};

                    if (passwordsData.isBlank()) {
                        return;
                    }

                    @SuppressWarnings("unchecked")
                    HashMap<String, String> passwords =
                            (HashMap<String, String>) JsonFileHandler.convertJsonToType(passwordsData, typeReference);
                    addUser(username, password, passwords);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

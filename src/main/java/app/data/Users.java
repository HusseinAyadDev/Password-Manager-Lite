package app.data;

import app.utils.SHA256;

import java.util.HashMap;

public class Users {
    private final HashMap<String, String> users;

    public Users() {
        users = new HashMap<>();
    }

    public void addUser(String username, String password) {
        if (users.containsKey(username)) {
            System.out.println("Username taken.");
        }
        password = SHA256.hashString(password);
        users.put(username, password);
    }

    public boolean hasUser(String username) {
        return users.containsKey(username);
    }

    public String getUserPassword(String username) {
        return users.get(username);
    }

    public boolean userValidator(String username, String password) {
        if (hasUser(username)) {
            return getUserPassword(username).equals(SHA256.hashString(password));
        }
        return false;
    }
}

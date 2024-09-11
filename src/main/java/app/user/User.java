package app.user;

import app.utils.JsonFileHandler;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class User {

    private final String username;
    private final String password;
    private HashMap<String, String> passwords;

    public User(String username, String password, HashMap<String, String> passwords) {
        this.username = username;
        this.password = password;
        this.passwords = passwords;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    protected void serialize(String filePath) throws IOException {
        ObjectNode objectNode = JsonFileHandler.createObjectNode();

        objectNode.put("username", username);
        objectNode.put("password", password);

        ObjectNode passwordsObjectNode = JsonFileHandler.createObjectNode();

        for (Map.Entry<String, String> entry : passwords.entrySet()) {
            passwordsObjectNode.put(entry.getKey(), entry.getValue());
        }
        objectNode.set("passwords", passwordsObjectNode);

        JsonFileHandler.jsonFileWriter(objectNode, filePath + username + ".json");
    }
}

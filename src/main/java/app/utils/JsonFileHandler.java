package app.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;

public class JsonFileHandler {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static ObjectNode createObjectNode() {
        return OBJECT_MAPPER.createObjectNode();
    }

    public static void jsonFileWriter(ObjectNode objectNode, String filePath) throws IOException {
        OBJECT_MAPPER.writeValue(new File(filePath), objectNode);
    }

    public static JsonNode jsonFileGetter(String filePath) throws IOException {
        return OBJECT_MAPPER.readTree(new File(filePath));
    }

    public static <T> Object convertJsonToType(String jsonInput, TypeReference<T> typeReference) {
        try {
            return OBJECT_MAPPER.readValue(jsonInput, typeReference);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

package m.shigarov.baseFramework.fileTools;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import m.shigarov.baseFramework.models.User;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class JsonUtility {
    static ObjectMapper objectMapper = new ObjectMapper();
    public static String makeJsonFromList(List<Object> list) {
        try {
            return objectMapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public static List<User> jsonToListOfObjects(String sourceFileName) {
        File fileObj = new File(sourceFileName);
        List<User> usersList;
        try {
            usersList = objectMapper.readValue(fileObj, new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return usersList;
    }

    public static List<String> jsonToListOfStrings(String sourceFileName) {
        File fileObj = new File(sourceFileName);
        try {
            return objectMapper.readValue(fileObj, new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveToFile(List<Object> gameSpecsList, String filename) {
        String jsonList = JsonUtility.makeJsonFromList(gameSpecsList);
        try {
            Files.writeString(Path.of("src/test/resources/" + filename), jsonList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

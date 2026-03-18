package utils;
import java.util.*;

public class JsonParser {

    public static Map<String, String> parse(String json) {
        Map<String, String> map = new HashMap<>();

        json = json.trim();
        json = json.substring(1, json.length() - 1); // remove { }

        if (json.trim().isEmpty()) return map;

        String[] parts = json.split(",");

        for (String part : parts) {
            String[] kv = part.split(":");
            String key = kv[0].trim().replace("\"", "");
            String val = kv[1].trim().replace("\"", "");
            map.put(key, val);
        }

        return map;
    }
}
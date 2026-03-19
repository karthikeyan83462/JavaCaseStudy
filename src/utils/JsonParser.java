package utils;
import java.util.*;

public class JsonParser {

    public static Map<String, String> parse(String json) {
        Map<String, String> map = new HashMap<>();

        if (json == null || json.trim().length() < 2) return map;

        json = json.trim();

        // remove { }
        if (json.startsWith("{") && json.endsWith("}")) {
            json = json.substring(1, json.length() - 1);
        }

        if (json.trim().isEmpty()) return map;

        // split safely (ignore commas inside quotes)
        String[] parts = json.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

        for (String part : parts) {

            // split only on first colon outside quotes
            String[] kv = part.split(":(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", 2);

            if (kv.length < 2) continue;

            String key = kv[0].trim().replace("\"", "");
            String val = kv[1].trim().replace("\"", "");

            map.put(key, val);
        }

        return map;
    }
}
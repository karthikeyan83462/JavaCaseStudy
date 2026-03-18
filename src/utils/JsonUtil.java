package utils;
import java.util.*;

public class JsonUtil {

    // Convert List<Map> to JSON array string
    public static String listToJson(List<Map<String, String>> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < list.size(); i++) {
            sb.append(mapToJson(list.get(i)));
            if (i < list.size() - 1) sb.append(", ");
        }

        sb.append("]");
        return sb.toString();
    }

    // Convert single map to JSON object string
    public static String mapToJson(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        int i = 0;
        for (Map.Entry<String, String> e : map.entrySet()) {
            sb.append("\"").append(e.getKey()).append("\": ");
            sb.append("\"").append(e.getValue()).append("\"");

            if (i < map.size() - 1) sb.append(", ");
            i++;
        }

        sb.append("}");
        return sb.toString();
    }

    // Parse JSON array string into List<Map>
    public static List<Map<String, String>> parseList(String json) {
        List<Map<String, String>> list = new ArrayList<>();

        json = json.trim();
        json = json.substring(1, json.length() - 1).trim(); // remove [ ]

        if (json.isEmpty()) return list;

        // Split objects
        String[] objects = json.split("(?<=\\}),");

        for (String obj : objects) {
            list.add(JsonParser.parse(obj.trim()));
        }

        return list;
    }
}
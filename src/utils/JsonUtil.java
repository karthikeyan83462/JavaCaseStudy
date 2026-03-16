package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * JsonUtil provides JSON serialization/deserialization using native Java JSON handling.
 * Simplifies date/time serialization without external dependencies.
 */
public class JsonUtil {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_TIME;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    /**
     * Parse JSON array string to List of maps
     */
    public static List<Map<String, Object>> parseJsonArray(String jsonText) {
        List<Map<String, Object>> result = new ArrayList<>();
        jsonText = jsonText.trim();
        
        if (!jsonText.startsWith("[") || !jsonText.endsWith("]")) {
            return result;
        }
        
        String content = jsonText.substring(1, jsonText.length() - 1).trim();
        if (content.isEmpty()) {
            return result;
        }
        
        // Simple JSON parsing - handle individual objects in array
        int depth = 0;
        StringBuilder current = new StringBuilder();
        
        for (int i = 0; i < content.length(); i++) {
            char c = content.charAt(i);
            
            if (c == '{') depth++;
            else if (c == '}') {
                depth--;
                current.append(c);
                
                if (depth == 0) {
                    String objStr = current.toString().trim();
                    if (!objStr.isEmpty()) {
                        Map<String, Object> obj = parseJsonObject(objStr);
                        result.add(obj);
                    }
                    current = new StringBuilder();
                }
                continue;
            }
            
            if (depth > 0) {
                current.append(c);
            }
        }
        
        return result;
    }

    /**
     * Parse single JSON object to map
     */
    public static Map<String, Object> parseJsonObject(String jsonText) {
        Map<String, Object> result = new LinkedHashMap<>();
        jsonText = jsonText.trim();
        
        if (!jsonText.startsWith("{") || !jsonText.endsWith("}")) {
            return result;
        }
        
        String content = jsonText.substring(1, jsonText.length() - 1).trim();
        if (content.isEmpty()) {
            return result;
        }
        
        // Parse key-value pairs
        int depth = 0;
        StringBuilder key = new StringBuilder();
        StringBuilder value = new StringBuilder();
        boolean inKey = true;
        boolean inQuotes = false;
        boolean escapedChar = false;
        
        for (int i = 0; i < content.length(); i++) {
            char c = content.charAt(i);
            
            if (escapedChar) {
                if (inKey) key.append(c);
                else value.append(c);
                escapedChar = false;
                continue;
            }
            
            if (c == '\\') {
                escapedChar = true;
                continue;
            }
            
            if (c == '"') {
                inQuotes = !inQuotes;
                if (!inKey) value.append(c);
                continue;
            }
            
            if (!inQuotes) {
                if (c == ':' && depth == 0 && inKey) {
                    inKey = false;
                    key = new StringBuilder(key.toString().trim());
                    if (key.length() > 0 && key.charAt(0) == '"') {
                        key = new StringBuilder(key.substring(1, key.length() - 1));
                    }
                    continue;
                }
                
                if ((c == ',' || i == content.length() - 1) && depth == 0 && !inKey) {
                    if (i == content.length() - 1) {
                        value.append(c);
                    }
                    String valStr = value.toString().trim();
                    Object parsed = parseValue(valStr);
                    result.put(key.toString(), parsed);
                    
                    inKey = true;
                    key = new StringBuilder();
                    value = new StringBuilder();
                    continue;
                }
                
                if (c == '{' || c == '[') depth++;
                else if (c == '}' || c == ']') depth--;
            }
            
            if (!inKey) {
                value.append(c);
            }
        }
        
        return result;
    }

    /**
     * Convert maps back to JSON string
     */
    public static String toJsonString(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder("{");
        boolean first = true;
        
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (!first) sb.append(",");
            first = false;
            
            sb.append("\"").append(entry.getKey()).append("\":");
            sb.append(valueToJson(entry.getValue()));
        }
        
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert list of maps to JSON array string
     */
    public static String toJsonArray(List<Map<String, Object>> list) {
        StringBuilder sb = new StringBuilder("[");
        boolean first = true;
        
        for (Map<String, Object> item : list) {
            if (!first) sb.append(",");
            first = false;
            sb.append(toJsonString(item));
        }
        
        sb.append("]");
        return sb.toString();
    }

    /**
     * Parse individual JSON value
     */
    private static Object parseValue(String value) {
        value = value.trim();
        
        if (value.isEmpty()) {
            return null;
        }
        
        if (value.equalsIgnoreCase("null")) {
            return null;
        }
        
        if (value.equalsIgnoreCase("true")) {
            return true;
        }
        
        if (value.equalsIgnoreCase("false")) {
            return false;
        }
        
        if (value.startsWith("\"") && value.endsWith("\"")) {
            return value.substring(1, value.length() - 1);
        }
        
        if (value.startsWith("[") && value.endsWith("]")) {
            return parseJsonArray(value);
        }
        
        if (value.startsWith("{") && value.endsWith("}")) {
            return parseJsonObject(value);
        }
        
        try {
            if (value.contains(".")) {
                return Double.parseDouble(value);
            }
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return value;
        }
    }

    /**
     * Convert value to JSON representation
     */
    private static String valueToJson(Object value) {
        if (value == null) {
            return "null";
        }
        
        if (value instanceof Boolean || value instanceof Integer || value instanceof Double || value instanceof Long) {
            return value.toString();
        }
        
        if (value instanceof String) {
            String str = (String) value;
            return "\"" + escapeJson(str) + "\"";
        }
        
        if (value instanceof LocalDate) {
            return "\"" + ((LocalDate) value).format(DATE_FORMATTER) + "\"";
        }
        
        if (value instanceof LocalTime) {
            return "\"" + ((LocalTime) value).format(TIME_FORMATTER) + "\"";
        }
        
        if (value instanceof LocalDateTime) {
            return "\"" + ((LocalDateTime) value).format(DATE_TIME_FORMATTER) + "\"";
        }
        
        if (value instanceof Map) {
            @SuppressWarnings("unchecked")
            Map<String, Object> mapValue = (Map<String, Object>) value;
            return toJsonString(mapValue);
        }
        
        if (value instanceof List) {
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> listValue = (List<Map<String, Object>>) value;
            return toJsonArray(listValue);
        }
        
        return "\"" + escapeJson(value.toString()) + "\"";
    }

    /**
     * Escape special characters for JSON strings
     */
    private static String escapeJson(String str) {
        return str.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }

    /**
     * Write JSON to file
     */
    public static void writeJsonFile(String filePath, String jsonContent) throws IOException {
        Files.write(Paths.get(filePath), jsonContent.getBytes());
    }

    /**
     * Read JSON from file
     */
    public static String readJsonFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }
}

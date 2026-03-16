package utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Centralized utility for generating sequential, readable IDs for all entities.
 * Maintains separate counters for each entity type and ensures consistency across the system.
 */
public class IdGenerator {
    private static final Map<String, Integer> counters = new HashMap<>();
    
    // Entity type prefixes
    public static final String USER_PREFIX = "USR";
    public static final String EMPLOYEE_PREFIX = "EMP";
    public static final String PROJECT_PREFIX = "PRJ";
    public static final String ASSIGNMENT_PREFIX = "ASN";
    public static final String LEAVE_PREFIX = "LVE";
    public static final String ATTENDANCE_PREFIX = "ATT";
    public static final String REVIEW_PREFIX = "REV";
    
    // Initialize counters
    static {
        counters.put(USER_PREFIX, 0);
        counters.put(EMPLOYEE_PREFIX, 0);
        counters.put(PROJECT_PREFIX, 0);
        counters.put(ASSIGNMENT_PREFIX, 0);
        counters.put(LEAVE_PREFIX, 0);
        counters.put(ATTENDANCE_PREFIX, 0);
        counters.put(REVIEW_PREFIX, 0);
    }
    
    /**
     * Generate the next ID for a given entity type.
     * Format: PREFIX + 3-digit zero-padded counter (e.g., USR001, EMP025, PRJ100)
     */
    public static synchronized String generateId(String prefix) {
        if (!counters.containsKey(prefix)) {
            counters.put(prefix, 0);
        }
        int counter = counters.get(prefix) + 1;
        counters.put(prefix, counter);
        return String.format("%s%03d", prefix, counter);
    }
    
    /**
     * Set the counter for a specific entity type (used during data migration/loading).
     */
    public static synchronized void setCounter(String prefix, int value) {
        counters.put(prefix, value);
    }
    
    /**
     * Get the current counter value for a specific entity type.
     */
    public static synchronized int getCounter(String prefix) {
        return counters.getOrDefault(prefix, 0);
    }
    
    /**
     * Get all counters (used for persistence).
     */
    public static synchronized Map<String, Integer> getAllCounters() {
        return new HashMap<>(counters);
    }
    
    /**
     * Set all counters at once (used during loading from persistence).
     */
    public static synchronized void setAllCounters(Map<String, Integer> newCounters) {
        counters.clear();
        counters.putAll(newCounters);
    }
    
    /**
     * Reset all counters to zero (useful for testing).
     */
    public static synchronized void resetAll() {
        for (String key : counters.keySet()) {
            counters.put(key, 0);
        }
    }
}

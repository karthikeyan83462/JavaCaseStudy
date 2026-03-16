package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtility {
    // DD-MM-YYYY format for Indian locale
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    
    // Parse date in DD-MM-YYYY format
    public static LocalDate parseDate(String dateString) throws IllegalArgumentException {
        if (dateString == null || dateString.trim().isEmpty()) {
            throw new IllegalArgumentException("Date cannot be empty");
        }
        
        try {
            return LocalDate.parse(dateString.trim(), DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use DD-MM-YYYY (e.g., 25-12-2024)");
        }
    }
    
    // Format date to DD-MM-YYYY
    public static String formatDate(LocalDate date) {
        if (date == null) {
            return "N/A";
        }
        return date.format(DATE_FORMATTER);
    }
    
    // Format datetime to DD-MM-YYYY HH:MM
    public static String formatDateTime(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "N/A";
        }
        return dateTime.format(DATE_TIME_FORMATTER);
    }
    
    // Check if a string is a valid date in DD-MM-YYYY format
    public static boolean isValidDate(String dateString) {
        try {
            parseDate(dateString);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}

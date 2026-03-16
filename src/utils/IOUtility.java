package utils;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * IOUtility centralizes input/output operations to eliminate duplication across controllers.
 * Provides safe input methods with validation and error handling.
 */
public class IOUtility {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT_DISPLAY);
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(Constants.TIME_FORMAT_DISPLAY);

    /**
     * Read a string from user input
     */
    public static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    /**
     * Read a string with required field validation
     */
    public static String readRequiredString(String prompt) {
        String value;
        while (true) {
            value = readString(prompt);
            if (InputValidator.isNotEmpty(value)) {
                return value;
            }
            System.out.println("Error: " + Constants.MSG_REQUIRED_FIELD);
        }
    }

    /**
     * Read an integer from user input
     */
    public static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number");
            }
        }
    }

    /**
     * Read a positive integer
     */
    public static int readPositiveInt(String prompt) {
        while (true) {
            int value = readInt(prompt);
            if (value > 0) {
                return value;
            }
            System.out.println("Error: Please enter a positive number");
        }
    }

    /**
     * Read a double from user input
     */
    public static double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = Double.parseDouble(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid decimal number");
            }
        }
    }

    /**
     * Read a positive double
     */
    public static double readPositiveDouble(String prompt) {
        while (true) {
            double value = readDouble(prompt);
            if (value > 0) {
                return value;
            }
            System.out.println("Error: Please enter a positive number");
        }
    }

    /**
     * Read a date in dd-MM-yyyy format
     */
    public static LocalDate readDate(String prompt) {
        while (true) {
            String dateStr = readString(prompt);
            if (InputValidator.isValidDate(dateStr)) {
                try {
                    return LocalDate.parse(dateStr, DATE_FORMATTER);
                } catch (DateTimeParseException e) {
                    System.out.println("Error: " + Constants.MSG_INVALID_DATE);
                }
            } else {
                System.out.println("Error: " + Constants.MSG_INVALID_DATE);
            }
        }
    }

    /**
     * Read a time in HH:mm:ss format
     */
    public static LocalTime readTime(String prompt) {
        while (true) {
            String timeStr = readString(prompt);
            try {
                return LocalTime.parse(timeStr, TIME_FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println("Error: Please enter time in HH:mm:ss format");
            }
        }
    }

    /**
     * Read an email address
     */
    public static String readEmail(String prompt) {
        while (true) {
            String email = readString(prompt);
            if (InputValidator.isValidEmail(email)) {
                return email;
            }
            System.out.println("Error: " + Constants.MSG_INVALID_EMAIL);
        }
    }

    /**
     * Read a password with strength validation
     */
    public static String readPassword(String prompt) {
        while (true) {
            String password = readString(prompt);
            if (InputValidator.isValidPassword(password)) {
                return password;
            }
            System.out.println("Error: " + Constants.MSG_WEAK_PASSWORD);
        }
    }

    /**
     * Read a yes/no choice
     */
    public static boolean readYesNo(String prompt) {
        while (true) {
            String response = readString(prompt + " (yes/no): ").toLowerCase();
            if (response.equals("yes") || response.equals("y")) {
                return true;
            } else if (response.equals("no") || response.equals("n")) {
                return false;
            }
            System.out.println("Error: Please enter 'yes' or 'no'");
        }
    }

    /**
     * Read choice from menu (1 to maxOption)
     */
    public static int readMenuChoice(int maxOption) {
        while (true) {
            try {
                int choice = readInt("Enter choice: ");
                if (choice >= 1 && choice <= maxOption) {
                    return choice;
                }
                System.out.println("Error: Please enter a number between 1 and " + maxOption);
            } catch (Exception e) {
                System.out.println("Error: Invalid input");
            }
        }
    }

    /**
     * Print section header
     */
    public static void printHeader(String title) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println(centerText(title, 60));
        System.out.println("=".repeat(60));
    }

    /**
     * Print sub-header
     */
    public static void printSubHeader(String title) {
        System.out.println("\n" + "-".repeat(60));
        System.out.println(title);
        System.out.println("-".repeat(60));
    }

    /**
     * Print a line break
     */
    public static void printLine() {
        System.out.println();
    }

    /**
     * Print success message
     */
    public static void printSuccess(String message) {
        System.out.println("\n✓ " + message);
    }

    /**
     * Print error message
     */
    public static void printError(String message) {
        System.err.println("\n✗ Error: " + message);
    }

    /**
     * Print info message
     */
    public static void printInfo(String message) {
        System.out.println("\nℹ " + message);
    }

    /**
     * Print message without formatting
     */
    public static void print(String message) {
        System.out.println(message);
    }

    /**
     * Center text within given width
     */
    private static String centerText(String text, int width) {
        int padding = (width - text.length()) / 2;
        if (padding > 0) {
            return " ".repeat(padding) + text;
        }
        return text;
    }

    /**
     * Format table row
     */
    public static void printTableRow(String... columns) {
        StringBuilder row = new StringBuilder();
        for (String col : columns) {
            row.append(String.format("%-20s | ", col.substring(0, Math.min(col.length(), 20))));
        }
        System.out.println(row);
    }

    /**
     * Close scanner (call before exiting)
     */
    public static void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}

package utils;

import java.util.Collection;

public class UIUtility {
    private static final int SCREEN_WIDTH = 80;
    private static final String SEPARATOR = "════════════════════════════════════════";
    private static final String LINE_SEPARATOR = "================================================================================";
    private static final String DASH_SEPARATOR = "--------------------------------------------------------------------------------";
    
    // Box drawing characters
    private static final String BOX_TOP_LEFT = "┌";
    private static final String BOX_TOP_RIGHT = "┐";
    private static final String BOX_BOTTOM_LEFT = "└";
    private static final String BOX_BOTTOM_RIGHT = "┘";
    private static final String BOX_HORIZONTAL = "─";
    private static final String BOX_VERTICAL = "│";
    
    /**
     * Clear the screen with proper formatting
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    /**
     * Print a header with consistent formatting
     */
    public static void printHeader(String title) {
        System.out.println();
        System.out.println("  " + title);
        printSeparator();
        System.out.println();
    }
    
    /**
     * Print a separator line
     */
    public static void printSeparator() {
        System.out.println("  " + SEPARATOR);
    }
    
    /**
     * Pad a string to a specific width (right-aligned)
     */
    public static String padRight(String text, int width) {
        if (text == null) {
            text = "";
        }
        return String.format("%-" + width + "s", text);
    }
    
    /**
     * Pad a string to a specific width (left-aligned, used for centering)
     */
    public static String padLeft(String text, int width) {
        if (text == null) {
            text = "";
        }
        return String.format("%" + width + "s", text);
    }
    
    /**
     * Center text within the screen width
     */
    public static String centerText(String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }
        int contentWidth = SCREEN_WIDTH - 4;
        int totalPadding = contentWidth - text.length();
        if (totalPadding <= 0) {
            return "  " + text;
        }
        int leftPadding = totalPadding / 2;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < leftPadding; i++) {
            sb.append(" ");
        }
        sb.append(text);
        return "  " + sb.toString();
    }

    /**
     * Wait for user to press Enter and clear input buffer
     */
    public static void waitForContinue() {
        System.out.print("\n  Press Enter to continue...");
        try {
            System.in.read();
            while (System.in.available() > 0) {
                System.in.read();
            }
        } catch (Exception e) {
            // Continue regardless
        }
    }

    /**
     * Display available departments for selection
     */
    public static void displayAvailableDepartments() {
        System.out.println("\n  Available Departments:");
        System.out.println("    1. Management");
        System.out.println("    2. Sales");
        System.out.println("    3. Engineering");
        System.out.println("    4. HR");
        System.out.println("    5. Finance");
        System.out.println();
    }

    /**
     * Display available positions for selection
     */
    public static void displayAvailablePositions() {
        System.out.println("\n  Available Positions:");
        System.out.println("    1. System Administrator");
        System.out.println("    2. Sales Manager");
        System.out.println("    3. Software Engineer");
        System.out.println("    4. Java Developer");
        System.out.println("    5. Junior Developer");
        System.out.println("    6. HR Manager");
        System.out.println("    7. Finance Manager");
        System.out.println("    8. Accountant");
        System.out.println();
    }

    /**
     * Display available projects for selection
     */
    public static void displayAvailableProjects(Collection<String> projectNames) {
        if (projectNames == null || projectNames.isEmpty()) {
            System.out.println("\n  No projects available.");
            return;
        }
        System.out.println("\n  Available Projects:");
        int index = 1;
        for (String project : projectNames) {
            System.out.println("    " + index + ". " + project);
            index++;
        }
        System.out.println();
    }

    /**
     * Display available managers for selection
     */
    public static void displayAvailableManagers(Collection<String> managerNames) {
        if (managerNames == null || managerNames.isEmpty()) {
            System.out.println("\n  No managers available.");
            return;
        }
        System.out.println("\n  Available Managers:");
        int index = 1;
        for (String manager : managerNames) {
            System.out.println("    " + index + ". " + manager);
            index++;
        }
        System.out.println();
    }

    /**
     * Print a header with line separators
     */
    public static void printHeaderWithLine(String title) {
        clearScreen();
        System.out.println("\n  " + LINE_SEPARATOR);
        System.out.println("  " + title);
        System.out.println("  " + LINE_SEPARATOR + "\n");
    }

    /**
     * Print a section divider with dashes
     */
    public static void printDashLine() {
        System.out.println("  " + DASH_SEPARATOR);
    }

    /**
     * Print success message
     */
    public static void printSuccess(String message) {
        System.out.println("\n  ✓ " + message);
    }

    /**
     * Print error message
     */
    public static void printError(String message) {
        System.out.println("\n  ✗ " + message);
    }

    /**
     * Print info message
     */
    public static void printInfo(String message) {
        System.out.println("\n  ℹ " + message);
    }

    /**
     * Print a menu option
     */
    public static void printMenuOption(String option, String description) {
        System.out.println("  " + padRight(option, 3) + " -> " + description);
    }

    /**
     * Print a table row with fixed column widths
     */
    public static void printTableRow(String col1, String col2, String col3, String col4) {
        System.out.println("  " + padRight(col1, 12) + " | " + padRight(col2, 20) + " | " + padRight(col3, 15) + " | " + col4);
    }

    /**
     * Print a 2-column table row
     */
    public static void printTableRow2Col(String col1, String col2) {
        System.out.println("  " + padRight(col1, 25) + " | " + col2);
    }

    /**
     * Print menu prompt
     */
    public static void printPrompt(String message) {
        System.out.print("  " + message + ": ");
    }

    /**
     * Print numbered option with description
     */
    public static void printOption(int num, String description) {
        System.out.println("  " + num + ". " + description);
    }

    /**
     * Map numbered selection to string value (for departments)
     */
    public static String mapDepartmentSelection(String selection) {
        switch (selection.trim()) {
            case "1": return "Management";
            case "2": return "Sales";
            case "3": return "Engineering";
            case "4": return "HR";
            case "5": return "Finance";
            default: return selection;
        }
    }

    /**
     * Map numbered selection to string value (for positions)
     */
    public static String mapPositionSelection(String selection) {
        switch (selection.trim()) {
            case "1": return "System Administrator";
            case "2": return "Sales Manager";
            case "3": return "Software Engineer";
            case "4": return "Java Developer";
            case "5": return "Junior Developer";
            case "6": return "HR Manager";
            case "7": return "Finance Manager";
            case "8": return "Accountant";
            default: return selection;
        }
    }

    // ==================== BOX DRAWING METHODS ====================

    /**
     * Print the top of a box
     */
    public static void printBoxStart(int width) {
        System.out.print("  " + BOX_TOP_LEFT);
        for (int i = 0; i < width - 2; i++) {
            System.out.print(BOX_HORIZONTAL);
        }
        System.out.println(BOX_TOP_RIGHT);
    }

    /**
     * Print the bottom of a box
     */
    public static void printBoxEnd(int width) {
        System.out.print("  " + BOX_BOTTOM_LEFT);
        for (int i = 0; i < width - 2; i++) {
            System.out.print(BOX_HORIZONTAL);
        }
        System.out.println(BOX_BOTTOM_RIGHT);
    }

    /**
     * Print a box line with content
     */
    public static void printBoxLine(String content, int width) {
        String paddedContent = padRight(content, width - 4);
        System.out.println("  " + BOX_VERTICAL + " " + paddedContent + " " + BOX_VERTICAL);
    }

    /**
     * Print a box line with left indent
     */
    public static void printBoxLineIndented(String content, int indent, int width) {
        String indentation = "";
        for (int i = 0; i < indent; i++) {
            indentation += " ";
        }
        String paddedContent = padRight(indentation + content, width - 4);
        System.out.println("  " + BOX_VERTICAL + " " + paddedContent + " " + BOX_VERTICAL);
    }

    /**
     * Print a hierarchy level item
     */
    public static void printHierarchyItem(String prefix, String content, String indent) {
        System.out.println("  " + indent + prefix + content);
    }

    /**
     * Print a team member card with details
     */
    public static void printTeamMember(String name, String id, String department, String skills) {
        printBoxStart(40);
        printBoxLine("├─ " + name, 40);
        printBoxLine("ID: " + id, 40);
        if (department != null && !department.isEmpty()) {
            printBoxLine("Department: " + department, 40);
        }
        if (skills != null && !skills.isEmpty()) {
            printBoxLine("Skills: " + skills, 40);
        }
        printBoxEnd(40);
    }

    /**
     * Print an employee card with basic info
     */
    public static void printEmployeeCard(String name, String id, String email, String department) {
        printBoxStart(42);
        printBoxLine(name, 42);
        printBoxLine("ID: " + id, 42);
        printBoxLine("Email: " + email, 42);
        printBoxLine("Department: " + department, 42);
        printBoxEnd(42);
    }

    /**
     * Print a section divider
     */
    public static void printSectionDivider(String title) {
        System.out.println();
        System.out.println("  ├─ " + title);
        System.out.println("  │");
    }

    /**
     * Print a list item in hierarchy
     */
    public static void printListItem(String prefix, String content) {
        System.out.println("  " + prefix + content);
    }

    /**
     * Print key-value pair
     */
    public static void printKeyValue(String key, String value) {
        System.out.println("  " + padRight(key, 20) + ": " + value);
    }

    /**
     * Print indented text
     */
    public static void printIndented(String text, int spaces) {
        String indent = "";
        for (int i = 0; i < spaces; i++) {
            indent += " ";
        }
        System.out.println(indent + text);
    }
}

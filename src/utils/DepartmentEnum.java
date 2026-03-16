package utils;

/**
 * Enumeration for all departments in the organization.
 * Provides numerical selection options for UI menus.
 */
public enum DepartmentEnum {
    IT("IT", 1, "Information Technology"),
    HR("HR", 2, "Human Resources"),
    FINANCE("Finance", 3, "Finance"),
    OPERATIONS("Operations", 4, "Operations"),
    SALES("Sales", 5, "Sales"),
    MARKETING("Marketing", 6, "Marketing"),
    LEGAL("Legal", 7, "Legal"),
    RESEARCH("Research", 8, "Research & Development");

    private final String code;
    private final int optionNumber;
    private final String displayName;

    DepartmentEnum(String code, int optionNumber, String displayName) {
        this.code = code;
        this.optionNumber = optionNumber;
        this.displayName = displayName;
    }

    public String getCode() {
        return code;
    }

    public int getOptionNumber() {
        return optionNumber;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static DepartmentEnum fromCode(String code) {
        if (code == null) return null;
        for (DepartmentEnum dept : DepartmentEnum.values()) {
            if (dept.code.equalsIgnoreCase(code)) {
                return dept;
            }
        }
        return null;
    }

    public static DepartmentEnum fromOptionNumber(int optionNumber) {
        for (DepartmentEnum dept : DepartmentEnum.values()) {
            if (dept.optionNumber == optionNumber) {
                return dept;
            }
        }
        return null;
    }

    public static void printOptions() {
        for (DepartmentEnum dept : DepartmentEnum.values()) {
            System.out.printf("  %d   -> %s (%s)%n", dept.optionNumber, dept.displayName, dept.code);
        }
    }
}

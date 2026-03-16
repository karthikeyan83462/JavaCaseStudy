package utils;

/**
 * Enumeration for all positions in the organization.
 * Provides numerical selection options for UI menus.
 */
public enum PositionEnum {
    JUNIOR_DEVELOPER("Junior Developer", 1),
    SENIOR_DEVELOPER("Senior Developer", 2),
    LEAD_DEVELOPER("Lead Developer", 3),
    ARCHITECT("Architect", 4),
    PROJECT_MANAGER("Project Manager", 5),
    TEAM_LEAD("Team Lead", 6),
    BUSINESS_ANALYST("Business Analyst", 7),
    QA_ENGINEER("QA Engineer", 8),
    DEVOPS_ENGINEER("DevOps Engineer", 9),
    HR_SPECIALIST("HR Specialist", 10),
    RECRUITER("Recruiter", 11),
    ACCOUNTANT("Accountant", 12),
    FINANCIAL_ANALYST("Financial Analyst", 13),
    OPERATIONS_MANAGER("Operations Manager", 14),
    SALES_EXECUTIVE("Sales Executive", 15),
    SALES_MANAGER("Sales Manager", 16),
    MARKETING_SPECIALIST("Marketing Specialist", 17),
    LAWYER("Lawyer", 18),
    RESEARCHER("Researcher", 19),
    CONSULTANT("Consultant", 20);

    private final String displayName;
    private final int optionNumber;

    PositionEnum(String displayName, int optionNumber) {
        this.displayName = displayName;
        this.optionNumber = optionNumber;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getOptionNumber() {
        return optionNumber;
    }

    public String getCode() {
        return this.name();
    }

    public static PositionEnum fromOptionNumber(int optionNumber) {
        for (PositionEnum pos : PositionEnum.values()) {
            if (pos.optionNumber == optionNumber) {
                return pos;
            }
        }
        return null;
    }

    public static PositionEnum fromDisplayName(String displayName) {
        if (displayName == null) return null;
        for (PositionEnum pos : PositionEnum.values()) {
            if (pos.displayName.equalsIgnoreCase(displayName)) {
                return pos;
            }
        }
        return null;
    }

    public static void printOptions() {
        for (PositionEnum pos : PositionEnum.values()) {
            System.out.printf("  %d   -> %s%n", pos.optionNumber, pos.displayName);
        }
    }

    public static void printOptionsByDepartment(DepartmentEnum department) {
        // Filter positions by department (simplified - could be more complex)
        for (PositionEnum pos : PositionEnum.values()) {
            System.out.printf("  %d   -> %s%n", pos.optionNumber, pos.displayName);
        }
    }
}

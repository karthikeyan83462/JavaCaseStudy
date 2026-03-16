package utils;

/**
 * Constants holds all magic strings and configuration values used throughout the application.
 * Centralizes constants to reduce duplication and improve maintainability.
 */
public class Constants {
    // User Roles
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_MANAGER = "MANAGER";
    public static final String ROLE_EMPLOYEE = "EMPLOYEE";

    // Employee Status
    public static final String STATUS_ACTIVE = "ACTIVE";
    public static final String STATUS_INACTIVE = "INACTIVE";
    public static final String STATUS_ON_LEAVE = "ON_LEAVE";

    // Project Status
    public static final String PROJECT_STATUS_PLANNING = "PLANNING";
    public static final String PROJECT_STATUS_IN_PROGRESS = "IN_PROGRESS";
    public static final String PROJECT_STATUS_ON_HOLD = "ON_HOLD";
    public static final String PROJECT_STATUS_COMPLETED = "COMPLETED";

    // Leave Status
    public static final String LEAVE_STATUS_PENDING = "PENDING";
    public static final String LEAVE_STATUS_APPROVED = "APPROVED";
    public static final String LEAVE_STATUS_REJECTED = "REJECTED";
    public static final String LEAVE_STATUS_CANCELLED = "CANCELLED";

    // Leave Types
    public static final String LEAVE_TYPE_ANNUAL = "ANNUAL";
    public static final String LEAVE_TYPE_SICK = "SICK";
    public static final String LEAVE_TYPE_PERSONAL = "PERSONAL";
    public static final String LEAVE_TYPE_COMPASSIONATE = "COMPASSIONATE";
    public static final String LEAVE_TYPE_MATERNITY = "MATERNITY";

    // Attendance Status
    public static final String ATTENDANCE_PRESENT = "PRESENT";
    public static final String ATTENDANCE_ABSENT = "ABSENT";
    public static final String ATTENDANCE_LEAVE = "LEAVE";
    public static final String ATTENDANCE_HALF_DAY = "HALF_DAY";
    public static final String ATTENDANCE_LATE = "LATE";

    // Assignment Status
    public static final String ASSIGNMENT_STATUS_ASSIGNED = "ASSIGNED";
    public static final String ASSIGNMENT_STATUS_IN_PROGRESS = "IN_PROGRESS";
    public static final String ASSIGNMENT_STATUS_COMPLETED = "COMPLETED";
    public static final String ASSIGNMENT_STATUS_ON_HOLD = "ON_HOLD";

    // ID Prefixes
    public static final String ID_PREFIX_USER = "USR";
    public static final String ID_PREFIX_EMPLOYEE = "EMP";
    public static final String ID_PREFIX_PROJECT = "PRJ";
    public static final String ID_PREFIX_ASSIGNMENT = "ASN";
    public static final String ID_PREFIX_LEAVE = "LEV";
    public static final String ID_PREFIX_ATTENDANCE = "ATT";
    public static final String ID_PREFIX_REVIEW = "REV";

    // Validation Messages
    public static final String MSG_REQUIRED_FIELD = "This field is required";
    public static final String MSG_INVALID_EMAIL = "Invalid email format";
    public static final String MSG_INVALID_DATE = "Invalid date format";
    public static final String MSG_WEAK_PASSWORD = "Password must be at least 6 characters";
    public static final String MSG_USER_EXISTS = "User already exists";
    public static final String MSG_USER_NOT_FOUND = "User not found";
    public static final String MSG_INVALID_CREDENTIALS = "Invalid username or password";
    public static final String MSG_EMPLOYEE_NOT_FOUND = "Employee not found";
    public static final String MSG_PROJECT_NOT_FOUND = "Project not found";
    public static final String MSG_UNAUTHORIZED = "Unauthorized access";
    public static final String MSG_SUCCESS = "Operation completed successfully";
    public static final String MSG_ERROR = "An error occurred. Please try again.";

    // Validation Patterns
    public static final String REGEX_EMAIL = "^[A-Za-z0-9+_.-]+@(.+)$";
    public static final String REGEX_ALPHANUMERIC = "^[a-zA-Z0-9_-]+$";
    public static final int MIN_PASSWORD_LENGTH = 6;
    public static final int MAX_USERNAME_LENGTH = 50;
    public static final int MAX_EMAIL_LENGTH = 100;

    // Date Formats
    public static final String DATE_FORMAT_DISPLAY = "dd-MM-yyyy";
    public static final String TIME_FORMAT_DISPLAY = "HH:mm:ss";
    public static final String DATE_TIME_FORMAT_DISPLAY = "dd-MM-yyyy HH:mm:ss";

    // Pagination
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int MAX_PAGE_SIZE = 100;
}

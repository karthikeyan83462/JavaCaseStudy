package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

/**
 * InputValidator centralizes all input validation logic to eliminate duplication across controllers.
 * Provides static methods for common validation tasks.
 */
public class InputValidator {
    private static final Pattern EMAIL_PATTERN = Pattern.compile(Constants.REGEX_EMAIL);
    private static final Pattern ALPHANUMERIC_PATTERN = Pattern.compile(Constants.REGEX_ALPHANUMERIC);

    /**
     * Validate email format
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }

    /**
     * Validate password strength
     */
    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= Constants.MIN_PASSWORD_LENGTH;
    }

    /**
     * Validate string is not empty/null
     */
    public static boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

    /**
     * Validate username format
     */
    public static boolean isValidUsername(String username) {
        if (username == null || username.length() > Constants.MAX_USERNAME_LENGTH) {
            return false;
        }
        return isNotEmpty(username) && ALPHANUMERIC_PATTERN.matcher(username).matches();
    }

    /**
     * Validate required field
     */
    public static boolean isRequired(String value) {
        return isNotEmpty(value);
    }

    /**
     * Validate date string in format dd-MM-yyyy
     */
    public static boolean isValidDate(String dateStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT_DISPLAY);
            LocalDate.parse(dateStr, formatter);
            return true;
        } catch (DateTimeParseException | NullPointerException e) {
            return false;
        }
    }

    /**
     * Validate date is not in the past
     */
    public static boolean isFutureDate(LocalDate date) {
        return date != null && date.isAfter(LocalDate.now());
    }

    /**
     * Validate date is not in the future
     */
    public static boolean isPastOrTodayDate(LocalDate date) {
        return date != null && (date.isBefore(LocalDate.now()) || date.isEqual(LocalDate.now()));
    }

    /**
     * Validate date range: start <= end
     */
    public static boolean isValidDateRange(LocalDate startDate, LocalDate endDate) {
        return startDate != null && endDate != null && !startDate.isAfter(endDate);
    }

    /**
     * Validate salary is positive
     */
    public static boolean isValidSalary(double salary) {
        return salary > 0;
    }

    /**
     * Validate number of days is positive
     */
    public static boolean isValidDays(int days) {
        return days > 0;
    }

    /**
     * Validate role is valid
     */
    public static boolean isValidRole(String role) {
        return Constants.ROLE_ADMIN.equals(role) || 
               Constants.ROLE_MANAGER.equals(role) || 
               Constants.ROLE_EMPLOYEE.equals(role);
    }

    /**
     * Validate employee status
     */
    public static boolean isValidEmployeeStatus(String status) {
        return Constants.STATUS_ACTIVE.equals(status) || 
               Constants.STATUS_INACTIVE.equals(status) || 
               Constants.STATUS_ON_LEAVE.equals(status);
    }

    /**
     * Validate project status
     */
    public static boolean isValidProjectStatus(String status) {
        return Constants.PROJECT_STATUS_PLANNING.equals(status) || 
               Constants.PROJECT_STATUS_IN_PROGRESS.equals(status) || 
               Constants.PROJECT_STATUS_ON_HOLD.equals(status) || 
               Constants.PROJECT_STATUS_COMPLETED.equals(status);
    }

    /**
     * Validate leave type
     */
    public static boolean isValidLeaveType(String leaveType) {
        return Constants.LEAVE_TYPE_ANNUAL.equals(leaveType) || 
               Constants.LEAVE_TYPE_SICK.equals(leaveType) || 
               Constants.LEAVE_TYPE_PERSONAL.equals(leaveType) || 
               Constants.LEAVE_TYPE_COMPASSIONATE.equals(leaveType) || 
               Constants.LEAVE_TYPE_MATERNITY.equals(leaveType);
    }

    /**
     * Validate leave status
     */
    public static boolean isValidLeaveStatus(String status) {
        return Constants.LEAVE_STATUS_PENDING.equals(status) || 
               Constants.LEAVE_STATUS_APPROVED.equals(status) || 
               Constants.LEAVE_STATUS_REJECTED.equals(status) || 
               Constants.LEAVE_STATUS_CANCELLED.equals(status);
    }

    /**
     * Validate attendance status
     */
    public static boolean isValidAttendanceStatus(String status) {
        return Constants.ATTENDANCE_PRESENT.equals(status) || 
               Constants.ATTENDANCE_ABSENT.equals(status) || 
               Constants.ATTENDANCE_LEAVE.equals(status) || 
               Constants.ATTENDANCE_HALF_DAY.equals(status) || 
               Constants.ATTENDANCE_LATE.equals(status);
    }
}

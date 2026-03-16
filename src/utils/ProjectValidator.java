package utils;

import models.Project;
import repository.DataStore;
import java.time.LocalDate;

/**
 * ProjectValidator centralizes project-specific validation logic.
 */
public class ProjectValidator {

    /**
     * Validate project data for creation
     */
    public static String validateProjectCreation(String name, String description,
                                                 String managerId, LocalDate startDate,
                                                 LocalDate endDate) {
        if (!InputValidator.isRequired(name)) {
            return "Project name is required";
        }
        if (!InputValidator.isRequired(description)) {
            return "Project description is required";
        }
        if (!InputValidator.isRequired(managerId)) {
            return "Project manager is required";
        }
        if (startDate == null) {
            return "Start date is required";
        }
        if (endDate == null) {
            return "End date is required";
        }
        if (!InputValidator.isValidDateRange(startDate, endDate)) {
            return "Start date must be before or equal to end date";
        }
        if (!isValidProjectManager(managerId)) {
            return "Project manager not found or invalid";
        }
        return null; // No errors
    }

    /**
     * Validate project data for update
     */
    public static String validateProjectUpdate(String projectId, String name, 
                                              String description, LocalDate startDate,
                                              LocalDate endDate) {
        Project proj = DataStore.getInstance().getProject(projectId);
        if (proj == null) {
            return "Project not found";
        }
        if (!InputValidator.isRequired(name)) {
            return "Project name is required";
        }
        if (!InputValidator.isRequired(description)) {
            return "Project description is required";
        }
        if (startDate == null) {
            return "Start date is required";
        }
        if (endDate == null) {
            return "End date is required";
        }
        if (!InputValidator.isValidDateRange(startDate, endDate)) {
            return "Start date must be before or equal to end date";
        }
        return null;
    }

    /**
     * Validate project status
     */
    public static boolean isValidStatus(String status) {
        return InputValidator.isValidProjectStatus(status);
    }

    /**
     * Validate project manager exists and has required role
     */
    public static boolean isValidProjectManager(String managerId) {
        if (!InputValidator.isRequired(managerId)) {
            return false;
        }
        models.Employee manager = DataStore.getInstance().getEmployeeByUserId(managerId);
        return manager != null && 
               (Constants.ROLE_MANAGER.equals(manager.getRole()) || 
                Constants.ROLE_ADMIN.equals(manager.getRole()));
    }

    /**
     * Validate project name is not empty
     */
    public static boolean isValidName(String name) {
        return InputValidator.isRequired(name) && name.length() <= 100;
    }

    /**
     * Validate project description is not empty
     */
    public static boolean isValidDescription(String description) {
        return InputValidator.isRequired(description) && description.length() <= 500;
    }

    /**
     * Validate dates are valid
     */
    public static boolean isValidDates(LocalDate startDate, LocalDate endDate) {
        return startDate != null && endDate != null && 
               InputValidator.isValidDateRange(startDate, endDate);
    }

    /**
     * Validate project start date is not in the past
     */
    public static boolean isProjectStartDateValid(LocalDate startDate) {
        return startDate != null && !startDate.isBefore(LocalDate.now().minusDays(1));
    }
}

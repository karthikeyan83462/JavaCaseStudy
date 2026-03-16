package utils;

import models.Employee;
import repository.DataStore;
import java.time.LocalDate;

/**
 * EmployeeValidator centralizes employee-specific validation logic.
 */
public class EmployeeValidator {

    /**
     * Validate employee data for creation
     */
    public static String validateEmployeeCreation(String firstName, String lastName, 
                                                   String department, String position,
                                                   LocalDate dateOfJoining, double salary) {
        if (!InputValidator.isRequired(firstName)) {
            return "First name is required";
        }
        if (!InputValidator.isRequired(lastName)) {
            return "Last name is required";
        }
        if (!InputValidator.isRequired(department)) {
            return "Department is required";
        }
        if (!InputValidator.isRequired(position)) {
            return "Position is required";
        }
        if (dateOfJoining == null) {
            return "Date of joining is required";
        }
        if (!InputValidator.isValidSalary(salary)) {
            return "Salary must be greater than 0";
        }
        if (dateOfJoining.isAfter(LocalDate.now())) {
            return "Date of joining cannot be in the future";
        }
        return null; // No errors
    }

    /**
     * Validate employee data for update
     */
    public static String validateEmployeeUpdate(String employeeId, String firstName, 
                                               String lastName, String department,
                                               String position, double salary) {
        Employee emp = DataStore.getInstance().getEmployee(employeeId);
        if (emp == null) {
            return "Employee not found";
        }
        if (!InputValidator.isRequired(firstName)) {
            return "First name is required";
        }
        if (!InputValidator.isRequired(lastName)) {
            return "Last name is required";
        }
        if (!InputValidator.isRequired(department)) {
            return "Department is required";
        }
        if (!InputValidator.isRequired(position)) {
            return "Position is required";
        }
        if (!InputValidator.isValidSalary(salary)) {
            return "Salary must be greater than 0";
        }
        return null;
    }

    /**
     * Validate salary range
     */
    public static boolean isValidSalaryRange(double minSalary, double maxSalary) {
        return minSalary > 0 && maxSalary > 0 && minSalary <= maxSalary;
    }

    /**
     * Validate employee status
     */
    public static boolean isValidStatus(String status) {
        return InputValidator.isValidEmployeeStatus(status);
    }

    /**
     * Validate skill is not empty
     */
    public static boolean isValidSkill(String skill) {
        return InputValidator.isNotEmpty(skill);
    }

    /**
     * Validate manager exists and is actually a manager
     */
    public static boolean isValidManager(String managerUserId) {
        if (managerUserId == null || managerUserId.isEmpty()) {
            return true; // Manager is optional
        }
        Employee manager = DataStore.getInstance().getEmployeeByUserId(managerUserId);
        return manager != null && 
               (Constants.ROLE_MANAGER.equals(manager.getRole()) || 
                Constants.ROLE_ADMIN.equals(manager.getRole()));
    }

    /**
     * Validate department is not empty
     */
    public static boolean isValidDepartment(String department) {
        return InputValidator.isNotEmpty(department);
    }

    /**
     * Validate position is not empty
     */
    public static boolean isValidPosition(String position) {
        return InputValidator.isNotEmpty(position);
    }
}

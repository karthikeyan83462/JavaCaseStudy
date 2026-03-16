package utils;

import models.*;
import repository.DataStore;

/**
 * UIHelper provides reusable UI methods for displaying common information across controllers.
 * Centralizes menu display logic to eliminate duplication.
 */
public class UIHelper {

    /**
     * Display main menu based on user role
     */
    public static void displayMainMenu(User currentUser) {
        IOUtility.printHeader("EMPLOYEE RESOURCE MANAGEMENT SYSTEM");
        IOUtility.print("Welcome, " + currentUser.getUsername() + " (" + currentUser.getRole() + ")");
        IOUtility.printSubHeader("Main Menu");

        if (Constants.ROLE_ADMIN.equals(currentUser.getRole())) {
            displayAdminMenu();
        } else if (Constants.ROLE_MANAGER.equals(currentUser.getRole())) {
            displayManagerMenu();
        } else {
            displayEmployeeMenu();
        }
    }

    /**
     * Display admin menu options
     */
    public static void displayAdminMenu() {
        IOUtility.print("1. Manage Users");
        IOUtility.print("2. Manage Employees");
        IOUtility.print("3. Manage Projects");
        IOUtility.print("4. View Assignments");
        IOUtility.print("5. Manage Attendance");
        IOUtility.print("6. View Performance Reviews");
        IOUtility.print("7. View Reporting Dashboard");
        IOUtility.print("8. View Organizational Hierarchy");
        IOUtility.print("9. Manage Leave Requests");
        IOUtility.print("10. Logout");
    }

    /**
     * Display manager menu options
     */
    public static void displayManagerMenu() {
        IOUtility.print("1. View Team Employees");
        IOUtility.print("2. Manage Projects");
        IOUtility.print("3. View Assignments");
        IOUtility.print("4. Manage Attendance");
        IOUtility.print("5. Approve/Reject Leave");
        IOUtility.print("6. Create Performance Review");
        IOUtility.print("7. View Team Reports");
        IOUtility.print("8. View Profile");
        IOUtility.print("9. Logout");
    }

    /**
     * Display employee menu options
     */
    public static void displayEmployeeMenu() {
        IOUtility.print("1. View Profile");
        IOUtility.print("2. Apply for Leave");
        IOUtility.print("3. View Leave Status");
        IOUtility.print("4. View Assignments");
        IOUtility.print("5. View Attendance");
        IOUtility.print("6. View Performance Reviews");
        IOUtility.print("7. Logout");
    }

    /**
     * Display project details
     */
    public static void displayProjectDetails(Project project) {
        IOUtility.printSubHeader("Project Details");
        IOUtility.print("Project ID: " + project.getProjectId());
        IOUtility.print("Name: " + project.getName());
        IOUtility.print("Description: " + project.getDescription());
        IOUtility.print("Status: " + project.getStatus());
        IOUtility.print("Start Date: " + DateUtility.formatDate(project.getStartDate()));
        IOUtility.print("End Date: " + DateUtility.formatDate(project.getEndDate()));
        IOUtility.print("Team Members: " + project.getTeamMemberIds().size());
    }

    /**
     * Display employee details
     */
    public static void displayEmployeeDetails(Employee emp) {
        IOUtility.printSubHeader("Employee Details");
        IOUtility.print("Employee ID: " + emp.getEmployeeId());
        IOUtility.print("Name: " + emp.getFullName());
        IOUtility.print("Email: " + getEmployeeEmail(emp));
        IOUtility.print("Department: " + emp.getDepartment());
        IOUtility.print("Position: " + emp.getPosition());
        IOUtility.print("Status: " + emp.getStatus());
        IOUtility.print("Salary: " + String.format("%.2f", emp.getSalary()));
        IOUtility.print("Date of Joining: " + DateUtility.formatDate(emp.getDateOfJoining()));
        if (!emp.getSkills().isEmpty()) {
            IOUtility.print("Skills: " + String.join(", ", emp.getSkills()));
        }
    }

    /**
     * Display leave details
     */
    public static void displayLeaveDetails(Leave leave) {
        IOUtility.printSubHeader("Leave Details");
        IOUtility.print("Leave ID: " + leave.getLeaveId());
        IOUtility.print("Employee ID: " + leave.getEmployeeId());
        IOUtility.print("Type: " + leave.getLeaveType());
        IOUtility.print("Start Date: " + DateUtility.formatDate(leave.getStartDate()));
        IOUtility.print("End Date: " + DateUtility.formatDate(leave.getEndDate()));
        IOUtility.print("Days: " + leave.getNumberOfDays());
        IOUtility.print("Reason: " + leave.getReason());
        IOUtility.print("Status: " + leave.getStatus());
    }

    /**
     * Display attendance details
     */
    public static void displayAttendanceDetails(Attendance att) {
        IOUtility.printSubHeader("Attendance Details");
        IOUtility.print("Attendance ID: " + att.getAttendanceId());
        IOUtility.print("Employee ID: " + att.getEmployeeId());
        IOUtility.print("Date: " + DateUtility.formatDate(att.getDate()));
        IOUtility.print("Status: " + att.getStatus());
        if (att.getCheckInTime() != null) {
            IOUtility.print("Check-In: " + att.getCheckInTime());
        }
        if (att.getCheckOutTime() != null) {
            IOUtility.print("Check-Out: " + att.getCheckOutTime());
        }
        if (att.getWorkingHours() > 0) {
            IOUtility.print("Working Hours: " + String.format("%.2f", att.getWorkingHours()));
        }
    }

    /**
     * Display assignment details
     */
    public static void displayAssignmentDetails(Assignment assign) {
        IOUtility.printSubHeader("Assignment Details");
        IOUtility.print("Assignment ID: " + assign.getAssignmentId());
        IOUtility.print("Employee ID: " + assign.getEmployeeId());
        IOUtility.print("Project ID: " + assign.getProjectId());
        IOUtility.print("Role: " + assign.getRole());
        IOUtility.print("Allocation: " + assign.getAllocationPercentage() + "%");
        IOUtility.print("Status: " + assign.getStatus());
        IOUtility.print("Assigned Date: " + DateUtility.formatDate(assign.getAssignedDate()));
    }

    /**
     * Display performance review details
     */
    public static void displayReviewDetails(PerformanceReview review) {
        IOUtility.printSubHeader("Performance Review");
        IOUtility.print("Review ID: " + review.getReviewId());
        IOUtility.print("Employee ID: " + review.getEmployeeId());
        IOUtility.print("Review Date: " + DateUtility.formatDate(review.getReviewDate()));
        IOUtility.print("Rating: " + review.getPerformanceRating() + "/5");
        if (review.getComments() != null && !review.getComments().isEmpty()) {
            IOUtility.print("Comments: " + review.getComments());
        }
        IOUtility.print("Status: " + review.getOverallStatus());
    }

    /**
     * Display list table header with columns
     */
    public static void printTableHeader(String... columns) {
        IOUtility.printTableRow(columns);
        IOUtility.print("-".repeat(60));
    }

    /**
     * Get employee email from associated user record
     */
    private static String getEmployeeEmail(Employee emp) {
        User user = DataStore.getInstance().getUser(emp.getUserId());
        return user != null ? user.getEmail() : "N/A";
    }

    /**
     * Display assignment list
     */
    public static void displayAssignmentsList(java.util.List<Assignment> assignments) {
        if (assignments.isEmpty()) {
            IOUtility.printInfo("No assignments found");
            return;
        }
        printTableHeader("Assignment ID", "Employee", "Project", "Status");
        for (Assignment assign : assignments) {
            IOUtility.print(String.format("%-20s | %-15s | %-15s | %s", 
                assign.getAssignmentId(), assign.getEmployeeId(), 
                assign.getProjectId(), assign.getStatus()));
        }
    }

    /**
     * Display projects list
     */
    public static void displayProjectsList(java.util.List<Project> projects) {
        if (projects.isEmpty()) {
            IOUtility.printInfo("No projects found");
            return;
        }
        printTableHeader("Project ID", "Name", "Status");
        for (Project proj : projects) {
            IOUtility.print(String.format("%-20s | %-20s | %s", 
                proj.getProjectId(), proj.getName(), proj.getStatus()));
        }
    }

    /**
     * Display employees list
     */
    public static void displayEmployeesList(java.util.List<Employee> employees) {
        if (employees.isEmpty()) {
            IOUtility.printInfo("No employees found");
            return;
        }
        printTableHeader("Emp ID", "Name", "Department", "Position");
        for (Employee emp : employees) {
            IOUtility.print(String.format("%-12s | %-20s | %-15s | %s", 
                emp.getEmployeeId(), emp.getFullName(), emp.getDepartment(), emp.getPosition()));
        }
    }
}

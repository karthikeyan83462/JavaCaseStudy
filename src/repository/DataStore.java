package repository;

import models.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Singleton DataStore that maintains all in-memory data for the ERMS.
 * This class provides a centralized storage for all entities and is persisted to JSON files.
 */
public class DataStore {
    private static DataStore instance;

    private Map<String, User> users;
    private Map<String, Employee> employees;
    private Map<String, Project> projects;
    private Map<String, Assignment> assignments;
    private Map<String, Leave> leaves;
    private Map<String, Attendance> attendanceRecords;
    private Map<String, PerformanceReview> performanceReviews;
    private String currentUserId; // Track logged-in user

    private DataStore() {
        this.users = new HashMap<>();
        this.employees = new HashMap<>();
        this.projects = new HashMap<>();
        this.assignments = new HashMap<>();
        this.leaves = new HashMap<>();
        this.attendanceRecords = new HashMap<>();
        this.performanceReviews = new HashMap<>();
    }

    public static synchronized DataStore getInstance() {
        if (instance == null) {
            instance = new DataStore();
        }
        return instance;
    }

    // ============== User Operations ==============
    public void addUser(User user) {
        users.put(user.getUserId(), user);
    }

    public User getUser(String userId) {
        return users.get(userId);
    }

    public User getUserByUsername(String username) {
        if (username == null) return null;
        return users.values().stream()
                .filter(u -> u != null && u.getUsername() != null && u.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    public User getUserByEmail(String email) {
        if (email == null) return null;
        return users.values().stream()
                .filter(u -> u != null && u.getEmail() != null && u.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    public Collection<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public void updateUser(User user) {
        users.put(user.getUserId(), user);
    }

    public void deleteUser(String userId) {
        users.remove(userId);
    }

    // ============== Employee Operations ==============
    public void addEmployee(Employee employee) {
        employees.put(employee.getEmployeeId(), employee);
    }

    public Employee getEmployee(String employeeId) {
        return employees.get(employeeId);
    }

    public Employee getEmployeeByUserId(String userId) {
        if (userId == null) return null;
        return employees.values().stream()
                .filter(e -> e != null && e.getUserId() != null && e.getUserId().equals(userId))
                .findFirst()
                .orElse(null);
    }

    public Collection<Employee> getAllEmployees() {
        return new ArrayList<>(employees.values());
    }

    public Collection<Employee> getEmployeesByDepartment(String department) {
        if (department == null) return new ArrayList<>();
        return employees.values().stream()
                .filter(e -> e != null && e.getDepartment() != null && e.getDepartment().equals(department))
                .collect(Collectors.toList());
    }

    public void updateEmployee(Employee employee) {
        employees.put(employee.getEmployeeId(), employee);
    }

    // ============== Project Operations ==============
    public void addProject(Project project) {
        projects.put(project.getProjectId(), project);
    }

    public Project getProject(String projectId) {
        return projects.get(projectId);
    }

    public Collection<Project> getAllProjects() {
        return new ArrayList<>(projects.values());
    }

    public Collection<Project> getProjectsByManager(String managerId) {
        if (managerId == null) return new ArrayList<>();
        return projects.values().stream()
                .filter(p -> p != null && p.getManagerId() != null && p.getManagerId().equals(managerId))
                .collect(Collectors.toList());
    }

    public void updateProject(Project project) {
        projects.put(project.getProjectId(), project);
    }

    // ============== Assignment Operations ==============
    public void addAssignment(Assignment assignment) {
        assignments.put(assignment.getAssignmentId(), assignment);
    }

    public Assignment getAssignment(String assignmentId) {
        return assignments.get(assignmentId);
    }

    public Collection<Assignment> getAllAssignments() {
        return new ArrayList<>(assignments.values());
    }

    public Collection<Assignment> getAssignmentsByEmployee(String employeeId) {
        if (employeeId == null) return new ArrayList<>();
        return assignments.values().stream()
                .filter(a -> a != null && a.getEmployeeId() != null && a.getEmployeeId().equals(employeeId))
                .collect(Collectors.toList());
    }

    public Collection<Assignment> getAssignmentsByProject(String projectId) {
        if (projectId == null) return new ArrayList<>();
        return assignments.values().stream()
                .filter(a -> a != null && a.getProjectId() != null && a.getProjectId().equals(projectId))
                .collect(Collectors.toList());
    }

    public void updateAssignment(Assignment assignment) {
        assignments.put(assignment.getAssignmentId(), assignment);
    }

    // ============== Leave Operations ==============
    public void addLeave(Leave leave) {
        leaves.put(leave.getLeaveId(), leave);
    }

    public Leave getLeave(String leaveId) {
        return leaves.get(leaveId);
    }

    public Collection<Leave> getAllLeaves() {
        return new ArrayList<>(leaves.values());
    }

    public Collection<Leave> getLeavesByEmployee(String employeeId) {
        if (employeeId == null) return new ArrayList<>();
        return leaves.values().stream()
                .filter(l -> l != null && l.getEmployeeId() != null && l.getEmployeeId().equals(employeeId))
                .collect(Collectors.toList());
    }

    public Collection<Leave> getPendingLeaves() {
        return leaves.values().stream()
                .filter(l -> "PENDING".equals(l.getStatus()))
                .collect(Collectors.toList());
    }

    public void updateLeave(Leave leave) {
        leaves.put(leave.getLeaveId(), leave);
    }

    // ============== Attendance Operations ==============
    public void addAttendance(Attendance attendance) {
        attendanceRecords.put(attendance.getAttendanceId(), attendance);
    }

    public Attendance getAttendance(String attendanceId) {
        return attendanceRecords.get(attendanceId);
    }

    public Collection<Attendance> getAllAttendance() {
        return new ArrayList<>(attendanceRecords.values());
    }

    public Collection<Attendance> getAttendanceByEmployee(String employeeId) {
        if (employeeId == null) return new ArrayList<>();
        return attendanceRecords.values().stream()
                .filter(a -> a != null && a.getEmployeeId() != null && a.getEmployeeId().equals(employeeId))
                .collect(Collectors.toList());
    }

    public void updateAttendance(Attendance attendance) {
        attendanceRecords.put(attendance.getAttendanceId(), attendance);
    }

    // ============== Performance Review Operations ==============
    public void addPerformanceReview(PerformanceReview review) {
        performanceReviews.put(review.getReviewId(), review);
    }

    public PerformanceReview getPerformanceReview(String reviewId) {
        return performanceReviews.get(reviewId);
    }

    public Collection<PerformanceReview> getAllPerformanceReviews() {
        return new ArrayList<>(performanceReviews.values());
    }

    public Collection<PerformanceReview> getPerformanceReviewsByEmployee(String employeeId) {
        if (employeeId == null) return new ArrayList<>();
        return performanceReviews.values().stream()
                .filter(pr -> pr != null && pr.getEmployeeId() != null && pr.getEmployeeId().equals(employeeId))
                .collect(Collectors.toList());
    }

    public void updatePerformanceReview(PerformanceReview review) {
        performanceReviews.put(review.getReviewId(), review);
    }

    // ============== Session Management ==============
    public void setCurrentUser(String userId) {
        this.currentUserId = userId;
    }

    public String getCurrentUserId() {
        return currentUserId;
    }

    public User getCurrentUser() {
        return currentUserId != null ? users.get(currentUserId) : null;
    }

    public void logout() {
        this.currentUserId = null;
    }

    public void clearAllData() {
        users.clear();
        employees.clear();
        projects.clear();
        assignments.clear();
        leaves.clear();
        attendanceRecords.clear();
        performanceReviews.clear();
        currentUserId = null;
    }
}

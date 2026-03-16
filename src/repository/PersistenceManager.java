package repository;

import models.*;
import utils.IdGenerator;
import java.util.*;

/**
 * PersistenceManager handles saving and loading data to/from JSON files.
 * Delegates to JsonPersistenceManager for all file I/O operations.
 */
public class PersistenceManager {
    private static PersistenceManager instance;

    public static synchronized PersistenceManager getInstance() {
        if (instance == null) {
            instance = new PersistenceManager();
        }
        return instance;
    }

    public void saveData() {
        saveAllData();
    }

    public static void loadAllData() {
        DataStore store = DataStore.getInstance();
        store.clearAllData();
        
        loadIdCounters();
        loadUsers();
        loadEmployees();
        loadProjects();
        loadAssignments();
        loadLeaves();
        loadAttendance();
        loadPerformanceReviews();
    }

    public static void saveAllData() {
        saveIdCounters();
        saveUsers();
        saveEmployees();
        saveProjects();
        saveAssignments();
        saveLeaves();
        saveAttendance();
        savePerformanceReviews();
    }

    // ============== User Persistence ==============
    private static void loadUsers() {
        DataStore store = DataStore.getInstance();
        List<User> users = JsonPersistenceManager.loadUsers();
        for (User user : users) {
            store.addUser(user);
        }
    }

    private static void saveUsers() {
        DataStore store = DataStore.getInstance();
        JsonPersistenceManager.saveUsers(store.getAllUsers());
    }

    // ============== Employee Persistence ==============
    private static void loadEmployees() {
        DataStore store = DataStore.getInstance();
        List<Employee> employees = JsonPersistenceManager.loadEmployees();
        for (Employee emp : employees) {
            store.addEmployee(emp);
        }
    }

    private static void saveEmployees() {
        DataStore store = DataStore.getInstance();
        JsonPersistenceManager.saveEmployees(store.getAllEmployees());
    }

    // ============== Project Persistence ==============
    private static void loadProjects() {
        DataStore store = DataStore.getInstance();
        List<Project> projects = JsonPersistenceManager.loadProjects();
        for (Project proj : projects) {
            store.addProject(proj);
        }
    }

    private static void saveProjects() {
        DataStore store = DataStore.getInstance();
        JsonPersistenceManager.saveProjects(store.getAllProjects());
    }

    // ============== Assignment Persistence ==============
    private static void loadAssignments() {
        DataStore store = DataStore.getInstance();
        List<Assignment> assignments = JsonPersistenceManager.loadAssignments();
        for (Assignment assign : assignments) {
            store.addAssignment(assign);
        }
    }

    private static void saveAssignments() {
        DataStore store = DataStore.getInstance();
        JsonPersistenceManager.saveAssignments(store.getAllAssignments());
    }

    // ============== Leave Persistence ==============
    private static void loadLeaves() {
        DataStore store = DataStore.getInstance();
        List<Leave> leaves = JsonPersistenceManager.loadLeaves();
        for (Leave leave : leaves) {
            store.addLeave(leave);
        }
    }

    private static void saveLeaves() {
        DataStore store = DataStore.getInstance();
        JsonPersistenceManager.saveLeaves(store.getAllLeaves());
    }

    // ============== Attendance Persistence ==============
    private static void loadAttendance() {
        DataStore store = DataStore.getInstance();
        List<Attendance> attendanceRecords = JsonPersistenceManager.loadAttendance();
        for (Attendance att : attendanceRecords) {
            store.addAttendance(att);
        }
    }

    private static void saveAttendance() {
        DataStore store = DataStore.getInstance();
        JsonPersistenceManager.saveAttendance(store.getAllAttendance());
    }

    // ============== Performance Review Persistence ==============
    private static void loadPerformanceReviews() {
        DataStore store = DataStore.getInstance();
        List<PerformanceReview> reviews = JsonPersistenceManager.loadPerformanceReviews();
        for (PerformanceReview review : reviews) {
            store.addPerformanceReview(review);
        }
    }

    private static void savePerformanceReviews() {
        DataStore store = DataStore.getInstance();
        JsonPersistenceManager.savePerformanceReviews(store.getAllPerformanceReviews());
    }

    // ============== ID Counter Persistence ==============
    private static void loadIdCounters() {
        Map<String, Integer> counters = JsonPersistenceManager.loadIdCounters();
        for (Map.Entry<String, Integer> entry : counters.entrySet()) {
            IdGenerator.setCounter(entry.getKey(), entry.getValue());
        }
    }

    private static void saveIdCounters() {
        Map<String, Integer> counters = IdGenerator.getAllCounters();
        JsonPersistenceManager.saveIdCounters(counters);
    }
}

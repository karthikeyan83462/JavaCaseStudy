package repository;

import models.*;
import utils.JsonUtil;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

/**
 * JsonPersistenceManager handles all JSON file I/O operations.
 * Provides a unified interface for reading and writing JSON data files.
 * Uses native JSON parsing without external dependencies.
 */
public class JsonPersistenceManager {
    private static final String DATA_DIR = "data";
    private static final String USERS_FILE = "data/users.json";
    private static final String EMPLOYEES_FILE = "data/employees.json";
    private static final String PROJECTS_FILE = "data/projects.json";
    private static final String ASSIGNMENTS_FILE = "data/assignments.json";
    private static final String LEAVES_FILE = "data/leaves.json";
    private static final String ATTENDANCE_FILE = "data/attendance.json";
    private static final String REVIEWS_FILE = "data/reviews.json";
    private static final String ID_COUNTERS_FILE = "data/id_counters.json";

    static {
        new File(DATA_DIR).mkdirs();
    }

    // ============== User Methods ==============
    public static List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        try {
            String content = readFile(USERS_FILE);
            if (content == null || content.isEmpty()) {
                return users;
            }
            
            List<Map<String, Object>> jsonList = JsonUtil.parseJsonArray(content);
            for (Map<String, Object> map : jsonList) {
                users.add(mapToUser(map));
            }
        } catch (Exception e) {
            System.err.println("Error loading users: " + e.getMessage());
        }
        return users;
    }

    public static void saveUsers(Collection<User> userList) {
        try {
            List<Map<String, Object>> jsonList = new ArrayList<>();
            for (User user : userList) {
                jsonList.add(userToMap(user));
            }
            String json = buildJsonArray(jsonList);
            writeFile(USERS_FILE, json);
        } catch (Exception e) {
            System.err.println("Error saving users: " + e.getMessage());
        }
    }

    // ============== Employee Methods ==============
    public static List<Employee> loadEmployees() {
        List<Employee> employees = new ArrayList<>();
        try {
            String content = readFile(EMPLOYEES_FILE);
            if (content == null || content.isEmpty()) {
                return employees;
            }
            
            List<Map<String, Object>> jsonList = JsonUtil.parseJsonArray(content);
            for (Map<String, Object> map : jsonList) {
                employees.add(mapToEmployee(map));
            }
        } catch (Exception e) {
            System.err.println("Error loading employees: " + e.getMessage());
        }
        return employees;
    }

    public static void saveEmployees(Collection<Employee> empList) {
        try {
            List<Map<String, Object>> jsonList = new ArrayList<>();
            for (Employee emp : empList) {
                jsonList.add(employeeToMap(emp));
            }
            String json = buildJsonArray(jsonList);
            writeFile(EMPLOYEES_FILE, json);
        } catch (Exception e) {
            System.err.println("Error saving employees: " + e.getMessage());
        }
    }

    // ============== Project Methods ==============
    public static List<Project> loadProjects() {
        List<Project> projects = new ArrayList<>();
        try {
            String content = readFile(PROJECTS_FILE);
            if (content == null || content.isEmpty()) {
                return projects;
            }
            
            List<Map<String, Object>> jsonList = JsonUtil.parseJsonArray(content);
            for (Map<String, Object> map : jsonList) {
                projects.add(mapToProject(map));
            }
        } catch (Exception e) {
            System.err.println("Error loading projects: " + e.getMessage());
        }
        return projects;
    }

    public static void saveProjects(Collection<Project> projList) {
        try {
            List<Map<String, Object>> jsonList = new ArrayList<>();
            for (Project proj : projList) {
                if (proj != null) {
                    jsonList.add(projectToMap(proj));
                }
            }
            String json = buildJsonArray(jsonList);
            writeFile(PROJECTS_FILE, json);
        } catch (Exception e) {
            System.err.println("Error saving projects: " + (e.getMessage() != null ? e.getMessage() : e.toString()));
            e.printStackTrace();
        }
    }

    // ============== Assignment Methods ==============
    public static List<Assignment> loadAssignments() {
        List<Assignment> assignments = new ArrayList<>();
        try {
            String content = readFile(ASSIGNMENTS_FILE);
            if (content == null || content.isEmpty()) {
                return assignments;
            }
            
            List<Map<String, Object>> jsonList = JsonUtil.parseJsonArray(content);
            for (Map<String, Object> map : jsonList) {
                assignments.add(mapToAssignment(map));
            }
        } catch (Exception e) {
            System.err.println("Error loading assignments: " + e.getMessage());
        }
        return assignments;
    }

    public static void saveAssignments(Collection<Assignment> assignList) {
        try {
            List<Map<String, Object>> jsonList = new ArrayList<>();
            for (Assignment assign : assignList) {
                jsonList.add(assignmentToMap(assign));
            }
            String json = buildJsonArray(jsonList);
            writeFile(ASSIGNMENTS_FILE, json);
        } catch (Exception e) {
            System.err.println("Error saving assignments: " + e.getMessage());
        }
    }

    // ============== Leave Methods ==============
    public static List<Leave> loadLeaves() {
        List<Leave> leaves = new ArrayList<>();
        try {
            String content = readFile(LEAVES_FILE);
            if (content == null || content.isEmpty()) {
                return leaves;
            }
            
            List<Map<String, Object>> jsonList = JsonUtil.parseJsonArray(content);
            for (Map<String, Object> map : jsonList) {
                leaves.add(mapToLeave(map));
            }
        } catch (Exception e) {
            System.err.println("Error loading leaves: " + e.getMessage());
        }
        return leaves;
    }

    public static void saveLeaves(Collection<Leave> leaveList) {
        try {
            List<Map<String, Object>> jsonList = new ArrayList<>();
            for (Leave leave : leaveList) {
                jsonList.add(leaveToMap(leave));
            }
            String json = buildJsonArray(jsonList);
            writeFile(LEAVES_FILE, json);
        } catch (Exception e) {
            System.err.println("Error saving leaves: " + e.getMessage());
        }
    }

    // ============== Attendance Methods ==============
    public static List<Attendance> loadAttendance() {
        List<Attendance> records = new ArrayList<>();
        try {
            String content = readFile(ATTENDANCE_FILE);
            if (content == null || content.isEmpty()) {
                return records;
            }
            
            List<Map<String, Object>> jsonList = JsonUtil.parseJsonArray(content);
            for (Map<String, Object> map : jsonList) {
                records.add(mapToAttendance(map));
            }
        } catch (Exception e) {
            System.err.println("Error loading attendance: " + e.getMessage());
        }
        return records;
    }

    public static void saveAttendance(Collection<Attendance> attList) {
        try {
            List<Map<String, Object>> jsonList = new ArrayList<>();
            for (Attendance att : attList) {
                jsonList.add(attendanceToMap(att));
            }
            String json = buildJsonArray(jsonList);
            writeFile(ATTENDANCE_FILE, json);
        } catch (Exception e) {
            System.err.println("Error saving attendance: " + e.getMessage());
        }
    }

    // ============== Performance Review Methods ==============
    public static List<PerformanceReview> loadPerformanceReviews() {
        List<PerformanceReview> reviews = new ArrayList<>();
        try {
            String content = readFile(REVIEWS_FILE);
            if (content == null || content.isEmpty()) {
                return reviews;
            }
            
            List<Map<String, Object>> jsonList = JsonUtil.parseJsonArray(content);
            for (Map<String, Object> map : jsonList) {
                reviews.add(mapToPerformanceReview(map));
            }
        } catch (Exception e) {
            System.err.println("Error loading performance reviews: " + e.getMessage());
        }
        return reviews;
    }

    public static void savePerformanceReviews(Collection<PerformanceReview> revList) {
        try {
            List<Map<String, Object>> jsonList = new ArrayList<>();
            for (PerformanceReview review : revList) {
                jsonList.add(performanceReviewToMap(review));
            }
            String json = buildJsonArray(jsonList);
            writeFile(REVIEWS_FILE, json);
        } catch (Exception e) {
            System.err.println("Error saving performance reviews: " + e.getMessage());
        }
    }

    // ============== ID Counters Methods ==============
    public static Map<String, Integer> loadIdCounters() {
        Map<String, Integer> counters = new HashMap<>();
        try {
            String content = readFile(ID_COUNTERS_FILE);
            if (content == null || content.isEmpty()) {
                return counters;
            }
            
            Map<String, Object> map = JsonUtil.parseJsonObject(content);
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                Object val = entry.getValue();
                if (val instanceof Integer) {
                    counters.put(entry.getKey(), (Integer) val);
                } else if (val instanceof Double) {
                    counters.put(entry.getKey(), ((Double) val).intValue());
                } else if (val instanceof String) {
                    counters.put(entry.getKey(), Integer.parseInt((String) val));
                }
            }
        } catch (Exception e) {
            System.err.println("Error loading ID counters: " + e.getMessage());
        }
        return counters;
    }

    public static void saveIdCounters(Map<String, Integer> counters) {
        try {
            Map<String, Object> map = new LinkedHashMap<>();
            for (Map.Entry<String, Integer> entry : counters.entrySet()) {
                map.put(entry.getKey(), entry.getValue());
            }
            String json = JsonUtil.toJsonString(map);
            writeFile(ID_COUNTERS_FILE, json);
        } catch (Exception e) {
            System.err.println("Error saving ID counters: " + e.getMessage());
        }
    }

    // ============== Entity to Map Converters ==============
    private static Map<String, Object> userToMap(User user) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("userId", user.getUserId());
        map.put("username", user.getUsername());
        map.put("email", user.getEmail());
        map.put("passwordHash", user.getPasswordHash());
        map.put("role", user.getRole());
        map.put("active", user.isActive());
        return map;
    }

    private static User mapToUser(Map<String, Object> map) {
        String userId = getString(map, "userId");
        String username = getString(map, "username");
        String email = getString(map, "email");
        String passwordHash = getString(map, "passwordHash");
        String role = getString(map, "role");
        boolean active = getBoolean(map, "active", true);
        
        User user = new User(userId, username, email, passwordHash, role);
        if (!active) user.setActive(false);
        return user;
    }

    private static Map<String, Object> employeeToMap(Employee emp) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("employeeId", emp.getEmployeeId());
        map.put("userId", emp.getUserId());
        map.put("firstName", emp.getFirstName());
        map.put("lastName", emp.getLastName());
        map.put("department", emp.getDepartment());
        map.put("position", emp.getPosition());
        map.put("dateOfJoining", emp.getDateOfJoining().toString());
        map.put("managerUserId", emp.getManagerUserId());
        map.put("salary", emp.getSalary());
        map.put("status", emp.getStatus());
        map.put("skills", emp.getSkills());
        return map;
    }

    private static Employee mapToEmployee(Map<String, Object> map) {
        String employeeId = getString(map, "employeeId");
        String userId = getString(map, "userId");
        String firstName = getString(map, "firstName");
        String lastName = getString(map, "lastName");
        String department = getString(map, "department");
        String position = getString(map, "position");
        LocalDate doj = getLocalDate(map, "dateOfJoining");
        String managerUserId = getString(map, "managerUserId");
        double salary = getDouble(map, "salary", 0);
        
        Employee emp = new Employee(employeeId, userId, firstName, lastName, department, position, doj, managerUserId, salary);
        emp.setStatus(getString(map, "status", "ACTIVE"));
        
        Object skillsObj = map.get("skills");
        if (skillsObj instanceof List) {
            List<?> skillsList = (List<?>) skillsObj;
            for (Object skill : skillsList) {
                emp.addSkill(skill.toString());
            }
        }
        
        return emp;
    }

    private static Map<String, Object> projectToMap(Project proj) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("projectId", proj.getProjectId());
        map.put("name", proj.getName());
        map.put("description", proj.getDescription());
        map.put("managerId", proj.getManagerId());
        map.put("startDate", proj.getStartDate() != null ? proj.getStartDate().toString() : "");
        map.put("endDate", proj.getEndDate() != null ? proj.getEndDate().toString() : "");
        map.put("status", proj.getStatus() != null ? proj.getStatus() : "ACTIVE");
        return map;
    }

    private static Project mapToProject(Map<String, Object> map) {
        String projectId = getString(map, "projectId");
        String name = getString(map, "name");
        String description = getString(map, "description");
        String managerId = getString(map, "managerId");
        LocalDate startDate = getLocalDate(map, "startDate");
        LocalDate endDate = getLocalDate(map, "endDate");
        
        Project proj = new Project(projectId, name, description, managerId, startDate, endDate);
        proj.setStatus(getString(map, "status", "ACTIVE"));
        return proj;
    }

    private static Map<String, Object> assignmentToMap(Assignment assign) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("assignmentId", assign.getAssignmentId());
        map.put("employeeId", assign.getEmployeeId());
        map.put("projectId", assign.getProjectId());
        map.put("role", assign.getRole());
        map.put("assignedDate", assign.getAssignedDate().toString());
        map.put("allocationPercentage", assign.getAllocationPercentage());
        map.put("status", assign.getStatus());
        return map;
    }

    private static Assignment mapToAssignment(Map<String, Object> map) {
        String assignmentId = getString(map, "assignmentId");
        String employeeId = getString(map, "employeeId");
        String projectId = getString(map, "projectId");
        String role = getString(map, "role");
        LocalDate assignedDate = getLocalDate(map, "assignedDate");
        String allocationPercentage = getString(map, "allocationPercentage");
        
        Assignment assign = new Assignment(assignmentId, employeeId, projectId, role, assignedDate, allocationPercentage);
        assign.setStatus(getString(map, "status", "ASSIGNED"));
        return assign;
    }

    private static Map<String, Object> leaveToMap(Leave leave) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("leaveId", leave.getLeaveId());
        map.put("employeeId", leave.getEmployeeId());
        map.put("leaveType", leave.getLeaveType());
        map.put("startDate", leave.getStartDate().toString());
        map.put("endDate", leave.getEndDate().toString());
        map.put("reason", leave.getReason());
        map.put("status", leave.getStatus());
        return map;
    }

    private static Leave mapToLeave(Map<String, Object> map) {
        String leaveId = getString(map, "leaveId");
        String employeeId = getString(map, "employeeId");
        String leaveType = getString(map, "leaveType");
        LocalDate startDate = getLocalDate(map, "startDate");
        LocalDate endDate = getLocalDate(map, "endDate");
        String reason = getString(map, "reason");
        
        Leave leave = new Leave(leaveId, employeeId, leaveType, startDate, endDate, reason);
        leave.setStatus(getString(map, "status", "PENDING"));
        return leave;
    }

    private static Map<String, Object> attendanceToMap(Attendance att) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("attendanceId", att.getAttendanceId());
        map.put("employeeId", att.getEmployeeId());
        map.put("date", att.getDate().toString());
        if (att.getCheckInTime() != null) map.put("checkInTime", att.getCheckInTime().toString());
        if (att.getCheckOutTime() != null) map.put("checkOutTime", att.getCheckOutTime().toString());
        map.put("status", att.getStatus());
        return map;
    }

    private static Attendance mapToAttendance(Map<String, Object> map) {
        String attendanceId = getString(map, "attendanceId");
        String employeeId = getString(map, "employeeId");
        LocalDate date = getLocalDate(map, "date");
        
        Attendance att = new Attendance(attendanceId, employeeId, date);
        
        String checkInStr = getString(map, "checkInTime");
        if (checkInStr != null && !checkInStr.isEmpty()) {
            att.setCheckInTime(LocalTime.parse(checkInStr));
        }
        
        String checkOutStr = getString(map, "checkOutTime");
        if (checkOutStr != null && !checkOutStr.isEmpty()) {
            att.setCheckOutTime(LocalTime.parse(checkOutStr));
        }
        
        att.setStatus(getString(map, "status", "PRESENT"));
        return att;
    }

    private static Map<String, Object> performanceReviewToMap(PerformanceReview review) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("reviewId", review.getReviewId());
        map.put("employeeId", review.getEmployeeId());
        map.put("reviewerId", review.getReviewerId());
        map.put("reviewDate", review.getReviewDate().toString());
        map.put("performanceRating", review.getPerformanceRating());
        map.put("comments", review.getComments());
        map.put("overallStatus", review.getOverallStatus());
        return map;
    }

    private static PerformanceReview mapToPerformanceReview(Map<String, Object> map) {
        String reviewId = getString(map, "reviewId");
        String employeeId = getString(map, "employeeId");
        String reviewerId = getString(map, "reviewerId");
        LocalDate reviewDate = getLocalDate(map, "reviewDate");
        
        PerformanceReview review = new PerformanceReview(reviewId, employeeId, reviewerId, reviewDate);
        review.setPerformanceRating(getInt(map, "performanceRating", 5));
        review.setComments(getString(map, "comments"));
        review.setOverallStatus(getString(map, "overallStatus", "PENDING"));
        return review;
    }

    // ============== Helper Methods ==============
    private static String getString(Map<String, Object> map, String key) {
        Object val = map.get(key);
        return val != null ? val.toString() : null;
    }

    private static String getString(Map<String, Object> map, String key, String defaultVal) {
        Object val = map.get(key);
        return val != null ? val.toString() : defaultVal;
    }

    private static int getInt(Map<String, Object> map, String key, int defaultVal) {
        Object val = map.get(key);
        if (val instanceof Integer) return (Integer) val;
        if (val instanceof Double) return ((Double) val).intValue();
        if (val instanceof String) {
            try {
                return Integer.parseInt((String) val);
            } catch (NumberFormatException e) {
                return defaultVal;
            }
        }
        return defaultVal;
    }

    private static double getDouble(Map<String, Object> map, String key, double defaultVal) {
        Object val = map.get(key);
        if (val instanceof Double) return (Double) val;
        if (val instanceof Integer) return ((Integer) val).doubleValue();
        if (val instanceof String) {
            try {
                return Double.parseDouble((String) val);
            } catch (NumberFormatException e) {
                return defaultVal;
            }
        }
        return defaultVal;
    }

    private static boolean getBoolean(Map<String, Object> map, String key, boolean defaultVal) {
        Object val = map.get(key);
        if (val instanceof Boolean) return (Boolean) val;
        if (val instanceof String) return Boolean.parseBoolean((String) val);
        return defaultVal;
    }

    private static LocalDate getLocalDate(Map<String, Object> map, String key) {
        Object val = map.get(key);
        if (val != null) {
            try {
                return LocalDate.parse(val.toString());
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    private static String buildJsonArray(List<Map<String, Object>> list) {
        StringBuilder sb = new StringBuilder("[");
        boolean first = true;
        for (Map<String, Object> map : list) {
            if (!first) sb.append(",");
            first = false;
            sb.append(JsonUtil.toJsonString(map));
        }
        sb.append("]");
        return sb.toString();
    }

    private static String readFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) return null;
        
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        }
        return content.toString();
    }

    private static void writeFile(String filePath, String content) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.print(content);
        }
    }
}

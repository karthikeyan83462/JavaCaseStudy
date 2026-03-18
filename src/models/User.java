package models;
import java.util.HashMap;
import java.util.Map;

public class User {
    private static int counter=1;
    private String userId;
    private String name;
    private String email;
    private String passwordHash;
    private String empID;

    public User( String name, String email, String passwordHash,
                String empID) {

        this.userId = "USR"+String.valueOf(counter++);
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.empID = empID;
    }
    public User( String userID, String name, String email, String passwordHash,
                 String empID) {

        this.userId = userID;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.empID = empID;
    }

    // Getters
    public String getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
    public String getEmpID() { return empID; }

    // Setters
//    public void setUserId(String userId) { this.userId = userId; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public void setEmpID(String role) { this.empID = role; }
    // Convert to Map
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();

        map.put("userId", userId);
        map.put("name", name);
        map.put("email", email);
        map.put("passwordHash", passwordHash);
        map.put("empID", empID);

        return map;
    }

    // Convert back to user
    public static User fromMap(Map<String, String> map) {
        return new User(
                map.get("userId"),
                map.get("name"),
                map.get("email"),
                map.get("passwordHash"),
                map.get("empID")
        );
    }
}
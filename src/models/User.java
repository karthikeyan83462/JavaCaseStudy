package models;
import java.util.*;

public class User {
    private static int counter = 1;

    private String userId;
    private String name;
    private String email;
    private String password;
    private String empID;

    public User(String name, String email, String password, String empID) {
        this.userId = "USR" + counter++;
        this.name = name;
        this.email = email;
        this.password = password;
        this.empID = empID;
    }

    public User(String userID, String name, String email, String password, String empID) {
        this.userId = userID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.empID = empID;
    }

    public String getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getEmpID() { return empID; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setEmpID(String empID) { this.empID = empID; }

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();

        map.put("userId", userId);
        map.put("name", name);
        map.put("email", email);
        map.put("password", password);
        map.put("empID", empID);

        return map;
    }

    public static User fromMap(Map<String, String> map) {
        return new User(
                map.get("userId"),
                map.get("name"),
                map.get("email"),
                map.get("password"),
                map.get("empID")
        );
    }
}
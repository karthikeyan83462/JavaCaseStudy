
import java.util.HashMap;
import java.util.Map;

public class User {

    private String userId;
    private String name;
    private String email;
    private String passwordHash;
    private String role;

    public User(String userId, String name, String email, String passwordHash,
                String role) {

        this.userId = userId;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    // Getters
    public String getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
    public String getRole() { return role; }

    // Setters
    public void setUserId(String userId) { this.userId = userId; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public void setRole(String role) { this.role = role; }
    // Convert to Map
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();

        map.put("userId", userId);
        map.put("name", name);
        map.put("email", email);
        map.put("passwordHash", passwordHash);
        map.put("role", role);

        return map;
    }

    // Convert back to user
    public static User fromMap(Map<String, String> map) {
        return new User(
                map.get("userId"),
                map.get("name"),
                map.get("email"),
                map.get("passwordHash"),
                map.get("role")
        );
    }
}
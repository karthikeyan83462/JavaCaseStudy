package models;
import java.util.*;

public class Employee {
    private static int counter = 1;

    private String empID;
    private String name;
    private List<String> skills;
    private Role role;
    private EmployeeStatus status;

    public Employee(String name, Role role, List<String> skills, EmployeeStatus status) {
        this.empID = "EMP" + counter++;
        this.name = name;
        this.role = role;
        this.skills = skills;
        this.status = status;
    }

    public Employee(String empID, String name, Role role, List<String> skills, EmployeeStatus status) {
        this.empID = empID;
        this.name = name;
        this.role = role;
        this.skills = skills;
        this.status = status;
    }

    public String getEmpID() { return empID; }
    public String getName() { return name; }
    public List<String> getSkills() { return skills; }
    public Role getRole() { return role; }
    public EmployeeStatus getStatus() { return status; }

    public void setName(String name) { this.name = name; }
    public void setSkills(List<String> skills) { this.skills = skills; }
    public void setRole(Role role) { this.role = role; }
    public void setStatus(EmployeeStatus status) { this.status = status; }

    // Convert to Map (STRING SAFE)
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();

        map.put("empID", empID);
        map.put("name", name);
        map.put("role", role.name());
        map.put("skills", String.join(";", skills)); // list → string
        map.put("status", status.name());

        return map;
    }

    public static Employee fromMap(Map<String, String> map) {
        return new Employee(
                map.get("empID"),
                map.get("name"),
                Role.valueOf(map.get("role")),
                Arrays.asList(map.getOrDefault("skills", "").split(";")),
                EmployeeStatus.valueOf(map.get("status"))
        );
    }
}
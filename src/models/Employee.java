package models;
import java.util.*;

public class Employee {
    private static int counter = 1;

    private String empID;
    private String name;
    private List<String> skills;
    private Role role;
    private EmployeeStatus status;
    private String ProjectID;

    public Employee(String name, Role role, List<String> skills) {
        this.empID = "EMP" + counter++;
        this.name = name;
        this.role = role;
        this.skills = skills;
        this.status = EmployeeStatus.INACTIVE;
        this.ProjectID=null;
    }

    public Employee(String empID, String name, Role role, List<String> skills, EmployeeStatus status, String PrID) {
        this.empID = empID;
        this.name = name;
        this.role = role;
        this.skills = skills;
        this.status = status;
        this.ProjectID=PrID;
    }
    public Employee(){

    }

    public String getEmpID() { return empID; }
    public String getName() { return name; }
    public List<String> getSkills() { return skills; }
    public Role getRole() { return role; }
    public EmployeeStatus getStatus() { return status; }
    public String getProjectID() { return ProjectID;}
    public void setName(String name) { this.name = name; }
    public void setSkills(List<String> skills) { this.skills = skills; }
    public void setRole(Role role) { this.role = role; }
    public void setStatus(EmployeeStatus status) { this.status = status; }
    public void setProjectID(String ProjectID) {this.ProjectID=ProjectID;}

    // Convert to Map (STRING SAFE)
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();

        map.put("empID", empID);
        map.put("name", name);
        map.put("role", role.name());
        map.put("skills", String.join(";", skills)); // list → string
        map.put("status", status.name());
        map.put("ProjectID", ProjectID);

        return map;
    }

    public static Employee fromMap(Map<String, String> map) {
        return new Employee(
                map.get("empID"),
                map.get("name"),
                Role.valueOf(map.get("role")),
                Arrays.asList(map.getOrDefault("skills", "").split(";")),
                EmployeeStatus.valueOf(map.get("status")),
                map.get("ProjectID")
        );
    }
}
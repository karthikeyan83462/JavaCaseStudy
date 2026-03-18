package models;
import javax.management.relation.Role;
import java.util.*;
public class Employee {
    private static int counter=1;
    private String empID;
    private String name;
    private List<String> Skills;
    private Role role;
    private EmployeeStatus Status;

    public Employee(String name, Role role, List<String> skills, EmployeeStatus status) {
        this.empID = "EMP" + String.valueOf(counter++);
        this.name = name;
        this.role = role;
        Skills = skills;
        Status = status;
    }
    public Employee(String empID, String name, Role role, List<String> skills, EmployeeStatus status) {
        this.empID = empID;
        this.name = name;
        this.role = role;
        Skills = skills;
        Status = status;
    }

    public String getEmpID() {
        return empID;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSkills() {
        return Skills;
    }

    public void setSkills(List<String> skills) {
        Skills = skills;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public EmployeeStatus getStatus() {
        return Status;
    }

    public void setStatus(EmployeeStatus status) {
        Status = status;
    }
}

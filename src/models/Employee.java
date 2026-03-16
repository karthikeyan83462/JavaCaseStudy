package models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Employee {
    private String employeeId;
    private String userId; // Reference to User
    private String firstName;
    private String lastName;
    private String department;
    private String position;
    private String role; // ADMIN, MANAGER, EMPLOYEE
    private LocalDate dateOfJoining;
    private String managerUserId; // Reference to manager's userId
    private double salary;
    private String status; // ACTIVE, INACTIVE, ON_LEAVE
    private List<String> skills;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Employee(String employeeId, String userId, String firstName, String lastName,
                   String department, String position, LocalDate dateOfJoining,
                   String managerUserId, double salary) {
        this.employeeId = employeeId;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.position = position;
        this.role = "EMPLOYEE";
        this.dateOfJoining = dateOfJoining;
        this.managerUserId = managerUserId;
        this.salary = salary;
        this.status = "ACTIVE";
        this.skills = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Getters
    public String getEmployeeId() { return employeeId; }
    public String getId() { return employeeId; }
    public String getUserId() { return userId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getName() { return getFullName(); }
    public String getDepartment() { return department; }
    public String getPosition() { return position; }
    public String getRole() { return role; }
    public LocalDate getDateOfJoining() { return dateOfJoining; }
    public String getManagerUserId() { return managerUserId; }
    public double getSalary() { return salary; }
    public String getStatus() { return status; }
    public List<String> getSkills() { return new ArrayList<>(skills); }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    // Setters
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setDepartment(String department) { this.department = department; }
    public void setPosition(String position) { this.position = position; }
    public void setRole(String role) { this.role = role; }
    public void setManagerUserId(String managerUserId) { this.managerUserId = managerUserId; }
    public void setSalary(double salary) { this.salary = salary; }
    public void setStatus(String status) { this.status = status; }
    public void setSkills(List<String> skills) { this.skills = new ArrayList<>(skills); }
    public void addSkill(String skill) { this.skills.add(skill); }
    public void removeSkill(String skill) { this.skills.remove(skill); }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId='" + employeeId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", department='" + department + '\'' +
                ", position='" + position + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

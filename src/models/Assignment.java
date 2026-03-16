package models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Assignment {
    private String assignmentId;
    private String employeeId;
    private String projectId;
    private String role; // Developer, Designer, QA, etc.
    private LocalDate assignedDate;
    private LocalDate unassignedDate;
    private String allocationPercentage; // 25%, 50%, 75%, 100%
    private String status; // ASSIGNED, COMPLETED, CANCELLED
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Assignment(String assignmentId, String employeeId, String projectId,
                     String role, LocalDate assignedDate, String allocationPercentage) {
        this.assignmentId = assignmentId;
        this.employeeId = employeeId;
        this.projectId = projectId;
        this.role = role;
        this.assignedDate = assignedDate;
        this.allocationPercentage = allocationPercentage;
        this.status = "ASSIGNED";
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Getters
    public String getAssignmentId() { return assignmentId; }
    public String getEmployeeId() { return employeeId; }
    public String getProjectId() { return projectId; }
    public String getRole() { return role; }
    public LocalDate getAssignedDate() { return assignedDate; }
    public LocalDate getUnassignedDate() { return unassignedDate; }
    public String getAllocationPercentage() { return allocationPercentage; }
    public String getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    // Setters
    public void setRole(String role) { this.role = role; }
    public void setAllocationPercentage(String allocationPercentage) { this.allocationPercentage = allocationPercentage; }
    public void setStatus(String status) { this.status = status; }
    public void setUnassignedDate(LocalDate unassignedDate) { this.unassignedDate = unassignedDate; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    @Override
    public String toString() {
        return "Assignment{" +
                "assignmentId='" + assignmentId + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", projectId='" + projectId + '\'' +
                ", role='" + role + '\'' +
                ", allocation='" + allocationPercentage + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

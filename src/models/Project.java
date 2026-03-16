package models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Project {
    private String projectId;
    private String name;
    private String description;
    private String managerId; // userId of project manager
    private LocalDate startDate;
    private LocalDate endDate;
    private String status; // PLANNING, IN_PROGRESS, ON_HOLD, COMPLETED
    private List<String> teamMemberIds; // List of employee userIds
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Project(String projectId, String name, String description, String managerId,
                   LocalDate startDate, LocalDate endDate) {
        this.projectId = projectId;
        this.name = name;
        this.description = description;
        this.managerId = managerId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = "PLANNING";
        this.teamMemberIds = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Getters
    public String getProjectId() { return projectId; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getManagerId() { return managerId; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public String getStatus() { return status; }
    public List<String> getTeamMemberIds() { return new ArrayList<>(teamMemberIds); }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public void setStatus(String status) { this.status = status; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public void addTeamMember(String userId) {
        if (!teamMemberIds.contains(userId)) {
            teamMemberIds.add(userId);
            this.updatedAt = LocalDateTime.now();
        }
    }

    public void removeTeamMember(String userId) {
        if (teamMemberIds.remove(userId)) {
            this.updatedAt = LocalDateTime.now();
        }
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId='" + projectId + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", teamSize=" + teamMemberIds.size() +
                '}';
    }
}

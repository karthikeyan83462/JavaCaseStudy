package models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PerformanceReview {
    private String reviewId;
    private String employeeId;
    private String reviewerId; // userId of reviewer (typically manager)
    private LocalDate reviewDate;
    private int performanceRating; // 1-5 scale
    private String comments;
    private String strengths;
    private String areasForImprovement;
    private String goals;
    private String overallStatus; // PENDING, COMPLETED, ACKNOWLEDGED
    private LocalDateTime acknowledgedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PerformanceReview(String reviewId, String employeeId, String reviewerId,
                            LocalDate reviewDate) {
        this.reviewId = reviewId;
        this.employeeId = employeeId;
        this.reviewerId = reviewerId;
        this.reviewDate = reviewDate;
        this.performanceRating = 3; // Default to neutral
        this.overallStatus = "PENDING";
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Getters
    public String getReviewId() { return reviewId; }
    public String getEmployeeId() { return employeeId; }
    public String getReviewerId() { return reviewerId; }
    public LocalDate getReviewDate() { return reviewDate; }
    public int getPerformanceRating() { return performanceRating; }
    public String getComments() { return comments; }
    public String getStrengths() { return strengths; }
    public String getAreasForImprovement() { return areasForImprovement; }
    public String getGoals() { return goals; }
    public String getOverallStatus() { return overallStatus; }
    public LocalDateTime getAcknowledgedAt() { return acknowledgedAt; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    // Setters
    public void setPerformanceRating(int performanceRating) { this.performanceRating = Math.min(5, Math.max(1, performanceRating)); }
    public void setComments(String comments) { this.comments = comments; }
    public void setStrengths(String strengths) { this.strengths = strengths; }
    public void setAreasForImprovement(String areasForImprovement) { this.areasForImprovement = areasForImprovement; }
    public void setGoals(String goals) { this.goals = goals; }
    public void setOverallStatus(String overallStatus) { this.overallStatus = overallStatus; }
    public void setAcknowledgedAt(LocalDateTime acknowledgedAt) { this.acknowledgedAt = acknowledgedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    @Override
    public String toString() {
        return "PerformanceReview{" +
                "reviewId='" + reviewId + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", rating=" + performanceRating +
                ", status='" + overallStatus + '\'' +
                '}';
    }
}

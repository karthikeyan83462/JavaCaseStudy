package models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Leave {
    private String leaveId;
    private String employeeId;
    private String leaveType; // ANNUAL, SICK, PERSONAL, COMPASSIONATE, MATERNITY
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    private String status; // PENDING, APPROVED, REJECTED, CANCELLED
    private String approverUserId;
    private LocalDateTime approvedAt;
    private String rejectionReason;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Leave(String leaveId, String employeeId, String leaveType,
                LocalDate startDate, LocalDate endDate, String reason) {
        this.leaveId = leaveId;
        this.employeeId = employeeId;
        this.leaveType = leaveType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.status = "PENDING";
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Getters
    public String getLeaveId() { return leaveId; }
    public String getEmployeeId() { return employeeId; }
    public String getLeaveType() { return leaveType; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public String getReason() { return reason; }
    public String getStatus() { return status; }
    public String getApproverUserId() { return approverUserId; }
    public LocalDateTime getApprovedAt() { return approvedAt; }
    public String getRejectionReason() { return rejectionReason; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    // Setters
    public void setStatus(String status) { this.status = status; }
    public void setApproverUserId(String approverUserId) { this.approverUserId = approverUserId; }
    public void setApprovedAt(LocalDateTime approvedAt) { this.approvedAt = approvedAt; }
    public void setRejectionReason(String rejectionReason) { this.rejectionReason = rejectionReason; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public int getNumberOfDays() {
        return (int) java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate) + 1;
    }

    @Override
    public String toString() {
        return "Leave{" +
                "leaveId='" + leaveId + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", leaveType='" + leaveType + '\'' +
                ", days=" + getNumberOfDays() +
                ", status='" + status + '\'' +
                '}';
    }
}

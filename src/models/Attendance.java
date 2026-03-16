package models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

public class Attendance {
    private String attendanceId;
    private String employeeId;
    private LocalDate date;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
    private String status; // PRESENT, ABSENT, LEAVE, HALF_DAY, LATE
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Attendance(String attendanceId, String employeeId, LocalDate date) {
        this.attendanceId = attendanceId;
        this.employeeId = employeeId;
        this.date = date;
        this.status = "ABSENT";
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Getters
    public String getAttendanceId() { return attendanceId; }
    public String getEmployeeId() { return employeeId; }
    public LocalDate getDate() { return date; }
    public LocalTime getCheckInTime() { return checkInTime; }
    public LocalTime getCheckOutTime() { return checkOutTime; }
    public String getStatus() { return status; }
    public String getNotes() { return notes; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    // Setters
    public void setCheckInTime(LocalTime checkInTime) { this.checkInTime = checkInTime; }
    public void setCheckOutTime(LocalTime checkOutTime) { this.checkOutTime = checkOutTime; }
    public void setStatus(String status) { this.status = status; }
    public void setNotes(String notes) { this.notes = notes; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public double getWorkingHours() {
        if (checkInTime != null && checkOutTime != null) {
            return java.time.temporal.ChronoUnit.MINUTES.between(checkInTime, checkOutTime) / 60.0;
        }
        return 0.0;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "attendanceId='" + attendanceId + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", date=" + date +
                ", status='" + status + '\'' +
                ", workingHours=" + getWorkingHours() +
                '}';
    }
}

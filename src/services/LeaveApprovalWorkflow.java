package services;

import models.*;
import repository.DataStore;
import utils.Constants;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * LeaveApprovalWorkflow manages leave request lifecycle and approval workflow.
 * Extracted to eliminate leave approval logic duplication across controllers.
 */
public class LeaveApprovalWorkflow {
    private DataStore store = DataStore.getInstance();

    /**
     * Get pending leave requests for approval
     */
    public List<Leave> getPendingLeaveRequests() {
        return store.getAllLeaves().stream()
                .filter(l -> Constants.LEAVE_STATUS_PENDING.equals(l.getStatus()))
                .collect(Collectors.toList());
    }

    /**
     * Get pending leave requests for specific manager
     */
    public List<Leave> getPendingLeavesForManager(String managerId) {
        return getPendingLeaveRequests().stream()
                .filter(l -> {
                    Employee emp = store.getEmployee(l.getEmployeeId());
                    return emp != null && managerId.equals(emp.getManagerUserId());
                })
                .collect(Collectors.toList());
    }

    /**
     * Get leave history for employee
     */
    public List<Leave> getEmployeeLeaveHistory(String employeeId) {
        return store.getAllLeaves().stream()
                .filter(l -> l.getEmployeeId().equals(employeeId))
                .collect(Collectors.toList());
    }

    /**
     * Get employee's approved leaves
     */
    public List<Leave> getApprovedLeaves(String employeeId) {
        return getEmployeeLeaveHistory(employeeId).stream()
                .filter(l -> Constants.LEAVE_STATUS_APPROVED.equals(l.getStatus()))
                .collect(Collectors.toList());
    }

    /**
     * Approve leave request
     */
    public boolean approveLeave(String leaveId, String approverId) {
        Leave leave = store.getLeave(leaveId);
        if (leave == null || !Constants.LEAVE_STATUS_PENDING.equals(leave.getStatus())) {
            return false;
        }

        leave.setStatus(Constants.LEAVE_STATUS_APPROVED);
        leave.setApproverUserId(approverId);
        leave.setApprovedAt(LocalDateTime.now());
        leave.setUpdatedAt(LocalDateTime.now());
        store.updateLeave(leave);

        // Update employee status if on extended leave
        updateEmployeeLeaveStatus(leave.getEmployeeId());
        return true;
    }

    /**
     * Reject leave request
     */
    public boolean rejectLeave(String leaveId, String rejectionReason) {
        Leave leave = store.getLeave(leaveId);
        if (leave == null || !Constants.LEAVE_STATUS_PENDING.equals(leave.getStatus())) {
            return false;
        }

        leave.setStatus(Constants.LEAVE_STATUS_REJECTED);
        leave.setRejectionReason(rejectionReason);
        leave.setUpdatedAt(LocalDateTime.now());
        store.updateLeave(leave);
        return true;
    }

    /**
     * Cancel leave request
     */
    public boolean cancelLeave(String leaveId) {
        Leave leave = store.getLeave(leaveId);
        if (leave == null || Constants.LEAVE_STATUS_REJECTED.equals(leave.getStatus())) {
            return false;
        }

        leave.setStatus(Constants.LEAVE_STATUS_CANCELLED);
        leave.setUpdatedAt(LocalDateTime.now());
        store.updateLeave(leave);

        // Update employee status
        updateEmployeeLeaveStatus(leave.getEmployeeId());
        return true;
    }

    /**
     * Get total approved leave days for employee in date range
     */
    public int getTotalAppprovedLeaveDays(String employeeId, java.time.LocalDate startDate, 
                                         java.time.LocalDate endDate) {
        return (int) getApprovedLeaves(employeeId).stream()
                .filter(l -> !l.getStartDate().isAfter(endDate) && !l.getEndDate().isBefore(startDate))
                .mapToInt(Leave::getNumberOfDays)
                .sum();
    }

    /**
     * Check if employee has overlapping leave requests
     */
    public boolean hasOverlappingLeave(String employeeId, java.time.LocalDate startDate, 
                                      java.time.LocalDate endDate) {
        return getApprovedLeaves(employeeId).stream()
                .anyMatch(l -> !l.getStartDate().isAfter(endDate) && !l.getEndDate().isBefore(startDate));
    }

    /**
     * Get leave statistics for employee
     */
    public Map<String, Object> getLeaveStatistics(String employeeId) {
        List<Leave> history = getEmployeeLeaveHistory(employeeId);
        Map<String, Long> leaveByType = history.stream()
                .filter(l -> Constants.LEAVE_STATUS_APPROVED.equals(l.getStatus()))
                .collect(Collectors.groupingBy(Leave::getLeaveType, Collectors.counting()));

        int totalApprovedDays = (int) history.stream()
                .filter(l -> Constants.LEAVE_STATUS_APPROVED.equals(l.getStatus()))
                .mapToInt(Leave::getNumberOfDays)
                .sum();

        int pendingRequests = (int) history.stream()
                .filter(l -> Constants.LEAVE_STATUS_PENDING.equals(l.getStatus()))
                .count();

        Map<String, Object> stats = new HashMap<>();
        stats.put("employeeId", employeeId);
        stats.put("totalApprovedDays", totalApprovedDays);
        stats.put("pendingRequests", pendingRequests);
        stats.put("leaveByType", leaveByType);
        return stats;
    }

    /**
     * Update employee status based on active leave
     */
    private void updateEmployeeLeaveStatus(String employeeId) {
        Employee emp = store.getEmployee(employeeId);
        if (emp == null) return;

        java.time.LocalDate today = java.time.LocalDate.now();
        boolean isOnLeave = getApprovedLeaves(employeeId).stream()
                .anyMatch(l -> !l.getStartDate().isAfter(today) && !l.getEndDate().isBefore(today));

        if (isOnLeave) {
            emp.setStatus(Constants.STATUS_ON_LEAVE);
        } else if (Constants.STATUS_ON_LEAVE.equals(emp.getStatus())) {
            emp.setStatus(Constants.STATUS_ACTIVE);
        }
        store.updateEmployee(emp);
    }

    /**
     * Validate leave request before submission
     */
    public String validateLeaveRequest(String employeeId, java.time.LocalDate startDate, 
                                      java.time.LocalDate endDate, String leaveType) {
        // Check if employee exists
        Employee emp = store.getEmployee(employeeId);
        if (emp == null) {
            return "Employee not found";
        }

        // Check date validity
        if (startDate.isAfter(endDate)) {
            return "Start date cannot be after end date";
        }

        // Check if dates are in past
        if (endDate.isBefore(java.time.LocalDate.now())) {
            return "Cannot apply for leave in the past";
        }

        // Check for overlapping leaves
        if (hasOverlappingLeave(employeeId, startDate, endDate)) {
            return "Employee already has approved leave during this period";
        }

        // Validate leave type
        if (!isValidLeaveType(leaveType)) {
            return "Invalid leave type";
        }

        return null; // No validation errors
    }

    /**
     * Check if leave type is valid
     */
    private boolean isValidLeaveType(String leaveType) {
        return Constants.LEAVE_TYPE_ANNUAL.equals(leaveType) ||
               Constants.LEAVE_TYPE_SICK.equals(leaveType) ||
               Constants.LEAVE_TYPE_PERSONAL.equals(leaveType) ||
               Constants.LEAVE_TYPE_COMPASSIONATE.equals(leaveType) ||
               Constants.LEAVE_TYPE_MATERNITY.equals(leaveType);
    }
}

package utils;

import models.Leave;
import repository.DataStore;
import services.LeaveApprovalWorkflow;
import java.time.LocalDate;

/**
 * LeaveValidator centralizes leave-specific validation logic.
 */
public class LeaveValidator {

    /**
     * Validate leave request data for creation
     */
    public static String validateLeaveCreation(String employeeId, String leaveType,
                                              LocalDate startDate, LocalDate endDate,
                                              String reason) {
        if (!InputValidator.isRequired(employeeId)) {
            return "Employee ID is required";
        }
        models.Employee emp = DataStore.getInstance().getEmployee(employeeId);
        if (emp == null) {
            return "Employee not found";
        }
        if (!InputValidator.isValidLeaveType(leaveType)) {
            return "Invalid leave type";
        }
        if (startDate == null) {
            return "Start date is required";
        }
        if (endDate == null) {
            return "End date is required";
        }
        if (!InputValidator.isValidDateRange(startDate, endDate)) {
            return "Start date must be before or equal to end date";
        }
        if (endDate.isBefore(LocalDate.now())) {
            return "Cannot apply for leave in the past";
        }
        if (!InputValidator.isRequired(reason)) {
            return "Reason for leave is required";
        }

        // Check for overlapping leaves
        LeaveApprovalWorkflow workflow = new LeaveApprovalWorkflow();
        if (workflow.hasOverlappingLeave(employeeId, startDate, endDate)) {
            return "Employee already has approved leave during this period";
        }

        return null; // No errors
    }

    /**
     * Validate leave approval
     */
    public static String validateLeaveApproval(String leaveId, String approverId) {
        Leave leave = DataStore.getInstance().getLeave(leaveId);
        if (leave == null) {
            return "Leave request not found";
        }
        if (!Constants.LEAVE_STATUS_PENDING.equals(leave.getStatus())) {
            return "Only pending leave requests can be approved";
        }
        if (!InputValidator.isRequired(approverId)) {
            return "Approver ID is required";
        }
        return null;
    }

    /**
     * Validate leave rejection
     */
    public static String validateLeaveRejection(String leaveId, String rejectionReason) {
        Leave leave = DataStore.getInstance().getLeave(leaveId);
        if (leave == null) {
            return "Leave request not found";
        }
        if (!Constants.LEAVE_STATUS_PENDING.equals(leave.getStatus())) {
            return "Only pending leave requests can be rejected";
        }
        if (!InputValidator.isRequired(rejectionReason)) {
            return "Rejection reason is required";
        }
        return null;
    }

    /**
     * Validate leave cancellation
     */
    public static String validateLeaveCancellation(String leaveId) {
        Leave leave = DataStore.getInstance().getLeave(leaveId);
        if (leave == null) {
            return "Leave request not found";
        }
        if (Constants.LEAVE_STATUS_REJECTED.equals(leave.getStatus())) {
            return "Cannot cancel rejected leave";
        }
        if (Constants.LEAVE_STATUS_CANCELLED.equals(leave.getStatus())) {
            return "Leave is already cancelled";
        }
        return null;
    }

    /**
     * Validate leave type
     */
    public static boolean isValidLeaveType(String leaveType) {
        return InputValidator.isValidLeaveType(leaveType);
    }

    /**
     * Validate reason is provided
     */
    public static boolean isValidReason(String reason) {
        return InputValidator.isRequired(reason) && reason.length() <= 500;
    }

    /**
     * Validate dates
     */
    public static boolean isValidDates(LocalDate startDate, LocalDate endDate) {
        return startDate != null && endDate != null && 
               InputValidator.isValidDateRange(startDate, endDate) &&
               !endDate.isBefore(LocalDate.now());
    }

    /**
     * Validate leave is not in past
     */
    public static boolean isLeaveNotInPast(LocalDate endDate) {
        return endDate != null && !endDate.isBefore(LocalDate.now());
    }

    /**
     * Validate minimum leave duration (at least 1 day)
     */
    public static boolean isMinimumDuration(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            return false;
        }
        return !startDate.isAfter(endDate);
    }

    /**
     * Validate rejection reason
     */
    public static boolean isValidRejectionReason(String reason) {
        return InputValidator.isRequired(reason);
    }
}

package models;

import java.util.Date;

public class Leave {
    private static int counter=1;
    private String leaveID;
    private String empID;
    private Date startDate;
    private Date endDate;
    private LeaveStatus status;

    public Leave(String leaveID, LeaveStatus status, Date endDate, Date startDate, String empID) {
        this.leaveID = leaveID;
        this.status = status;
        this.endDate = endDate;
        this.startDate = startDate;
        this.empID = empID;
    }

    public Leave(LeaveStatus status, Date endDate, Date startDate, String empID) {
        this.status = status;
        this.endDate = endDate;
        this.startDate = startDate;
        this.empID = empID;
        this.leaveID = "LVE"+String.valueOf(counter++);
    }

    public LeaveStatus getStatus() {
        return status;
    }

    public void setStatus(LeaveStatus status) {
        this.status = status;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getLeaveID() {
        return leaveID;
    }

    public void setLeaveID(String leaveID) {
        this.leaveID = leaveID;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Leave.counter = counter;
    }
}

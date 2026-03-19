package models;

import java.util.*;

public class Leave {
    private static int counter = 1;

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
        this.leaveID = "LVE" + counter++;
        this.status = status;
        this.endDate = endDate;
        this.startDate = startDate;
        this.empID = empID;
    }

    public String getLeaveID() { return leaveID; }
    public String getEmpID() { return empID; }
    public Date getStartDate() { return startDate; }
    public Date getEndDate() { return endDate; }
    public LeaveStatus getStatus() { return status; }

    public void setStatus(LeaveStatus status) { this.status = status; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }
    public void setEmpID(String empID) { this.empID = empID; }

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();

        map.put("leaveID", leaveID);
        map.put("empID", empID);
        map.put("status", status.name());
        map.put("startDate", String.valueOf(startDate.getTime()));
        map.put("endDate", String.valueOf(endDate.getTime()));

        return map;
    }

    public static Leave fromMap(Map<String, String> map) {
        return new Leave(
                map.get("leaveID"),
                LeaveStatus.valueOf(map.get("status")),
                new Date(Long.parseLong(map.get("endDate"))),
                new Date(Long.parseLong(map.get("startDate"))),
                map.get("empID")
        );
    }
}
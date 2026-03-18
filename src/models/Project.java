package models;
import java.util.*;
public class Project {
    private static int counter=1;
    private String ProjectID;
    private String name;
    private List<String> team;
    private Date startDate;
    private List<String> requiredSkills;

    public Project(List<String> requiredSkills, Date startDate, List<String> team, String name, String ProjectID) {
        this.requiredSkills = requiredSkills;
        this.startDate = startDate;
        this.team = team;
        this.name = name;
        this.ProjectID = ProjectID;
    }

    public Project(String leaveID, String name, List<String> team, Date startDate, List<String> requiredSkills) {
        this.ProjectID = "PRO"+String.valueOf(counter++);
        this.name = name;
        this.team = team;
        this.startDate = startDate;
        this.requiredSkills = requiredSkills;
    }

    public List<String> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(List<String> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public List<String> getTeam() {
        return team;
    }

    public void setTeam(List<String> team) {
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProjectID() {
        return ProjectID;
    }

    public void setProjectID(String projectID) {
        ProjectID = projectID;
    }
}

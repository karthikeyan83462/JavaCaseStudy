package models;
import java.util.*;

public class Project {
    private static int counter = 1;

    private String projectID;
    private String name;
    private List<String> team;
    private Date startDate;
    private List<String> requiredSkills;

    public Project(String projectID, String name, List<String> team, Date startDate, List<String> requiredSkills) {
        this.projectID = projectID;
        this.name = name;
        this.team = team;
        this.startDate = startDate;
        this.requiredSkills = requiredSkills;
    }

    public Project(String name, List<String> team, Date startDate, List<String> requiredSkills) {
        this.projectID = "PRO" + counter++;
        this.name = name;
        this.team = team;
        this.startDate = startDate;
        this.requiredSkills = requiredSkills;
    }

    public String getProjectID() { return projectID; }
    public String getName() { return name; }
    public List<String> getTeam() { return team; }
    public Date getStartDate() { return startDate; }
    public List<String> getRequiredSkills() { return requiredSkills; }

    public void setProjectID(String projectID) { this.projectID = projectID; }
    public void setName(String name) { this.name = name; }
    public void setTeam(List<String> team) { this.team = team; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }
    public void setRequiredSkills(List<String> requiredSkills) { this.requiredSkills = requiredSkills; }

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();

        map.put("projectID", projectID);
        map.put("name", name);
        map.put("team", String.join(";", team));
        map.put("startDate", String.valueOf(startDate.getTime()));
        map.put("requiredSkills", String.join(";", requiredSkills));

        return map;
    }

    public static Project fromMap(Map<String, String> map) {
        return new Project(
                map.get("projectID"),
                map.get("name"),
                Arrays.asList(map.getOrDefault("team", "").split(";")),
                new Date(Long.parseLong(map.get("startDate"))),
                Arrays.asList(map.getOrDefault("requiredSkills", "").split(";"))
        );
    }
}
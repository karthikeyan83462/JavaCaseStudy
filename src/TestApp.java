import java.util.*;
import models.*;
import utils.*;

public class TestApp {
    public static void main(String[] args) {

        // ===== EMPLOYEE TEST =====
        Repository<Employee> empRepo =
                new Repository<>("data/employee.json", Employee::fromMap);

        List<String> skills = Arrays.asList("Java", "Spring", "SQL");

        Employee emp = new Employee(
                "Alice",
                Role.DEVELOPER,
                skills,
                EmployeeStatus.ACTIVE
        );

        empRepo.save(emp.toMap());

        System.out.println("\n=== Employees ===");
        for (Employee e : empRepo.loadAll()) {
            System.out.println(e.getEmpID() + " | " +
                    e.getName() + " | " +
                    e.getRole() + " | " +
                    e.getSkills() + " | " +
                    e.getStatus());
        }

        // ===== LEAVE TEST =====
        Repository<Leave> leaveRepo =
                new Repository<>("data/leave.json", Leave::fromMap);

        Leave leave = new Leave(
                LeaveStatus.PENDING,
                new Date(System.currentTimeMillis() + 86400000), // tomorrow
                new Date(),
                emp.getEmpID()
        );

        leaveRepo.save(leave.toMap());

        System.out.println("\n=== Leaves ===");
        for (Leave l : leaveRepo.loadAll()) {
            System.out.println(l.getLeaveID() + " | " +
                    l.getEmpID() + " | " +
                    l.getStatus() + " | " +
                    l.getStartDate() + " → " +
                    l.getEndDate());
        }

        // ===== PROJECT TEST =====
        Repository<Project> projRepo =
                new Repository<>("data/project.json", Project::fromMap);

        List<String> team = Arrays.asList(emp.getEmpID());
        List<String> reqSkills = Arrays.asList("Java", "Microservices");

        Project project = new Project(
                "Inventory System",
                team,
                new Date(),
                reqSkills
        );

        projRepo.save(project.toMap());

        System.out.println("\n=== Projects ===");
        for (Project p : projRepo.loadAll()) {
            System.out.println(p.getProjectID() + " | " +
                    p.getName() + " | " +
                    p.getTeam() + " | " +
                    p.getRequiredSkills() + " | " +
                    p.getStartDate());
        }
    }
}
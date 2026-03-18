import java.util.*;
import models.*;
import utils.*;

public class TestApp {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Repository<User> repo = new Repository<>("data/user.json", User::fromMap);

        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Password Hash: ");
        String passwordHash = scanner.nextLine();

        System.out.print("Enter EmpID: ");
        String role = scanner.nextLine();

        User user = new User(userId, name, email, passwordHash, role);

        repo.save(user.toMap());
        System.out.println("User saved!\n");

        List<User> users = repo.loadAll();

        System.out.println("=== All Users ===");
        for (User u : users) {
            System.out.println("--------------------------");
            System.out.println("ID: " + u.getUserId());
            System.out.println("Name: " + u.getName());
            System.out.println("Email: " + u.getEmail());
            System.out.println("EmpID: " + u.getEmpID());
        }

        scanner.close();
    }
}
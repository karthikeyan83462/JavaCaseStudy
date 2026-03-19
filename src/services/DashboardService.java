package services;
import models.*;
import java.util.*;

public class DashboardService {

    Scanner sc=new Scanner(System.in);
    public void normalDahboard(Employee e){



    }
    public void managerDashboard(Employee e){


    }
    public void AdminDashboard(Employee e){

    }

    public void RegistrationDashboard(){
        RegistrationService rs=new RegistrationService();
        
        System.out.print("Enter name:");
        String name=sc.nextLine();

        System.out.print("Enter Skills with space:");
        String a=sc.nextLine();

        List<String> Skills=Arrays.asList(a.split("\s"));
        System.out.print("Enter password:");
        String password=sc.nextLine();

        System.out.print("Enter email:");
        String email=sc.nextLine();

        System.out.println("Choose role:");
        System.out.println("1.Admin");
        System.out.println("2.Manager");
        System.out.println("3.Developer");
        System.out.print("Select role:");
        int ch=sc.nextInt();sc.nextLine();
        
        switch (ch) {
            case 1:
                rs.registerUser(name, Role.ADMIN, Skills, password, email);
                break;
            case 2:
                rs.registerUser(name, Role.MANAGER, Skills, password, email);
                break;
            case 3:
                rs.registerUser(name, Role.DEVELOPER, Skills, password, email);
                break;
            default:
                break;
        }
        


    }
}

package services;
import java.util.*;
import models.*;
import utils.Repository;

public class RegistrationService {
    Scanner sc=new Scanner(System.in);

    public void registerUser(String name, Role role, List<String> skills, String password, String email){
        Employee e=new Employee(name, role, skills);
        User u=new User(name, email, password, e.getEmpID());
        Repository<Employee> re1 =new Repository<>("data/employee.json", Employee::fromMap);
        Repository<User> re2 =new Repository<>("data/user.json", User::fromMap);
        re1.save(e.toMap());
        re2.save(u.toMap());
        

    }
}

package services;
import models.*;
import utils.Repository;
import services.DashboardService.*;

import java.util.*;
public class AuthService{
    public User isAvailable(String a){
        Repository<User> repo =new Repository<>("data/user.json", User::fromMap);
            List<User> allData= repo.loadAll();
            for (User u: allData){
                if (u.getName().equalsIgnoreCase(a)) return u;
            }
            return null;
    }

    public User checkpwd(String a, String b){
        User temp=isAvailable(a);
        if(temp!=null){
            if (temp.getPassword().equals(b)) return temp;
            else return null;
        }
            
        return null;


    }
    public void goToDashboard(User u){
        Repository<Employee> repo =new Repository<>("data/employee.json", Employee::fromMap);
        List<Employee> allData= repo.loadAll();
        Employee temp=new Employee();
        for (Employee e:allData){
            if (e.getEmpID()==u.getEmpID()){
                
                break;
            } 

        }
        DashboardService ds=new DashboardService();
        if (temp.getRole()==Role.ADMIN){
            ds.AdminDashboard(temp);
        }
        if (temp.getRole()==Role.MANAGER){
            ds.managerDashboard(temp);
        }
        if (temp.getRole()==Role.DEVELOPER){
            ds.normalDahboard(temp);
        }
    }


}
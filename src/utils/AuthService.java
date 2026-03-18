package utils;
import models.*;

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

    public boolean checkpwd(String a, String b){
        User temp=isAvailable(a);
        if(temp!=null){
            if (temp.getPasswordHash().equals(b)) return true;
            else return false;
        }
            
        return false;


    }
    public void checkRole(User u){

    }


}



import java.util.*;
public class AuthService{
    public boolean isAvailable(String a){
        Repository<User> repo =new Repository<>("data/user.json", User::fromMap);
            List<User> allData= repo.loadAll();
            for (User u: allData){
                return u.getUserId().equalsIgnoreCase(a);
            }
            return false;
    }

}
import java.util.*;
public class Sample {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter username:");
        String U_name=sc.nextLine();
        System.out.print("Enter password:");
        String U_pas=sc.nextLine();


        AuthService as=new AuthService();
        if (as.checkpwd(U_name, U_pas)) System.out.println("Success");
        else System.out.println("fail");
        


    }
    
}

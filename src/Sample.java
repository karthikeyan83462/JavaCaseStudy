import java.util.*;
public class Sample {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter username:");
        String U_name=sc.nextLine();

        AuthService as=new AuthService();
        if (as.isAvailable(U_name)) System.out.println("true");
        else System.out.println("false");


    }
    
}

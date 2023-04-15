import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class bank {

    public static void main(String args[]) {

        Scanner scanner = new Scanner(System.in);
        bankOperations bops = new bankOperations();
        bankOperations.readFile();


        while (true) { // so the bank is always on haha
            System.out.println("Welcome to Genio's Bank");
            System.out.println("Do you want to log in or sign up. L for login, S for signup");
            char choice = scanner.next().charAt(0);

            // Sign up code
            if (choice == 'S') { // if user wants to create a new account
                bops.createNewUser(scanner);

            } else if (choice == 'L') { // if user wants to log in
                bops.logIn(scanner);

            }
        }
    }
}

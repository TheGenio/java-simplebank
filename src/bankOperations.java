import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class bankOperations {

    private static List<client> clients;



    // This method is used to read data to the file
    public static void readFile() {
        try { //reads the clients from the file input
            FileInputStream fileIn = new FileInputStream("Clients.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            clients = (List<client>) in.readObject();
            in.close();
            fileIn.close();
        } catch (Exception e) {
            System.out.println("Error reading : " + e.getMessage());
            clients = new ArrayList<>();
        }


    }

    // This method is used to write data to the file
    public static void writeFile(List<client> clients) {

        try {
            FileOutputStream fileOut = new FileOutputStream("Clients.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(clients);
            out.close();
            fileOut.close();
        } catch (Exception e) {
            System.out.println("Error saving clients :" + e.getMessage());
        }
    }


    public void createNewUser(Scanner scanner) {

        System.out.println("Please enter your username");
        String userId = scanner.next();
        //TODO create a username check, see if username is already in use
        System.out.println("Please enter your password");
        String password = scanner.next();
        //TODO create a method to see if password is correct

        System.out.println("Are you a banker? y/n");
        char response = scanner.next().charAt(0);

        if (response == 'y') {
            banker newClient = new banker(clients, userId, password);
            clients.add(newClient);

        } else if (response == 'n') {
            System.out.println("Please enter you balance");
            Double balance = scanner.nextDouble();
            client newClient = new client(balance, userId, password);  // create a new user
            clients.add(newClient);
        }

        writeFile(clients);

        System.out.println("Created a new user :) Welcome to the future");
        System.out.println("-------------------------------------------");

    }


    public void logIn(Scanner scanner) {

        System.out.println("Please enter your username");
        String userId = scanner.next();
        System.out.println("Please enter your password");
        String password = scanner.next();

        boolean found = false;
        for (client user : clients) {
            if (user.getUserId().equals(userId) && user.getPassword().equals(password)) {
                System.out.println("Log in successful ");

                found = true; // if the user is found, it means that the user is logged in

                if (user instanceof banker) { // A small test to make sure that bankers can see all users
                    ((banker) user).GetAllUsers();
                }

                System.out.println("Do you want to transfer money? y/n");
                char decision = scanner.next().charAt(0);

                if (decision == 'y') {
                    transferMoney(user,scanner);
                }

                break;
            }
        }
        if (!found) { // TODO fix this logic error
            System.out.println("Invalid username or password");
        }else {
            System.out.println("Please enter a correct choice");
        }


    }



// This method is used to transfer money to other accounts
    public static void transferMoney(client user, Scanner scanner){

        System.out.println("Please enter the amount you want to transfer");
        double transAmount = scanner.nextDouble();
        if (user.checkBalance(transAmount)){ //checks if transaction can be made
            System.out.println("Please enter the destination account");
            String accountName = scanner.next();

            for (client transferAcc : clients) { //for loop to search for the account we want to transfer to
                if (accountName.equals(transferAcc.getUserId())) {
                    transferAcc.transfer(transAmount);
                    System.out.println("Transfer Successful");
                    user.setBalance( user.getBalance() -  transAmount);
                    System.out.println("Your new balance is " + user.getBalance());
                }

            }

        } else {
            System.out.println("Insufficient funds");
        }



    }
}

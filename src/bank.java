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
        List<client> clients = null;

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

        while (true) { // so the bank is always on haha

            System.out.println("Welcome to Genio's Bank");
            System.out.println("Do you want to log in or sign up. L for login, S for signup");
            char choice = scanner.next().charAt(0);

            if (choice == 'S') { // if user wants to create a new account


                System.out.println("Please enter your username");
                String userId = scanner.next();
                //TODO create a username check, see if username is already in use
                System.out.println("Please enter your password");
                String password = scanner.next();
                //TODO create a method to see if password is correct

                System.out.println("Are you an administrator? y/n");
                char response = scanner.next().charAt(0);

                if (response == 'y'){
                    banker newClient = new banker(clients, userId, password);
                    clients.add(newClient);


                } else if (response == 'n'){
                    System.out.println("Please enter you balance");
                    Double balance = scanner.nextDouble();
                    client newClient = new client(balance, userId, password);  // create a new user
                    clients.add(newClient);

                }


                try {
                    FileOutputStream fileOut = new FileOutputStream("Clients.ser");
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(clients);
                    out.close();
                    fileOut.close();
                }catch(Exception e){
                    System.out.println("Error saving clients :" + e.getMessage());
                }




                System.out.println("Created a new user :) Welcome to the future");
                System.out.println("-------------------------------------------");

            } else if (choice == 'L') { // if user wants to log in

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

                            System.out.println("Please enter the amount you want to transfer");
                            double transAmount = scanner.nextDouble(); // #todo implement a check if balance is suffecent feature

                            System.out.println("Please enter the destination account");
                            String accountNumber = scanner.next();

                            for (client transfer : clients) { //for loop to search for the account we want to transfer to
                                if (accountNumber.equals(transfer.getUserId())) {
                                    transfer.transfer(transAmount);
                                    System.out.println("Transfer Successful");
                                    System.out.println("Your new balance is " + user.getBalance());
                                }

                            }


                        }


                        break;
                    }
                }
                if (!found) {
                    System.out.println("Invalid username or password");
                }


            } else {
                System.out.println("Please enter a correct choice");
            }


        }
    }
}

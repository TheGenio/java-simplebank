import java.util.ArrayList;
import java.util.Scanner;

public class bank {

    public static void main(String args[]) {

        ArrayList<client> clients = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("Welcome to Genio's Bank");
            System.out.println("Do you want to log in or sign up. L for login, S for signup");
            char choice = scanner.next().charAt(0);

            boolean check = false;
            if (choice == 'S') {
                System.out.println("Please enter your username");
                String userId = scanner.next();
                System.out.println("Please enter your password");
                String password = scanner.next();
                System.out.println("Please enter you balance");
                Double balance = scanner.nextDouble();
                client newClient = new client(balance, userId, password);
                clients.add(newClient);

                System.out.println("Created a new user :) Welcome to the future");
                check = true;


            } else if (choice == 'L') {
                System.out.println("Please enter your username");
                String userId = scanner.next();
                System.out.println("Please enter your password");
                String password = scanner.next();
                boolean found = false;
                for (client user : clients) {
                    if (user.getUserId().equals(userId) && user.getPassword().equals(password)) {
                        System.out.println("Log in successful ");
                        found = true;
                        System.out.println("Do you want to transfer money? y/n");
                        char decision = scanner.next().charAt(0);
                        if (decision == 'y') {
                            System.out.println("Please enter the amount you want to transfer");
                            double transAmount = scanner.nextDouble();
                            System.out.println("Please enter the destination account");
                            String accountNumber = scanner.next();

                            for (client transfer : clients) {
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

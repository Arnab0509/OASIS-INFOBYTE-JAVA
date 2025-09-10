package JavaProject;

import java.util.*;

class BankAccount {

    String name;
    String userName;
    String password;
    String accountNo;
    float balance = 10000f;
    int transactions = 0;
    List<String> transactionHistory = new ArrayList<>();

    public void register(Scanner sc) {
        System.out.println("\nEnter your Name: ");
        this.name = sc.nextLine();

        System.out.println("\nEnter your Username: ");
        this.userName = sc.nextLine();

        System.out.println("\nEnter your Password: ");
        this.password = sc.nextLine();

        System.out.println("\nEnter your Account Number: ");
        this.accountNo = sc.nextLine();

        System.out.println("\nRegistration Successful. Please Log in to your Bank Account.");
    }

    public boolean login(Scanner sc) {
        int attempts = 3; // allow only 3 attempts
        while (attempts > 0) {
            System.out.println("\nEnter your username: ");
            String inputUsername = sc.nextLine();

            if (inputUsername.equals(userName)) {
                System.out.println("\nEnter your password: ");
                String inputPassword = sc.nextLine();

                if (inputPassword.equals(password)) {
                    System.out.println("\nLogin Successful!");
                    return true;
                } else {
                    System.out.println("\nIncorrect Password.");
                }
            } else {
                System.out.println("\nUsername not found.");
            }
            attempts--;
            System.out.println("Attempts left: " + attempts);
        }
        return false;
    }

    public void withdraw(Scanner sc) {
        System.out.println("\nEnter Amount to Withdraw: ");
        float amount = sc.nextFloat();
        sc.nextLine(); // consume newline

        if (amount <= 0) {
            System.out.println("\nInvalid amount.");
            return;
        }

        if (balance >= amount) {
            transactions++;
            balance -= amount;
            System.out.println("\nWithdrawal Successful.");
            transactionHistory.add(amount + " Rs Withdrawn");
        } else {
            System.out.println("\nInsufficient Balance.");
        }
    }

    public void deposit(Scanner sc) {
        System.out.println("\nEnter Amount to Deposit: ");
        float amount = sc.nextFloat();
        sc.nextLine(); // consume newline

        if (amount <= 0) {
            System.out.println("\nInvalid amount.");
            return;
        }

        if (amount <= 10000f) {
            transactions++;
            balance += amount;
            System.out.println("\nDeposit Successful.");
            transactionHistory.add(amount + " Rs Deposited");
        } else {
            System.out.println("\nDeposit limit is 10000 Rs.");
        }
    }

    public void transfer(Scanner sc) {
        System.out.println("\nEnter Recipient's Name: ");
        String recipient = sc.nextLine();

        System.out.println("\nEnter Amount to Transfer: ");
        float amount = sc.nextFloat();
        sc.nextLine(); // consume newline

        if (amount <= 0) {
            System.out.println("\nInvalid amount.");
            return;
        }

        if (balance >= amount) {
            if (amount <= 50000f) {
                transactions++;
                balance -= amount;
                System.out.println("\nSuccessfully Transferred " + amount + " Rs to " + recipient);
                transactionHistory.add(amount + " Rs Transferred to " + recipient);
            } else {
                System.out.println("\nTransfer limit is 50000 Rs.");
            }
        } else {
            System.out.println("\nInsufficient Balance.");
        }
    }

    public void checkBalance() {
        System.out.println("\nCurrent Balance: " + balance + " Rs");
    }

    public void transHistory() {
        if (transactions == 0) {
            System.out.println("\nNo Transactions yet.");
        } else {
            System.out.println("\nTransaction History:");
            for (String record : transactionHistory) {
                System.out.println(" - " + record);
            }
        }
    }
}

public class ATMInterface {

    public static int takeIntegerInput(int limit, Scanner sc) {
        int input = -1;
        boolean valid = false;

        while (!valid) {
            try {
                input = sc.nextInt();
                sc.nextLine(); // consume newline
                if (input >= 1 && input <= limit) {
                    valid = true;
                } else {
                    System.out.println("Choose a number between 1 and " + limit);
                }
            } catch (InputMismatchException e) {
                System.out.println(" Enter only integer value.");
                sc.nextLine(); // clear invalid input
            }
        }
        return input;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n******************** WELCOME TO SBI ATM ********************");
        System.out.println("\n1. Register \n2. Exit");
        System.out.println("Choose one option: ");
        int choice = takeIntegerInput(2, sc);

        if (choice == 1) {
            BankAccount account = new BankAccount();
            account.register(sc);

            while (true) {
                System.out.println("\n1. Login \n2. Exit");
                System.out.println("Enter your choice: ");
                int ch = takeIntegerInput(2, sc);

                if (ch == 1) {
                    if (account.login(sc)) {
                        System.out.println("\n******************** WELCOME BACK " + account.name + " ********************");

                        boolean quit = false;
                        while (!quit) {
                            System.out.println("\n1. Withdraw \n2. Deposit \n3. Transfer \n4. Check Balance \n5. Transaction History \n6. Logout");
                            System.out.println("Enter your choice: ");

                            int option = takeIntegerInput(6, sc);

                            switch (option) {
                                case 1 -> account.withdraw(sc);
                                case 2 -> account.deposit(sc);
                                case 3 -> account.transfer(sc);
                                case 4 -> account.checkBalance();
                                case 5 -> account.transHistory();
                                case 6 -> {
                                    System.out.println("\nLogged out successfully.");
                                    quit = true;
                                }
                            }
                        }
                    } else {
                        System.out.println("\nLogin Failed. Returning to main menu.");
                    }
                } else {
                    System.out.println("\nThank you for using ATM. Goodbye!");
                    System.exit(0);
                }
            }
        } else {
            System.out.println("\nGoodbye!");
            System.exit(0);
        }
    }
}

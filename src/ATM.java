import java.util.Scanner;

public class ATM {
    private Bank bank;
    private Scanner scanner;

    public ATM() {
        bank = new Bank();
        scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;

        while (running) {
            printWelcome();

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    handleLogin();
                    break;
                case 2:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void handleLogin() {
        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user = bank.login(username, password);

        if (user == null) {
            System.out.println("Invalid login.");
            System.out.print("Sign up? (Y/N): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("y")) {
                user = bank.register(username, password);
                System.out.println("Account created!");
                atmMenu(user);
            }
        } else {
            atmMenu(user);
        }
    }

    private void atmMenu(User user) {
        boolean loggedIn = true;

        while (loggedIn) {
            printMenu();

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    System.out.println("Balance: " + user.getAccBalance());
                    break;

                case 2:
                    System.out.print("Deposit amount: ");
                    double deposit = getDoubleInput();
                    user.deposit(deposit);
                    break;

                case 3:
                    System.out.print("Withdraw amount: ");
                    double withdraw = getDoubleInput();

                    if (!user.withdraw(withdraw)) {
                        System.out.println("Insufficient funds.");
                    }
                    break;

                case 4:
                    loggedIn = false;
                    System.out.println("Logged out.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void printWelcome() {
        System.out.println("*******" +
                "\n* ATM *" +
                "\n*******");
        System.out.println("1. Login");
        System.out.println("2. Exit");
    }

    private void printMenu() {
        System.out.println("Welcome!");
        System.out.println("*******" +
                "\n* MENU *" +
                "\n*******");
        System.out.println("1. View Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Logout");
    }

    private int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.println("Enter a number:");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // clear buffer
        return value;
    }

    private double getDoubleInput() {
        while (!scanner.hasNextDouble()) {
            System.out.println("Enter a valid number:");
            scanner.next();
        }
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }
}
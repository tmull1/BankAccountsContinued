package org.example;
import java.util.ArrayList;
import java.util.Scanner;

public class BankAccount {
    private String name;
    private double balance;
    private int accountNumber;

    // Constructor with parameters
    public BankAccount(String name, double balance, int accountNumber) {
        this.name = name;
        this.balance = balance;
        this.accountNumber = accountNumber;
    }

    // Constructor with no parameters, allowing user input
    public BankAccount(int accountNumber) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Let's make a new account!");
        System.out.print("What is the name for the account? ");
        this.name = scanner.nextLine();
        System.out.print("What is the beginning balance for the account? ");
        this.balance = scanner.nextDouble();
        this.accountNumber = accountNumber;
    }

    // Method to deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited " + amount);
        } else {
            System.out.println("Deposit amount must be positive");
        }
    }

    // Method to withdraw money
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully withdrew " + amount);
        } else if (amount > balance) {
            System.out.println("Insufficient funds");
        } else {
            System.out.println("Withdrawal amount must be positive");
        }
    }

    // Method to print account details
    public void printAccountDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + name);
        System.out.println("Account Balance: " + balance);
    }

    // Method to check account balance
    public void checkBalance() {
        System.out.println("Current balance: " + balance);
    }

    // Main menu method
    public static void mainMenu(BankAccount account, ArrayList<BankAccount> accounts) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Hello " + account.name + "!");
            System.out.println("Welcome to the Main Menu, what would you like to do today?");
            System.out.println("1. To check account balance");
            System.out.println("2. To make a withdrawal");
            System.out.println("3. To make a deposit");
            System.out.println("4. To make a transfer to another account");
            System.out.println("0. To exit.");
            int choice = scanner.nextInt();
            if (choice == 0) {
                break;
            }
            switch (choice) {
                case 1:
                    account.checkBalance();
                    break;
                case 2:
                    System.out.print("Enter the amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter the amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 4:
                    System.out.print("Enter the account number to transfer to: ");
                    int targetAccountNumber = scanner.nextInt();
                    BankAccount targetAccount = findAccountByNumber(accounts, targetAccountNumber);
                    if (targetAccount == null) {
                        System.out.println("Account not found.");
                    } else {
                        System.out.print("Enter the amount to transfer: ");
                        double transferAmount = scanner.nextDouble();
                        account.transfer(targetAccount, transferAmount);
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to transfer money to another account
    public void transfer(BankAccount targetAccount, double amount) {
        if (amount > 0 && amount <= balance) {
            this.balance -= amount;
            targetAccount.balance += amount;
            System.out.println("Successfully transferred " + amount + " to account number " + targetAccount.accountNumber);
            System.out.println("The name on the account is: " + targetAccount.name + " and they have a balance of $" + targetAccount.balance);
            System.out.println("The name on the account is: " + this.name + " and they have a balance of $" + this.balance);
        } else {
            System.out.println("Insufficient funds or invalid amount.");
        }
    }

    // Method to find an account by account number
    public static BankAccount findAccountByNumber(ArrayList<BankAccount> accounts, int accountNumber) {
        for (BankAccount account : accounts) {
            if (account.accountNumber == accountNumber) {
                return account;
            }
        }
        return null;
    }

    // Main method to demonstrate functionality
    public static void main(String[] args) {
        ArrayList<BankAccount> accounts = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello world! Welcome to the Bank of Matt!");
        while (true) {
            System.out.println("Are you an existing customer? (-1 to exit)");
            System.out.println("1. Yes");
            System.out.println("2. No");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline

            if (choice == -1) {
                break;
            } else if (choice == 1) {
                System.out.println("Enter your account number: ");
                int accountNumber = scanner.nextInt();
                boolean found = false;
                for (BankAccount account : accounts) {
                    if (account.accountNumber == accountNumber) {
                        found = true;
                        mainMenu(account, accounts);
                        break;
                    }
                }
                if (!found) {
                    System.out.println("Account not found");
                }
            } else if (choice == 2) {
                int newAccountNumber = accounts.size() + 1;
                BankAccount newAccount = new BankAccount(newAccountNumber);
                accounts.add(newAccount);
                System.out.println("Account created successfully!");
                newAccount.printAccountDetails();
                mainMenu(newAccount, accounts);
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}


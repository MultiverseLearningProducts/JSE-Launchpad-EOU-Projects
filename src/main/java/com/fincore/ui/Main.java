package com.fincore.ui;

import com.fincore.models.Account;
import com.fincore.models.SavingsAccount;
import com.fincore.models.InsufficientFundsException;

import java.util.Scanner;

/**
 * Main class for the FinCore CLI Banking application.
 * This is the entry point for the command-line banking system.
 * Handles user interaction and coordinates between different components.
 * 
 * @author FinCore Development Team
 * @version 4.0.0
 */
public class Main {
    
    private static Scanner scanner = new Scanner(System.in);
    
    /**
     * Main method - entry point of the application.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Welcome to FinCore CLI Banking!");
        
        // Create account objects
        Account myAccount = new Account("Alex Doe", 1000.00);
        SavingsAccount mySavingsAccount = new SavingsAccount("Alex Doe", 500.00, 0.05); // 5% interest rate
        
        System.out.println("Account Holder: " + myAccount.getAccountHolder());
        System.out.println("Checking Account Balance: $" + String.format("%.2f", myAccount.getBalance()));
        System.out.println("Savings Account Balance: $" + String.format("%.2f", mySavingsAccount.getBalance()));
        System.out.println();
        
        // Interactive menu loop
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getUserChoice();
            
            switch (choice) {
                case 1:
                    handleDeposit(myAccount);
                    break;
                case 2:
                    handleWithdrawal(myAccount);
                    break;
                case 3:
                    myAccount.checkBalance();
                    break;
                case 4:
                    handleSavingsDeposit(mySavingsAccount);
                    break;
                case 5:
                    handleSavingsWithdrawal(mySavingsAccount);
                    break;
                case 6:
                    mySavingsAccount.checkBalance();
                    break;
                case 7:
                    mySavingsAccount.applyInterest();
                    break;
                case 8:
                    System.out.println("Thank you for using FinCore CLI Banking. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            
            if (running) {
                System.out.println(); // Add spacing between operations
            }
        }
        
        scanner.close();
    }
    
    /**
     * Displays the main menu options to the user.
     */
    private static void displayMenu() {
        System.out.println("=== FinCore CLI Banking Menu ===");
        System.out.println("Checking Account Operations:");
        System.out.println("1. Deposit to Checking");
        System.out.println("2. Withdraw from Checking");
        System.out.println("3. Check Checking Balance");
        System.out.println();
        System.out.println("Savings Account Operations:");
        System.out.println("4. Deposit to Savings");
        System.out.println("5. Withdraw from Savings");
        System.out.println("6. Check Savings Balance");
        System.out.println("7. Apply Interest to Savings");
        System.out.println();
        System.out.println("8. Exit");
        System.out.print("Please select an option (1-8): ");
    }
    
    /**
     * Gets the user's menu choice and validates input.
     * 
     * @return the user's choice as an integer
     */
    private static int getUserChoice() {
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number (1-8): ");
            scanner.next(); // Clear invalid input
        }
        return scanner.nextInt();
    }
    
    /**
     * Handles the deposit operation for checking account.
     * 
     * @param account the account to deposit to
     */
    private static void handleDeposit(Account account) {
        System.out.print("Enter amount to deposit to checking account: $");
        while (!scanner.hasNextDouble()) {
            System.out.print("Please enter a valid amount: $");
            scanner.next(); // Clear invalid input
        }
        double amount = scanner.nextDouble();
        
        if (account.deposit(amount)) {
            System.out.println("Deposit successful!");
            System.out.println("Amount deposited: $" + String.format("%.2f", amount));
            System.out.println("New balance: $" + String.format("%.2f", account.getBalance()));
        } else {
            System.out.println("Error: Deposit amount must be positive.");
        }
    }
    
    /**
     * Handles the withdrawal operation for checking account.
     * Uses try-catch to handle InsufficientFundsException.
     * 
     * @param account the account to withdraw from
     */
    private static void handleWithdrawal(Account account) {
        System.out.print("Enter amount to withdraw from checking account: $");
        while (!scanner.hasNextDouble()) {
            System.out.print("Please enter a valid amount: $");
            scanner.next(); // Clear invalid input
        }
        double amount = scanner.nextDouble();
        
        try {
            account.withdraw(amount);
            System.out.println("Withdrawal successful!");
            System.out.println("Amount withdrawn: $" + String.format("%.2f", amount));
            System.out.println("New balance: $" + String.format("%.2f", account.getBalance()));
        } catch (InsufficientFundsException e) {
            System.out.println("Withdrawal failed: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Withdrawal failed: " + e.getMessage());
        }
    }
    
    /**
     * Handles the deposit operation for savings account.
     * 
     * @param account the savings account to deposit to
     */
    private static void handleSavingsDeposit(SavingsAccount account) {
        System.out.print("Enter amount to deposit to savings account: $");
        while (!scanner.hasNextDouble()) {
            System.out.print("Please enter a valid amount: $");
            scanner.next(); // Clear invalid input
        }
        double amount = scanner.nextDouble();
        
        if (account.deposit(amount)) {
            System.out.println("Deposit successful!");
            System.out.println("Amount deposited: $" + String.format("%.2f", amount));
            System.out.println("New balance: $" + String.format("%.2f", account.getBalance()));
        } else {
            System.out.println("Error: Deposit amount must be positive.");
        }
    }
    
    /**
     * Handles the withdrawal operation for savings account.
     * Uses try-catch to handle InsufficientFundsException.
     * 
     * @param account the savings account to withdraw from
     */
    private static void handleSavingsWithdrawal(SavingsAccount account) {
        System.out.print("Enter amount to withdraw from savings account: $");
        while (!scanner.hasNextDouble()) {
            System.out.print("Please enter a valid amount: $");
            scanner.next(); // Clear invalid input
        }
        double amount = scanner.nextDouble();
        
        try {
            account.withdraw(amount);
            System.out.println("Withdrawal successful!");
            System.out.println("Amount withdrawn: $" + String.format("%.2f", amount));
            System.out.println("New balance: $" + String.format("%.2f", account.getBalance()));
        } catch (InsufficientFundsException e) {
            System.out.println("Withdrawal failed: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Withdrawal failed: " + e.getMessage());
        }
    }
}

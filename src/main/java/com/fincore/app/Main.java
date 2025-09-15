package com.fincore.app;

import java.util.Scanner;

/**
 * Main class for the FinCore CLI Banking application.
 * This is the entry point for the command-line banking system.
 * 
 * @author FinCore Development Team
 * @version 2.0.0
 */
public class Main {
    
    // Account variables
    private static String accountHolder = "Alex Doe";
    private static double balance = 1000.00;
    private static Scanner scanner = new Scanner(System.in);
    
    /**
     * Main method - entry point of the application.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Welcome to FinCore CLI Banking!");
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Initial Balance: $" + String.format("%.2f", balance));
        System.out.println();
        
        // Interactive menu loop
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getUserChoice();
            
            switch (choice) {
                case 1:
                    handleDeposit();
                    break;
                case 2:
                    handleWithdrawal();
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
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
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
        System.out.print("Please select an option (1-4): ");
    }
    
    /**
     * Gets the user's menu choice and validates input.
     * 
     * @return the user's choice as an integer
     */
    private static int getUserChoice() {
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number (1-4): ");
            scanner.next(); // Clear invalid input
        }
        return scanner.nextInt();
    }
    
    /**
     * Handles the deposit operation by getting amount from user.
     */
    private static void handleDeposit() {
        System.out.print("Enter amount to deposit: $");
        while (!scanner.hasNextDouble()) {
            System.out.print("Please enter a valid amount: $");
            scanner.next(); // Clear invalid input
        }
        double amount = scanner.nextDouble();
        deposit(amount);
    }
    
    /**
     * Handles the withdrawal operation by getting amount from user.
     */
    private static void handleWithdrawal() {
        System.out.print("Enter amount to withdraw: $");
        while (!scanner.hasNextDouble()) {
            System.out.print("Please enter a valid amount: $");
            scanner.next(); // Clear invalid input
        }
        double amount = scanner.nextDouble();
        withdraw(amount);
    }
    
    /**
     * Deposits the specified amount into the account.
     * 
     * @param amount the amount to deposit
     */
    public static void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful!");
            System.out.println("Amount deposited: $" + String.format("%.2f", amount));
            System.out.println("New balance: $" + String.format("%.2f", balance));
        } else {
            System.out.println("Error: Deposit amount must be positive.");
        }
    }
    
    /**
     * Withdraws the specified amount from the account.
     * Prevents withdrawal if it would result in a negative balance.
     * 
     * @param amount the amount to withdraw
     */
    public static void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Error: Withdrawal amount must be positive.");
        } else if (amount > balance) {
            System.out.println("Error: Insufficient funds!");
            System.out.println("Current balance: $" + String.format("%.2f", balance));
            System.out.println("Attempted withdrawal: $" + String.format("%.2f", amount));
        } else {
            balance -= amount;
            System.out.println("Withdrawal successful!");
            System.out.println("Amount withdrawn: $" + String.format("%.2f", amount));
            System.out.println("New balance: $" + String.format("%.2f", balance));
        }
    }
    
    /**
     * Displays the current account balance.
     */
    public static void checkBalance() {
        System.out.println("=== Account Balance ===");
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Current Balance: $" + String.format("%.2f", balance));
    }
}

package com.fincore.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Customer class representing a bank customer with multiple accounts.
 * This class manages customer information and their associated accounts.
 * 
 * @author FinCore Development Team
 * @version 5.0.0
 */
public class Customer {
    
    private final String customerId;
    private final String name;
    private final List<Account> accounts;
    
    /**
     * Constructor to initialize a Customer with ID and name.
     * 
     * @param customerId the unique customer identifier
     * @param name the customer's full name
     */
    public Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
        this.accounts = new ArrayList<>();
    }
    
    /**
     * Gets the customer's unique ID.
     * 
     * @return the customer ID
     */
    public String getCustomerId() {
        return customerId;
    }
    
    /**
     * Gets the customer's name.
     * 
     * @return the customer's name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Gets a copy of the customer's accounts list.
     * 
     * @return a list of accounts belonging to this customer
     */
    public List<Account> getAccounts() {
        return new ArrayList<>(accounts);
    }
    
    /**
     * Adds a new account to this customer's account list.
     * 
     * @param account the account to add
     * @return true if the account was added successfully
     */
    public boolean addAccount(Account account) {
        if (account != null && !accounts.contains(account)) {
            accounts.add(account);
            return true;
        }
        return false;
    }
    
    /**
     * Removes an account from this customer's account list.
     * 
     * @param account the account to remove
     * @return true if the account was removed successfully
     */
    public boolean removeAccount(Account account) {
        return accounts.remove(account);
    }
    
    /**
     * Gets the number of accounts this customer has.
     * 
     * @return the number of accounts
     */
    public int getAccountCount() {
        return accounts.size();
    }
    
    /**
     * Gets an account by index from the customer's account list.
     * 
     * @param index the index of the account
     * @return the account at the specified index, or null if index is invalid
     */
    public Account getAccount(int index) {
        if (index >= 0 && index < accounts.size()) {
            return accounts.get(index);
        }
        return null;
    }
    
    /**
     * Gets the total balance across all accounts for this customer.
     * 
     * @return the sum of all account balances
     */
    public double getTotalBalance() {
        return accounts.stream()
                      .mapToDouble(Account::getBalance)
                      .sum();
    }
    
    /**
     * Displays a summary of all accounts for this customer.
     */
    public void displayAccountSummary() {
        System.out.println("=== Customer Account Summary ===");
        System.out.println("Customer ID: " + customerId);
        System.out.println("Customer Name: " + name);
        System.out.println("Number of Accounts: " + getAccountCount());
        System.out.println("Total Balance: $" + String.format("%.2f", getTotalBalance()));
        System.out.println();
        
        if (accounts.isEmpty()) {
            System.out.println("No accounts found for this customer.");
        } else {
            for (int i = 0; i < accounts.size(); i++) {
                Account account = accounts.get(i);
                System.out.println("Account " + (i + 1) + ": " + account.getClass().getSimpleName());
                System.out.println("  Balance: $" + String.format("%.2f", account.getBalance()));
                if (account instanceof SavingsAccount) {
                    SavingsAccount savingsAccount = (SavingsAccount) account;
                    System.out.println("  Interest Rate: " + String.format("%.2f%%", savingsAccount.getInterestRate() * 100));
                }
            }
        }
    }
    
    /**
     * Returns a string representation of the customer.
     * 
     * @return string representation of the customer
     */
    @Override
    public String toString() {
        return "Customer[id=" + customerId + ", name=" + name + ", accounts=" + getAccountCount() + "]";
    }
    
    /**
     * Checks if this customer is equal to another object.
     * 
     * @param obj the object to compare
     * @return true if the objects are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Customer customer = (Customer) obj;
        return customerId.equals(customer.customerId);
    }
    
    /**
     * Returns the hash code for this customer.
     * 
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return customerId.hashCode();
    }
}

package com.fincore.models;

/**
 * Custom checked exception thrown when a withdrawal operation would result
 * in insufficient funds in the account.
 * 
 * @author FinCore Development Team
 * @version 4.0.0
 */
public class InsufficientFundsException extends Exception {
    
    private final double requestedAmount;
    private final double availableBalance;
    
    /**
     * Constructs a new InsufficientFundsException with the specified details.
     * 
     * @param requestedAmount the amount that was requested for withdrawal
     * @param availableBalance the current available balance in the account
     */
    public InsufficientFundsException(double requestedAmount, double availableBalance) {
        super(String.format("Insufficient funds: Requested $%.2f, but only $%.2f available", 
                           requestedAmount, availableBalance));
        this.requestedAmount = requestedAmount;
        this.availableBalance = availableBalance;
    }
    
    /**
     * Gets the amount that was requested for withdrawal.
     * 
     * @return the requested withdrawal amount
     */
    public double getRequestedAmount() {
        return requestedAmount;
    }
    
    /**
     * Gets the current available balance in the account.
     * 
     * @return the available balance
     */
    public double getAvailableBalance() {
        return availableBalance;
    }
}

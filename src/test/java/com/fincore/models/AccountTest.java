package com.fincore.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Account and SavingsAccount classes.
 * This class contains comprehensive unit tests to verify the reliability
 * of the banking application's core business logic.
 * 
 * @author FinCore Development Team
 * @version 6.0.0
 */
@DisplayName("Account Tests")
class AccountTest {
    
    private Account testAccount;
    private SavingsAccount testSavingsAccount;
    private static final String TEST_ACCOUNT_HOLDER = "Test User";
    private static final double INITIAL_BALANCE = 1000.0;
    private static final double INTEREST_RATE = 0.05; // 5%
    
    /**
     * Sets up test fixtures before each test method.
     * This method runs before each individual test to ensure clean state.
     */
    @BeforeEach
    void setUp() {
        testAccount = new Account(TEST_ACCOUNT_HOLDER, INITIAL_BALANCE);
        testSavingsAccount = new SavingsAccount(TEST_ACCOUNT_HOLDER, INITIAL_BALANCE, INTEREST_RATE);
    }
    
    /**
     * Test that account is initialized with correct values.
     */
    @Test
    @DisplayName("Account initialization should set correct values")
    void testAccountInitialization() {
        assertEquals(TEST_ACCOUNT_HOLDER, testAccount.getAccountHolder());
        assertEquals(INITIAL_BALANCE, testAccount.getBalance(), 0.01);
    }
    
    /**
     * Test that savings account is initialized with correct values.
     */
    @Test
    @DisplayName("SavingsAccount initialization should set correct values")
    void testSavingsAccountInitialization() {
        assertEquals(TEST_ACCOUNT_HOLDER, testSavingsAccount.getAccountHolder());
        assertEquals(INITIAL_BALANCE, testSavingsAccount.getBalance(), 0.01);
        assertEquals(INTEREST_RATE, testSavingsAccount.getInterestRate(), 0.01);
    }
    
    /**
     * Test that a valid deposit correctly increases the balance.
     */
    @Test
    @DisplayName("Valid deposit should increase balance")
    void testValidDeposit() {
        double depositAmount = 250.0;
        double expectedBalance = INITIAL_BALANCE + depositAmount;
        
        boolean result = testAccount.deposit(depositAmount);
        
        assertTrue(result, "Deposit should return true for valid amount");
        assertEquals(expectedBalance, testAccount.getBalance(), 0.01, 
                   "Balance should increase by deposit amount");
    }
    
    /**
     * Test that multiple deposits accumulate correctly.
     */
    @Test
    @DisplayName("Multiple deposits should accumulate correctly")
    void testMultipleDeposits() {
        double firstDeposit = 100.0;
        double secondDeposit = 200.0;
        double expectedBalance = INITIAL_BALANCE + firstDeposit + secondDeposit;
        
        testAccount.deposit(firstDeposit);
        testAccount.deposit(secondDeposit);
        
        assertEquals(expectedBalance, testAccount.getBalance(), 0.01,
                   "Multiple deposits should accumulate correctly");
    }
    
    /**
     * Test that invalid deposits are rejected.
     */
    @Test
    @DisplayName("Invalid deposits should be rejected")
    void testInvalidDeposits() {
        // Test negative deposit
        boolean result1 = testAccount.deposit(-100.0);
        assertFalse(result1, "Negative deposit should be rejected");
        assertEquals(INITIAL_BALANCE, testAccount.getBalance(), 0.01,
                   "Balance should not change for invalid deposit");
        
        // Test zero deposit
        boolean result2 = testAccount.deposit(0.0);
        assertFalse(result2, "Zero deposit should be rejected");
        assertEquals(INITIAL_BALANCE, testAccount.getBalance(), 0.01,
                   "Balance should not change for zero deposit");
    }
    
    /**
     * Test that a valid withdrawal correctly decreases the balance.
     */
    @Test
    @DisplayName("Valid withdrawal should decrease balance")
    void testValidWithdrawal() throws InsufficientFundsException {
        double withdrawalAmount = 300.0;
        double expectedBalance = INITIAL_BALANCE - withdrawalAmount;
        
        testAccount.withdraw(withdrawalAmount);
        
        assertEquals(expectedBalance, testAccount.getBalance(), 0.01,
                   "Balance should decrease by withdrawal amount");
    }
    
    /**
     * Test that withdrawal of exact balance amount is allowed.
     */
    @Test
    @DisplayName("Withdrawal of exact balance should be allowed")
    void testWithdrawalOfExactBalance() throws InsufficientFundsException {
        testAccount.withdraw(INITIAL_BALANCE);
        
        assertEquals(0.0, testAccount.getBalance(), 0.01,
                   "Balance should be zero after withdrawing exact amount");
    }
    
    /**
     * Test that attempting to withdraw more than available balance throws InsufficientFundsException.
     */
    @Test
    @DisplayName("Withdrawal exceeding balance should throw InsufficientFundsException")
    void testWithdrawalExceedingBalance() {
        double excessiveAmount = INITIAL_BALANCE + 100.0;
        
        InsufficientFundsException exception = assertThrows(
            InsufficientFundsException.class,
            () -> testAccount.withdraw(excessiveAmount),
            "Withdrawal exceeding balance should throw InsufficientFundsException"
        );
        
        // Verify exception details
        assertEquals(excessiveAmount, exception.getRequestedAmount(), 0.01,
                   "Exception should contain correct requested amount");
        assertEquals(INITIAL_BALANCE, exception.getAvailableBalance(), 0.01,
                   "Exception should contain correct available balance");
        
        // Verify balance unchanged
        assertEquals(INITIAL_BALANCE, testAccount.getBalance(), 0.01,
                   "Balance should remain unchanged after failed withdrawal");
    }
    
    /**
     * Test that invalid withdrawal amounts throw IllegalArgumentException.
     */
    @Test
    @DisplayName("Invalid withdrawal amounts should throw IllegalArgumentException")
    void testInvalidWithdrawalAmounts() {
        // Test negative withdrawal
        IllegalArgumentException exception1 = assertThrows(
            IllegalArgumentException.class,
            () -> testAccount.withdraw(-100.0),
            "Negative withdrawal should throw IllegalArgumentException"
        );
        assertEquals("Withdrawal amount must be positive", exception1.getMessage());
        
        // Test zero withdrawal
        IllegalArgumentException exception2 = assertThrows(
            IllegalArgumentException.class,
            () -> testAccount.withdraw(0.0),
            "Zero withdrawal should throw IllegalArgumentException"
        );
        assertEquals("Withdrawal amount must be positive", exception2.getMessage());
    }
    
    /**
     * Test that SavingsAccount applyInterest method calculates new balance correctly.
     */
    @Test
    @DisplayName("SavingsAccount applyInterest should calculate balance correctly")
    void testSavingsAccountApplyInterest() {
        double expectedInterest = INITIAL_BALANCE * INTEREST_RATE;
        double expectedBalance = INITIAL_BALANCE + expectedInterest;
        
        testSavingsAccount.applyInterest();
        
        assertEquals(expectedBalance, testSavingsAccount.getBalance(), 0.01,
                   "Balance should increase by calculated interest amount");
    }
    
    /**
     * Test that multiple interest applications compound correctly.
     */
    @Test
    @DisplayName("Multiple interest applications should compound correctly")
    void testMultipleInterestApplications() {
        // Apply interest twice
        testSavingsAccount.applyInterest();
        double balanceAfterFirst = testSavingsAccount.getBalance();
        testSavingsAccount.applyInterest();
        
        double expectedSecondInterest = balanceAfterFirst * INTEREST_RATE;
        double expectedFinalBalance = balanceAfterFirst + expectedSecondInterest;
        
        assertEquals(expectedFinalBalance, testSavingsAccount.getBalance(), 0.01,
                   "Multiple interest applications should compound correctly");
    }
    
    /**
     * Test that interest rate can be updated.
     */
    @Test
    @DisplayName("Interest rate should be updatable")
    void testInterestRateUpdate() {
        double newInterestRate = 0.08; // 8%
        
        testSavingsAccount.setInterestRate(newInterestRate);
        
        assertEquals(newInterestRate, testSavingsAccount.getInterestRate(), 0.01,
                   "Interest rate should be updated correctly");
    }
    
    /**
     * Test that negative interest rate is rejected.
     */
    @Test
    @DisplayName("Negative interest rate should be rejected")
    void testNegativeInterestRate() {
        double originalRate = testSavingsAccount.getInterestRate();
        
        testSavingsAccount.setInterestRate(-0.05);
        
        assertEquals(originalRate, testSavingsAccount.getInterestRate(), 0.01,
                   "Interest rate should not change for negative value");
    }
    
    /**
     * Test account toString method.
     */
    @Test
    @DisplayName("Account toString should return proper representation")
    void testAccountToString() {
        String accountString = testAccount.toString();
        
        assertTrue(accountString.contains(TEST_ACCOUNT_HOLDER),
                  "toString should contain account holder name");
        assertTrue(accountString.contains("Account"),
                  "toString should contain class name");
        assertTrue(accountString.contains("$1000.00"),
                  "toString should contain formatted balance");
    }
    
    /**
     * Test savings account toString method.
     */
    @Test
    @DisplayName("SavingsAccount toString should return proper representation")
    void testSavingsAccountToString() {
        String savingsString = testSavingsAccount.toString();
        
        assertTrue(savingsString.contains(TEST_ACCOUNT_HOLDER),
                  "toString should contain account holder name");
        assertTrue(savingsString.contains("SavingsAccount"),
                  "toString should contain class name");
        assertTrue(savingsString.contains("$1000.00"),
                  "toString should contain formatted balance");
        assertTrue(savingsString.contains("5.00%"),
                  "toString should contain formatted interest rate");
    }
    
    /**
     * Test edge case: very small deposit amounts.
     */
    @Test
    @DisplayName("Very small deposit amounts should be handled correctly")
    void testVerySmallDeposit() {
        double smallAmount = 0.01;
        double expectedBalance = INITIAL_BALANCE + smallAmount;
        
        boolean result = testAccount.deposit(smallAmount);
        
        assertTrue(result, "Very small deposit should be accepted");
        assertEquals(expectedBalance, testAccount.getBalance(), 0.01,
                   "Very small deposit should be added correctly");
    }
    
    /**
     * Test edge case: very small withdrawal amounts.
     */
    @Test
    @DisplayName("Very small withdrawal amounts should be handled correctly")
    void testVerySmallWithdrawal() throws InsufficientFundsException {
        double smallAmount = 0.01;
        double expectedBalance = INITIAL_BALANCE - smallAmount;
        
        testAccount.withdraw(smallAmount);
        
        assertEquals(expectedBalance, testAccount.getBalance(), 0.01,
                   "Very small withdrawal should be processed correctly");
    }
    
    /**
     * Test that account operations work correctly with floating point precision.
     */
    @Test
    @DisplayName("Floating point precision should be handled correctly")
    void testFloatingPointPrecision() throws InsufficientFundsException {
        double depositAmount = 0.1;
        double withdrawalAmount = 0.1;
        
        testAccount.deposit(depositAmount);
        testAccount.withdraw(withdrawalAmount);
        
        assertEquals(INITIAL_BALANCE, testAccount.getBalance(), 0.01,
                   "Floating point operations should maintain precision");
    }
}

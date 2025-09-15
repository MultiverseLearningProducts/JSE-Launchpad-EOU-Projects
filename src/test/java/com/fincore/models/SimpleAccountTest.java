package com.fincore.models;

/**
 * Simple test class for Account and SavingsAccount classes.
 * This class provides basic unit testing without external dependencies.
 * 
 * @author FinCore Development Team
 * @version 6.0.0
 */
public class SimpleAccountTest {
    
    private static int testsPassed = 0;
    private static int testsFailed = 0;
    
    /**
     * Main method to run all tests.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        System.out.println("=== FinCore CLI Banking - Simple Test Suite ===");
        System.out.println("Running comprehensive unit tests...");
        System.out.println();
        
        // Run all tests
        testAccountInitialization();
        testSavingsAccountInitialization();
        testValidDeposit();
        testMultipleDeposits();
        testInvalidDeposits();
        testValidWithdrawal();
        testWithdrawalOfExactBalance();
        testWithdrawalExceedingBalance();
        testInvalidWithdrawalAmounts();
        testSavingsAccountApplyInterest();
        testMultipleInterestApplications();
        testInterestRateUpdate();
        testNegativeInterestRate();
        testAccountToString();
        testSavingsAccountToString();
        testVerySmallDeposit();
        testVerySmallWithdrawal();
        testFloatingPointPrecision();
        
        // Print results
        System.out.println();
        System.out.println("=== Test Results ===");
        System.out.println("Tests Passed: " + testsPassed);
        System.out.println("Tests Failed: " + testsFailed);
        System.out.println("Total Tests: " + (testsPassed + testsFailed));
        
        if (testsFailed == 0) {
            System.out.println("🎉 All tests passed! The banking system is reliable.");
        } else {
            System.out.println("❌ Some tests failed. Please review the implementation.");
        }
    }
    
    /**
     * Test that account is initialized with correct values.
     */
    private static void testAccountInitialization() {
        try {
            Account account = new Account("Test User", 1000.0);
            
            assertEqual("Test User", account.getAccountHolder(), "Account holder should be set correctly");
            assertEqual(1000.0, account.getBalance(), "Initial balance should be set correctly");
            
            System.out.println("✅ Account initialization test passed");
            testsPassed++;
        } catch (Exception e) {
            System.out.println("❌ Account initialization test failed: " + e.getMessage());
            testsFailed++;
        }
    }
    
    /**
     * Test that savings account is initialized with correct values.
     */
    private static void testSavingsAccountInitialization() {
        try {
            SavingsAccount savingsAccount = new SavingsAccount("Test User", 1000.0, 0.05);
            
            assertEqual("Test User", savingsAccount.getAccountHolder(), "Account holder should be set correctly");
            assertEqual(1000.0, savingsAccount.getBalance(), "Initial balance should be set correctly");
            assertEqual(0.05, savingsAccount.getInterestRate(), "Interest rate should be set correctly");
            
            System.out.println("✅ SavingsAccount initialization test passed");
            testsPassed++;
        } catch (Exception e) {
            System.out.println("❌ SavingsAccount initialization test failed: " + e.getMessage());
            testsFailed++;
        }
    }
    
    /**
     * Test that a valid deposit correctly increases the balance.
     */
    private static void testValidDeposit() {
        try {
            Account account = new Account("Test User", 1000.0);
            double depositAmount = 250.0;
            double expectedBalance = 1000.0 + depositAmount;
            
            boolean result = account.deposit(depositAmount);
            
            assertTrue(result, "Deposit should return true for valid amount");
            assertEqual(expectedBalance, account.getBalance(), "Balance should increase by deposit amount");
            
            System.out.println("✅ Valid deposit test passed");
            testsPassed++;
        } catch (Exception e) {
            System.out.println("❌ Valid deposit test failed: " + e.getMessage());
            testsFailed++;
        }
    }
    
    /**
     * Test that multiple deposits accumulate correctly.
     */
    private static void testMultipleDeposits() {
        try {
            Account account = new Account("Test User", 1000.0);
            double firstDeposit = 100.0;
            double secondDeposit = 200.0;
            double expectedBalance = 1000.0 + firstDeposit + secondDeposit;
            
            account.deposit(firstDeposit);
            account.deposit(secondDeposit);
            
            assertEqual(expectedBalance, account.getBalance(), "Multiple deposits should accumulate correctly");
            
            System.out.println("✅ Multiple deposits test passed");
            testsPassed++;
        } catch (Exception e) {
            System.out.println("❌ Multiple deposits test failed: " + e.getMessage());
            testsFailed++;
        }
    }
    
    /**
     * Test that invalid deposits are rejected.
     */
    private static void testInvalidDeposits() {
        try {
            Account account = new Account("Test User", 1000.0);
            double originalBalance = account.getBalance();
            
            // Test negative deposit
            boolean result1 = account.deposit(-100.0);
            assertFalse(result1, "Negative deposit should be rejected");
            assertEqual(originalBalance, account.getBalance(), "Balance should not change for invalid deposit");
            
            // Test zero deposit
            boolean result2 = account.deposit(0.0);
            assertFalse(result2, "Zero deposit should be rejected");
            assertEqual(originalBalance, account.getBalance(), "Balance should not change for zero deposit");
            
            System.out.println("✅ Invalid deposits test passed");
            testsPassed++;
        } catch (Exception e) {
            System.out.println("❌ Invalid deposits test failed: " + e.getMessage());
            testsFailed++;
        }
    }
    
    /**
     * Test that a valid withdrawal correctly decreases the balance.
     */
    private static void testValidWithdrawal() {
        try {
            Account account = new Account("Test User", 1000.0);
            double withdrawalAmount = 300.0;
            double expectedBalance = 1000.0 - withdrawalAmount;
            
            account.withdraw(withdrawalAmount);
            
            assertEqual(expectedBalance, account.getBalance(), "Balance should decrease by withdrawal amount");
            
            System.out.println("✅ Valid withdrawal test passed");
            testsPassed++;
        } catch (Exception e) {
            System.out.println("❌ Valid withdrawal test failed: " + e.getMessage());
            testsFailed++;
        }
    }
    
    /**
     * Test that withdrawal of exact balance amount is allowed.
     */
    private static void testWithdrawalOfExactBalance() {
        try {
            Account account = new Account("Test User", 1000.0);
            
            account.withdraw(1000.0);
            
            assertEqual(0.0, account.getBalance(), "Balance should be zero after withdrawing exact amount");
            
            System.out.println("✅ Withdrawal of exact balance test passed");
            testsPassed++;
        } catch (Exception e) {
            System.out.println("❌ Withdrawal of exact balance test failed: " + e.getMessage());
            testsFailed++;
        }
    }
    
    /**
     * Test that attempting to withdraw more than available balance throws InsufficientFundsException.
     */
    private static void testWithdrawalExceedingBalance() {
        try {
            Account account = new Account("Test User", 1000.0);
            double excessiveAmount = 1500.0;
            double originalBalance = account.getBalance();
            
            try {
                account.withdraw(excessiveAmount);
                System.out.println("❌ Withdrawal exceeding balance test failed: Should have thrown exception");
                testsFailed++;
                return;
            } catch (InsufficientFundsException e) {
                // Expected exception
                assertEqual(excessiveAmount, e.getRequestedAmount(), "Exception should contain correct requested amount");
                assertEqual(originalBalance, e.getAvailableBalance(), "Exception should contain correct available balance");
                assertEqual(originalBalance, account.getBalance(), "Balance should remain unchanged after failed withdrawal");
            }
            
            System.out.println("✅ Withdrawal exceeding balance test passed");
            testsPassed++;
        } catch (Exception e) {
            System.out.println("❌ Withdrawal exceeding balance test failed: " + e.getMessage());
            testsFailed++;
        }
    }
    
    /**
     * Test that invalid withdrawal amounts throw IllegalArgumentException.
     */
    private static void testInvalidWithdrawalAmounts() {
        try {
            Account account = new Account("Test User", 1000.0);
            
            // Test negative withdrawal
            try {
                account.withdraw(-100.0);
                System.out.println("❌ Invalid withdrawal amounts test failed: Should have thrown exception for negative amount");
                testsFailed++;
                return;
            } catch (IllegalArgumentException e) {
                assertEqual("Withdrawal amount must be positive", e.getMessage(), "Exception message should be correct");
            }
            
            // Test zero withdrawal
            try {
                account.withdraw(0.0);
                System.out.println("❌ Invalid withdrawal amounts test failed: Should have thrown exception for zero amount");
                testsFailed++;
                return;
            } catch (IllegalArgumentException e) {
                assertEqual("Withdrawal amount must be positive", e.getMessage(), "Exception message should be correct");
            }
            
            System.out.println("✅ Invalid withdrawal amounts test passed");
            testsPassed++;
        } catch (Exception e) {
            System.out.println("❌ Invalid withdrawal amounts test failed: " + e.getMessage());
            testsFailed++;
        }
    }
    
    /**
     * Test that SavingsAccount applyInterest method calculates new balance correctly.
     */
    private static void testSavingsAccountApplyInterest() {
        try {
            SavingsAccount savingsAccount = new SavingsAccount("Test User", 1000.0, 0.05);
            double expectedInterest = 1000.0 * 0.05;
            double expectedBalance = 1000.0 + expectedInterest;
            
            savingsAccount.applyInterest();
            
            assertEqual(expectedBalance, savingsAccount.getBalance(), "Balance should increase by calculated interest amount");
            
            System.out.println("✅ SavingsAccount applyInterest test passed");
            testsPassed++;
        } catch (Exception e) {
            System.out.println("❌ SavingsAccount applyInterest test failed: " + e.getMessage());
            testsFailed++;
        }
    }
    
    /**
     * Test that multiple interest applications compound correctly.
     */
    private static void testMultipleInterestApplications() {
        try {
            SavingsAccount savingsAccount = new SavingsAccount("Test User", 1000.0, 0.05);
            
            // Apply interest twice
            savingsAccount.applyInterest();
            double balanceAfterFirst = savingsAccount.getBalance();
            savingsAccount.applyInterest();
            
            double expectedSecondInterest = balanceAfterFirst * 0.05;
            double expectedFinalBalance = balanceAfterFirst + expectedSecondInterest;
            
            assertEqual(expectedFinalBalance, savingsAccount.getBalance(), "Multiple interest applications should compound correctly");
            
            System.out.println("✅ Multiple interest applications test passed");
            testsPassed++;
        } catch (Exception e) {
            System.out.println("❌ Multiple interest applications test failed: " + e.getMessage());
            testsFailed++;
        }
    }
    
    /**
     * Test that interest rate can be updated.
     */
    private static void testInterestRateUpdate() {
        try {
            SavingsAccount savingsAccount = new SavingsAccount("Test User", 1000.0, 0.05);
            double newInterestRate = 0.08;
            
            savingsAccount.setInterestRate(newInterestRate);
            
            assertEqual(newInterestRate, savingsAccount.getInterestRate(), "Interest rate should be updated correctly");
            
            System.out.println("✅ Interest rate update test passed");
            testsPassed++;
        } catch (Exception e) {
            System.out.println("❌ Interest rate update test failed: " + e.getMessage());
            testsFailed++;
        }
    }
    
    /**
     * Test that negative interest rate is rejected.
     */
    private static void testNegativeInterestRate() {
        try {
            SavingsAccount savingsAccount = new SavingsAccount("Test User", 1000.0, 0.05);
            double originalRate = savingsAccount.getInterestRate();
            
            savingsAccount.setInterestRate(-0.05);
            
            assertEqual(originalRate, savingsAccount.getInterestRate(), "Interest rate should not change for negative value");
            
            System.out.println("✅ Negative interest rate test passed");
            testsPassed++;
        } catch (Exception e) {
            System.out.println("❌ Negative interest rate test failed: " + e.getMessage());
            testsFailed++;
        }
    }
    
    /**
     * Test account toString method.
     */
    private static void testAccountToString() {
        try {
            Account account = new Account("Test User", 1000.0);
            String accountString = account.toString();
            
            assertTrue(accountString.contains("Test User"), "toString should contain account holder name");
            assertTrue(accountString.contains("Account"), "toString should contain class name");
            assertTrue(accountString.contains("$1000.00"), "toString should contain formatted balance");
            
            System.out.println("✅ Account toString test passed");
            testsPassed++;
        } catch (Exception e) {
            System.out.println("❌ Account toString test failed: " + e.getMessage());
            testsFailed++;
        }
    }
    
    /**
     * Test savings account toString method.
     */
    private static void testSavingsAccountToString() {
        try {
            SavingsAccount savingsAccount = new SavingsAccount("Test User", 1000.0, 0.05);
            String savingsString = savingsAccount.toString();
            
            assertTrue(savingsString.contains("Test User"), "toString should contain account holder name");
            assertTrue(savingsString.contains("SavingsAccount"), "toString should contain class name");
            assertTrue(savingsString.contains("$1000.00"), "toString should contain formatted balance");
            assertTrue(savingsString.contains("5.00%"), "toString should contain formatted interest rate");
            
            System.out.println("✅ SavingsAccount toString test passed");
            testsPassed++;
        } catch (Exception e) {
            System.out.println("❌ SavingsAccount toString test failed: " + e.getMessage());
            testsFailed++;
        }
    }
    
    /**
     * Test edge case: very small deposit amounts.
     */
    private static void testVerySmallDeposit() {
        try {
            Account account = new Account("Test User", 1000.0);
            double smallAmount = 0.01;
            double expectedBalance = 1000.0 + smallAmount;
            
            boolean result = account.deposit(smallAmount);
            
            assertTrue(result, "Very small deposit should be accepted");
            assertEqual(expectedBalance, account.getBalance(), "Very small deposit should be added correctly");
            
            System.out.println("✅ Very small deposit test passed");
            testsPassed++;
        } catch (Exception e) {
            System.out.println("❌ Very small deposit test failed: " + e.getMessage());
            testsFailed++;
        }
    }
    
    /**
     * Test edge case: very small withdrawal amounts.
     */
    private static void testVerySmallWithdrawal() {
        try {
            Account account = new Account("Test User", 1000.0);
            double smallAmount = 0.01;
            double expectedBalance = 1000.0 - smallAmount;
            
            account.withdraw(smallAmount);
            
            assertEqual(expectedBalance, account.getBalance(), "Very small withdrawal should be processed correctly");
            
            System.out.println("✅ Very small withdrawal test passed");
            testsPassed++;
        } catch (Exception e) {
            System.out.println("❌ Very small withdrawal test failed: " + e.getMessage());
            testsFailed++;
        }
    }
    
    /**
     * Test that account operations work correctly with floating point precision.
     */
    private static void testFloatingPointPrecision() {
        try {
            Account account = new Account("Test User", 1000.0);
            double depositAmount = 0.1;
            double withdrawalAmount = 0.1;
            
            account.deposit(depositAmount);
            account.withdraw(withdrawalAmount);
            
            assertEqual(1000.0, account.getBalance(), "Floating point operations should maintain precision");
            
            System.out.println("✅ Floating point precision test passed");
            testsPassed++;
        } catch (Exception e) {
            System.out.println("❌ Floating point precision test failed: " + e.getMessage());
            testsFailed++;
        }
    }
    
    // Helper assertion methods
    
    private static void assertEqual(Object expected, Object actual, String message) {
        if (!expected.equals(actual)) {
            throw new AssertionError(message + " - Expected: " + expected + ", Actual: " + actual);
        }
    }
    
    private static void assertEqual(double expected, double actual, String message) {
        if (Math.abs(expected - actual) > 0.01) {
            throw new AssertionError(message + " - Expected: " + expected + ", Actual: " + actual);
        }
    }
    
    private static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
    
    private static void assertFalse(boolean condition, String message) {
        if (condition) {
            throw new AssertionError(message);
        }
    }
}

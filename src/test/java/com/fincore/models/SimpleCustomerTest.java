package com.fincore.models;

/**
 * Simple test class for Customer and Bank classes.
 * This class provides basic unit testing without external dependencies.
 * 
 * @author FinCore Development Team
 * @version 6.0.0
 */
public class SimpleCustomerTest {
    
    private static int testsPassed = 0;
    private static int testsFailed = 0;
    
    /**
     * Main method to run all tests.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        System.out.println("=== FinCore CLI Banking - Customer Test Suite ===");
        System.out.println("Running customer and bank management tests...");
        System.out.println();
        
        // Run all tests
        testCustomerInitialization();
        testAddAccount();
        testAddMultipleAccounts();
        testAddDuplicateAccount();
        testAddNullAccount();
        testGetAccountByIndex();
        testGetAccountInvalidIndex();
        testRemoveAccount();
        testRemoveNonExistentAccount();
        testGetTotalBalance();
        testGetTotalBalanceNoAccounts();
        testCustomerEquality();
        testCustomerHashCode();
        testCustomerToString();
        testBankInitialization();
        testAddCustomerWithAutoId();
        testAddCustomerWithSpecificId();
        testAddDuplicateCustomerId();
        testGetCustomerById();
        testGetNonExistentCustomer();
        testHasCustomer();
        testRemoveCustomer();
        testRemoveNonExistentCustomer();
        testGetAllCustomers();
        testGetTotalAccountCount();
        testGetTotalBankBalance();
        testBankToString();
        
        // Print results
        System.out.println();
        System.out.println("=== Test Results ===");
        System.out.println("Tests Passed: " + testsPassed);
        System.out.println("Tests Failed: " + testsFailed);
        System.out.println("Total Tests: " + (testsPassed + testsFailed));
        
        if (testsFailed == 0) {
            System.out.println("🎉 All customer tests passed! The customer management system is reliable.");
        } else {
            System.out.println("❌ Some customer tests failed. Please review the implementation.");
        }
    }
    
    /**
     * Test that customer is initialized with correct values.
     */
    private static void testCustomerInitialization() {
        try {
            Customer customer = new Customer("TEST001", "Test Customer");
            
            assertEqual("TEST001", customer.getCustomerId(), "Customer ID should be set correctly");
            assertEqual("Test Customer", customer.getName(), "Customer name should be set correctly");
            assertEqual(0, customer.getAccountCount(), "Initial account count should be 0");
            assertTrue(customer.getAccounts().isEmpty(), "Initial accounts list should be empty");
            
            System.out.println("✅ Customer initialization test passed");
            testsPassed++;
        } catch (Exception e) {
            System.out.println("❌ Customer initialization test failed: " + e.getMessage());
            testsFailed++;
        }
    }
    
    /**
     * Test that account can be added to customer.
     */
    private static void testAddAccount() {
        try {
            Customer customer = new Customer("TEST001", "Test Customer");
            Account account = new Account("Test Customer", 1000.0);
            
            boolean result = customer.addAccount(account);
            
            assertTrue(result, "Account should be added successfully");
            assertEqual(1, customer.getAccountCount(), "Account count should be 1");
            assertTrue(customer.getAccounts().contains(account), "Accounts list should contain the account");
            
            System.out.println("✅ Add account test passed");
            testsPassed++;
        } catch (Exception e) {
            System.out.println("❌ Add account test failed: " + e.getMessage());
            testsFailed++;
        }
    }
    
    /**
     * Test that multiple accounts can be added to customer.
     */
    private static void testAddMultipleAccounts() {
        try {
            Customer customer = new Customer("TEST001", "Test Customer");
            Account account1 = new Account("Test Customer", 1000.0);
            Account account2 = new SavingsAccount("Test Customer", 500.0, 0.05);
            
            customer.addAccount(account1);
            customer.addAccount(account2);
            
            assertEqual(2, customer.getAccountCount(), "Account count should be 2");
            assertTrue(customer.getAccounts().contains(account1), "Accounts list should contain account1");
            assertTrue(customer.getAccounts().contains(account2), "Accounts list should contain account2");
            
            System.out.println("✅ Add multiple accounts test passed");
            testsPassed++;
        } catch (Exception e) {
            System.out.println("❌ Add multiple accounts test failed: " + e.getMessage());
            testsFailed++;
        }
    }
    
    /**
     * Test that duplicate accounts are not added.
     */
    private static void testAddDuplicateAccount() {
        try {
            Customer customer = new Customer("TEST001", "Test Customer");
            Account account = new Account("Test Customer", 1000.0);
            
            customer.addAccount(account);
            boolean result = customer.addAccount(account);
            
            assertFalse(result, "Duplicate account should not be added");
            assertEqual(1, customer.getAccountCount(), "Account count should remain 1");
            
            System.out.println("✅ Add duplicate account test passed");
            testsPassed++;
        } catch (Exception e) {
            System.out.println("❌ Add duplicate account test failed: " + e.getMessage());
            testsFailed++;
        }
    }
    
    /**
     * Test that null account is not added.
     */
    private static void testAddNullAccount() {
        try {
            Customer customer = new Customer("TEST001", "Test Customer");
            
            boolean result = customer.addAccount(null);
            
            assertFalse(result, "Null account should not be added");
            assertEqual(0, customer.getAccountCount(), "Account count should remain 0");
            
            System.out.println("✅ Add null account test passed");
            testsPassed++;
        } catch (Exception e) {
            System.out.println("❌ Add null account test failed: " + e.getMessage());
            testsFailed++;
        }
    }
    
    /**
     * Test that account can be retrieved by index.
     */
    private static void testGetAccountByIndex() {
        try {
            Customer customer = new Customer("TEST001", "Test Customer");
            Account account1 = new Account("Test Customer", 1000.0);
            Account account2 = new SavingsAccount("Test Customer", 500.0, 0.05);
            
            customer.addAccount(account1);
            customer.addAccount(account2);
            
            assertEqual(account1, customer.getAccount(0), "First account should be retrieved correctly");
            assertEqual(account2, customer.getAccount(1), "Second account should be retrieved correctly");
            
            System.out.println("✅ Get account by index test passed");
            testsPassed++;
        } catch (Exception e) {
            System.out.println("❌ Get account by index test failed: " + e.getMessage());
            testsFailed++;
        }
    }
    
    /**
     * Test that invalid index returns null.
     */
    private static void testGetAccountInvalidIndex() {
        try {
            Customer customer = new Customer("TEST001", "Test Customer");
            Account account = new Account("Test Customer", 1000.0);
            
            customer.addAccount(account);
            
            assertNull(customer.getAccount(-1), "Negative index should return null");
            assertNull(customer.getAccount(1), "Index beyond bounds should return null");
            
            System.out.println("✅ Get account invalid index test passed");
            testsPassed++;
        } catch (Exception e) {
            System.out.println("❌ Get account invalid index test failed: " + e.getMessage());
            testsFailed++;
        }
    }
    
    /**
     * Test that account can be removed from customer.
     */
    private static void testRemoveAccount() {
        try {
            Customer customer = new Customer("TEST001", "Test Customer");
            Account account = new Account("Test Customer", 1000.0);
            
            customer.addAccount(account);
            boolean result = customer.removeAccount(account);
            
            assertTrue(result, "Account should be removed successfully");
            assertEqual(0, customer.getAccountCount(), "Account count should be 0");
            assertFalse(customer.getAccounts().contains(account), "Accounts list should not contain the account");
            
            System.out.println("✅ Remove account test passed");
            testsPassed++;
        } catch (Exception e) {
            System.out.println("❌ Remove account test failed: " + e.getMessage());
            testsFailed++;
        }
    }
    
    /**
     * Test that removing non-existent account returns false.
     */
    private static void testRemoveNonExistentAccount() {
        try {
            Customer customer = new Customer("TEST001", "Test Customer");
            Account account = new Account("Test Customer", 1000.0);
            
            boolean result = customer.removeAccount(account);
            
            assertFalse(result, "Removing non-existent account should return false");
            assertEqual(0, customer.getAccountCount(), "Account count should remain 0");
            
            System.out.println("✅ Remove non-existent account test passed");
            testsPassed++;
        } catch (Exception e) {
            System.out.println("❌ Remove non-existent account test failed: " + e.getMessage());
            testsFailed++;
        }
    }
    
    /**
     * Test that total balance is calculated correctly.
     */
    private static void testGetTotalBalance() {
        try {
            Customer customer = new Customer("TEST001", "Test Customer");
            Account account1 = new Account("Test Customer", 1000.0);
            Account account2 = new SavingsAccount("Test Customer", 500.0, 0.05);
            
            customer.addAccount(account1);
            customer.addAccount(account2);
            
            double expectedTotal = 1000.0 + 500.0;
            assertEqual(expectedTotal, customer.getTotalBalance(), "Total balance should be calculated correctly");
            
            System.out.println("✅ Get total balance test passed");
            testsPassed++;
        } catch (Exception e) {
            System.out.println("❌ Get total balance test failed: " + e.getMessage());
            testsFailed++;
        }
    }
    
    /**
     * Test that total balance is zero for customer with no accounts.
     */
    private static void testGetTotalBalanceNoAccounts() {
        try {
            Customer customer = new Customer("TEST001", "Test Customer");
            
            assertEqual(0.0, customer.getTotalBalance(), "Total balance should be zero for customer with no accounts");
            
            System.out.println("✅ Get total balance no accounts test passed");
            testsPassed++;
        } catch (Exception e) {
            System.out.println("❌ Get total balance no accounts test failed: " + e.getMessage());
            testsFailed++;
        }
    }
    
    /**
     * Test customer equality.
     */
    private static void testCustomerEquality() {
        try {
            Customer customer1 = new Customer("TEST001", "Test Customer");
            Customer customer2 = new Customer("TEST001", "Different Name");
            Customer customer3 = new Customer("TEST002", "Test Customer");
            
            assertEqual(customer1, customer2, "Customers with same ID should be equal");
            assertNotEqual(customer1, customer3, "Customers with different IDs should not be equal");
            assertNotEqual(customer1, null, "Customer should not equal null");
            
            System.out.println("✅ Customer equality test passed");
            testsPassed++;
        } catch (Exception e) {
            System.out.println("❌ Customer equality test failed: " + e.getMessage());
            testsFailed++;
        }
    }
    
    /**
     * Test customer hash code.
     */
    private static void testCustomerHashCode() {
        try {
            Customer customer1 = new Customer("TEST001", "Test Customer");
            Customer customer2 = new Customer("TEST001", "Different Name");
            
            assertEqual(customer1.hashCode(), customer2.hashCode(), "Customers with same ID should have same hash code");
            
            System.out.println("✅ Customer hash code test passed");
            testsPassed++;
        } catch (Exception e) {
            System.out.println("❌ Customer hash code test failed: " + e.getMessage());
            testsFailed++;
        }
    }
    
    /**
     * Test customer toString method.
     */
    private static void testCustomerToString() {
        try {
            Customer customer = new Customer("TEST001", "Test Customer");
            Account account = new Account("Test Customer", 1000.0);
            
            customer.addAccount(account);
            String customerString = customer.toString();
            
            assertTrue(customerString.contains("TEST001"), "toString should contain customer ID");
            assertTrue(customerString.contains("Test Customer"), "toString should contain customer name");
            assertTrue(customerString.contains("1"), "toString should contain account count");
            
            System.out.println("✅ Customer toString test passed");
            testsPassed++;
        } catch (Exception e) {
            System.out.println("❌ Customer toString test failed: " + e.getMessage());
            testsFailed++;
        }
    }
    
    /**
     * Test that bank is initialized empty.
     */
    private static void testBankInitialization() {
        try {
            Bank bank = new Bank();
            
            assertEqual(0, bank.getCustomerCount(), "Initial customer count should be 0");
            assertEqual(0, bank.getTotalAccountCount(), "Initial account count should be 0");
            assertEqual(0.0, bank.getTotalBankBalance(), "Initial bank balance should be 0");
            assertTrue(bank.getAllCustomers().isEmpty(), "Initial customers list should be empty");
            
            System.out.println("✅ Bank initialization test passed");
            testsPassed++;
        } catch (Exception e) {
            System.out.println("❌ Bank initialization test failed: " + e.getMessage());
            testsFailed++;
        }
    }
    
    /**
     * Test that customer can be added with auto-generated ID.
     */
    private static void testAddCustomerWithAutoId() {
        try {
            Bank bank = new Bank();
            Customer customer = bank.addCustomer("Test Customer");
            
            assertNotNull(customer, "Customer should be created");
            assertEqual("Test Customer", customer.getName(), "Customer name should be set correctly");
            assertTrue(customer.getCustomerId().startsWith("CUST"), "Customer ID should start with CUST");
            assertEqual(1, bank.getCustomerCount(), "Customer count should be 1");
            
            System.out.println("✅ Add customer with auto ID test passed");
            testsPassed++;
        } catch (Exception e) {
            System.out.println("❌ Add customer with auto ID test failed: " + e.getMessage());
            testsFailed++;
        }
    }
    
    /**
     * Test that customer can be added with specific ID.
     */
    private static void testAddCustomerWithSpecificId() {
        try {
            Bank bank = new Bank();
            Customer customer = bank.addCustomer("TEST001", "Test Customer");
            
            assertNotNull(customer, "Customer should be created");
            assertEqual("TEST001", customer.getCustomerId(), "Customer ID should be set correctly");
            assertEqual("Test Customer", customer.getName(), "Customer name should be set correctly");
            assertEqual(1, bank.getCustomerCount(), "Customer count should be 1");
            
            System.out.println("✅ Add customer with specific ID test passed");
            testsPassed++;
        } catch (Exception e) {
            System.out.println("❌ Add customer with specific ID test failed: " + e.getMessage());
            testsFailed++;
        }
    }
    
    /**
     * Test that duplicate customer ID is rejected.
     */
    private static void testAddDuplicateCustomerId() {
        try {
            Bank bank = new Bank();
            bank.addCustomer("TEST001", "Test Customer");
            Customer duplicateCustomer = bank.addCustomer("TEST001", "Different Name");
            
            assertNull(duplicateCustomer, "Duplicate customer ID should be rejected");
            assertEqual(1, bank.getCustomerCount(), "Customer count should remain 1");
            
            System.out.println("✅ Add duplicate customer ID test passed");
            testsPassed++;
        } catch (Exception e) {
            System.out.println("❌ Add duplicate customer ID test failed: " + e.getMessage());
            testsFailed++;
        }
    }
    
    /**
     * Test that customer can be retrieved by ID.
     */
    private static void testGetCustomerById() {
        try {
            Bank bank = new Bank();
            Customer addedCustomer = bank.addCustomer("TEST001", "Test Customer");
            Customer retrievedCustomer = bank.getCustomer("TEST001");
            
            assertEqual(addedCustomer, retrievedCustomer, "Retrieved customer should be the same as added customer");
            assertEqual("Test Customer", retrievedCustomer.getName(), "Retrieved customer name should be correct");
            
            System.out.println("✅ Get customer by ID test passed");
            testsPassed++;
        } catch (Exception e) {
            System.out.println("❌ Get customer by ID test failed: " + e.getMessage());
            testsFailed++;
        }
    }
    
    /**
     * Test that non-existent customer ID returns null.
     */
    private static void testGetNonExistentCustomer() {
        try {
            Bank bank = new Bank();
            Customer customer = bank.getCustomer("NONEXISTENT");
            
            assertNull(customer, "Non-existent customer should return null");
            
            System.out.println("✅ Get non-existent customer test passed");
            testsPassed++;
        } catch (Exception e) {
            System.out.println("❌ Get non-existent customer test failed: " + e.getMessage());
            testsFailed++;
        }
    }
    
    /**
     * Test that customer existence can be checked.
     */
    private static void testHasCustomer() {
        try {
            Bank bank = new Bank();
            bank.addCustomer("TEST001", "Test Customer");
            
            assertTrue(bank.hasCustomer("TEST001"), "Existing customer should be found");
            assertFalse(bank.hasCustomer("NONEXISTENT"), "Non-existent customer should not be found");
            
            System.out.println("✅ Has customer test passed");
            testsPassed++;
        } catch (Exception e) {
            System.out.println("❌ Has customer test failed: " + e.getMessage());
            testsFailed++;
        }
    }
    
    /**
     * Test that customer can be removed.
     */
    private static void testRemoveCustomer() {
        try {
            Bank bank = new Bank();
            Customer addedCustomer = bank.addCustomer("TEST001", "Test Customer");
            Customer removedCustomer = bank.removeCustomer("TEST001");
            
            assertEqual(addedCustomer, removedCustomer, "Removed customer should be the same as added customer");
            assertEqual(0, bank.getCustomerCount(), "Customer count should be 0 after removal");
            assertFalse(bank.hasCustomer("TEST001"), "Customer should not exist after removal");
            
            System.out.println("✅ Remove customer test passed");
            testsPassed++;
        } catch (Exception e) {
            System.out.println("❌ Remove customer test failed: " + e.getMessage());
            testsFailed++;
        }
    }
    
    /**
     * Test that removing non-existent customer returns null.
     */
    private static void testRemoveNonExistentCustomer() {
        try {
            Bank bank = new Bank();
            Customer removedCustomer = bank.removeCustomer("NONEXISTENT");
            
            assertNull(removedCustomer, "Removing non-existent customer should return null");
            assertEqual(0, bank.getCustomerCount(), "Customer count should remain 0");
            
            System.out.println("✅ Remove non-existent customer test passed");
            testsPassed++;
        } catch (Exception e) {
            System.out.println("❌ Remove non-existent customer test failed: " + e.getMessage());
            testsFailed++;
        }
    }
    
    /**
     * Test that all customers can be retrieved.
     */
    private static void testGetAllCustomers() {
        try {
            Bank bank = new Bank();
            Customer customer1 = bank.addCustomer("CUST001", "Customer 1");
            Customer customer2 = bank.addCustomer("CUST002", "Customer 2");
            
            var allCustomers = bank.getAllCustomers();
            
            assertEqual(2, allCustomers.size(), "All customers collection should have 2 customers");
            assertTrue(allCustomers.contains(customer1), "All customers should contain customer1");
            assertTrue(allCustomers.contains(customer2), "All customers should contain customer2");
            
            System.out.println("✅ Get all customers test passed");
            testsPassed++;
        } catch (Exception e) {
            System.out.println("❌ Get all customers test failed: " + e.getMessage());
            testsFailed++;
        }
    }
    
    /**
     * Test that total account count is calculated correctly.
     */
    private static void testGetTotalAccountCount() {
        try {
            Bank bank = new Bank();
            Customer customer1 = bank.addCustomer("CUST001", "Customer 1");
            Customer customer2 = bank.addCustomer("CUST002", "Customer 2");
            
            customer1.addAccount(new Account("Customer 1", 1000.0));
            customer1.addAccount(new SavingsAccount("Customer 1", 500.0, 0.05));
            customer2.addAccount(new Account("Customer 2", 2000.0));
            
            assertEqual(3, bank.getTotalAccountCount(), "Total account count should be 3");
            
            System.out.println("✅ Get total account count test passed");
            testsPassed++;
        } catch (Exception e) {
            System.out.println("❌ Get total account count test failed: " + e.getMessage());
            testsFailed++;
        }
    }
    
    /**
     * Test that total bank balance is calculated correctly.
     */
    private static void testGetTotalBankBalance() {
        try {
            Bank bank = new Bank();
            Customer customer1 = bank.addCustomer("CUST001", "Customer 1");
            Customer customer2 = bank.addCustomer("CUST002", "Customer 2");
            
            customer1.addAccount(new Account("Customer 1", 1000.0));
            customer1.addAccount(new SavingsAccount("Customer 1", 500.0, 0.05));
            customer2.addAccount(new Account("Customer 2", 2000.0));
            
            double expectedTotal = 1000.0 + 500.0 + 2000.0;
            assertEqual(expectedTotal, bank.getTotalBankBalance(), "Total bank balance should be calculated correctly");
            
            System.out.println("✅ Get total bank balance test passed");
            testsPassed++;
        } catch (Exception e) {
            System.out.println("❌ Get total bank balance test failed: " + e.getMessage());
            testsFailed++;
        }
    }
    
    /**
     * Test bank toString method.
     */
    private static void testBankToString() {
        try {
            Bank bank = new Bank();
            bank.addCustomer("CUST001", "Customer 1");
            String bankString = bank.toString();
            
            assertTrue(bankString.contains("Bank"), "toString should contain Bank");
            assertTrue(bankString.contains("customers=1"), "toString should contain customer count");
            assertTrue(bankString.contains("totalAccounts=0"), "toString should contain account count");
            
            System.out.println("✅ Bank toString test passed");
            testsPassed++;
        } catch (Exception e) {
            System.out.println("❌ Bank toString test failed: " + e.getMessage());
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
    
    private static void assertNull(Object object, String message) {
        if (object != null) {
            throw new AssertionError(message + " - Expected: null, Actual: " + object);
        }
    }
    
    private static void assertNotNull(Object object, String message) {
        if (object == null) {
            throw new AssertionError(message + " - Expected: not null, Actual: null");
        }
    }
    
    private static void assertNotEqual(Object expected, Object actual, String message) {
        if (expected.equals(actual)) {
            throw new AssertionError(message + " - Expected: not equal, but they are equal");
        }
    }
}

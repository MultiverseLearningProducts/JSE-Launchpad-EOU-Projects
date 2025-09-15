package com.fincore.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Customer class.
 * This class contains unit tests to verify the customer management functionality.
 * 
 * @author FinCore Development Team
 * @version 6.0.0
 */
@DisplayName("Customer Tests")
class CustomerTest {
    
    private Customer testCustomer;
    private Account testAccount;
    private SavingsAccount testSavingsAccount;
    private static final String TEST_CUSTOMER_ID = "TEST001";
    private static final String TEST_CUSTOMER_NAME = "Test Customer";
    private static final double INITIAL_BALANCE = 1000.0;
    
    /**
     * Sets up test fixtures before each test method.
     */
    @BeforeEach
    void setUp() {
        testCustomer = new Customer(TEST_CUSTOMER_ID, TEST_CUSTOMER_NAME);
        testAccount = new Account(TEST_CUSTOMER_NAME, INITIAL_BALANCE);
        testSavingsAccount = new SavingsAccount(TEST_CUSTOMER_NAME, INITIAL_BALANCE, 0.05);
    }
    
    /**
     * Test that customer is initialized with correct values.
     */
    @Test
    @DisplayName("Customer initialization should set correct values")
    void testCustomerInitialization() {
        assertEquals(TEST_CUSTOMER_ID, testCustomer.getCustomerId());
        assertEquals(TEST_CUSTOMER_NAME, testCustomer.getName());
        assertEquals(0, testCustomer.getAccountCount());
        assertTrue(testCustomer.getAccounts().isEmpty());
    }
    
    /**
     * Test that account can be added to customer.
     */
    @Test
    @DisplayName("Account should be added to customer successfully")
    void testAddAccount() {
        boolean result = testCustomer.addAccount(testAccount);
        
        assertTrue(result, "Account should be added successfully");
        assertEquals(1, testCustomer.getAccountCount());
        assertTrue(testCustomer.getAccounts().contains(testAccount));
    }
    
    /**
     * Test that multiple accounts can be added to customer.
     */
    @Test
    @DisplayName("Multiple accounts should be added to customer")
    void testAddMultipleAccounts() {
        testCustomer.addAccount(testAccount);
        testCustomer.addAccount(testSavingsAccount);
        
        assertEquals(2, testCustomer.getAccountCount());
        assertTrue(testCustomer.getAccounts().contains(testAccount));
        assertTrue(testCustomer.getAccounts().contains(testSavingsAccount));
    }
    
    /**
     * Test that duplicate accounts are not added.
     */
    @Test
    @DisplayName("Duplicate accounts should not be added")
    void testAddDuplicateAccount() {
        testCustomer.addAccount(testAccount);
        boolean result = testCustomer.addAccount(testAccount);
        
        assertFalse(result, "Duplicate account should not be added");
        assertEquals(1, testCustomer.getAccountCount());
    }
    
    /**
     * Test that null account is not added.
     */
    @Test
    @DisplayName("Null account should not be added")
    void testAddNullAccount() {
        boolean result = testCustomer.addAccount(null);
        
        assertFalse(result, "Null account should not be added");
        assertEquals(0, testCustomer.getAccountCount());
    }
    
    /**
     * Test that account can be retrieved by index.
     */
    @Test
    @DisplayName("Account should be retrieved by index")
    void testGetAccountByIndex() {
        testCustomer.addAccount(testAccount);
        testCustomer.addAccount(testSavingsAccount);
        
        assertEquals(testAccount, testCustomer.getAccount(0));
        assertEquals(testSavingsAccount, testCustomer.getAccount(1));
    }
    
    /**
     * Test that invalid index returns null.
     */
    @Test
    @DisplayName("Invalid index should return null")
    void testGetAccountInvalidIndex() {
        testCustomer.addAccount(testAccount);
        
        assertNull(testCustomer.getAccount(-1));
        assertNull(testCustomer.getAccount(1));
    }
    
    /**
     * Test that account can be removed from customer.
     */
    @Test
    @DisplayName("Account should be removed from customer")
    void testRemoveAccount() {
        testCustomer.addAccount(testAccount);
        boolean result = testCustomer.removeAccount(testAccount);
        
        assertTrue(result, "Account should be removed successfully");
        assertEquals(0, testCustomer.getAccountCount());
        assertFalse(testCustomer.getAccounts().contains(testAccount));
    }
    
    /**
     * Test that removing non-existent account returns false.
     */
    @Test
    @DisplayName("Removing non-existent account should return false")
    void testRemoveNonExistentAccount() {
        boolean result = testCustomer.removeAccount(testAccount);
        
        assertFalse(result, "Removing non-existent account should return false");
        assertEquals(0, testCustomer.getAccountCount());
    }
    
    /**
     * Test that total balance is calculated correctly.
     */
    @Test
    @DisplayName("Total balance should be calculated correctly")
    void testGetTotalBalance() {
        testCustomer.addAccount(testAccount);
        testCustomer.addAccount(testSavingsAccount);
        
        double expectedTotal = INITIAL_BALANCE + INITIAL_BALANCE;
        assertEquals(expectedTotal, testCustomer.getTotalBalance(), 0.01);
    }
    
    /**
     * Test that total balance is zero for customer with no accounts.
     */
    @Test
    @DisplayName("Total balance should be zero for customer with no accounts")
    void testGetTotalBalanceNoAccounts() {
        assertEquals(0.0, testCustomer.getTotalBalance(), 0.01);
    }
    
    /**
     * Test customer equality.
     */
    @Test
    @DisplayName("Customer equality should be based on customer ID")
    void testCustomerEquality() {
        Customer sameCustomer = new Customer(TEST_CUSTOMER_ID, "Different Name");
        Customer differentCustomer = new Customer("DIFF001", TEST_CUSTOMER_NAME);
        
        assertEquals(testCustomer, sameCustomer);
        assertNotEquals(testCustomer, differentCustomer);
        assertNotEquals(testCustomer, null);
        assertNotEquals(testCustomer, "Not a customer");
    }
    
    /**
     * Test customer hash code.
     */
    @Test
    @DisplayName("Customer hash code should be consistent")
    void testCustomerHashCode() {
        Customer sameCustomer = new Customer(TEST_CUSTOMER_ID, "Different Name");
        
        assertEquals(testCustomer.hashCode(), sameCustomer.hashCode());
    }
    
    /**
     * Test customer toString method.
     */
    @Test
    @DisplayName("Customer toString should return proper representation")
    void testCustomerToString() {
        testCustomer.addAccount(testAccount);
        String customerString = testCustomer.toString();
        
        assertTrue(customerString.contains(TEST_CUSTOMER_ID));
        assertTrue(customerString.contains(TEST_CUSTOMER_NAME));
        assertTrue(customerString.contains("1")); // account count
    }
}

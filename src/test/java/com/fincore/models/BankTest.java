package com.fincore.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Bank class.
 * This class contains unit tests to verify the bank management functionality.
 * 
 * @author FinCore Development Team
 * @version 6.0.0
 */
@DisplayName("Bank Tests")
class BankTest {
    
    private Bank testBank;
    private static final String TEST_CUSTOMER_NAME = "Test Customer";
    private static final String TEST_CUSTOMER_ID = "TEST001";
    
    /**
     * Sets up test fixtures before each test method.
     */
    @BeforeEach
    void setUp() {
        testBank = new Bank();
    }
    
    /**
     * Test that bank is initialized empty.
     */
    @Test
    @DisplayName("Bank should be initialized empty")
    void testBankInitialization() {
        assertEquals(0, testBank.getCustomerCount());
        assertEquals(0, testBank.getTotalAccountCount());
        assertEquals(0.0, testBank.getTotalBankBalance(), 0.01);
        assertTrue(testBank.getAllCustomers().isEmpty());
    }
    
    /**
     * Test that customer can be added with auto-generated ID.
     */
    @Test
    @DisplayName("Customer should be added with auto-generated ID")
    void testAddCustomerWithAutoId() {
        Customer customer = testBank.addCustomer(TEST_CUSTOMER_NAME);
        
        assertNotNull(customer, "Customer should be created");
        assertEquals(TEST_CUSTOMER_NAME, customer.getName());
        assertTrue(customer.getCustomerId().startsWith("CUST"));
        assertEquals(1, testBank.getCustomerCount());
    }
    
    /**
     * Test that customer can be added with specific ID.
     */
    @Test
    @DisplayName("Customer should be added with specific ID")
    void testAddCustomerWithSpecificId() {
        Customer customer = testBank.addCustomer(TEST_CUSTOMER_ID, TEST_CUSTOMER_NAME);
        
        assertNotNull(customer, "Customer should be created");
        assertEquals(TEST_CUSTOMER_ID, customer.getCustomerId());
        assertEquals(TEST_CUSTOMER_NAME, customer.getName());
        assertEquals(1, testBank.getCustomerCount());
    }
    
    /**
     * Test that duplicate customer ID is rejected.
     */
    @Test
    @DisplayName("Duplicate customer ID should be rejected")
    void testAddDuplicateCustomerId() {
        testBank.addCustomer(TEST_CUSTOMER_ID, TEST_CUSTOMER_NAME);
        Customer duplicateCustomer = testBank.addCustomer(TEST_CUSTOMER_ID, "Different Name");
        
        assertNull(duplicateCustomer, "Duplicate customer ID should be rejected");
        assertEquals(1, testBank.getCustomerCount());
    }
    
    /**
     * Test that customer can be retrieved by ID.
     */
    @Test
    @DisplayName("Customer should be retrieved by ID")
    void testGetCustomerById() {
        Customer addedCustomer = testBank.addCustomer(TEST_CUSTOMER_ID, TEST_CUSTOMER_NAME);
        Customer retrievedCustomer = testBank.getCustomer(TEST_CUSTOMER_ID);
        
        assertEquals(addedCustomer, retrievedCustomer);
        assertEquals(TEST_CUSTOMER_NAME, retrievedCustomer.getName());
    }
    
    /**
     * Test that non-existent customer ID returns null.
     */
    @Test
    @DisplayName("Non-existent customer ID should return null")
    void testGetNonExistentCustomer() {
        Customer customer = testBank.getCustomer("NONEXISTENT");
        
        assertNull(customer, "Non-existent customer should return null");
    }
    
    /**
     * Test that customer existence can be checked.
     */
    @Test
    @DisplayName("Customer existence should be checked correctly")
    void testHasCustomer() {
        testBank.addCustomer(TEST_CUSTOMER_ID, TEST_CUSTOMER_NAME);
        
        assertTrue(testBank.hasCustomer(TEST_CUSTOMER_ID));
        assertFalse(testBank.hasCustomer("NONEXISTENT"));
    }
    
    /**
     * Test that customer can be removed.
     */
    @Test
    @DisplayName("Customer should be removed successfully")
    void testRemoveCustomer() {
        Customer addedCustomer = testBank.addCustomer(TEST_CUSTOMER_ID, TEST_CUSTOMER_NAME);
        Customer removedCustomer = testBank.removeCustomer(TEST_CUSTOMER_ID);
        
        assertEquals(addedCustomer, removedCustomer);
        assertEquals(0, testBank.getCustomerCount());
        assertFalse(testBank.hasCustomer(TEST_CUSTOMER_ID));
    }
    
    /**
     * Test that removing non-existent customer returns null.
     */
    @Test
    @DisplayName("Removing non-existent customer should return null")
    void testRemoveNonExistentCustomer() {
        Customer removedCustomer = testBank.removeCustomer("NONEXISTENT");
        
        assertNull(removedCustomer, "Removing non-existent customer should return null");
        assertEquals(0, testBank.getCustomerCount());
    }
    
    /**
     * Test that all customers can be retrieved.
     */
    @Test
    @DisplayName("All customers should be retrieved")
    void testGetAllCustomers() {
        Customer customer1 = testBank.addCustomer("CUST001", "Customer 1");
        Customer customer2 = testBank.addCustomer("CUST002", "Customer 2");
        
        var allCustomers = testBank.getAllCustomers();
        
        assertEquals(2, allCustomers.size());
        assertTrue(allCustomers.contains(customer1));
        assertTrue(allCustomers.contains(customer2));
    }
    
    /**
     * Test that total account count is calculated correctly.
     */
    @Test
    @DisplayName("Total account count should be calculated correctly")
    void testGetTotalAccountCount() {
        Customer customer1 = testBank.addCustomer("CUST001", "Customer 1");
        Customer customer2 = testBank.addCustomer("CUST002", "Customer 2");
        
        customer1.addAccount(new Account("Customer 1", 1000.0));
        customer1.addAccount(new SavingsAccount("Customer 1", 500.0, 0.05));
        customer2.addAccount(new Account("Customer 2", 2000.0));
        
        assertEquals(3, testBank.getTotalAccountCount());
    }
    
    /**
     * Test that total bank balance is calculated correctly.
     */
    @Test
    @DisplayName("Total bank balance should be calculated correctly")
    void testGetTotalBankBalance() {
        Customer customer1 = testBank.addCustomer("CUST001", "Customer 1");
        Customer customer2 = testBank.addCustomer("CUST002", "Customer 2");
        
        customer1.addAccount(new Account("Customer 1", 1000.0));
        customer1.addAccount(new SavingsAccount("Customer 1", 500.0, 0.05));
        customer2.addAccount(new Account("Customer 2", 2000.0));
        
        double expectedTotal = 1000.0 + 500.0 + 2000.0;
        assertEquals(expectedTotal, testBank.getTotalBankBalance(), 0.01);
    }
    
    /**
     * Test that bank summary displays correctly.
     */
    @Test
    @DisplayName("Bank summary should display correctly")
    void testDisplayBankSummary() {
        // This test verifies the method doesn't throw exceptions
        assertDoesNotThrow(() -> testBank.displayBankSummary());
        
        testBank.addCustomer("CUST001", "Customer 1");
        assertDoesNotThrow(() -> testBank.displayBankSummary());
    }
    
    /**
     * Test that multiple customers can be managed.
     */
    @Test
    @DisplayName("Multiple customers should be managed correctly")
    void testMultipleCustomers() {
        Customer customer1 = testBank.addCustomer("CUST001", "Customer 1");
        Customer customer2 = testBank.addCustomer("CUST002", "Customer 2");
        Customer customer3 = testBank.addCustomer("Customer 3");
        
        assertEquals(3, testBank.getCustomerCount());
        assertTrue(testBank.hasCustomer("CUST001"));
        assertTrue(testBank.hasCustomer("CUST002"));
        assertTrue(customer3.getCustomerId().startsWith("CUST"));
    }
    
    /**
     * Test bank toString method.
     */
    @Test
    @DisplayName("Bank toString should return proper representation")
    void testBankToString() {
        testBank.addCustomer("CUST001", "Customer 1");
        String bankString = testBank.toString();
        
        assertTrue(bankString.contains("Bank"));
        assertTrue(bankString.contains("customers=1"));
        assertTrue(bankString.contains("totalAccounts=0"));
    }
}

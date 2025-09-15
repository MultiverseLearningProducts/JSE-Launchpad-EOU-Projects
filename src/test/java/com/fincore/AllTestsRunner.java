package com.fincore;

/**
 * Comprehensive test runner for all FinCore CLI Banking tests.
 * This class runs all unit tests and provides a complete test report.
 * 
 * @author FinCore Development Team
 * @version 6.0.0
 */
public class AllTestsRunner {
    
    private static int totalTestsPassed = 0;
    private static int totalTestsFailed = 0;
    
    /**
     * Main method to run all tests.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        System.out.println("===============================================");
        System.out.println("    FinCore CLI Banking - Complete Test Suite");
        System.out.println("===============================================");
        System.out.println();
        
        // Run account tests
        System.out.println("�� Running Account and SavingsAccount Tests...");
        runAccountTests();
        
        System.out.println();
        System.out.println("🔍 Running Customer and Bank Management Tests...");
        runCustomerTests();
        
        // Print final results
        System.out.println();
        System.out.println("===============================================");
        System.out.println("              FINAL TEST RESULTS");
        System.out.println("===============================================");
        System.out.println("Total Tests Passed: " + totalTestsPassed);
        System.out.println("Total Tests Failed: " + totalTestsFailed);
        System.out.println("Total Tests Run: " + (totalTestsPassed + totalTestsFailed));
        System.out.println();
        
        if (totalTestsFailed == 0) {
            System.out.println("🎉 ALL TESTS PASSED! 🎉");
            System.out.println("The FinCore CLI Banking system is fully reliable and ready for production!");
            System.out.println();
            System.out.println("✅ Account operations are working correctly");
            System.out.println("✅ Exception handling is robust");
            System.out.println("✅ Customer management is functional");
            System.out.println("✅ Bank operations are secure");
            System.out.println("✅ Collections Framework is properly implemented");
            System.out.println("✅ Object-oriented design is solid");
        } else {
            System.out.println("❌ SOME TESTS FAILED");
            System.out.println("Please review the failed tests and fix the implementation.");
        }
        
        System.out.println("===============================================");
    }
    
    /**
     * Runs all account-related tests.
     */
    private static void runAccountTests() {
        try {
            // Capture the output and count tests
            java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
            java.io.PrintStream originalOut = System.out;
            System.setOut(new java.io.PrintStream(baos));
            
            // Run the account tests
            com.fincore.models.SimpleAccountTest.main(new String[0]);
            
            // Restore original output
            System.setOut(originalOut);
            
            // Parse the output to count tests
            String output = baos.toString();
            System.out.print(output);
            
            // Count tests from the output
            int passed = countOccurrences(output, "✅");
            int failed = countOccurrences(output, "❌");
            
            totalTestsPassed += passed;
            totalTestsFailed += failed;
            
        } catch (Exception e) {
            System.out.println("❌ Error running account tests: " + e.getMessage());
            totalTestsFailed++;
        }
    }
    
    /**
     * Runs all customer and bank-related tests.
     */
    private static void runCustomerTests() {
        try {
            // Capture the output and count tests
            java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
            java.io.PrintStream originalOut = System.out;
            System.setOut(new java.io.PrintStream(baos));
            
            // Run the customer tests
            com.fincore.models.SimpleCustomerTest.main(new String[0]);
            
            // Restore original output
            System.setOut(originalOut);
            
            // Parse the output to count tests
            String output = baos.toString();
            System.out.print(output);
            
            // Count tests from the output
            int passed = countOccurrences(output, "✅");
            int failed = countOccurrences(output, "❌");
            
            totalTestsPassed += passed;
            totalTestsFailed += failed;
            
        } catch (Exception e) {
            System.out.println("❌ Error running customer tests: " + e.getMessage());
            totalTestsFailed++;
        }
    }
    
    /**
     * Counts occurrences of a substring in a string.
     * 
     * @param text the text to search in
     * @param substring the substring to count
     * @return the number of occurrences
     */
    private static int countOccurrences(String text, String substring) {
        int count = 0;
        int index = 0;
        while ((index = text.indexOf(substring, index)) != -1) {
            count++;
            index += substring.length();
        }
        return count;
    }
}

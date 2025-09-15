package com.fincore;

import org.junit.platform.console.ConsoleLauncher;

/**
 * Test runner class to execute all JUnit tests.
 * This class provides a simple way to run all tests from the command line.
 * 
 * @author FinCore Development Team
 * @version 6.0.0
 */
public class TestRunner {
    
    /**
     * Main method to run all tests.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        System.out.println("=== FinCore CLI Banking - Test Suite ===");
        System.out.println("Running comprehensive unit tests...");
        System.out.println();
        
        // Run all tests in the com.fincore.models package
        String[] testArgs = {
            "--select-package", "com.fincore.models",
            "--details", "verbose"
        };
        
        ConsoleLauncher.main(testArgs);
    }
}

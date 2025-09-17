package com.solvians.showcase;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the {@link ISINGenerator} class.
 * This test suite verifies the correctness of ISIN generation and check digit calculation
 * according to the specified algorithm and format rules.
 *
 * @author Ramanjulu Muddusetty
 */
public class ISINGeneratorTest {

    /**
     * Verifies that the generated ISIN string:
     * - Is not null
     * - Has exactly 12 characters
     * - Matches the expected format: 2 uppercase letters, 9 digits, and 1 check digit
     */
    @Test
    public void testISINFormat() {
        String isin = ISINGenerator.generateISIN();
        assertNotNull(isin);
        assertEquals(12, isin.length(), "ISIN should be 12 characters long");
        assertTrue(isin.matches("[A-Z]{2}\\d{9}\\d"), "ISIN format should match pattern: 2 letters + 9 digits + 1 check digit");
    }

    /**
     * Validates the check digit calculation for a known ISIN base "DE123456789".
     * The expected check digit is 6, based on the documented algorithm.
     */
    @Test
    public void testCheckDigitCalculation() {
        String base = "DE123456789";
        int expectedCheckDigit = 6;
        int actualCheckDigit = ISINGenerator.calculateCheckDigit(base);
        assertEquals(expectedCheckDigit, actualCheckDigit, "Check digit should match expected value");
    }

    /**
     * Ensures that the calculated check digit for any ISIN base is a single digit (0–9).
     * This test uses "FR987654321" as a sample base.
     */
    @Test
    public void testSingleCheckDigit() {
        String base = "FR987654321";
        int checkDigit = ISINGenerator.calculateCheckDigit(base);
        assertTrue(checkDigit >= 0 && checkDigit <= 9, "Check digit should be a single digit between 0 and 9");
    }

    /**
     * Confirms that two consecutively generated ISINs are not identical.
     * This test helps ensure randomness and uniqueness in ISIN generation.
     */
    @Test
    public void testUniqueISINCheck() {
        String isin1 = ISINGenerator.generateISIN();
        String isin2 = ISINGenerator.generateISIN();
        assertNotEquals(isin1, isin2, "Two generated ISINs should not be identical");
    }

    /**
     * Verifies the character-to-number conversion logic used in ISIN check digit calculation.
     * This test checks specific mappings for 'A', 'E', and 'Z'.
     */
    @Test
    public void testNumberConversionCheck() {
        assertEquals(10, ISINGenerator.charToNumber('A'));
        assertEquals(14, ISINGenerator.charToNumber('E'));
        assertEquals(35, ISINGenerator.charToNumber('Z'));
    }
}

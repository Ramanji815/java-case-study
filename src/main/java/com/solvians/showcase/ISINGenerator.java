package com.solvians.showcase;

import java.util.Random;

/**
 * Utility class for generating valid ISIN (International Securities Identification Number) strings.
 * An ISIN consists of:
 * 2 uppercase letters (country code)
 * 9 numeric characters
 * 1 check digit calculated using the ISO 6166 algorithm
 * This class provides methods to generate random ISINs and calculate the check digit.
 *
 * @author Ramanjulu Muddusetty
 */
public class ISINGenerator {
    private static final Random random = new Random();

    /**
     * Generates a valid ISIN string.
     * The ISIN is composed of:
     * Two random uppercase letters
     * Nine random digits
     * One check digit
     *
     * @return a valid 12-character ISIN string
     */
    public static String generateISIN() {
        StringBuilder base = new StringBuilder();
        base.append("DE");

        for (int i = 0; i < 9; i++) {
            base.append(random.nextInt(10));
        }

        String checkDigit = String.valueOf(calculateCheckDigit(base.toString()));
        return base + checkDigit;
    }

    /**
     * Calculates the ISIN check digit
     * Steps:
     * Convert letters to numbers using {@link #charToNumber(char)}
     * Reverse the numeric string
     * Double every second digit from the right
     * Sum all digits (splitting two-digit results)
     * Subtract from the next highest multiple of 10
     *
     * @param base the 11-character ISIN base (2 letters + 9 digits)
     * @return the computed check digit (0–9)
     */
    public static int calculateCheckDigit(String base) {
        StringBuilder numeric = new StringBuilder();
        for (char c : base.toCharArray()) {
            if (Character.isLetter(c)) {
                numeric.append(charToNumber(c));
            } else {
                numeric.append(c);
            }
        }
        String reversed = numeric.reverse().toString();
        int sum = 0;
        for (int i = 0; i < reversed.length(); i++) {
            int digit = Character.getNumericValue(reversed.charAt(i));
            if (i % 2 == 0) {
                digit *= 2;
            }
            sum += digit / 10 + digit % 10;
        }
        return (10 - (sum % 10)) % 10;
    }

    /**
     * Converts an uppercase letter to its numeric equivalent.
     * A = 10, B = 11, ..., Z = 35
     *
     * @param c the uppercase letter to convert
     * @return the numeric value of the letter
     */
    public static int charToNumber(char c) {
        return c - 'A' + 10;
    }
}

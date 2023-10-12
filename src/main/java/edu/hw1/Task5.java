package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task5 {
    private final static Logger LOGGER = LogManager.getLogger();

    private final static int RADIX = 10;

    private Task5() {
    }

    public static boolean isPalindromeDescendant(int number) {
        LOGGER.trace("Checking if number {} or any of its descendants is a palindrome", number);
        if (number < 0) {
            throw new IllegalArgumentException("Number can't be negative");
        }
        int currentDescendant = number;
        while (currentDescendant >= RADIX) {
            if (isNumberPalindrome(currentDescendant)) {
                return true;
            }
            if (Task2.countDigitsInNumber(currentDescendant) % 2 != 0) {
                return false;
            }
            currentDescendant = formNextDescendant(currentDescendant);
        }
        return isNumberPalindrome(currentDescendant);
    }

    private static boolean isNumberPalindrome(int number) {
        LOGGER.trace("Checking if number {} is a palindrome", number);
        int numberRemainder = number;
        int reversedNumber = 0;
        while (numberRemainder > 0) {
            reversedNumber = reversedNumber * RADIX + numberRemainder % RADIX;
            numberRemainder /= RADIX;
        }
        return number == reversedNumber;
    }

    private static int formNextDescendant(int currentDescendant) {
        int descendantRemainder = currentDescendant;
        int reversedNextDescendant = 0;
        while (descendantRemainder > 0) {
            int lastTwoDigits = descendantRemainder % (RADIX * RADIX);
            int sumOfLastTwoDigits = lastTwoDigits / RADIX + lastTwoDigits % RADIX;
            if (sumOfLastTwoDigits >= RADIX) {
                reversedNextDescendant *= RADIX * RADIX;
                reversedNextDescendant += (sumOfLastTwoDigits % RADIX) * RADIX + (sumOfLastTwoDigits / RADIX);
            } else {
                reversedNextDescendant *= RADIX;
                reversedNextDescendant += sumOfLastTwoDigits;
            }
            descendantRemainder /= (RADIX * RADIX);
        }
        int nextDescendant = reverseNumber(reversedNextDescendant);
        LOGGER.trace("The next descendant for {} is {}", currentDescendant, nextDescendant);
        return nextDescendant;
    }

    private static int reverseNumber(int number) {
        int reversedNumber = 0;
        int numberRemainder = number;
        while (numberRemainder != 0) {
            int digit = numberRemainder % RADIX;
            reversedNumber = reversedNumber * RADIX + digit;
            numberRemainder /= RADIX;
        }
        return reversedNumber;
    }
}

package edu.hw1;

import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task6 {
    private final static Logger LOGGER = LogManager.getLogger();

    private final static int RADIX = 10;
    private final static int LOWER_BOUND = 1000;
    private final static int UPPER_BOUND = 9999;
    private final static int NUMBER_OF_DIGITS = 4;
    private final static int KAPREKARS_ROUTINE = 6174;

    private Task6() {
    }

    public static int countK(int number) {
        LOGGER.trace("Counting steps to Kaprekar's routine for number {}", number);
        if (number < LOWER_BOUND || number > UPPER_BOUND) {
            throw new IllegalArgumentException("Number must have 4 digits");
        }
        if (number == KAPREKARS_ROUTINE) {
            return 0;
        }

        int[] digitsOfNumber = getDigitsOfNumber(number);
        int numberWithIncreasingDigits = constructNumberWithIncreasingDigits(digitsOfNumber);
        int numberWithDecreasingDigits = constructNumberWithDecreasingDigits(digitsOfNumber);
        if (numberWithIncreasingDigits != numberWithDecreasingDigits) {
            return countK(numberWithDecreasingDigits - numberWithIncreasingDigits) + 1;
        }
        throw new IllegalArgumentException("Digits in number can't be the same");
    }

    private static int[] getDigitsOfNumber(int number) {
        int numberRemainder = number;
        int[] digitsOfNumber = new int[NUMBER_OF_DIGITS];
        for (int i = 0; i < NUMBER_OF_DIGITS; ++i) {
            digitsOfNumber[i] = numberRemainder % RADIX;
            numberRemainder /= RADIX;
        }
        return digitsOfNumber;
    }

    private static int constructNumberWithIncreasingDigits(int[] digitsOfNumber) {
        int[] sortedDigitsOfNumber = Arrays.stream(digitsOfNumber).sorted().toArray();
        int numberWithIncreasingDigits = 0;
        for (int i = 0; i < NUMBER_OF_DIGITS; ++i) {
            numberWithIncreasingDigits = numberWithIncreasingDigits * RADIX + sortedDigitsOfNumber[i];
        }
        return numberWithIncreasingDigits;
    }

    private static int constructNumberWithDecreasingDigits(int[] digitsOfNumber) {
        int[] sortedDigitsOfNumber = Arrays.stream(digitsOfNumber).sorted().toArray();
        int numberWithDecreasingDigits = 0;
        for (int i = NUMBER_OF_DIGITS - 1; i >= 0; --i) {
            numberWithDecreasingDigits = numberWithDecreasingDigits * RADIX + sortedDigitsOfNumber[i];
        }
        return numberWithDecreasingDigits;
    }
}

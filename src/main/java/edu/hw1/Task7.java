package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task7 {
    private final static Logger LOGGER = LogManager.getLogger();

    private final static String NEGATIVE_NUMBER_EXCEPTION_MESSAGE = "Number can't be negative";

    private final static int BITS_IN_INT = 32;

    private Task7() {
    }

    public static int rotateLeft(int number, int shift) {
        if (number < 0) {
            throw new IllegalArgumentException(NEGATIVE_NUMBER_EXCEPTION_MESSAGE);
        }
        LOGGER.trace("Rotating number {} to the left by {}", number, shift);
        if (shift < 0) {
            return rotateRight(number, -shift);
        }
        int numberOfBits = BITS_IN_INT - Integer.numberOfLeadingZeros(number);
        int shiftRemainder = shift % numberOfBits;
        return ((number << shiftRemainder) >> numberOfBits) | ((number << shiftRemainder) % (1 << numberOfBits));
    }

    public static int rotateRight(int number, int shift) {
        if (number < 0) {
            throw new IllegalArgumentException(NEGATIVE_NUMBER_EXCEPTION_MESSAGE);
        }
        LOGGER.trace("Rotating number {} to the right by {}", number, shift);
        if (shift < 0) {
            return rotateLeft(number, -shift);
        }
        int numberOfBits = BITS_IN_INT - Integer.numberOfLeadingZeros(number);
        int shiftRemainder = shift % numberOfBits;
        return ((number << numberOfBits) >> shiftRemainder) % (1 << numberOfBits) | (number >> shiftRemainder);
    }
}

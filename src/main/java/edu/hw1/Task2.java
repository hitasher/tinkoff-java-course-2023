package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static java.lang.Math.abs;

public final class Task2 {
    private final static Logger LOGGER = LogManager.getLogger();

    private final static int RADIX = 10;

    private Task2() {
    }

    public static int countDigitsInNumber(int number) {
        LOGGER.trace("Counting digits in {}", number);
        int absoluteValue = abs(number);
        int numberOfDigits = 1;
        while (absoluteValue > RADIX - 1) {
            absoluteValue /= RADIX;
            ++numberOfDigits;
        }
        return numberOfDigits;
    }
}

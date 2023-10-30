package edu.hw3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public final class Task4 {
    private final static Logger LOGGER = LogManager.getLogger();

    private final static int MINIMUM_ROMAN_NUMBER = 1;
    private final static int MAXIMUM_ROMAN_NUMBER = 3999;


    private final static int[] VALUES = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private final static String[] ROMAN_STRINGS =
        {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    private Task4() {
    }

    @NotNull
    public static String integerToRoman(int number) {
        LOGGER.trace("Converting {} to roman number", number);
        if (number < MINIMUM_ROMAN_NUMBER || number > MAXIMUM_ROMAN_NUMBER) {
            throw new IllegalArgumentException("Provided number must be in range [1, 3999]");
        }
        int numberRemainder = number;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < VALUES.length; ++i) {
            while (numberRemainder >= VALUES[i]) {
                numberRemainder -= VALUES[i];
                stringBuilder.append(ROMAN_STRINGS[i]);
            }
        }
        return stringBuilder.toString();
    }
}

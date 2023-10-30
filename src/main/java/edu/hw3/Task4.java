package edu.hw3;

import java.util.Map;
import java.util.TreeMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import static java.util.Map.entry;

public final class Task4 {
    private final static Logger LOGGER = LogManager.getLogger();

    private final static int MINIMUM_ROMAN_NUMBER = 1;
    private final static int MAXIMUM_ROMAN_NUMBER = 3999;

    private final static TreeMap<Integer, String> VALUE_TO_ROMAN_STRING = new TreeMap<>(Map.ofEntries(
        entry(1000, "M"),
        entry(900, "CM"),
        entry(500, "D"),
        entry(400, "CD"),
        entry(100, "C"),
        entry(90, "XC"),
        entry(50, "L"),
        entry(40, "XL"),
        entry(10, "X"),
        entry(9, "IX"),
        entry(5, "V"),
        entry(4, "IV"),
        entry(1, "I")
    ));

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
        for (Map.Entry<Integer, String> entry : VALUE_TO_ROMAN_STRING.descendingMap().entrySet()) {
            int value = entry.getKey();
            String romanString = entry.getValue();
            while (numberRemainder >= value) {
                numberRemainder -= value;
                stringBuilder.append(romanString);
            }
        }
        return stringBuilder.toString();
    }
}

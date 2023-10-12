package edu.hw1;

import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task4 {
    private final static Logger LOGGER = LogManager.getLogger();

    private Task4() {
    }

    public static String fixString(String string) {
        Objects.requireNonNull(string);
        LOGGER.trace("Fixing a string {}", string);
        char[] charArray = string.toCharArray();
        for (int i = 1; i < charArray.length; i += 2) {
            char tmpChar = charArray[i];
            charArray[i] = charArray[i - 1];
            charArray[i - 1] = tmpChar;
        }
        return new String(charArray);
    }
}

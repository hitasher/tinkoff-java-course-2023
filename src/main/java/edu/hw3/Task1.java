package edu.hw3;

import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task1 {
    private final static Logger LOGGER = LogManager.getLogger();

    private Task1() {
    }

    public static String encryptViaAtbash(String string) {
        Objects.requireNonNull(string);
        LOGGER.trace("Encrypting string {} via atbash", string);
        StringBuilder encryptedStringBuilder = new StringBuilder();
        for (int i = 0; i < string.length(); ++i) {
            encryptedStringBuilder.append(getMirrorLetter(string.charAt(i)));
        }
        return encryptedStringBuilder.toString();
    }

    private static char getMirrorLetter(char letter) {
        LOGGER.trace("Getting mirror letter for '{}'", letter);
        if (!isLatinLetter(letter)) {
            return letter;
        }
        if (Character.isLowerCase(letter)) {
            return (char) ('z' - letter + 'a');
        }
        return (char) ('Z' - letter + 'A');
    }

    private static boolean isLatinLetter(char letter) {
        LOGGER.trace("Checking if '{}' is a latin letter", letter);
        return (letter >= 'a' && letter <= 'z') || (letter >= 'A' && letter <= 'Z');
    }
}

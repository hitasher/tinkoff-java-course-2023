package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;

public final class Task5 {

    private Task5() {
    }

    private final static String REGEX = "^[АВЕКМНОРСТУХ]\\d{3}(?<!000)[АВЕКМНОРСТУХ]{2}\\d{2}(?<!00)\\d?$";
    private final static Pattern PATTERN = Pattern.compile(REGEX);

    public static boolean isRegistrationPlateValid(@NotNull String plate) {
        Matcher matcher = PATTERN.matcher(plate);
        return matcher.matches();
    }
}

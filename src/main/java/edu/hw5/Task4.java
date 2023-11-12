package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;

public final class Task4 {
    private Task4() {
    }

    private final static Pattern PATTERN = Pattern.compile("^.*[~!@#$%^&*|].*$");

    public static boolean checkPassword(@NotNull String password) {
        Matcher matcher = PATTERN.matcher(password);
        return matcher.matches();
    }
}

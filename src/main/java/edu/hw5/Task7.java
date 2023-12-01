package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;

public final class Task7 {

    private Task7() {
    }

    private final static String REGEX1 = "^[01]{2}0[01]*$";
    private final static String REGEX2 = "^([01])[01]*\\1|[01]$";
    private final static String REGEX3 = "^[01]{1,3}$";
    private final static Pattern PATTERN1 = Pattern.compile(REGEX1);
    private final static Pattern PATTERN2 = Pattern.compile(REGEX2);
    private final static Pattern PATTERN3 = Pattern.compile(REGEX3);

    public static boolean subtask1(@NotNull String string) {
        Matcher matcher = PATTERN1.matcher(string);
        return matcher.matches();
    }

    public static boolean subtask2(@NotNull String string) {
        Matcher matcher = PATTERN2.matcher(string);
        return matcher.matches();
    }

    public static boolean subtask3(@NotNull String string) {
        Matcher matcher = PATTERN3.matcher(string);
        return matcher.matches();
    }
}

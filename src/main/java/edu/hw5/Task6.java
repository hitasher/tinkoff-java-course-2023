package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;

public final class Task6 {
    private Task6() {
    }

    public static boolean isSubstring(@NotNull String s, @NotNull String t) {
        String regex = "^.*" + t + ".*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }
}

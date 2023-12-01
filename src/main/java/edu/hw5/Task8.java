package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;

public final class Task8 {

    private Task8() {
    }

    private final static String REGEX1 = "^[01]([01]{2})*$";
    private final static String REGEX2 = "^(0([01]{2})*)|(1[01]([01]{2})*)$";
    private final static String REGEX3 = "^(1*01*01*01*)+$";
    private final static String REGEX4 = "^(?!11$)(?!111$)[01]+$";
    private final static String REGEX5 = "^(1[01])*(1[01]?)$";
    private final static String REGEX6 = "^10{2,}|010+|00+1?0*$";
    private final static String REGEX7 = "^1|(0|10)+1?$";
    private final static Pattern PATTERN1 = Pattern.compile(REGEX1);
    private final static Pattern PATTERN2 = Pattern.compile(REGEX2);
    private final static Pattern PATTERN3 = Pattern.compile(REGEX3);
    private final static Pattern PATTERN4 = Pattern.compile(REGEX4);
    private final static Pattern PATTERN5 = Pattern.compile(REGEX5);
    private final static Pattern PATTERN6 = Pattern.compile(REGEX6);
    private final static Pattern PATTERN7 = Pattern.compile(REGEX7);


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

    public static boolean subtask4(@NotNull String string) {
        Matcher matcher = PATTERN4.matcher(string);
        return matcher.matches();
    }

    public static boolean subtask5(@NotNull String string) {
        Matcher matcher = PATTERN5.matcher(string);
        return matcher.matches();
    }

    public static boolean subtask6(@NotNull String string) {
        Matcher matcher = PATTERN6.matcher(string);
        return matcher.matches();
    }

    public static boolean subtask7(@NotNull String string) {
        Matcher matcher = PATTERN7.matcher(string);
        return matcher.matches();
    }

}

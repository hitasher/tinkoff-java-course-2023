package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;

public final class Task1 {

    private final static Pattern SESSION_PATTERN = Pattern.compile("^(.*) - (.*)$");
    private final static String DATE_TIME_PATTERN = "uuuu-MM-dd, HH:mm";
    private final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
        .ofPattern(DATE_TIME_PATTERN)
        .withResolverStyle(ResolverStyle.STRICT);

    private Task1() {
    }

    @NotNull
    public static Duration getAverageSessionDuration(@NotNull List<String> sessions) {
        Objects.requireNonNull(sessions);
        if (sessions.isEmpty()) {
            return Duration.ZERO;
        }
        Duration totalDuration = Duration.ZERO;
        for (String session : sessions) {
            Duration sessionDuration = getSessionDuration(session);
            totalDuration = totalDuration.plus(sessionDuration);
        }
        return totalDuration.dividedBy(sessions.size());
    }

    @NotNull
    private static Duration getSessionDuration(@NotNull String session) {
        Matcher sessionMatcher = SESSION_PATTERN.matcher(session);
        if (!sessionMatcher.matches()) {
            throw new IllegalArgumentException("String \"{}\" doesn't match the session pattern");
        }
        String sessionStart = sessionMatcher.group(1);
        String sessionEnd = sessionMatcher.group(2);
        LocalDateTime sessionStartDateTime = LocalDateTime.parse(sessionStart, DATE_TIME_FORMATTER);
        LocalDateTime sessionEndDateTime = LocalDateTime.parse(sessionEnd, DATE_TIME_FORMATTER);
        Duration sessionDuration = Duration.between(sessionStartDateTime, sessionEndDateTime);
        if (sessionDuration.isNegative()) {
            throw new IllegalArgumentException("Session duration can't be negative");
        }
        return sessionDuration;
    }
}

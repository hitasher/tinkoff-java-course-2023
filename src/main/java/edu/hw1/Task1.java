package edu.hw1;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task1 {
    private final static Logger LOGGER = LogManager.getLogger();

    private final static int SECONDS_PER_MINUTE = 60;
    private final static Pattern PATTERN = Pattern.compile("^(\\d{2,}):([0-5][0-9])$");

    private Task1() {
    }

    public static int getVideoLengthInSeconds(String videoLength) {
        Objects.requireNonNull(videoLength);
        LOGGER.trace("Processing a string {}", videoLength);
        Matcher matcher = PATTERN.matcher(videoLength);
        if (!matcher.matches()) {
            return -1;
        }
        int minutes = Integer.parseInt(matcher.group(1));
        int seconds = Integer.parseInt(matcher.group(2));
        return minutes * SECONDS_PER_MINUTE + seconds;
    }
}

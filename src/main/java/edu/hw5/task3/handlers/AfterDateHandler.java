package edu.hw5.task3.handlers;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AfterDateHandler implements DateHandler {

    private final static String REGEX = "^\\s*(\\d+)\\s+days?\\s+after\\s*$";
    private final static Pattern PATTERN = Pattern.compile(REGEX);

    @Override
    public Optional<LocalDate> handle(String date) {
        Matcher matcher = PATTERN.matcher(date);
        if (matcher.matches()) {
            int numberOfDays = Integer.parseInt(matcher.group(1));
            return Optional.of(LocalDate.now().plusDays(numberOfDays));
        }
        return Optional.empty();
    }
}

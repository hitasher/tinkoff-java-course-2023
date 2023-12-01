package edu.hw5.task3.handlers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class DateTimeFormatterDateHandler implements DateHandler {
    private final DateTimeFormatter formatter;

    public DateTimeFormatterDateHandler(String pattern) {
         formatter = DateTimeFormatter.ofPattern(pattern);
    }

    @Override
    public Optional<LocalDate> handle(String date) {
        try {
            return Optional.of(LocalDate.parse(date, formatter));
        } catch (DateTimeParseException exception) {
            return Optional.empty();
        }
    }
}

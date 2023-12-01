package edu.hw5.task3.handlers;

import java.time.LocalDate;
import java.util.Optional;

public class WordDateHandler implements DateHandler {

    @Override
    public Optional<LocalDate> handle(String date) {
        switch (date.strip()) {
            case "today" -> {
                return Optional.of(LocalDate.now());
            }
            case "yesterday" -> {
                return Optional.of(LocalDate.now().minusDays(1));
            }
            case "tomorrow" -> {
                return Optional.of(LocalDate.now().plusDays(1));
            }
            default -> {
                return Optional.empty();
            }
        }
    }
}

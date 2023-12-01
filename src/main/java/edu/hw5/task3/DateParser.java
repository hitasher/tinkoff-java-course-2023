package edu.hw5.task3;

import edu.hw5.task3.handlers.AfterDateHandler;
import edu.hw5.task3.handlers.BeforeDateHandler;
import edu.hw5.task3.handlers.DateHandler;
import edu.hw5.task3.handlers.DateTimeFormatterDateHandler;
import edu.hw5.task3.handlers.WordDateHandler;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public final class DateParser {

    private final static List<DateHandler> HANDLERS = List.of(
        new DateTimeFormatterDateHandler("yyyy-M-d"),
        new DateTimeFormatterDateHandler("d/M/yyyy"),
        new DateTimeFormatterDateHandler("d/M/yy"),
        new WordDateHandler(),
        new AfterDateHandler(),
        new BeforeDateHandler()
    );

    private DateParser() {
    }

    @NotNull
    public static Optional<LocalDate> parseDate(@NotNull String date) {
        return HANDLERS.stream()
            .map(handler -> handler.handle(date))
            .flatMap(Optional::stream)
            .findFirst();
    }
}

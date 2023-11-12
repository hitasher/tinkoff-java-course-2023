package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public final class Task2 {

    private Task2() {
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    public static List<LocalDate> getAllFridaysThe13th(int year) {
        return Arrays.stream(Month.values())
            .map(month -> LocalDate.of(year, month, 13))
            .filter(date -> date.getDayOfWeek() == DayOfWeek.FRIDAY)
            .toList();
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    @NotNull
    public static LocalDate getNextFridayThe13th(@NotNull LocalDate date) {
        LocalDate nextFriday = date.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        while (nextFriday.getDayOfMonth() != 13) {
            nextFriday = nextFriday.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        }
        return nextFriday;
    }
}

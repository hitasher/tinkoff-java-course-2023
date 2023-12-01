package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestTask2 {
    private static Stream<Arguments> getAllFridaysThe13th_ShouldReturnExpectedDates() {
        return Stream.of(
            Arguments.of(
                1925,
                List.of(
                    LocalDate.of(1925, 2, 13),
                    LocalDate.of(1925, 3, 13),
                    LocalDate.of(1925, 11, 13)
                )
            ),
            Arguments.of(
                2024,
                List.of(
                    LocalDate.of(2024, 9, 13),
                    LocalDate.of(2024, 12, 13)
                )
            ),
            Arguments.of(
                -1,
                List.of(
                    LocalDate.of(-1, 8, 13)
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource
    void getAllFridaysThe13th_ShouldReturnExpectedDates(int year, List<LocalDate> expectedDates) {
        List<LocalDate> actualDates = Task2.getAllFridaysThe13th(year);
        assertThat(actualDates).isEqualTo(expectedDates);
    }

    private static Stream<Arguments> getNextFridayThe13th_ShouldReturnExpectedDate() {
        return Stream.of(
            Arguments.of(
                LocalDate.of(2024, 9, 12),
                LocalDate.of(2024, 9, 13)
            ),
            Arguments.of(
                LocalDate.of(2024, 9, 13),
                LocalDate.of(2024, 12, 13)
            ),
            Arguments.of(
                LocalDate.of(2024, 12, 13),
                LocalDate.of(2025, 6, 13)
            ),
            Arguments.of(
                LocalDate.of(-2, 11, 14),
                LocalDate.of(-1, 8, 13)
            )
        );
    }

    @ParameterizedTest
    @MethodSource
    void getNextFridayThe13th_ShouldReturnExpectedDate(LocalDate date, LocalDate expectedDate) {
        LocalDate actualDate = Task2.getNextFridayThe13th(date);
        assertThat(actualDate).isEqualTo(expectedDate);
    }
}

package edu.hw5;

import edu.hw5.task3.DateParser;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestTask3 {
    private static Stream<Arguments> parseDate_ShouldReturnExpectedDate() {
        return Stream.of(
            Arguments.of(
                "2020-10-10",
                LocalDate.of(2020, 10, 10)
            ),
            Arguments.of(
                "2020-10-2",
                LocalDate.of(2020, 10, 2)
            ),
            Arguments.of(
                "2020-10-02",
                LocalDate.of(2020, 10, 2)
            ),
            Arguments.of(
                "2020-01-02",
                LocalDate.of(2020, 1, 2)
            ),
            Arguments.of(
                "2020-1-02",
                LocalDate.of(2020, 1, 2)
            ),
            Arguments.of(
                "1/3/1976",
                LocalDate.of(1976, 3, 1)
            ),
            Arguments.of(
                "01/03/1976",
                LocalDate.of(1976, 3, 1)
            ),
            Arguments.of(
                "11/11/1976",
                LocalDate.of(1976, 11, 11)
            ),
            Arguments.of(
                "1/3/20",
                LocalDate.of(2020, 3, 1)
            ),
            Arguments.of(
                "01/03/20",
                LocalDate.of(2020, 3, 1)
            ),
            Arguments.of(
                "11/11/20",
                LocalDate.of(2020, 11, 11)
            ),
            Arguments.of(
                "tomorrow",
                LocalDate.now().plusDays(1)
            ),
            Arguments.of(
                "today",
                LocalDate.now()
            ),
            Arguments.of(
                "yesterday",
                LocalDate.now().minusDays(1)
            ),
            Arguments.of(
                "0 day ago",
                LocalDate.now()
            ),
            Arguments.of(
                "0 days ago",
                LocalDate.now()
            ),
            Arguments.of(
                "1 day ago",
                LocalDate.now().minusDays(1)
            ),
            Arguments.of(
                "1 days ago",
                LocalDate.now().minusDays(1)
            ),
            Arguments.of(
                "2234 day ago",
                LocalDate.now().minusDays(2234)
            ),
            Arguments.of(
                "2234 days ago",
                LocalDate.now().minusDays(2234)
            ),
            Arguments.of(
                "0 day after",
                LocalDate.now()
            ),
            Arguments.of(
                "0 days after",
                LocalDate.now()
            ),
            Arguments.of(
                "1 day after",
                LocalDate.now().plusDays(1)
            ),
            Arguments.of(
                "1 days after",
                LocalDate.now().plusDays(1)
            ),
            Arguments.of(
                "2234 day after",
                LocalDate.now().plusDays(2234)
            ),
            Arguments.of(
                "2234 days after",
                LocalDate.now().plusDays(2234)
            )
        );
    }


    @ParameterizedTest
    @MethodSource
    void parseDate_ShouldReturnExpectedDate(String date, LocalDate expectedDate) {
        Optional<LocalDate> actualDate = DateParser.parseDate(date);
        assertThat(actualDate).hasValue(expectedDate);
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = {
        "2020-10-101",
        "2020-101-10",
        "2020-10/10",
        "2020/10-10",
        "2020/10/10",
        "100/3/1976",
        "1/300/1976",
        "1-3/1976",
        "1/3-1976",
        "1-3-1976",
        "abc",
        "the day before yesterday",
        "the day after tomorrow",
        "x days after",
        "x days ago",
        "1 days before",
        "x days before"
    })
    void parseDate_ShouldReturnEmptyOptional(String date) {
        Optional<LocalDate> actualDate = DateParser.parseDate(date);
        assertThat(actualDate).isEmpty();
    }
}

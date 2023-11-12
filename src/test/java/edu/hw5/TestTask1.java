package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.time.Duration;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class TestTask1 {
    private static Stream<Arguments> getAverageSessionDuration_ShouldReturnExpectedDuration() {
        return Stream.of(
            Arguments.of(
                List.of(),
                Duration.ofMinutes(0)
            ),
            Arguments.of(
                List.of("2023-11-11, 23:50 - 2023-11-11, 23:55"),
                Duration.ofMinutes(5)
            ),
            Arguments.of(
                List.of("2023-11-11, 23:50 - 2023-11-12, 01:20"),
                Duration.ofMinutes(90)
            ),
            Arguments.of(
                List.of("2023-11-11, 00:00 - 2024-11-11, 00:00"),
                Duration.ofDays(366)
            ),
            Arguments.of(
                List.of("2024-02-29, 07:35 - 2024-02-29, 07:55"),
                Duration.ofMinutes(20)
            ),
            Arguments.of(
                List.of(
                    "2022-03-12, 20:20 - 2022-03-12, 23:50",
                    "2022-04-01, 21:30 - 2022-04-02, 01:20"
                ),
                Duration.ofMinutes(220)
            ),
            Arguments.of(
                List.of(
                    "2023-11-11, 01:18 - 2023-11-11, 03:13",
                    "2023-11-11, 07:49 - 2023-11-11, 11:23",
                    "2023-11-11, 21:32 - 2023-11-11, 23:43"
                ),
                Duration.ofSeconds(9200)
            )
        );
    }

    @ParameterizedTest
    @MethodSource
    void getAverageSessionDuration_ShouldReturnExpectedDuration(List<String> sessions, Duration expectedDuration) {
        Duration actualDuration = Task1.getAverageSessionDuration(sessions);
        assertThat(actualDuration).isEqualTo(expectedDuration);
    }

    private static Stream<Arguments> getAverageSessionDuration_ShouldThrowDateTimeParseException() {
        return Stream.of(
            Arguments.of(List.of("2022-03-12, 20:20 - 2022-03-12, 23:60")),
            Arguments.of(List.of("2022-03-12, 20:20 - 2022-03-12, 24:00")),
            Arguments.of(List.of("2022-03-12, 20:20 - 2022-03-32, 23:59")),
            Arguments.of(List.of("2022-03-12, 20:20 - 2022-02-29, 23:59")),
            Arguments.of(List.of("2022-03-12, 20:20 - 2022-03-12, 23:0")),
            Arguments.of(List.of("2022-03-12, 20:20 - 2022-03-12, 0:59")),
            Arguments.of(List.of("2022-03-12, 20:20 - 2022-03-5, 0:59")),
            Arguments.of(List.of("2022-03-12, 20:20 - 2022-3-12, 0:59")),
            Arguments.of(List.of("2022-03-12, 20:20 - 999-03-12, 23:59")),
            Arguments.of(List.of("2022-03-12, 20:20 - 999-03-12 23:59")),
            Arguments.of(List.of("2022-03-12, 20:20 - 0")),
            Arguments.of(List.of("2022-03-12, 20:20 - "))
        );
    }

    @ParameterizedTest
    @MethodSource
    void getAverageSessionDuration_ShouldThrowDateTimeParseException(List<String> sessions) {
        assertThatThrownBy(
            () -> Task1.getAverageSessionDuration(sessions)
        ).isInstanceOf(DateTimeParseException.class);
    }

    private static Stream<Arguments> getAverageSessionDuration_ShouldThrowIllegalArgumentException() {
        return Stream.of(
            Arguments.of(List.of("2023-11-11, 01:18")),
            Arguments.of(List.of("2023-11-11, 01:18 + 2023-11-11, 03:13")),
            Arguments.of(List.of("2023-11-11 01:18  2023-11-11, 03:13")),
            Arguments.of(List.of("2023-11-11, 04:20 - 2023-11-11, 04:19"))
        );
    }

    @ParameterizedTest
    @MethodSource
    void getAverageSessionDuration_ShouldThrowIllegalArgumentException(List<String> sessions) {
        assertThatThrownBy(
            () -> Task1.getAverageSessionDuration(sessions)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}

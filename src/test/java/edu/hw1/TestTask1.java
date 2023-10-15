package edu.hw1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestTask1 {

    @Test
    void getVideoLengthInSeconds_ShouldReturn60() {
        // given
        String videoLength = "01:00";
        // when
        int videoLengthInSeconds = Task1.getVideoLengthInSeconds(videoLength);
        // then
        assertThat(videoLengthInSeconds).isEqualTo(60);
    }

    @Test
    void getVideoLengthInSeconds_ShouldReturn836() {
        // given
        String videoLength = "13:56";
        // when
        int videoLengthInSeconds = Task1.getVideoLengthInSeconds(videoLength);
        // then
        assertThat(videoLengthInSeconds).isEqualTo(836);
    }

    @Test
    void getVideoLengthInSeconds_ShouldReturn59999() {
        // given
        String videoLength = "999:59";
        // when
        int videoLengthInSeconds = Task1.getVideoLengthInSeconds(videoLength);
        // then
        assertThat(videoLengthInSeconds).isEqualTo(59999);
    }

    @ParameterizedTest
    @ValueSource(strings = {"00:00", "000:00", "0000:00"})
    void getVideoLengthInSeconds_ShouldReturn0(String videoLength) {
        // when
        int videoLengthInSeconds = Task1.getVideoLengthInSeconds(videoLength);
        // then
        assertThat(videoLengthInSeconds).isEqualTo(0);
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = {"10:60", "01:0", "1:00", "1:00", "59"})
    void getVideoLengthInSeconds_ShouldReturnMinusOne(String string) {
        // when
        int videoLengthInSeconds = Task1.getVideoLengthInSeconds(string);
        // then
        assertThat(videoLengthInSeconds).isEqualTo(-1);
    }

    @ParameterizedTest
    @NullSource
    void getVideoLengthInSeconds_ShouldThrowNullPointerException(String string) {
        assertThatThrownBy(
            () -> Task1.getVideoLengthInSeconds(string)
        ).isInstanceOf(NullPointerException.class);
    }
}

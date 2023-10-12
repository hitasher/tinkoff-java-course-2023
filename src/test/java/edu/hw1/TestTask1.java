package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestTask1 {
    @Test
    @DisplayName("Получение длины видео в секундах из строки, соответствующей шаблону")
    void testGetVideoLengthInSeconds() {
        // given
        String formattedVideoLengthString = "01:00";
        // when
        int videoLengthInSeconds = Task1.getVideoLengthInSeconds(formattedVideoLengthString);
        // then
        assertThat(videoLengthInSeconds).isEqualTo(60);

        // given
        formattedVideoLengthString = "13:56";
        // when
        videoLengthInSeconds = Task1.getVideoLengthInSeconds(formattedVideoLengthString);
        // then
        assertThat(videoLengthInSeconds).isEqualTo(836);

        // given
        formattedVideoLengthString = "999:59";
        // when
        videoLengthInSeconds = Task1.getVideoLengthInSeconds(formattedVideoLengthString);
        // then
        assertThat(videoLengthInSeconds).isEqualTo(59999);

        // given
        formattedVideoLengthString = "00:00";
        // when
        videoLengthInSeconds = Task1.getVideoLengthInSeconds(formattedVideoLengthString);
        // then
        assertThat(videoLengthInSeconds).isEqualTo(0);
    }

    @Test
    @DisplayName("Получение длины видео в секундах из строки, несоответствующей шаблону")
    void testGetVideoLengthInSecondsWithInvalidArguments() {
        // given
        String formattedVideoLengthString = "10:60";
        // when
        int videoLengthInSeconds = Task1.getVideoLengthInSeconds(formattedVideoLengthString);
        // then
        assertThat(videoLengthInSeconds).isEqualTo(-1);

        // given
        formattedVideoLengthString = "01:0";
        // when
        videoLengthInSeconds = Task1.getVideoLengthInSeconds(formattedVideoLengthString);
        // then
        assertThat(videoLengthInSeconds).isEqualTo(-1);

        // given
        formattedVideoLengthString = "1:00";
        // when
        videoLengthInSeconds = Task1.getVideoLengthInSeconds(formattedVideoLengthString);
        // then
        assertThat(videoLengthInSeconds).isEqualTo(-1);

        // given
        formattedVideoLengthString = "59";
        // when
        videoLengthInSeconds = Task1.getVideoLengthInSeconds(formattedVideoLengthString);
        // then
        assertThat(videoLengthInSeconds).isEqualTo(-1);

        // given
        formattedVideoLengthString = "";
        // when
        videoLengthInSeconds = Task1.getVideoLengthInSeconds(formattedVideoLengthString);
        // then
        assertThat(videoLengthInSeconds).isEqualTo(-1);

        assertThatThrownBy(
            () -> Task1.getVideoLengthInSeconds(null)
        ).isInstanceOf(NullPointerException.class);
    }
}

package edu.hw1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TestTask4 {

    @Test
    void fixString_ShouldReturn123456() {
        // given
        String string = "214365";
        // when
        String fixedString = Task4.fixString(string);
        // then
        assertThat(fixedString).isEqualTo("123456");
    }

    @Test
    void fixString_ShouldReturnThisIsAMixedUpString() {
        // given
        String string = "hTsii  s aimex dpus rtni.g";
        // when
        String fixedString = Task4.fixString(string);
        // then
        assertThat(fixedString).isEqualTo("This is a mixed up string.");
    }

    @Test
    void fixString_ShouldReturn98765() {
        // given
        String string = "89675";
        // when
        String fixedString = Task4.fixString(string);
        // then
        assertThat(fixedString).isEqualTo("98765");
    }

    @Test
    void fixString_ShouldReturnEmptyString() {
        // given
        String string = "";
        // when
        String fixedString = Task4.fixString(string);
        // then
        assertThat(fixedString).isEqualTo("");
    }

    @Test
    void fixString_ShouldReturnA() {
        // given
        String string = "a";
        // when
        String fixedString = Task4.fixString(string);
        // then
        assertThat(fixedString).isEqualTo("a");
    }

    @ParameterizedTest
    @NullSource
    void fixString_ShouldThrowNullPointerException(String string) {
        assertThatThrownBy(
            () -> Task4.fixString(string)
        ).isInstanceOf(NullPointerException.class);
    }
}



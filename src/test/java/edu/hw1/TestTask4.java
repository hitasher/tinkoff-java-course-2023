package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TestTask4 {
    @Test
    @DisplayName("Исправление строки")
    void testFixString() {
        // given
        String string = "123456";
        // when
        String fixedString = Task4.fixString(string);
        // then
        assertThat(fixedString).isEqualTo("214365");

        // given
        string = "hTsii  s aimex dpus rtni.g";
        // when
        fixedString = Task4.fixString(string);
        // then
        assertThat(fixedString).isEqualTo("This is a mixed up string.");

        // given
        string = "badce";
        // when
        fixedString = Task4.fixString(string);
        // then
        assertThat(fixedString).isEqualTo("abcde");

        // given
        string = "";
        // when
        fixedString = Task4.fixString(string);
        // then
        assertThat(fixedString).isEqualTo("");

        // given
        string = "a";
        // when
        fixedString = Task4.fixString(string);
        // then
        assertThat(fixedString).isEqualTo("a");
    }

    @Test
    @DisplayName("Вызов метода при некорректных аргументах")
    void testFixStringWithInvalidArguments() {
        assertThatThrownBy(
            () -> Task4.fixString(null)
        ).isInstanceOf(NullPointerException.class);
    }
}



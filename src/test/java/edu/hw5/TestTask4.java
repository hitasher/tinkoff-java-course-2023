package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestTask4 {
    @ParameterizedTest
    @ValueSource(strings ={
        "~", "!", "@", "#", "$", "%", "^", "&", "*", "|",
        "&qwerty~1234*",
        "strong^Password"
    })
    void checkPassword_ShouldReturnTrue(String password) {
        assertThat(Task4.checkPassword(password)).isTrue();
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = {
        "1234567890",
        "qwertyQWERTY",
        "(010010)",
        "?[]/;:{}=+-`"
    })
    void checkPassword_ShouldReturnFalse(String password) {
        assertThat(Task4.checkPassword(password)).isFalse();
    }
}

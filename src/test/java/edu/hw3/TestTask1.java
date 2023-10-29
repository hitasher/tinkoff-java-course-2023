package edu.hw3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class TestTask1 {
    private static Stream<Arguments> atbash_ShouldReturnEncodedString() {
        return Stream.of(
            Arguments.of("abc|zyx", "zyx|abc"),
            Arguments.of("Hello world!", "Svool dliow!"),
            Arguments.of(
                "Any fool can write code that a computer can understand." +
                    " Good programmers write code that humans can understand. ― Martin Fowler",
                "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw." +
                    " Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi")
        );
    }

    @ParameterizedTest
    @MethodSource
    void atbash_ShouldReturnEncodedString(String originalString, String expectedEncodedString) {
        // when
        String actualEncodedString = Task1.atbash(originalString);
        // then
        assertThat(actualEncodedString).isEqualTo(expectedEncodedString);
    }

    @ParameterizedTest
    @EmptySource
    void atbash_ShouldReturnEmptyEncodedString(String string) {
        // when
        String encodedString = Task1.atbash(string);
        // then
        assertThat(encodedString.isEmpty()).isTrue();
    }

    @ParameterizedTest
    @NullSource
    void atbash_ShouldThrowNullPointerException(String string) {
        assertThatThrownBy(
            () -> Task1.atbash(string)
        ).isInstanceOf(NullPointerException.class);
    }
}

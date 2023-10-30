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
    private static Stream<Arguments> encryptViaAtbash_ShouldReturnEncodedString() {
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
    void encryptViaAtbash_ShouldReturnEncodedString(String originalString, String expectedEncryptedString) {
        // when
        String actualEncryptedString = Task1.encryptViaAtbash(originalString);
        // then
        assertThat(actualEncryptedString).isEqualTo(expectedEncryptedString);
    }

    @ParameterizedTest
    @EmptySource
    void encryptViaAtbash_ShouldReturnEmptyEncodedString(String string) {
        // when
        String encryptedString = Task1.encryptViaAtbash(string);
        // then
        assertThat(encryptedString.isEmpty()).isTrue();
    }

    @ParameterizedTest
    @NullSource
    void encryptViaAtbash_ShouldThrowNullPointerException(String string) {
        assertThatThrownBy(
            () -> Task1.encryptViaAtbash(string)
        ).isInstanceOf(NullPointerException.class);
    }
}

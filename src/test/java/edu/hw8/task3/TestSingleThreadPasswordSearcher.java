package edu.hw8.task3;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TestSingleThreadPasswordSearcher {


    private static Stream<Arguments> search_ShouldReturnExpectedUserToPasswordMap() {
        return Stream.of(
            Arguments.of(
                new HashMap<String, String>() {{
                    put("81dc9bdb52d04dc20036dbd8313ed055", "a.s.ivanov");
                }},
                new HashMap<String, String>() {{
                    put("a.s.ivanov", "1234");
                }}
            )
        );
    }

    @ParameterizedTest
    @MethodSource
    public void search_ShouldReturnExpectedUserToPasswordMap(
        Map<String, String> passwordHashToUser,
        Map<String, String> expectedUserToPassword
    ) {
        PasswordSearcher passwordSearcher = new SingleThreadPasswordSearcher(passwordHashToUser);
        Map<String, String> actualUserToPassword = passwordSearcher.search();
        assertThat(actualUserToPassword).isEqualTo(expectedUserToPassword);
    }
}

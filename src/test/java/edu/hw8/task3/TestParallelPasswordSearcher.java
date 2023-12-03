package edu.hw8.task3;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TestParallelPasswordSearcher {


    private static Stream<Arguments> search_ShouldReturnExpectedUserToPasswordMap() {
        return Stream.of(
            Arguments.of(
                Map.of(
                    "e10adc3949ba59abbe56e057f20f883e", "a.v.petrov",
                    "e2fc714c4727ee9395f324cd2e7f331f", "v.v.belov",
                    "81dc9bdb52d04dc20036dbd8313ed055", "a.s.ivanov",
                    "827ccb0eea8a706c4c34a16891f84e7b", "k.p.maslov"
                ),
                Map.of(
                    "a.v.petrov", "123456",
                    "v.v.belov", "abcd",
                    "a.s.ivanov", "1234",
                    "k.p.maslov", "12345"
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource
    public void search_ShouldReturnExpectedUserToPasswordMap(
        Map<String, String> passwordHashToUser,
        Map<String, String> expectedUserToPassword
    ) {
        PasswordSearcher passwordSearcher = new ParallelPasswordSearcher(passwordHashToUser);
        Map<String, String> actualUserToPassword = passwordSearcher.search();
        assertThat(actualUserToPassword).isEqualTo(expectedUserToPassword);
    }
}

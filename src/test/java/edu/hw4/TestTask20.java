package edu.hw4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class TestTask20 {

    private static Stream<Arguments> getNamesAndErrorsOfAnimalsWithErrors_ShouldReturnMap() {
        return Stream.of(
            Arguments.of(List.of(), Map.of()),
            Arguments.of(
                List.of(Animal.builder().name("Aarne").age(5).height(10).weight(20).build()),
                Map.of()
            ),
            Arguments.of(
                List.of(Animal.builder().name("Aarne").age(0).height(10).weight(20).build()),
                Map.of("Aarne", "age")
            ),
            Arguments.of(
                List.of(Animal.builder().name("Aarne").age(-1).height(10).weight(20).build()),
                Map.of("Aarne", "age")
            ),
            Arguments.of(
                List.of(
                    Animal.builder().name("Aarne").age(10).height(-99).weight(5).build(),
                    Animal.builder().name("Bob").age(-1).height(0).weight(-1).build(),
                    Animal.builder().name("Dave").age(10).height(5).weight(7).build()
                ),
                Map.of(
                    "Aarne", "height",
                    "Bob", "height, weight, age"
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource
    void getNamesAndErrorsOfAnimalsWithErrors_ShouldReturnMap(
        List<Animal> animalList, Map<String, String> expectedMap
    ) {
        Map<String, String> actualMap = Task20.getNamesAndErrorsOfAnimalsWithErrors(animalList);
        assertThat(actualMap).isEqualTo(expectedMap);
    }

    @ParameterizedTest
    @NullSource
    void getNamesAndErrorsOfAnimalsWithErrors_ShouldThrowNullPointerException(List<Animal> animalList) {
        assertThatThrownBy(
            () -> Task20.getNamesAndErrorsOfAnimalsWithErrors(animalList)
        ).isInstanceOf(NullPointerException.class);
    }
}

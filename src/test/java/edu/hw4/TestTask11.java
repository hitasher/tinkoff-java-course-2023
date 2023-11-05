package edu.hw4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class TestTask11 {

    private static Stream<Arguments> getBitingAndTallAnimals_ShouldReturnBitingAndTallAnimals() {
        return Stream.of(
            Arguments.of(List.of(), List.of()),
            Arguments.of(
                List.of(Animal.builder().bites(true).height(99).build()),
                List.of()
            ),
            Arguments.of(
                List.of(Animal.builder().bites(true).height(101).build()),
                List.of(Animal.builder().bites(true).height(101).build())
            ),
            Arguments.of(
                List.of(
                    Animal.builder().bites(false).height(120).build(),
                    Animal.builder().bites(true).height(130).build(),
                    Animal.builder().bites(true).height(90).build(),
                    Animal.builder().bites(false).height(80).build(),
                    Animal.builder().bites(true).height(110).build(),
                    Animal.builder().bites(false).height(125).build()
                ),
                List.of(
                    Animal.builder().bites(true).height(130).build(),
                    Animal.builder().bites(true).height(110).build()
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource
    void getBitingAndTallAnimals_ShouldReturnBitingAndTallAnimals(
        List<Animal> animalList, List<Animal> expectedAnimals
    ) {
        List<Animal> actualAnimals = Task11.getBitingAndTallAnimals(animalList);
        assertThat(actualAnimals).isEqualTo(expectedAnimals);
    }

    @ParameterizedTest
    @NullSource
    void getBitingAndTallAnimals_ShouldThrowNulLPointerException(List<Animal> animalList) {
        assertThatThrownBy(
            () -> Task11.getBitingAndTallAnimals(animalList)
        ).isInstanceOf(NullPointerException.class);
    }
}

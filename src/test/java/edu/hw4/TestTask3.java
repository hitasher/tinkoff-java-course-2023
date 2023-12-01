package edu.hw4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import static edu.hw4.Animal.Type.BIRD;
import static edu.hw4.Animal.Type.CAT;
import static edu.hw4.Animal.Type.DOG;
import static edu.hw4.Animal.Type.FISH;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class TestTask3 {

    private static Stream<Arguments> countAnimalTypes_ShouldReturnCountedAnimalTypes() {
        return Stream.of(
            Arguments.of(List.of(), Map.of()),
            Arguments.of(
                List.of(Animal.builder().type(DOG).build()),
                Map.of(DOG, 1)
            ),
            Arguments.of(
                List.of(
                    Animal.builder().type(FISH).build(),
                    Animal.builder().type(CAT).build(),
                    Animal.builder().type(BIRD).build(),
                    Animal.builder().type(FISH).build()
                ),
                Map.of(
                    FISH, 2,
                    CAT, 1,
                    BIRD, 1
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource
    void countAnimalTypes_ShouldReturnCountedAnimalTypes(
        List<Animal> animalList, Map<Animal.Type, Integer> expectedCountedAnimalTypes
    ) {
        Map<Animal.Type, Integer> actualCountedAnimalTypes = Task3.countAnimalTypes(animalList);
        assertThat(actualCountedAnimalTypes).isEqualTo(expectedCountedAnimalTypes);
    }

    @ParameterizedTest
    @NullSource
    void countAnimalTypes_ShouldThrowNullPointerException(List<Animal> animalList) {
        assertThatThrownBy(
            () -> Task3.countAnimalTypes(animalList)
        ).isInstanceOf(NullPointerException.class);
    }

}

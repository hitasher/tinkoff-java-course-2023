package edu.hw4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import static edu.hw4.Animal.Type.CAT;
import static edu.hw4.Animal.Type.DOG;
import static edu.hw4.Animal.Type.FISH;
import static edu.hw4.Animal.Type.SPIDER;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class TestTask15 {

    private static Stream<Arguments>
    getSumOfWeightOfEachAnimalTypeInGivenAgeRange_ShouldReturnSumOfWeightOfEachAnimalType() {
        return Stream.of(
            Arguments.of(List.of(), 2, 5, Map.of()),
            Arguments.of(
                List.of(Animal.builder().type(CAT).age(7).weight(15).build()),
                2, 5, Map.of()
            ),
            Arguments.of(
                List.of(Animal.builder().type(CAT).age(3).weight(15).build()),
                2, 5, Map.of(CAT, 15)
            ),
            Arguments.of(
                List.of(
                    Animal.builder().type(SPIDER).age(1).weight(3).build(),
                    Animal.builder().type(SPIDER).age(2).weight(3).build(),
                    Animal.builder().type(SPIDER).age(3).weight(4).build(),
                    Animal.builder().type(SPIDER).age(4).weight(8).build()
                ), 2, 3, Map.of(SPIDER, 7)
            ),
            Arguments.of(
                List.of(
                    Animal.builder().type(FISH).age(4).weight(10).build(),
                    Animal.builder().type(DOG).age(5).weight(40).build(),
                    Animal.builder().type(FISH).age(10).weight(55).build(),
                    Animal.builder().type(DOG).age(3).weight(45).build(),
                    Animal.builder().type(CAT).age(5).weight(30).build(),
                    Animal.builder().type(SPIDER).age(1).weight(5).build()
                ), 2, 8,
                Map.of(
                    FISH, 10,
                    DOG, 85,
                    CAT, 30
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource
    void getSumOfWeightOfEachAnimalTypeInGivenAgeRange_ShouldReturnSumOfWeightOfEachAnimalType(
        List<Animal> animalList,
        int lowerBound,
        int upperBound,
        Map<Animal.Type, Integer> expectedSumOfWeightOfEachAnimalType
    ) {
        Map<Animal.Type, Integer> actualSumOfWeightOfEachAnimalType = Task15.getSumOfWeightOfEachAnimalTypeInGivenAgeRange(animalList, lowerBound, upperBound);
        assertThat(actualSumOfWeightOfEachAnimalType).isEqualTo(expectedSumOfWeightOfEachAnimalType);
    }

    @ParameterizedTest
    @NullSource
    void getSumOfWeightOfEachAnimalTypeInGivenAgeRange_ShouldThrowNullPointerException(List<Animal> animalList) {
        assertThatThrownBy(
            () -> Task15.getSumOfWeightOfEachAnimalTypeInGivenAgeRange(animalList, 2, 5)
        ).isInstanceOf(NullPointerException.class);
    }

    private static Stream<Arguments> isThereDogTallerThanK_ShouldThrowIllegalArgumentException() {
        return Stream.of(
            Arguments.of(0, 0),
            Arguments.of(0, 2),
            Arguments.of(1, 0),
            Arguments.of(5, 3),
            Arguments.of(2, 1),
            Arguments.of(-1, 1),
            Arguments.of(0, -2),
            Arguments.of(-3, -3)
        );
    }

    @ParameterizedTest
    @MethodSource
    void isThereDogTallerThanK_ShouldThrowIllegalArgumentException(int lowerBound, int upperBound) {
        List<Animal> animalList = List.of(Animal.builder().type(FISH).weight(16).build());
        assertThatThrownBy(
            () -> Task15.getSumOfWeightOfEachAnimalTypeInGivenAgeRange(animalList, lowerBound, upperBound)
        ).isInstanceOf(IllegalArgumentException.class);
    }

}

package edu.hw4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import java.util.List;
import java.util.stream.Stream;
import static edu.hw4.Animal.Type.CAT;
import static edu.hw4.Animal.Type.DOG;
import static edu.hw4.Animal.Type.FISH;
import static edu.hw4.Animal.Type.SPIDER;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class TestTask10 {

    private static Stream<Arguments> getAnimalsWhichAgeNotEqualsPaws_ShouldReturnAnimasWhichAgeNotEqualsPaws() {
        return Stream.of(
            Arguments.of(List.of(), List.of()),
            Arguments.of(
                List.of(Animal.builder().age(4).type(DOG).build()),
                List.of()
            ),
            Arguments.of(
                List.of(Animal.builder().age(5).type(SPIDER).build()),
                List.of(Animal.builder().age(5).type(SPIDER).build())
            ),
            Arguments.of(
                List.of(
                    Animal.builder().age(8).type(SPIDER).build(),
                    Animal.builder().age(1).type(DOG).build(),
                    Animal.builder().age(1).type(DOG).build(),
                    Animal.builder().age(4).type(CAT).build(),
                    Animal.builder().age(3).type(SPIDER).build(),
                    Animal.builder().age(2).type(FISH).build()
                ), List.of(
                    Animal.builder().age(1).type(DOG).build(),
                    Animal.builder().age(1).type(DOG).build(),
                    Animal.builder().age(3).type(SPIDER).build(),
                    Animal.builder().age(2).type(FISH).build()
                ))
        );
    }

    @ParameterizedTest
    @MethodSource
    void getAnimalsWhichAgeNotEqualsPaws_ShouldReturnAnimasWhichAgeNotEqualsPaws(
        List<Animal> animalList, List<Animal> expectedAnimals
    ) {
        List<Animal> actualAnimals = Task10.getAnimalsWhichAgeNotEqualsPaws(animalList);
        assertThat(actualAnimals).isEqualTo(expectedAnimals);
    }

    @ParameterizedTest
    @NullSource
    void getAnimalsWhichAgeNotEqualsPaws_ShouldThrowNullPointerException(List<Animal> animalList) {
        assertThatThrownBy(
            () -> Task10.getAnimalsWhichAgeNotEqualsPaws(animalList)
        ).isInstanceOf(NullPointerException.class);
    }
}

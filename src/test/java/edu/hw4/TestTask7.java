package edu.hw4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class TestTask7 {

    private static Stream<Arguments> getKtHOldestAnimal_ShouldReturnKthOldestAnimal() {
        return Stream.of(
            Arguments.of(List.of(Animal.builder().age(5).build()), 1, Animal.builder().age(5).build()),
            Arguments.of(
                List.of(
                    Animal.builder().age(10).build(),
                    Animal.builder().age(20).build(),
                    Animal.builder().age(5).build(),
                    Animal.builder().age(15).build()
                ), 3, Animal.builder().age(10).build()
            )
        );
    }

    @ParameterizedTest
    @MethodSource
    void getKtHOldestAnimal_ShouldReturnKthOldestAnimal(List<Animal> animalList, int k, Animal expectedAnimal) {
        Animal actualAnimal = Task7.getKthOldestAnimal(animalList, k);
        assertThat(actualAnimal).isEqualTo(expectedAnimal);
    }

    @ParameterizedTest
    @NullSource
    void getKthOldestAnimal_ShouldThrowNullPointerException(List<Animal> animalList) {
        assertThatThrownBy(
            () -> Task7.getKthOldestAnimal(animalList, 1)
        ).isInstanceOf(NullPointerException.class);
    }

    private static Stream<Arguments> getKthOldestAnimal_ShouldThrowIllegalArgumentException() {
        return Stream.of(
            Arguments.of(List.of(), 0),
            Arguments.of(List.of(), 1),
            Arguments.of(List.of(Animal.builder().age(5).build()), 2, Animal.builder().age(5).build()),
            Arguments.of(
                List.of(
                    Animal.builder().age(7).build(),
                    Animal.builder().age(10).build(),
                    Animal.builder().age(15).build()
                ), 4
            )
        );
    }

    @ParameterizedTest
    @MethodSource
    void getKthOldestAnimal_ShouldThrowIllegalArgumentException(List<Animal> animalList, int k) {
        assertThatThrownBy(
            () -> Task7.getKthOldestAnimal(animalList, k)
        ).isInstanceOf(IllegalArgumentException.class);
    }

}

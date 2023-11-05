package edu.hw4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class TestTask4 {

    private static Stream<Arguments> getAnimalWithLongestName_ShouldReturnAnimalWithLongestName() {
        return Stream.of(
            Arguments.of(List.of(
                Animal.builder().name("Yukon").build()
            ), Animal.builder().name("Yukon").build()),
            Arguments.of(List.of(
                Animal.builder().name("Bo").build(),
                Animal.builder().name("Bentley").build(),
                Animal.builder().name("Aries").build()
            ), Animal.builder().name("Bentley").build())
        );
    }

    @ParameterizedTest
    @MethodSource
    void getAnimalWithLongestName_ShouldReturnAnimalWithLongestName(List<Animal> animalList, Animal expectedAnimal) {
        Animal actualAnimal = Task4.getAnimalWithLongestName(animalList);
        assertThat(actualAnimal).isEqualTo(expectedAnimal);
    }

    @ParameterizedTest
    @NullSource
    void getAnimalWithLongestName_ShouldThrowNullPointerException(List<Animal> animalList) {
        assertThatThrownBy(
            () -> Task4.getAnimalWithLongestName(animalList)
        ).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @EmptySource
    void getAnimalWithLongestName_ShouldThrowIllegalArgumentException(List<Animal> animalList) {
        assertThatThrownBy(
            () -> Task4.getAnimalWithLongestName(animalList)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}

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

public class TestTask5 {

    private static Stream<Arguments> getMostFrequentAnimalSex_ShouldReturnMostFrequentAnimalSex() {
        return Stream.of(
            Arguments.of(
                List.of(Animal.builder().sex(Animal.Sex.FEMALE).build()),
                Animal.Sex.FEMALE
            ),
            Arguments.of(
                List.of(
                    Animal.builder().sex(Animal.Sex.FEMALE).build(),
                    Animal.builder().sex(Animal.Sex.MALE).build()
                ), null
            ),
            Arguments.of(
                List.of(
                    Animal.builder().sex(Animal.Sex.MALE).build(),
                    Animal.builder().sex(Animal.Sex.MALE).build(),
                    Animal.builder().sex(Animal.Sex.MALE).build(),
                    Animal.builder().sex(Animal.Sex.FEMALE).build()
                ), Animal.Sex.MALE
            )
        );
    }

    @ParameterizedTest
    @MethodSource
    void getMostFrequentAnimalSex_ShouldReturnMostFrequentAnimalSex(List<Animal> animalList, Animal.Sex expectedSex) {
        Animal.Sex actualSex = Task5.getMostFrequentAnimalSex(animalList);
        assertThat(actualSex).isEqualTo(expectedSex);
    }

    @ParameterizedTest
    @NullSource
    void getMostFrequentAnimalSex_ShouldThrowNullPointerException(List<Animal> animalList) {
        assertThatThrownBy(
            () -> Task5.getMostFrequentAnimalSex(animalList)
        ).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @EmptySource
    void getMostFrequentAnimalSex_ShouldThrowIllegalArgumentException(List<Animal> animalList) {
        assertThatThrownBy(
            () -> Task5.getMostFrequentAnimalSex(animalList)
        ).isInstanceOf(IllegalArgumentException.class);
    }

}

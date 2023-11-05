package edu.hw4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class TestTask1 {

    private static boolean isSortedByHeight(List<Animal> animalList) {
        for (int i = 0; i < animalList.size() - 1; ++i) {
            if (animalList.get(i).height() >= animalList.get(i + 1).height()) {
                return false;
            }
        }
        return true;
    }

    private static Stream<Arguments> sortAnimalsByHeight_ShouldReturnSortedAnimals() {
        return Stream.of(
            Arguments.of(List.of()),
            Arguments.of(List.of(
                Animal.builder().height(1).build()
            )),
            Arguments.of(List.of(
                Animal.builder().height(2).build(),
                Animal.builder().height(3).build(),
                Animal.builder().height(1).build(),
                Animal.builder().height(4).build()
            )),
            Arguments.of(List.of(
                Animal.builder().height(15).build(),
                Animal.builder().height(20).build(),
                Animal.builder().height(10).build(),
                Animal.builder().height(7).build(),
                Animal.builder().height(11).build(),
                Animal.builder().height(42).build()
            ))
        );
    }

    @ParameterizedTest
    @MethodSource
    void sortAnimalsByHeight_ShouldReturnSortedAnimals(List<Animal> animalList) {
        List<Animal> sortedAnimalList = Task1.sortAnimalsByHeight(animalList);
        assertThat(isSortedByHeight(sortedAnimalList)).isTrue();
    }

    @ParameterizedTest
    @NullSource
    void sortAnimalsByHeight_ShouldThrowNullPointerException(List<Animal> animalList) {
        assertThatThrownBy(
            () -> Task1.sortAnimalsByHeight(animalList)
        ).isInstanceOf(NullPointerException.class);
    }
}

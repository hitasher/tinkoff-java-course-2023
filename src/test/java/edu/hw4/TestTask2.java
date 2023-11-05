package edu.hw4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class TestTask2 {

    private static boolean isSortedByDecreasingWeight(List<Animal> animalList) {
        for (int i = 0; i < animalList.size() - 1; ++i) {
            if (animalList.get(i).weight() <= animalList.get(i + 1).weight()) {
                return false;
            }
        }
        return true;
    }

    private static Stream<Arguments> sortFirstKAnimalsByWeightDecreasing_ShouldReturnSortedAnimals() {
        return Stream.of(
            Arguments.of(List.of(
                Animal.builder().weight(1).build()
            ), 1),
            Arguments.of(List.of(
                Animal.builder().weight(2).build(),
                Animal.builder().weight(4).build(),
                Animal.builder().weight(1).build(),
                Animal.builder().weight(3).build()
            ), 3),
            Arguments.of(List.of(
                Animal.builder().weight(94).build(),
                Animal.builder().weight(19).build(),
                Animal.builder().weight(74).build(),
                Animal.builder().weight(23).build(),
                Animal.builder().weight(39).build(),
                Animal.builder().weight(3).build()
            ), 5)
        );
    }

    @ParameterizedTest
    @MethodSource
    void sortFirstKAnimalsByWeightDecreasing_ShouldReturnSortedAnimals(List<Animal> animalList, int k) {
        List<Animal> sortedAnimalList = Task2.sortFirstKAnimalsByWeightDecreasing(animalList, k);
        assertThat(sortedAnimalList.size()).isEqualTo(k);
        assertThat(isSortedByDecreasingWeight(sortedAnimalList)).isTrue();
    }

    @ParameterizedTest
    @NullSource
    void sortFirstKAnimalsByWeightDecreasing_ShouldThrowNullPointerException(List<Animal> animalList) {
        assertThatThrownBy(
            () -> Task2.sortFirstKAnimalsByWeightDecreasing(animalList, 3)
        ).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @ValueSource(ints={0, -1, -99})
    void sortFirstKAnimalsByWeightDecreasing_ShouldThrowIllegalArgumentException(int k) {
        List<Animal> animalList = List.of(Animal.builder().build());
        assertThatThrownBy(
            () -> Task2.sortFirstKAnimalsByWeightDecreasing(animalList, k)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}

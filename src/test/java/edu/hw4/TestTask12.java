package edu.hw4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class TestTask12 {

    private static Stream<Arguments>
    getNumberOfAnimalsWhoseWeightIsGreaterThanHeight_ShouldReturnNumberOfAnimalsWhoseWeightIsGreaterThanHeight() {
        return Stream.of(
            Arguments.of(List.of(), 0),
            Arguments.of(List.of(Animal.builder().weight(20).height(30).build()), 0),
            Arguments.of(List.of(Animal.builder().weight(30).height(30).build()), 0),
            Arguments.of(List.of(Animal.builder().weight(40).height(30).build()), 1),
            Arguments.of(
                List.of(
                    Animal.builder().weight(49).height(50).build(),
                    Animal.builder().weight(80).height(80).build(),
                    Animal.builder().weight(95).height(90).build(),
                    Animal.builder().weight(20).height(30).build(),
                    Animal.builder().weight(40).height(25).build(),
                    Animal.builder().weight(83).height(95).build(),
                    Animal.builder().weight(121).height(103).build()
                ), 3
            )
        );
    }

    @ParameterizedTest
    @MethodSource
    void getNumberOfAnimalsWhoseWeightIsGreaterThanHeight_ShouldReturnNumberOfAnimalsWhoseWeightIsGreaterThanHeight(
        List<Animal> animalList, Integer expectedNumberOfAnimals
    ) {
        Integer numberOfAnimals = Task12.getNumberOfAnimalsWhoseWeightIsGreaterThanHeight(animalList);
        assertThat(numberOfAnimals).isEqualTo(expectedNumberOfAnimals);
    }

    @ParameterizedTest
    @NullSource
    void getNumberOfAnimalsWhoseWeightIsGreaterThanHeight_ShouldThrowNulLPointerException(List<Animal> animalList) {
        assertThatThrownBy(
            () -> Task12.getNumberOfAnimalsWhoseWeightIsGreaterThanHeight(animalList)
        ).isInstanceOf(NullPointerException.class);
    }
}

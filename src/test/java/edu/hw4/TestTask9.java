package edu.hw4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import java.util.List;
import java.util.stream.Stream;
import static edu.hw4.Animal.Type.BIRD;
import static edu.hw4.Animal.Type.CAT;
import static edu.hw4.Animal.Type.DOG;
import static edu.hw4.Animal.Type.FISH;
import static edu.hw4.Animal.Type.SPIDER;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class TestTask9 {

    private static Stream<Arguments> sumPaws_ShouldReturnPawsSum() {
        return Stream.of(
            Arguments.of(List.of(), 0),
            Arguments.of(List.of(Animal.builder().type(FISH).build()), 0),
            Arguments.of(List.of(Animal.builder().type(SPIDER).build()), 8),
            Arguments.of(
                List.of(
                    Animal.builder().type(SPIDER).build(),
                    Animal.builder().type(DOG).build(),
                    Animal.builder().type(DOG).build(),
                    Animal.builder().type(CAT).build(),
                    Animal.builder().type(SPIDER).build(),
                    Animal.builder().type(FISH).build(),
                    Animal.builder().type(BIRD).build()
                ), 30)
        );
    }

    @ParameterizedTest
    @MethodSource
    void sumPaws_ShouldReturnPawsSum(List<Animal> animalList, int expectedPawsSum) {
        int actualPawsSum = Task9.sumPaws(animalList);
        assertThat(actualPawsSum).isEqualTo(expectedPawsSum);
    }

    @ParameterizedTest
    @NullSource
    void sumPaws_ShouldThrowNullPointerException(List<Animal> animalList) {
        assertThatThrownBy(
            () -> Task9.sumPaws(animalList)
        ).isInstanceOf(NullPointerException.class);
    }
}

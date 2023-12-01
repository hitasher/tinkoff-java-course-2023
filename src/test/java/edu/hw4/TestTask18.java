package edu.hw4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import java.util.List;
import java.util.stream.Stream;
import static edu.hw4.Animal.Type.CAT;
import static edu.hw4.Animal.Type.FISH;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class TestTask18 {

    private static Stream<Arguments> getHeaviestFish_ShouldReturnHeaviestFish() {
        return Stream.of(
            Arguments.of(List.of(), null),
            Arguments.of(List.of(Animal.builder().type(CAT).weight(10).build()), null),
            Arguments.of(
                List.of(Animal.builder().type(FISH).weight(10).build()),
                Animal.builder().type(FISH).weight(10).build()
            ),
            Arguments.of(
                List.of(
                    Animal.builder().type(FISH).weight(15).build(),
                    Animal.builder().type(CAT).weight(30).build(),
                    Animal.builder().type(FISH).weight(20).build(),
                    Animal.builder().type(FISH).weight(10).build()
                ),
                Animal.builder().type(FISH).weight(20).build()
            )
        );
    }

    @ParameterizedTest
    @MethodSource
    void getHeaviestFish_ShouldReturnHeaviestFish(List<Animal> animalList, Animal expectedHeaviestFish) {
        Animal actualHeaviestFish = Task18.getHeaviestFish(animalList);
        assertThat(actualHeaviestFish).isEqualTo(expectedHeaviestFish);
    }

    @ParameterizedTest
    @NullSource
    void getHeaviestFish_ShouldThrowNullPointerException(List<Animal> animalList) {
        assertThatThrownBy(
            () -> Task18.getHeaviestFish(animalList)
        ).isInstanceOf(NullPointerException.class);
    }
}

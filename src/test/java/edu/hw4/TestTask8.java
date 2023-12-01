package edu.hw4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class TestTask8 {

    private static Stream<Arguments> getHeaviestAnimalShorterThanK_ShouldReturnHeaviestAnimalShorterThanK() {
        return Stream.of(
            Arguments.of(
                List.of(),
                20,
                null
            ),
            Arguments.of(
                List.of(Animal.builder().weight(30).height(120).build()),
                80,
                null
            ),
            Arguments.of(
                List.of(Animal.builder().weight(20).height(10).build()),
                15,
                Animal.builder().weight(20).height(10).build()
            ),
            Arguments.of(
                List.of(
                    Animal.builder().weight(20).height(99).build(),
                    Animal.builder().weight(30).height(100).build(),
                    Animal.builder().weight(25).height(80).build()
                ), 100, Animal.builder().weight(25).height(80).build()
            )
        );
    }

    @ParameterizedTest
    @MethodSource
    void getHeaviestAnimalShorterThanK_ShouldReturnHeaviestAnimalShorterThanK(
        List<Animal> animalList, int k, Animal expectedAnimal
    ) {
        Optional<Animal> actualAnimal = Task8.getHeaviestAnimalShorterThanK(animalList, k);
        if (actualAnimal.isEmpty()) {
            assertThat(expectedAnimal).isNull();
        } else {
            assertThat(actualAnimal.get()).isEqualTo(expectedAnimal);
        }
    }

    @ParameterizedTest
    @NullSource
    void getHeaviestAnimalShorterThanK_ShouldThrowNullPointerException(List<Animal> animalList) {
        assertThatThrownBy(
            () -> Task8.getHeaviestAnimalShorterThanK(animalList, 1)
        ).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @ValueSource(ints={0, -1, -69})
    void getHeaviestAnimalShorterThanK_ShouldThrowIllegalArgumentException(int k) {
        List<Animal> animalList = List.of(Animal.builder().weight(100).height(60).build());
        assertThatThrownBy(
            () -> Task8.getHeaviestAnimalShorterThanK(animalList, k)
        ).isInstanceOf(IllegalArgumentException.class);
    }

}

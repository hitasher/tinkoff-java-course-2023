package edu.hw4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class TestTask13 {

    private static Stream<Arguments>
    getAnimalsWhoseNamesConsistOfMoreThanTwoWords_ShouldReturnAnimalsWhoseNamesConsistOfMoreThanTwoWords() {
        return Stream.of(
            Arguments.of(List.of(), List.of()),
            Arguments.of(
                List.of(Animal.builder().name("Alex").build()),
                List.of()
            ),
            Arguments.of(
                List.of(Animal.builder().name("Alex Bob").build()),
                List.of()
            ),
            Arguments.of(
                List.of(Animal.builder().name("Alex Bob Dane").build()),
                List.of(Animal.builder().name("Alex Bob Dane").build())
            ),
            Arguments.of(
                List.of(
                    Animal.builder().name("Flash Blaze").build(),
                    Animal.builder().name("Nico Otis Cooper").build(),
                    Animal.builder().name("Artie").build(),
                    Animal.builder().name("Ozzy Baron Gus Chip").build(),
                    Animal.builder().name("Teddy").build(),
                    Animal.builder().name("Coco Simon Archie").build()
                ),
                List.of(
                    Animal.builder().name("Nico Otis Cooper").build(),
                    Animal.builder().name("Ozzy Baron Gus Chip").build(),
                    Animal.builder().name("Coco Simon Archie").build()
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource
    void getAnimalsWhoseNamesConsistOfMoreThanTwoWords_ShouldReturnAnimalsWhoseNamesConsistOfMoreThanTwoWords(
        List<Animal> animalList, List<Animal> expectedAnimals
    ) {
        List<Animal> actualAnimals = Task13.getAnimalsWhoseNamesConsistOfMoreThanTwoWords(animalList);
        assertThat(actualAnimals).isEqualTo(expectedAnimals);
    }

    @ParameterizedTest
    @NullSource
    void getAnimalsWhoseNamesConsistOfMoreThanTwoWords_ShouldThrowNulLPointerException(List<Animal> animalList) {
        assertThatThrownBy(
            () -> Task13.getAnimalsWhoseNamesConsistOfMoreThanTwoWords(animalList)
        ).isInstanceOf(NullPointerException.class);
    }
}

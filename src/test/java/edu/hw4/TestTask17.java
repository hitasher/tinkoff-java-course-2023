package edu.hw4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import java.util.List;
import java.util.stream.Stream;
import static edu.hw4.Animal.Type.DOG;
import static edu.hw4.Animal.Type.SPIDER;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class TestTask17 {

    private static Stream<Arguments> doSpidersBiteMoreThanDogs_ShouldReturnDoSpidersBiteMoreThanDogs() {
        return Stream.of(
            Arguments.of(List.of(), false),
            Arguments.of(List.of(Animal.builder().type(SPIDER).bites(false).build()), false),
            Arguments.of(List.of(Animal.builder().type(DOG).bites(true).build()), false),
            Arguments.of(List.of(Animal.builder().type(SPIDER).bites(true).build()), false),
            Arguments.of(List.of(
                Animal.builder().type(SPIDER).bites(false).build(),
                Animal.builder().type(DOG).bites(false).build()
            ), false),
            Arguments.of(List.of(
                Animal.builder().type(SPIDER).bites(false).build(),
                Animal.builder().type(DOG).bites(true).build()
            ), false),
            Arguments.of(List.of(
                Animal.builder().type(SPIDER).bites(true).build(),
                Animal.builder().type(DOG).bites(true).build()
            ), false),
            Arguments.of(List.of(
                Animal.builder().type(SPIDER).bites(true).build(),
                Animal.builder().type(DOG).bites(false).build()
            ), true)
        );
    }

    @ParameterizedTest
    @MethodSource
    void doSpidersBiteMoreThanDogs_ShouldReturnDoSpidersBiteMoreThanDogs(
        List<Animal> animalList, boolean expectedBoolean
    ) {
        boolean actualBoolean = Task17.doSpidersBiteMoreThanDogs(animalList);
        assertThat(actualBoolean).isEqualTo(expectedBoolean);
    }

    @ParameterizedTest
    @NullSource
    void doSpidersBiteMoreThanDogs_ShouldThrowNullPointerException(List<Animal> animalList) {
        assertThatThrownBy(
            () -> Task17.doSpidersBiteMoreThanDogs(animalList)
        ).isInstanceOf(NullPointerException.class);
    }
}

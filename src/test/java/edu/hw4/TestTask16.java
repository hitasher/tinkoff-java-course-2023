package edu.hw4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import java.util.List;
import java.util.stream.Stream;
import static edu.hw4.Animal.Sex.FEMALE;
import static edu.hw4.Animal.Sex.MALE;
import static edu.hw4.Animal.Type.DOG;
import static edu.hw4.Animal.Type.FISH;
import static edu.hw4.Animal.Type.SPIDER;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class TestTask16 {
    private static Stream<Arguments> sortAnimalsByTypeThenBySexThenByName_ShouldReturnSortedAnimals() {
        return Stream.of(
            Arguments.of(List.of(), List.of()),
            Arguments.of(
                List.of(Animal.builder().type(FISH).build()),
                List.of(Animal.builder().type(FISH).build())
            ),
            Arguments.of(
                List.of(
                    Animal.builder().type(FISH).sex(FEMALE).name("Gloria").build(),
                    Animal.builder().type(DOG).sex(FEMALE).name("Aarne").build(),
                    Animal.builder().type(DOG).sex(MALE).name("Cooper").build(),
                    Animal.builder().type(SPIDER).sex(MALE).name("Spy").build(),
                    Animal.builder().type(FISH).sex(MALE).name("Nemo").build(),
                    Animal.builder().type(DOG).sex(FEMALE).name("Mickey").build()
                ),
                List.of(
                    Animal.builder().type(DOG).sex(MALE).name("Cooper").build(),
                    Animal.builder().type(DOG).sex(FEMALE).name("Aarne").build(),
                    Animal.builder().type(DOG).sex(FEMALE).name("Mickey").build(),
                    Animal.builder().type(FISH).sex(MALE).name("Nemo").build(),
                    Animal.builder().type(FISH).sex(FEMALE).name("Gloria").build(),
                    Animal.builder().type(SPIDER).sex(MALE).name("Spy").build()
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource
    void sortAnimalsByTypeThenBySexThenByName_ShouldReturnSortedAnimals(
        List<Animal> animalList, List<Animal> expectedSortedAnimals
    ) {
        List<Animal> actualSortedAnimals = Task16.sortAnimalsByTypeThenBySexThenByName(animalList);
        assertThat(actualSortedAnimals).isEqualTo(expectedSortedAnimals);
    }

    @ParameterizedTest
    @NullSource
    void sortAnimalsByTypeThenBySexThenByName_ShouldThrowNullPointerException(List<Animal> animalList) {
        assertThatThrownBy(
            () -> Task16.sortAnimalsByTypeThenBySexThenByName(animalList)
        ).isInstanceOf(NullPointerException.class);
    }
}

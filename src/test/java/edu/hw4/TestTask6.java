package edu.hw4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import static edu.hw4.Animal.Type.CAT;
import static edu.hw4.Animal.Type.DOG;
import static edu.hw4.Animal.Type.FISH;
import static edu.hw4.Animal.Type.SPIDER;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class TestTask6 {

    private static Stream<Arguments> getHeaviestAnimalOfEachType_ShouldReturnHeaviestAnimalOfEachType() {
        return Stream.of(
            Arguments.of(List.of(), Map.of()),
            Arguments.of(
                List.of(Animal.builder().type(FISH).weight(15).build()),
                Map.of(FISH, Animal.builder().type(FISH).weight(15).build())
            ),
            Arguments.of(
                List.of(
                    Animal.builder().type(FISH).weight(10).build(),
                    Animal.builder().type(SPIDER).weight(1).build(),
                    Animal.builder().type(DOG).weight(50).build(),
                    Animal.builder().type(CAT).weight(40).build()
                ),
                Map.of(
                    FISH, Animal.builder().type(FISH).weight(10).build(),
                    SPIDER, Animal.builder().type(SPIDER).weight(1).build(),
                    DOG, Animal.builder().type(DOG).weight(50).build(),
                    CAT, Animal.builder().type(CAT).weight(40).build()
                )
            ),
            Arguments.of(
                List.of(
                    Animal.builder().type(FISH).weight(7).build(),
                    Animal.builder().type(CAT).weight(69).build(),
                    Animal.builder().type(FISH).weight(51).build(),
                    Animal.builder().type(CAT).weight(21).build(),
                    Animal.builder().type(CAT).weight(17).build(),
                    Animal.builder().type(FISH).weight(14).build(),
                    Animal.builder().type(CAT).weight(13).build()
                ),
                Map.of(
                    CAT, Animal.builder().type(CAT).weight(69).build(),
                    FISH, Animal.builder().type(FISH).weight(51).build()
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource
    void getHeaviestAnimalOfEachType_ShouldReturnHeaviestAnimalOfEachType(
        List<Animal> animalList, Map<Animal.Type, Animal> expectedHeaviestAnimalOfEachType
    ) {
        Map<Animal.Type, Animal> actualHeaviestAnimalOfEachType = Task6.getHeaviestAnimalOfEachType(animalList);
        assertThat(actualHeaviestAnimalOfEachType).isEqualTo(expectedHeaviestAnimalOfEachType);
    }

    @ParameterizedTest
    @NullSource
    void getHeaviestAnimalOfEachType_ShouldThrowNullPointerException(List<Animal> animalList) {
        assertThatThrownBy(
            () -> Task6.getHeaviestAnimalOfEachType(animalList)
        ).isInstanceOf(NullPointerException.class);
    }

}

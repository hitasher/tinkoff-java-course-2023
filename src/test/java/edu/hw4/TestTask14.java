package edu.hw4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;
import java.util.stream.Stream;
import static edu.hw4.Animal.Type.BIRD;
import static edu.hw4.Animal.Type.CAT;
import static edu.hw4.Animal.Type.DOG;
import static edu.hw4.Animal.Type.FISH;
import static edu.hw4.Animal.Type.SPIDER;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class TestTask14 {

    private static Stream<Arguments> isThereDogTallerThanK_ShouldReturnIsThereDogTallerThanK() {
        return Stream.of(
            Arguments.of(List.of(), 1, false),
            Arguments.of(
                List.of(Animal.builder().type(CAT).height(10).build()),
                20, false
            ),
            Arguments.of(
                List.of(Animal.builder().type(DOG).height(50).build()),
                50, false
            ),
            Arguments.of(
                List.of(Animal.builder().type(DOG).height(42).build()),
                40, true
            ),
            Arguments.of(
                List.of(
                    Animal.builder().type(SPIDER).height(10).build(),
                    Animal.builder().type(DOG).height(40).build(),
                    Animal.builder().type(CAT).height(55).build(),
                    Animal.builder().type(DOG).height(45).build()
                    ), 50, false
            ),
            Arguments.of(
                List.of(
                    Animal.builder().type(DOG).height(40).build(),
                    Animal.builder().type(FISH).height(15).build(),
                    Animal.builder().type(DOG).height(45).build(),
                    Animal.builder().type(BIRD).height(40).build()
                ), 44, true
            )
        );
    }

    @ParameterizedTest
    @MethodSource
    void isThereDogTallerThanK_ShouldReturnIsThereDogTallerThanK(List<Animal> animalList, int k, boolean expectedBoolean) {
        boolean actualBoolean = Task14.isThereDogTallerThanK(animalList, k);
        assertThat(actualBoolean).isEqualTo(expectedBoolean);
    }

    @ParameterizedTest
    @NullSource
    void isThereDogTallerThanK_ShouldThrowNullPointerException(List<Animal> animalList) {
        assertThatThrownBy(
            () -> Task14.isThereDogTallerThanK(animalList, 1)
        ).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @ValueSource(ints={0, -1, -69})
    void isThereDogTallerThanK_ShouldThrowIllegalArgumentException(int k) {
        List<Animal> animalList = List.of(Animal.builder().type(DOG).height(42).build());
        assertThatThrownBy(
            () -> Task14.isThereDogTallerThanK(animalList, k)
        ).isInstanceOf(IllegalArgumentException.class);
    }

}

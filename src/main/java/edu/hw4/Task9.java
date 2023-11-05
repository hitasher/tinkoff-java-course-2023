package edu.hw4;

import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public final class Task9 {
    private Task9() {
    }

    @NotNull
    public static Integer sumPaws(List<Animal> animalList) {

        Objects.requireNonNull(animalList);

        return animalList.stream()
            .mapToInt(Animal::paws)
            .sum();
    }
}

package edu.hw4;

import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public final class Task13 {
    private Task13() {
    }

    @NotNull
    public static List<Animal> getAnimalsWhoseNamesConsistOfMoreThanTwoWords(List<Animal> animalList) {
        Objects.requireNonNull(animalList);

        return animalList.stream()
            .filter(animal -> animal.name().split(" ").length > 2)
            .toList();
    }
}

package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public final class Task7 {
    private Task7() {
    }

    @NotNull
    public static Animal getKthOldestAnimal(List<Animal> animalList, int k) {

        Objects.requireNonNull(animalList);

        if (k <= 0) {
            throw new IllegalArgumentException("K must be positive");
        }

        return animalList.stream()
            .sorted(Comparator.comparingInt(Animal::age).reversed())
            .skip(k - 1)
            .findFirst()
            .orElseThrow(
                () -> new IllegalArgumentException("There are too few animals in the provided list")
            );
    }
}

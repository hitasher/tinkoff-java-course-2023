package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public final class Task8 {
    private Task8() {
    }

    @NotNull
    public static Optional<Animal> getHeaviestAnimalShorterThanK(List<Animal> animalList, int k) {

        Objects.requireNonNull(animalList);

        if (k <= 0) {
            throw new IllegalArgumentException("K must be positive");
        }

        return animalList.stream()
            .filter(animal -> animal.height() < k)
            .max(Comparator.comparingInt(Animal::weight));
    }
}

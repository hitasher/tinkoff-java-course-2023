package edu.hw4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public final class Task18 {
    private Task18() {
    }

    @SafeVarargs
    public static Animal getHeaviestFish(List<Animal>... animals) {
        Objects.requireNonNull(animals);

        return Arrays.stream(animals)
            .flatMap(List::stream)
            .filter(animal -> animal.type() == Animal.Type.FISH)
            .max(Comparator.comparingInt(Animal::weight))
            .orElse(null);

    }
}

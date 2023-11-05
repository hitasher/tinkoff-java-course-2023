package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public final class Task2 {
    private Task2() {
    }

    @NotNull
    public static List<Animal> sortFirstKAnimalsByWeightDecreasing(List<Animal> animalList, int k) {
        Objects.requireNonNull(animalList);

        if (k <= 0) {
            throw new IllegalArgumentException("K must be positive");
        }

        return animalList.stream()
            .sorted(Comparator.comparingInt(Animal::weight).reversed())
            .limit(k)
            .toList();
    }
}

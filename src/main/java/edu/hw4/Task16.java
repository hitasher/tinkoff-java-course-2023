package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public final class Task16 {
    private Task16() {
    }

    @NotNull
    public static List<Animal> sortAnimalsByTypeThenBySexThenByName(List<Animal> animalList) {
        Objects.requireNonNull(animalList);

        return animalList.stream()
            .sorted(
                Comparator
                    .comparing(Animal::type)
                    .thenComparing(Animal::sex)
                    .thenComparing(Animal::name)
            ).toList();
    }
}

package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public final class Task1 {
    private Task1() {
    }

    @NotNull
    public static List<Animal> sortAnimalsByHeight(List<Animal> animalList) {
        Objects.requireNonNull(animalList);

        return animalList.stream()
            .sorted(Comparator.comparingInt(Animal::height))
            .toList();
    }
}

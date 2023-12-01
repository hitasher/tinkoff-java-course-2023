package edu.hw4;

import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public final class Task10 {
    private Task10() {
    }

    @NotNull
    public static List<Animal> getAnimalsWhichAgeNotEqualsPaws(List<Animal> animalList) {
        Objects.requireNonNull(animalList);

        return animalList.stream()
            .filter(animal -> animal.age() != animal.paws())
            .toList();
    }
}

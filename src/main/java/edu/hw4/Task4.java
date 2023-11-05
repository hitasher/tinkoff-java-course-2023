package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public final class Task4 {
    private Task4() {
    }

    @NotNull
    public static Animal getAnimalWithLongestName(List<Animal> animalList) {

        Objects.requireNonNull(animalList);

        if (animalList.isEmpty()) {
            throw new IllegalArgumentException("Provided animal list can't be empty");
        }

        return animalList.stream()
            .max(Comparator.comparingInt(animal -> animal.name().length()))
            .get();
    }
}

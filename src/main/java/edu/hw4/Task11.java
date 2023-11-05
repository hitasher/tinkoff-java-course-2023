package edu.hw4;

import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public final class Task11 {
    private final static int REQUIRED_HEIGHT = 100;

    private Task11() {
    }

    @NotNull
    public static List<Animal> getBitingAndTallAnimals(List<Animal> animalList) {
        Objects.requireNonNull(animalList);

        return animalList.stream()
            .filter(animal -> animal.bites() && animal.height() > REQUIRED_HEIGHT)
            .toList();
    }
}

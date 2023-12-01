package edu.hw4;

import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public final class Task12 {
    private Task12() {
    }

    @NotNull
    public static Integer getNumberOfAnimalsWhoseWeightIsGreaterThanHeight(List<Animal> animalList) {
        Objects.requireNonNull(animalList);

        return Math.toIntExact(
            animalList.stream()
                .filter(animal -> animal.weight() > animal.height())
                .count()
        );
    }
}

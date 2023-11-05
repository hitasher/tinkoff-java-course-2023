package edu.hw4;

import java.util.List;
import java.util.Objects;

public final class Task14 {
    private Task14() {
    }

    public static boolean isThereDogTallerThanK(List<Animal> animalList, int k) {
        Objects.requireNonNull(animalList);

        if (k <= 0) {
            throw new IllegalArgumentException("K must be positive");
        }


        return animalList.stream()
            .anyMatch(animal -> animal.type() == Animal.Type.DOG && animal.height() > k);
    }
}

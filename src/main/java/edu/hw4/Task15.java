package edu.hw4;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;

public final class Task15 {
    private Task15() {
    }

    @NotNull
    public static Map<Animal.Type, Integer> getSumOfWeightOfEachAnimalTypeInGivenAgeRange(
        List<Animal> animalList, int lowerAgeBound, int upperAgeBound
    ) {
        Objects.requireNonNull(animalList);

        if (lowerAgeBound <= 0 || upperAgeBound <= 0) {
            throw new IllegalArgumentException("Age bounds must be positive");
        } else if (lowerAgeBound > upperAgeBound) {
            throw new IllegalArgumentException("Lower age bound can't be greater than upper age bound");
        }

        return animalList.stream()
            .filter(animal -> animal.age() >= lowerAgeBound && animal.age() <= upperAgeBound)
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(Animal::weight)));
    }
}

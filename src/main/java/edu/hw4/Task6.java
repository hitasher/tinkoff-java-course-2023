package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;

public final class Task6 {
    private Task6() {
    }

    @NotNull
    public static Map<Animal.Type, Animal> getHeaviestAnimalOfEachType(List<Animal> animalList) {
        Objects.requireNonNull(animalList);

        return animalList.stream()
            .collect(Collectors.groupingBy(Animal::type, Collectors.collectingAndThen(
                Collectors.maxBy(Comparator.comparingInt(Animal::weight)),
                animal -> animal.orElse(null)
            )));
    }
}

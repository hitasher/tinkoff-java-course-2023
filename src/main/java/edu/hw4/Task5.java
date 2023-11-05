package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Task5 {
    private Task5() {
    }

    public static Animal.Sex getMostFrequentAnimalSex(List<Animal> animalList) {

        Objects.requireNonNull(animalList);

        Supplier<Stream<Entry<Animal.Sex, Integer>>> supplier = () -> animalList.stream()
            .collect(Collectors.groupingByConcurrent(Animal::sex, Collectors.summingInt(x -> 1)))
            .entrySet()
            .stream();

        if (supplier.get().count() == 2 && supplier.get().map(Entry::getValue).distinct().count() <= 1) {
            return null;
        }

        return supplier.get()
            .max(Comparator.comparingInt(Entry::getValue))
            .map(Entry::getKey)
            .orElseThrow(() -> new IllegalArgumentException("Provided animal list can't be empty"));
    }
}

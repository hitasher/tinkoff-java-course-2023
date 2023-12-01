package edu.hw4;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public final class Task20 {

    private final static AnimalValidator ANIMAL_VALIDATOR = new AnimalValidator();

    private Task20() {
    }

    public static Map<String, String> getNamesAndErrorsOfAnimalsWithErrors(List<Animal> animalList) {
        Objects.requireNonNull(animalList);

        return animalList.stream()
            .collect(Collectors.collectingAndThen(
                Collectors.toMap(
                    Animal::name,
                    animal -> ANIMAL_VALIDATOR.validateAnimal(animal).stream()
                        .map(ValidationError::field)
                        .collect(Collectors.joining(", "))
                ), map -> {
                    map.values().removeIf(String::isEmpty);
                    return map;
                }));
    }
}

package edu.hw4;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public final class Task19 {

    private final static AnimalValidator ANIMAL_VALIDATOR = new AnimalValidator();

    private Task19() {
    }

    public static Map<String, Set<ValidationError>> getNamesAndErrorsOfAnimalsWithErrors(List<Animal> animalList) {
        Objects.requireNonNull(animalList);

        return animalList.stream()
            .collect(Collectors.collectingAndThen(
                Collectors.toMap(Animal::name, ANIMAL_VALIDATOR::validateAnimal),
                map -> {
                    map.values().removeIf(Set::isEmpty);
                    return map;
                }
            ));
    }
}

package edu.hw4;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class Task17 {
    private Task17() {
    }

    public static boolean doSpidersBiteMoreThanDogs(List<Animal> animalList) {
        Objects.requireNonNull(animalList);

       return animalList.stream()
            .collect(Collectors.collectingAndThen(
                Collectors.groupingBy(
                    Animal::type,
                    Collectors.summingInt(a -> a.bites() ? 1 : 0)
                ),
                (map) -> {
                    if (!map.containsKey(Animal.Type.SPIDER) || !map.containsKey(Animal.Type.DOG)) {
                        return false;
                    }
                    return map.get(Animal.Type.SPIDER) > map.get(Animal.Type.DOG);
                }
            ));
    }
}

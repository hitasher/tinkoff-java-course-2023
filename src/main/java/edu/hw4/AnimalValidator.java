package edu.hw4;

import java.util.HashSet;
import java.util.Set;

public class AnimalValidator {
    public Set<ValidationError> validateAnimal(Animal animal) {
        Set<ValidationError> errorSet = new HashSet<>();
        if (animal.age() <= 0) {
            errorSet.add(getAgeValidationError());
        }
        if (animal.height() <= 0) {
            errorSet.add(getHeightValidationError());
        }
        if (animal.weight() <= 0) {
            errorSet.add(getWeightValidationError());
        }
        return errorSet;
    }

    public ValidationError getAgeValidationError() {
        return new ValidationError("age", "Age must be positive");
    }

    public ValidationError getHeightValidationError() {
        return new ValidationError("height", "Height must be positive");
    }

    public ValidationError getWeightValidationError() {
        return new ValidationError("weight", "Weight must be positive");
    }
}

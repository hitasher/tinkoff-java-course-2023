package edu.hw2.task1;

import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface Expression {
    Logger LOGGER = LogManager.getLogger();

    double evaluate();

    private static double objectToDouble(Object object) {
        LOGGER.trace("converting {} to double", object);
        if (object instanceof Double) {
            return (double) object;
        }
        if (object instanceof Integer) {
            return ((Integer) object).doubleValue();
        }
        if (object instanceof Expression) {
            return ((Expression) object).evaluate();
        }
        throw new IllegalArgumentException("Invalid argument provided: required int, double or Expression");
    }

    record Constant(Object object) implements Expression {
        @Override
        public double evaluate() {
            Objects.requireNonNull(object);
            LOGGER.trace("Making a constant from {}", object);
            return objectToDouble(object);
        }
    }

    record Negate(Object object) implements Expression {
        @Override
        public double evaluate() {
            Objects.requireNonNull(object);
            LOGGER.trace("Calculating negate from {}", object);
            return -objectToDouble(object);
        }
    }

    record Exponent(Object base, Object exp) implements Expression {
        @Override
        public double evaluate() {
            Objects.requireNonNull(base);
            Objects.requireNonNull(exp);
            LOGGER.trace("Calculating {} raised to the {} power", base, exp);
            return Math.pow(objectToDouble(base), objectToDouble(exp));
        }
    }

    record Addition(Object firstOperand, Object secondOperand) implements Expression {
        @Override
        public double evaluate() {
            Objects.requireNonNull(firstOperand);
            Objects.requireNonNull(secondOperand);
            LOGGER.trace("Calculating the sum of {} and {}", firstOperand, secondOperand);
            return objectToDouble(firstOperand) + objectToDouble(secondOperand);
        }
    }

    record Multiplication(Object firstOperand, Object secondOperand) implements Expression {
        @Override
        public double evaluate() {
            Objects.requireNonNull(firstOperand);
            Objects.requireNonNull(secondOperand);
            LOGGER.trace("Calculating multiplication of {} and {}", firstOperand, secondOperand);
            return objectToDouble(firstOperand) * objectToDouble(secondOperand);
        }
    }
}

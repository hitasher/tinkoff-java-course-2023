package edu.hw2.task1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public sealed interface Expression {
    Logger LOGGER = LogManager.getLogger();

    double evaluate();

    record Constant(double value) implements Expression {
        public Constant(Expression expression) {
            this(expression.evaluate());
        }

        @Override
        public double evaluate() {
            return value;
        }
    }

    record Negate(double value) implements Expression {
        public Negate(Expression expression) {
            this(expression.evaluate());
        }

        @Override
        public double evaluate() {
            return -value;
        }
    }

    record Exponent(double base, double power) implements Expression {
        public Exponent(double base, Expression power) {
            this(base, power.evaluate());
        }

        public Exponent(Expression base, double power) {
            this(base.evaluate(), power);
        }

        public Exponent(Expression base, Expression power) {
            this(base.evaluate(), power.evaluate());
        }

        @Override
        public double evaluate() {
            return Math.pow(base, power);
        }
    }

    record Addition(double firstOperand, double secondOperand) implements Expression {
        public Addition(double firstOperand, Expression secondOperand) {
            this(firstOperand, secondOperand.evaluate());
        }

        public Addition(Expression firstOperand, double secondOperand) {
            this(firstOperand.evaluate(), secondOperand);
        }

        public Addition(Expression firstOperand, Expression secondOperand) {
            this(firstOperand.evaluate(), secondOperand.evaluate());
        }

        @Override
        public double evaluate() {
            return firstOperand + secondOperand;
        }
    }

    record Multiplication(double firstOperand, double secondOperand) implements Expression {
        public Multiplication(double firstOperand, Expression secondOperand) {
            this(firstOperand, secondOperand.evaluate());
        }

        public Multiplication(Expression firstOperand, double secondOperand) {
            this(firstOperand.evaluate(), secondOperand);
        }

        public Multiplication(Expression firstOperand, Expression secondOperand) {
            this(firstOperand.evaluate(), secondOperand.evaluate());
        }


        @Override
        public double evaluate() {
            return firstOperand * secondOperand;
        }
    }
}

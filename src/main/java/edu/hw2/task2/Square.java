package edu.hw2.task2;

public class Square extends Rectangle {
    public Square(int sideLength) {
        super(sideLength, sideLength);
    }

    public Square setSizeLength(int sizeLength) {
        return new Square(sizeLength);
    }
}

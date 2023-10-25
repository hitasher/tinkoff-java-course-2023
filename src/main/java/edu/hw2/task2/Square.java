package edu.hw2.task2;

public class Square extends Rectangle {
    public Square(int side) {
        super(side, side);
    }

    public Square setSize(int size) {
        return new Square(size);
    }
}

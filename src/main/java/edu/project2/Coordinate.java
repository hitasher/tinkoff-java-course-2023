package edu.project2;

public record Coordinate(int rowIndex, int columnIndex) {
    private final static int STEP = 2;

    public Coordinate getCoordinateToTheRight() {
        return new Coordinate(rowIndex, columnIndex + STEP);
    }

    public Coordinate getCoordinateToTheLeft() {
        return new Coordinate(rowIndex, columnIndex - STEP);
    }

    public Coordinate getCoordinateToTheTop() {
        return new Coordinate(rowIndex - STEP, columnIndex);
    }

    public Coordinate getCoordinateToTheBottom() {
        return new Coordinate(rowIndex + STEP, columnIndex);
    }
}

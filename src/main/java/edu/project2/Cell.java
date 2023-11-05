package edu.project2;

public record Cell(Coordinate coordinate, Type type) {

    public Cell(int rowIndex, int columnIndex, Type type) {
        this(new Coordinate(rowIndex, columnIndex), type);
    }

    public enum Type {
        WALL,
        EMPTY
    }
}

package edu.project2;


public class MazeBuilder {
    private final int width;
    private final int height;
    private final Cell[][] grid;

    public MazeBuilder(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new Cell[height][width];
        for (int rowIndex = 0; rowIndex < height; ++rowIndex) {
            for (int columnIndex = 0; columnIndex < width; ++columnIndex) {
                grid[rowIndex][columnIndex] = new Cell(rowIndex, columnIndex, Cell.Type.EMPTY);
            }
        }
    }

    public Maze build() {
        return new Maze(grid);
    }

    public void setCeilTypeAt(int rowIndex, int columnIndex, Cell.Type type) {
        if (rowIndex < 0 || rowIndex >= height || columnIndex < 0 || columnIndex >= width) {
            throw new IllegalArgumentException("Coordinate is out of maze range");
        }

        grid[rowIndex][columnIndex] = new Cell(rowIndex, columnIndex, type);
    }
}

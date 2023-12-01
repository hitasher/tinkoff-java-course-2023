package edu.project2;

public final class Maze {

    private final static int MIN_MAZE_SIZE = 3;

    private final static int EMPTY_CELL_VALUE = 0;
    private final static int WALL_CELL_VALUE = 1;

    private final int width;
    private final int height;
    private final Cell[][] grid;

    public Maze(Cell[][] grid) {
        this.width = grid[0].length;
        this.height = grid.length;
        assertMazeSize(width, height);
        this.grid = grid;
    }

    public Maze(int[][] grid) {
        width = grid[0].length;
        height = grid.length;
        assertMazeSize(width, height);
        this.grid = new Cell[height][width];
        for (int rowIndex = 0; rowIndex < height; ++rowIndex) {
            for (int columnIndex = 0; columnIndex < width; ++columnIndex) {
                if (grid[rowIndex][columnIndex] == EMPTY_CELL_VALUE) {
                    this.grid[rowIndex][columnIndex] = new Cell(rowIndex, columnIndex, Cell.Type.EMPTY);
                } else if (grid[rowIndex][columnIndex] == WALL_CELL_VALUE) {
                    this.grid[rowIndex][columnIndex] = new Cell(rowIndex, columnIndex, Cell.Type.WALL);
                } else {
                    throw new IllegalArgumentException("Grid must contain only 0 and 1");
                }
            }
        }
    }

    private static void assertMazeSize(int width, int height) {
        if (width < MIN_MAZE_SIZE || width % 2 != 1 || height < MIN_MAZE_SIZE || height % 2 != 1) {
            throw new IllegalArgumentException(
                "Width and height of maze must be odd and greater or equal to " + MIN_MAZE_SIZE
            );
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private void assertCoordinateInMaze(Coordinate coordinate) {
        if (isCoordinateOutOfMaze(coordinate)) {
            throw new IllegalArgumentException("Coordinate is out of maze range");
        }
    }

    public boolean isCoordinateOutOfMaze(Coordinate coordinate) {
        int rowIndex = coordinate.rowIndex();
        int columnIndex = coordinate.columnIndex();
        return rowIndex < 0 || rowIndex >= height || columnIndex < 0 || columnIndex >= width;
    }

    public Cell.Type getCeilTypeAt(int rowIndex, int columnIndex) {
        Coordinate coordinate = new Coordinate(rowIndex, columnIndex);
        assertCoordinateInMaze(coordinate);
        return grid[rowIndex][columnIndex].type();
    }

    private boolean isThereWallAt(Coordinate coordinate) {
        assertCoordinateInMaze(coordinate);
        return grid[coordinate.rowIndex()][coordinate.columnIndex()].type() == Cell.Type.WALL;
    }

    public boolean isThereWallToTheRightOf(Coordinate coordinate) {
        Coordinate newCoordinate = new Coordinate(coordinate.rowIndex(), coordinate.columnIndex() + 1);
        assertCoordinateInMaze(newCoordinate);
        return isThereWallAt(newCoordinate);
    }

    public boolean isThereWallToTheLeftOf(Coordinate coordinate) {
        Coordinate newCoordinate = new Coordinate(coordinate.rowIndex(), coordinate.columnIndex() - 1);
        assertCoordinateInMaze(newCoordinate);
        return isThereWallAt(newCoordinate);
    }

    public boolean isThereWallBelow(Coordinate coordinate) {
        Coordinate newCoordinate = new Coordinate(coordinate.rowIndex() + 1, coordinate.columnIndex());
        assertCoordinateInMaze(newCoordinate);
        return isThereWallAt(newCoordinate);
    }

    public boolean isThereWallAbove(Coordinate coordinate) {
        Coordinate newCoordinate = new Coordinate(coordinate.rowIndex() - 1, coordinate.columnIndex());
        assertCoordinateInMaze(newCoordinate);
        return isThereWallAt(newCoordinate);
    }
}

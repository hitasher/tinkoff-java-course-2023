package edu.project2.renderers;

import edu.project2.Coordinate;
import edu.project2.Maze;
import java.util.List;

public class DefaultRenderer implements Renderer {

    private final static int WIDTH = 2;

    private final static char PATH_CHAR = '#';
    private final static char EMPTY_CELL_CHAR = ' ';
    private final static char WALL_CELL_CHAR = '█';

    @Override
    public String render(Maze maze) {
        return putWallsFrom(maze).toString();
    }

    @Override
    public String render(Maze maze, List<Coordinate> path) {
        StringBuilder renderedMaze = putWallsFrom(maze);
        for (Coordinate coordinate : path) {
            int index = (maze.getWidth() * coordinate.rowIndex() * WIDTH
                + coordinate.rowIndex() + coordinate.columnIndex() * WIDTH);
            for (int i = 0; i < WIDTH; ++i) {
                renderedMaze.setCharAt(index + i, PATH_CHAR);
            }
        }
        return renderedMaze.toString();
    }

    private static StringBuilder putWallsFrom(Maze maze) {
        StringBuilder renderedMaze = new StringBuilder();
        for (int rowIndex = 0; rowIndex < maze.getHeight(); ++rowIndex) {
            for (int columnIndex = 0; columnIndex < maze.getWidth(); ++columnIndex) {
                switch (maze.getCeilTypeAt(rowIndex, columnIndex)) {
                    case EMPTY -> renderedMaze.append(
                        new String(new char[WIDTH]).replace('\0', EMPTY_CELL_CHAR)
                    );
                    case WALL -> renderedMaze.append(
                        new String(new char[WIDTH]).replace('\0', WALL_CELL_CHAR)
                    );
                    default -> throw new IllegalStateException(
                        "Unexpected value: " + maze.getCeilTypeAt(rowIndex, columnIndex)
                    );
                }
            }
            renderedMaze.append('\n');
        }
        return renderedMaze;
    }
}

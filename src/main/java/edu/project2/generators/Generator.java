package edu.project2.generators;

import edu.project2.Cell;
import edu.project2.Maze;
import edu.project2.MazeBuilder;

public interface Generator {
    Maze generate(int width, int height);

    static MazeBuilder fillEverythingWithWalls(int width, int height) {
        MazeBuilder mazeBuilder = new MazeBuilder(width, height);
        for (int rowIndex = 0; rowIndex < height; rowIndex += 2) {
            for (int columnIndex = 0; columnIndex < width; ++columnIndex) {
                mazeBuilder.setCeilTypeAt(rowIndex, columnIndex, Cell.Type.WALL);
            }
        }
        for (int columnIndex = 0; columnIndex < width; columnIndex += 2) {
            for (int rowIndex = 0; rowIndex < height; ++rowIndex) {
                mazeBuilder.setCeilTypeAt(rowIndex, columnIndex, Cell.Type.WALL);
            }
        }
        return mazeBuilder;
    }
}

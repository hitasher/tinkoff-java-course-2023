package edu.project2.generators;

import edu.project2.Cell;
import edu.project2.Maze;
import edu.project2.MazeBuilder;

public class BinaryTreeGenerator implements Generator {

    @Override
    @SuppressWarnings("checkstyle:MagicNumber")
    public Maze generate(int width, int height) {
        MazeBuilder mazeBuilder = Generator.fillEverythingWithWalls(width, height);

        for (int columnIndex = 1; columnIndex < width - 1; ++columnIndex) {
            mazeBuilder.setCeilTypeAt(1, columnIndex, Cell.Type.EMPTY);
        }
        for (int rowIndex = 1; rowIndex < height - 1; ++rowIndex) {
            mazeBuilder.setCeilTypeAt(rowIndex, width - 2, Cell.Type.EMPTY);
        }

        for (int rowIndex = 3; rowIndex < height; rowIndex += 2) {
            for (int columnIndex = 1; columnIndex < width - 2; columnIndex += 2) {
                if (Math.random() < 0.5) {
                    mazeBuilder.setCeilTypeAt(rowIndex - 1, columnIndex, Cell.Type.EMPTY);
                } else {
                    mazeBuilder.setCeilTypeAt(rowIndex, columnIndex + 1, Cell.Type.EMPTY);
                }
            }
        }

        return mazeBuilder.build();
    }
}

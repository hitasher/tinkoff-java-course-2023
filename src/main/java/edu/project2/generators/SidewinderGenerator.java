package edu.project2.generators;

import edu.project2.Cell;
import edu.project2.Maze;
import edu.project2.MazeBuilder;
import java.util.Random;

public class SidewinderGenerator implements Generator {

    private final static Random RANDOM = new Random();

    @Override
    @SuppressWarnings("checkstyle:MagicNumber")
    public Maze generate(int width, int height) {
        MazeBuilder mazeBuilder = Generator.fillEverythingWithWalls(width, height);

        for (int columnIndex = 2; columnIndex < width - 2; columnIndex += 2) {
            mazeBuilder.setCeilTypeAt(1, columnIndex, Cell.Type.EMPTY);
        }

        for (int rowIndex = 3; rowIndex < height; rowIndex += 2) {
            int startColumnIndex = 1;
            for (int endColumnIndex = 1; endColumnIndex < width; endColumnIndex += 2) {
                if (endColumnIndex == width - 2 || Math.random() < 0.5) {
                    int columnIndex = RANDOM.nextInt((endColumnIndex - startColumnIndex) + 1) + startColumnIndex;
                    if (columnIndex % 2 != 1) {
                        --columnIndex;
                    }
                    mazeBuilder.setCeilTypeAt(rowIndex - 1, columnIndex, Cell.Type.EMPTY);
                    startColumnIndex = endColumnIndex + 2;
                } else {
                    mazeBuilder.setCeilTypeAt(rowIndex, endColumnIndex + 1, Cell.Type.EMPTY);
                }
            }
        }

        return mazeBuilder.build();
    }
}

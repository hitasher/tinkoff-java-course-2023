package edu.hw1;

import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task8 {
    private final static Logger LOGGER = LogManager.getLogger();

    private final static int BOARD_SIZE = 8;

    private final static int[][] KNIGHT_MOVES = {
        {-1, -2},
        {-1, 2},
        {1, -2},
        {1, 2},
        {-2, -1},
        {-2, 1},
        {2, -1},
        {2, 1}
    };

    private Task8() {
    }

    public static boolean knightBoardCapture(int[][] board) {
        Objects.requireNonNull(board);
        checkBoardSize(board);
        LOGGER.trace("Processing a board {}", (Object) board);
        for (int rowIndex = 0; rowIndex < BOARD_SIZE; ++rowIndex) {
            for (int columnIndex = 0; columnIndex < BOARD_SIZE; ++columnIndex) {
                if (board[rowIndex][columnIndex] == 1 && doesKnightCaptureOther(board, rowIndex, columnIndex)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void checkBoardSize(int[][] board) {
        if (board.length != BOARD_SIZE) {
            throw new IllegalArgumentException("Number of rows must be " + BOARD_SIZE);
        }
        for (int[] row : board) {
            Objects.requireNonNull(row);
            if (row.length != BOARD_SIZE) {
                throw new IllegalArgumentException("Each row must have " + BOARD_SIZE + "elements");
            }
        }
    }

    private static boolean doesKnightCaptureOther(int[][] board, int rowIndex, int columnIndex) {
        for (int[] knightMove : KNIGHT_MOVES) {
            int newRowIndex = rowIndex + knightMove[0];
            int newColumnIndex = columnIndex + knightMove[1];
            if (areCoordinatesInsideBoard(newRowIndex, newColumnIndex) && board[newRowIndex][newColumnIndex] == 1) {
                return true;
            }
        }
        return false;
    }

    private static boolean areCoordinatesInsideBoard(int rowIndex, int columnIndex) {
        return 0 <= rowIndex && rowIndex < BOARD_SIZE && 0 <= columnIndex && columnIndex < BOARD_SIZE;
    }
}

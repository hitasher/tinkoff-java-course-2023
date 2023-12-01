package edu.project2.solvers;

import edu.project2.Coordinate;
import edu.project2.Maze;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface Solver {
    List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end);

    static void assertStartAndEnd(Maze maze, Coordinate start, Coordinate end) {
        if (start.rowIndex() % 2 != 1
            || start.columnIndex() % 2 != 1
            || end.rowIndex() % 2 != 1
            || end.columnIndex() % 2 != 1
            || maze.isCoordinateOutOfMaze(start)
            || maze.isCoordinateOutOfMaze(end)
        ) {
            throw new IllegalArgumentException("Start and end must be odd and located in provided maze");
        }
    }

    static Set<Coordinate> getCoordinateNeighborsInMaze(Coordinate coordinate, Maze maze) {
        Set<Coordinate> coordinateNeighbors = new HashSet<>();
        if (!maze.isThereWallToTheRightOf(coordinate)) {
            coordinateNeighbors.add(coordinate.getCoordinateToTheRight());
        }
        if (!maze.isThereWallToTheLeftOf(coordinate)) {
            coordinateNeighbors.add(coordinate.getCoordinateToTheLeft());
        }
        if (!maze.isThereWallBelow(coordinate)) {
            coordinateNeighbors.add(coordinate.getCoordinateToTheBottom());
        }
        if (!maze.isThereWallAbove(coordinate)) {
            coordinateNeighbors.add(coordinate.getCoordinateToTheTop());
        }
        return coordinateNeighbors;
    }
}

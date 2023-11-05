package edu.project2.solvers;

import edu.project2.Coordinate;
import edu.project2.Maze;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

public class DepthFirstSolver implements Solver {

    private final static Random RANDOM = new Random();

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        Solver.assertStartAndEnd(maze, start, end);

        Set<Coordinate> visitedCoordinates = new HashSet<>();
        Stack<Coordinate> coordinateStack = new Stack<>();

        Coordinate coordinate = start;
        visitedCoordinates.add(start);

        while (!coordinate.equals(end)) {
            Set<Coordinate> coordinateNeighbors = Solver.getCoordinateNeighborsInMaze(coordinate, maze);
            List<Coordinate> notVisitedCoordinateNeighbors = coordinateNeighbors.stream()
                .filter(neighbor -> !visitedCoordinates.contains(neighbor))
                .toList();
            if (notVisitedCoordinateNeighbors.isEmpty()) {
                if (coordinateStack.empty()) {
                    throw new RuntimeException("There is no path from start to end");
                }
                coordinate = coordinateStack.pop();
            } else {
                coordinateStack.push(coordinate);
                coordinate = notVisitedCoordinateNeighbors
                    .get(RANDOM.nextInt(notVisitedCoordinateNeighbors.size()));
                visitedCoordinates.add(coordinate);
            }
        }

        List<Coordinate> solution = new ArrayList<>();
        solution.add(coordinate);
        while (!coordinateStack.empty()) {
            Coordinate previousCoordinate = coordinateStack.pop();
            Coordinate middleCoordinate = new Coordinate(
                (coordinate.rowIndex() + previousCoordinate.rowIndex()) / 2,
                (coordinate.columnIndex() + previousCoordinate.columnIndex()) / 2
            );
            solution.add(middleCoordinate);
            solution.add(previousCoordinate);
            coordinate = previousCoordinate;
        }

        return solution.reversed();
    }
}

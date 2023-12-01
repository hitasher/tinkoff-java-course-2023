package edu.project2.solvers;

import edu.project2.Coordinate;
import edu.project2.Maze;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class LeeSolver implements Solver {
    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        Solver.assertStartAndEnd(maze, start, end);

        int[][] grid = new int[maze.getHeight()][maze.getWidth()];
        Queue<Coordinate> coordinateQueue = new LinkedList<>();
        coordinateQueue.add(start);

        grid[start.rowIndex()][start.columnIndex()] = 1;
        while (grid[end.rowIndex()][end.columnIndex()] == 0 && !coordinateQueue.isEmpty()) {
            Coordinate coordinate = coordinateQueue.poll();
            int d = grid[coordinate.rowIndex()][coordinate.columnIndex()];
            Set<Coordinate> coordinateNeighbors = Solver.getCoordinateNeighborsInMaze(coordinate, maze);
            for (Coordinate neighbor : coordinateNeighbors) {
                if (grid[neighbor.rowIndex()][neighbor.columnIndex()] == 0) {
                    grid[neighbor.rowIndex()][neighbor.columnIndex()] = d + 1;
                    coordinateQueue.add(neighbor);
                }
            }
        }

        if (grid[end.rowIndex()][end.columnIndex()] == 0) {
            throw new RuntimeException("There is no path from start to end");
        }

        List<Coordinate> solution = new ArrayList<>();
        solution.add(end);
        Coordinate coordinate = end;
        while (!coordinate.equals(start)) {
            Set<Coordinate> coordinateNeighbors = Solver.getCoordinateNeighborsInMaze(coordinate, maze);
            int d = grid[coordinate.rowIndex()][coordinate.columnIndex()];
            Coordinate previousCoordinate = coordinateNeighbors.stream()
                .filter(neighbor -> grid[neighbor.rowIndex()][neighbor.columnIndex()] == d - 1)
                .findFirst()
                .orElseThrow(RuntimeException::new);
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

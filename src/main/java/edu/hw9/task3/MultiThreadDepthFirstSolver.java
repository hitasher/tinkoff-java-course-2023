package edu.hw9.task3;

import edu.project2.Coordinate;
import edu.project2.Maze;
import edu.project2.solvers.Solver;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import org.jetbrains.annotations.NotNull;

public class MultiThreadDepthFirstSolver implements Solver {
    private Maze maze;
    private boolean[][] visitedCells;

    @Override
    public synchronized List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        Solver.assertStartAndEnd(maze, start, end);

        visitedCells = new boolean[maze.getHeight()][maze.getWidth()];
        this.maze = maze;

        ForkJoinPool pool = new ForkJoinPool();
        List<Coordinate> coordinates = pool.invoke(new RecursiveSolverTask(start, end));
        return processCoordinates(coordinates);
    }

    @NotNull private List<Coordinate> processCoordinates(List<Coordinate> coordinates) {
        List<Coordinate> result = new ArrayList<>();
        result.add(coordinates.get(0));
        for (int i = 1; i < coordinates.size(); ++i) {
            Coordinate coordinate = coordinates.get(i);
            Coordinate previousCoordinate = coordinates.get(i - 1);
            result.add(new Coordinate(
                (coordinate.rowIndex() + previousCoordinate.rowIndex()) / 2,
                (coordinate.columnIndex() + previousCoordinate.columnIndex()) / 2
            ));
            result.add(coordinate);
        }
        return result;
    }

    private final class RecursiveSolverTask extends RecursiveTask<List<Coordinate>> {

        RecursiveSolverTask(Coordinate start, Coordinate end) {
            this.start = start;
            this.end = end;
        }

        private final Coordinate start;
        private final Coordinate end;

        @Override
        protected List<Coordinate> compute() {
            // Not needed to synchronize with visitedCells or maze, because maze is ideal
            // i.e. We have only one path from start to end, and we will not produce cell crossing
            visitedCells[start.rowIndex()][start.columnIndex()] = true;
            List<Coordinate> coordinates = new ArrayList<>();
            if (start.equals(end)) {
                coordinates.add(end);
                return coordinates;
            }
            List<ForkJoinTask<List<Coordinate>>> subTasks = createNeededTasks();
            for (ForkJoinTask<List<Coordinate>> subTask : subTasks) {
                List<Coordinate> subTaskResult = subTask.join();
                if (!subTaskResult.isEmpty()) {
                    coordinates.add(start);
                    coordinates.addAll(subTaskResult);
                }
            }
            return coordinates;

        }

        private List<ForkJoinTask<List<Coordinate>>> createNeededTasks() {
            List<ForkJoinTask<List<Coordinate>>> tasks = new ArrayList<>();
            for (Coordinate neighbor : Solver.getCoordinateNeighborsInMaze(start, maze)) {
                if (visitedCells[neighbor.rowIndex()][neighbor.columnIndex()]) {
                    continue;
                }
                tasks.add(new RecursiveSolverTask(neighbor, end).fork());
            }
            return tasks;
        }
    }

}

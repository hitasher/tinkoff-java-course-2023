package edu.hw9.task3;

import edu.project2.Coordinate;
import edu.project2.Maze;
import edu.project2.solvers.Solver;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class TestMultiThreadDepthFirstSolver {

    private final static Solver SOLVER = new MultiThreadDepthFirstSolver();

    @Test
    void solve_ShouldReturnExpectedPath() {
        Maze maze = new Maze(new int[][]{
            {1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1},
            {1, 0, 1, 1, 1},
            {1, 0, 0, 0, 1},
            {1, 1, 1, 1, 1}
        });
        Coordinate start = new Coordinate(1, 1);
        Coordinate end = new Coordinate(3, 3);
        List<Coordinate> actualPath = SOLVER.solve(maze, start, end);
        List<Coordinate> expectedPath = List.of(
            new Coordinate(1, 1),
            new Coordinate(2, 1),
            new Coordinate(3, 1),
            new Coordinate(3, 2),
            new Coordinate(3, 3)
        );
        assertThat(actualPath).isEqualTo(expectedPath);
    }

    @Test
    void solve_ShouldThrowRuntimeException() {
        Maze maze = new Maze(new int[][]{
            {1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1},
            {1, 0, 1, 1, 1},
            {1, 0, 1, 0, 1},
            {1, 1, 1, 1, 1}
        });
        Coordinate start = new Coordinate(1, 1);
        Coordinate end = new Coordinate(3, 3);
        assertThatThrownBy(
            () -> SOLVER.solve(maze, start, end)
        ).isInstanceOf(RuntimeException.class);
    }
}

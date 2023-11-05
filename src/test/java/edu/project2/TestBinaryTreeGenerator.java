package edu.project2;

import edu.project2.generators.BinaryTreeGenerator;
import edu.project2.generators.Generator;
import edu.project2.solvers.LeeSolver;
import edu.project2.solvers.Solver;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TestBinaryTreeGenerator {

    private final static Generator GENERATOR = new BinaryTreeGenerator();
    private final static Solver SOLVER = new LeeSolver();

    private static Stream<Arguments> generate_ShouldGenerateSolvableMaze() {
        return Stream.of(
            Arguments.of(3, 3),
            Arguments.of(5, 5),
            Arguments.of(7, 19),
            Arguments.of(31, 15),
            Arguments.of(101, 151)
        );
    }


    @ParameterizedTest
    @MethodSource
    void generate_ShouldGenerateSolvableMaze(int width, int height) {
        Maze maze = GENERATOR.generate(width, height);
        Coordinate start = new Coordinate(1, 1);
        Coordinate end = new Coordinate(height - 2, width - 2);
        assertDoesNotThrow(
            () -> SOLVER.solve(maze, start, end)
        );
    }

    private static Stream<Arguments> generate_ShouldThrowIllegalArgumentException() {
        return Stream.of(
            Arguments.of(4, 3),
            Arguments.of(5, 4),
            Arguments.of(6, 6),
            Arguments.of(2, 7),
            Arguments.of(7, 2),
            Arguments.of(2, 2),
            Arguments.of(0, 3),
            Arguments.of(3, 0)
        );
    }


    @ParameterizedTest
    @MethodSource
    void generate_ShouldThrowIllegalArgumentException(int width, int height) {
        assertThatThrownBy(
            () -> GENERATOR.generate(width, height)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}

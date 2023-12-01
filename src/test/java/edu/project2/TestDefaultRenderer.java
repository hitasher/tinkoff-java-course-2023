package edu.project2;

import edu.project2.renderers.DefaultRenderer;
import edu.project2.renderers.Renderer;
import edu.project2.solvers.LeeSolver;
import edu.project2.solvers.Solver;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestDefaultRenderer {
    @Test
    void render_ShouldReturnExpectedString() {
        Maze maze = new Maze(new int[][]{
            {1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 0, 1, 1, 1},
            {1, 0, 0, 0, 1, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1},
        });
        Renderer renderer = new DefaultRenderer();
        assertThat(renderer.render(maze)).isEqualTo(
                """
                  ██████████████████
                  ██              ██
                  ██  ██████  ██  ██
                  ██      ██  ██  ██
                  ██  ██████████████
                  ██              ██
                  ██  ██████  ██████
                  ██      ██      ██
                  ██████████████████
                        """
        );
        Solver solver = new LeeSolver();
        Coordinate start = new Coordinate(1, 1);
        Coordinate end = new Coordinate(7, 7);
        List<Coordinate> path = solver.solve(maze, start, end);
        assertThat(renderer.render(maze, path)).isEqualTo(
                """
                  ██████████████████
                  ██##            ██
                  ██##██████  ██  ██
                  ██##    ██  ██  ██
                  ██##██████████████
                  ██##########    ██
                  ██  ██████##██████
                  ██      ██######██
                  ██████████████████
                        """
        );
    }
}

package edu.project2;

import edu.project2.generators.Generator;
import edu.project2.generators.SidewinderGenerator;
import edu.project2.renderers.DefaultRenderer;
import edu.project2.renderers.Renderer;
import edu.project2.solvers.LeeSolver;
import edu.project2.solvers.Solver;
import java.util.List;


public final class Main {

    private final static int WIDTH = 15;
    private final static int HEIGHT = 15;

    private final static Coordinate START = new Coordinate(1, 1);
    private final static Coordinate END = new Coordinate(13, 13);

    private final static Generator GENERATOR = new SidewinderGenerator();
    private final static Solver SOLVER = new LeeSolver();
    private final static Renderer RENDERER = new DefaultRenderer();

    private Main() {
    }

    @SuppressWarnings({"checkstyle:RegexpSinglelineJava", "checkstyle:UncommentedMain"})
    public static void main(String[] args) {
        Maze maze = GENERATOR.generate(WIDTH, HEIGHT);
        List<Coordinate> path = SOLVER.solve(maze, START, END);

        System.out.println("Generated maze:");
        System.out.println(RENDERER.render(maze));
        System.out.println("Path in the maze:");
        System.out.println(RENDERER.render(maze, path));
    }
}

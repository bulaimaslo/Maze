package maze;

import java.io.IOException;
import java.util.Scanner;

import static maze.DFS_solver.solve;
import static maze.Menu.*;


public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean mazeGenerated = false;
        int response = 1;
        Maze maze = null;

        while (response != 0) {
            printMenu(mazeGenerated);
            response = sc.nextInt();

            switch (response) {
                case 1:
                    maze = generateMaze();
                    System.out.println(maze);
                    mazeGenerated = true;
                    break;
                case 2:
                    maze = loadMazeFromFile();
                    mazeGenerated = true;
                    break;
                case 3:
                    assert maze != null;
                    saveMaze(maze);
                    break;
                case 4:
                    System.out.println(maze);
                    break;
                case 5:
                    assert maze != null;
                    solve(maze);
                    break;
                case 0:
                    System.out.println("See ya!");
                    break;
                default:
                    System.out.println("Invalid menu choice. Try again.");
            }
        }
    }
}
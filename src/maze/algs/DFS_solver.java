package maze.algs;

import maze.CellValue;
import maze.Maze;

public class DFS_solver extends Solver {

    public static void solve(Maze maze) {
        CellValue[][] path = new CellValue[maze.getHeight()][maze.getWidth()];

        if (solveMaze(path, maze, maze.getEntry().getRow(), maze.getEntry().getCol()))
            printSolution(path, maze);
        else
            System.out.println("No solution");
    }

    private static boolean solveMaze(CellValue[][] path, Maze maze, int row, int col) {
        if (Solver.foundExit(row, col, maze, path)) {
            return true;
        }

        if (Solver.validCell(row, col, maze, path)) {
            path[row][col] = CellValue.PATH;

            for (int[] dir : DIRECTIONS) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];
                if (solveMaze(path, maze, newRow, newCol)) {
                    return true;
                }
            }

            path[row][col] = CellValue.EMPTY;
            return false;
        }

        return false;
    }
}

package maze.algs;

import maze.CellValue;
import maze.Maze;

public abstract class Solver {
    static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static boolean validCell(int row, int col, Maze maze, CellValue[][] path) {
        return maze.inBoundaryIncludingBorder(row, col)
                && path[row][col] != CellValue.VISITED
                && path[row][col] != CellValue.PATH
                && !maze.isWall(row, col);
    }

    public static void printSolution(CellValue[][] path, Maze maze) {
        int height = maze.getHeight();
        int width = maze.getWidth();
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (path[row][col] == CellValue.PATH) {
                    System.out.print(Maze.PATH);
                } else {
                    System.out.print(maze.getCellSymbol(row, col));
                }
            }
            System.out.print("\n");
        }
    }

    static boolean foundExit(int row, int col, Maze maze, CellValue[][] path) {
        int exitRow = maze.getExit().getRow();
        int exitCol = maze.getExit().getCol();

        if (row == exitRow && col == exitCol) {
            path[row][col] = CellValue.PATH;
            return true;
        } else {
            return false;
        }
    }
}

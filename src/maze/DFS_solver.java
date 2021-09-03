package maze;

public class DFS_solver {

    public static void solve(Maze maze) {
        boolean[][] path = new boolean[maze.getHeight()][maze.getWidth()];

        if (solveMaze(path, maze, maze.getEntry().getRow(), maze.getEntry().getCol()))
            printSolution(path, maze);
        else
            System.out.println("No solution");
    }

    public static void printSolution(boolean[][] path, Maze maze) {
        int height = maze.getHeight();
        int width = maze.getWidth();
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (path[row][col]) {
                    System.out.print(Maze.PATH);
                } else {
                    System.out.print(maze.getCellSymbol(row, col));
                }
            }
            System.out.print("\n");
        }
    }


    private static boolean solveMaze(boolean[][] path, Maze maze, int row, int col) {
        if (foundExit(row, col, maze, path)) {
            return true;
        }

        if (validCell(row, col, maze, path)) {
            path[row][col] = true;
            if (solveMaze(path, maze, row + 1, col)) {
                return true;
            }
            if (solveMaze(path, maze, row, col + 1)) {
                return true;
            }
            if (solveMaze(path, maze, row - 1, col)) {
                return true;
            }
            if (solveMaze(path, maze, row, col - 1)) {
                return true;
            }

            path[row][col] = false;
            return false;
        }
        return false;
    }

    private static boolean foundExit(int row, int col, Maze maze, boolean[][] path) {
        if ((row == maze.getExit().getRow()) && (col == maze.getExit().getCol())) {
            path[row][col] = true;
            return true;
        } else {
            return false;
        }
    }

    private static boolean validCell(int row, int col, Maze maze, boolean[][] path) {
        return maze.inBoundary(row, col)
                && !path[row][col]
                && !maze.isWall(row, col);
    }

}

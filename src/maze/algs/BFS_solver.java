package maze.algs;

import maze.CellValue;
import maze.Maze;
import maze.Point;

import java.util.LinkedList;
import java.util.Queue;


public class BFS_solver extends Solver {
    public static void solve(Maze maze) {
        CellValue[][] path = new CellValue[maze.getHeight()][maze.getWidth()];

        if (solveMaze(path, maze)) {
            printSolution(path, maze);
        } else {
            System.out.println("No solution");
        }
    }

    public static boolean solveMaze(CellValue[][] path, Maze maze) {
        Point entry = maze.getEntry();
        Point exit = maze.getExit();

        Queue<Point> q = new LinkedList<>();
        q.add(entry);
        path[entry.getRow()][entry.getCol()] = CellValue.PATH;

        while (!q.isEmpty()) {
            Point point = q.remove();

            if (point.equals(exit)) {
                drawExitPath(path, point);
                return true;
            }

            for (int[] dir : DIRECTIONS) {
                int newRow = point.getRow() + dir[0];
                int newCol = point.getCol() + dir[1];
                if (validCell(newRow, newCol, maze, path)) {
                    path[newRow][newCol] = CellValue.VISITED;
                    q.add(new Point(newRow, newCol, point));
                }
            }
        }
        return false;
    }

    private static void drawExitPath(CellValue[][] path, Point point) {
        while (point.getParent() != null) {
            path[point.getRow()][point.getCol()] = CellValue.PATH;
            point = point.getParent();
        }
    }
}

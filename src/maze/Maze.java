package maze;

import java.util.ArrayList;
import java.util.Random;


public class Maze {
    final public static String WALL = "\u2588\u2588";
    final public static String EMPTY = "  ";
    final public static String PATH = "//";
    final private int height;
    final private int width;
    final private Point[][] maze;
    final private int matrixHeight;
    final private int matrixWidth;
    final private int matrixSize;
    final private ArrayList<Edge> edges = new ArrayList<>();
    final private ArrayList<Edge> result = new ArrayList<>();
    final private Random seed = new Random();
    private Point entry;
    private Point exit;

    public Maze(int height, int width) {
        this.height = height;
        this.width = width;
        this.maze = new Point[height][width];
        this.matrixHeight = height % 2 == 0 ? (this.height - 1) / 2 : this.height / 2;
        this.matrixWidth = width % 2 == 0 ? (this.width - 1) / 2 : this.width / 2;
        this.matrixSize = matrixHeight * matrixWidth;
        fillMazeWithPoints();
    }

    private void fillMazeWithPoints() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                maze[row][col] = new Point(row, col);
            }
        }
    }


    public void setMaze(String mazeString) {
        char[] chars = mazeString.toCharArray();
        int row = 0;
        int col = 0;
        boolean secondChar = false;

        for (char ch : chars) {
            if (ch == '\n') {
                row++;
                col = 0;
                secondChar = false;
                continue;
            } else if (secondChar) {
                secondChar = false;
                continue;
            } else if (ch == WALL.charAt(0)) {
                maze[row][col % width].setCellValue(CellValue.WALL);
                col++;
            } else if (ch == EMPTY.charAt(0)) {
                maze[row][col % width].setCellValue(CellValue.EMPTY);
                col++;
            } else {
                System.out.println("Wrong char!");
            }
            secondChar = true;
        }

        findEntrance();
        findExit();
    }

    private void findEntrance() {
        for (int row = 0; row < this.height; row++) {
            if (maze[row][0].getCellValue() == CellValue.EMPTY) {
                this.entry = new Point(row, 0);
            }
        }
    }

    private void findExit() {
        for (int row = 0; row < this.height; row++) {
            if (maze[row][width - 1].getCellValue() == CellValue.EMPTY) {
                this.exit = new Point(row, width - 1);
            }
        }
    }

    public void setMaze() {
        generateEdges();
        edges.sort(Edge::compareTo);
        addToResults();
        connectEdges();
        createEntrance();
        createExit();
    }

    private void generateEdges() {
        for (int i = 0; i < matrixSize; i++) {
            if ((i + 1) % matrixWidth != 0) {
                edges.add(new Edge(i, i + 1, seed.nextInt(99) + 1));
            }
            if (i + matrixWidth < matrixSize) {
                edges.add(new Edge(i, i + matrixWidth, seed.nextInt(99) + 1));
            }
        }
    }

    private void connectEdges() {
        for (Edge edge : result) {
            int y1 = edge.point1 / matrixWidth * 2 + 1;
            int x1 = (edge.point1 % matrixWidth) * 2 + 1;
            int y2 = edge.point2 / matrixWidth * 2 + 1;
            int x2 = (edge.point2 % matrixWidth) * 2 + 1;
            int y = y1 == y2 ? y1 : (y1 + y2) / 2;
            int x = x1 == x2 ? x1 : (x1 + x2) / 2;
            maze[y][x].setCellValue(CellValue.EMPTY);
            maze[y1][x1].setCellValue(CellValue.EMPTY);
            maze[y2][x2].setCellValue(CellValue.EMPTY);
        }
    }

    private void addToResults() {
        while (result.size() + 1 < matrixSize) {
            for (int i = 0; i < edges.size(); i++) {
                Edge edge = edges.get(i);
                if (result.size() == 0) {
                    result.add(edges.get(i));
                    edges.remove(i);
                    break;
                } else if (result.contains(edge)) {
                    if (edge.check(result)) {
                        result.add(edges.get(i));
                    }
                    edges.remove(i);
                    break;
                }
            }
        }
    }

    private void createEntrance() {
        int entrance = seed.nextInt(matrixHeight) * 2 + 1;
        maze[entrance][0].setCellValue(CellValue.EMPTY);
        this.entry = new Point(entrance, 0);
    }

    private void createExit() {
        int exit = seed.nextInt(matrixHeight) * 2 + 1;
        maze[exit][width - 1].setCellValue(CellValue.EMPTY);
        this.exit = new Point(exit, width - 1);
        if (this.width % 2 == 0) {
            maze[exit][width - 2].setCellValue(CellValue.EMPTY);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int row = 0; row < this.height; row++) {
            for (int col = 0; col < this.width; col++) {
                switch (maze[row][col].getCellValue()) {
                    case WALL:
                        builder.append(WALL);
                        break;
                    case EMPTY:
                        builder.append(EMPTY);
                        break;
                }
            }
            builder.append('\n');
        }

        return builder.toString();
    }

    public Point getEntry() {
        return this.entry;
    }

    public Point getExit() {
        return this.exit;
    }

    public boolean isWall(int row, int col) {
        return maze[row][col].getCellValue() == CellValue.WALL;
    }

    public boolean inBoundary(int row, int col) {
        return row >= 0 && row < this.height && col >= 0 && col < this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public String getCellSymbol(int row, int col) {
        return maze[row][col].getCellValue() == CellValue.WALL ? Maze.WALL : Maze.EMPTY;
    }
}
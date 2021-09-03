package maze;

import java.io.*;
import java.util.Scanner;

public class Menu {

    static void printMenu(boolean mazeGenerated) {
        if (!mazeGenerated) {
            printMenuWithoutLoad();
        } else {
            printMenuWithLoad();
        }
    }

    private static void printMenuWithoutLoad() {
        System.out.println("=== Menu ===\n" +
                "1. Generate a new maze\n" +
                "2. Load a maze\n" +
                "0. Exit");
    }

    private static void printMenuWithLoad() {
        System.out.println("=== Menu ===\n" +
                "1. Generate a new maze\n" +
                "2. Load a maze\n" +
                "3. Save the maze\n" +
                "4. Display the maze\n" +
                "5. Find the escape\n" +
                "0. Exit\n");
    }

    public static Maze generateMaze() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please, enter the size of a maze");

        int height = sc.nextInt();
        int width = sc.nextInt();
        Maze maze = new Maze(height, width);
        maze.setMaze();

        return maze;
    }

    public static void saveMaze(Maze maze) throws IOException {
        Scanner sc = new Scanner(System.in);
        String filename = sc.nextLine();

        String mazeString = maze.toString();
        BufferedWriter writer = new BufferedWriter(new FileWriter("./" + filename));
        writer.write(mazeString);

        writer.close();
    }

    public static Maze loadMazeFromFile() {
        String mazeString = loadFile();
        int height = findHeight(mazeString);
        int width = findWidth(mazeString);

        Maze maze = new Maze(height, width);
        maze.setMaze(mazeString);

        return maze;
    }

    private static String loadFile() {
        Scanner sc = new Scanner(System.in);
        String filename = sc.nextLine();
        StringBuilder builder = new StringBuilder();

        try {
            File myObj = new File("./" + filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                builder.append(myReader.nextLine());
                builder.append('\n');
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return builder.toString();
    }

    private static int findHeight(String maze) {
        String[] lines = maze.split("\r\n|\r|\n");
        return lines.length;
    }

    private static int findWidth(String maze) {
        String[] lines = maze.split("\r\n|\r|\n");
        return lines[0].length() / 2;
    }
}


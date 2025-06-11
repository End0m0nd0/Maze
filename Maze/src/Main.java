import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        final int windowSize = 640;
        if (args.length != 2) {
            System.out.println("Usage: java Main <maze_size> <number_of_special_blocks>");
            System.out.println("Example: java Main 10 5");
            return;
        }
        try {
            int mazeSize = Integer.parseInt(args[0]);
            int specialBlocksCount = Integer.parseInt(args[1]);

            if (mazeSize <= 0 || specialBlocksCount < 0) {
                System.out.println("Maze size must be greater than 0, and number of special blocks must be non-negative.");
                return;
            }

            if (specialBlocksCount > mazeSize * mazeSize) {
                System.out.println("The number of special blocks cannot exceed the total number of blocks in the maze.");
                return;
            }

            SwingUtilities.invokeLater(() -> new MyFrame(mazeSize, windowSize, specialBlocksCount));

        } catch (NumberFormatException e) {
            System.out.println("Error: both arguments must be valid integers.");
            System.out.println("Usage: java Main <maze_size> <number_of_special_blocks>");
        }
    }

}